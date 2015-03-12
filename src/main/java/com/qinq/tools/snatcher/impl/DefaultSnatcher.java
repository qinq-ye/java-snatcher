package com.qinq.tools.snatcher.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qinq.tools.snatcher.Constants;
import com.qinq.tools.snatcher.ExceptionListener;
import com.qinq.tools.snatcher.OutputHandler;
import com.qinq.tools.snatcher.Snatcher;
import com.qinq.tools.snatcher.SnatcherFuture;
import com.qinq.tools.snatcher.SnatcherStateListener;
import com.qinq.tools.snatcher.exception.HttpTimeoutConnection;
import com.qinq.tools.snatcher.exception.SnatcherException;
import com.qinq.tools.snatcher.modle.Link;
import com.qinq.tools.snatcher.modle.LinkQueue;
import com.qinq.tools.snatcher.net.HttpConnection;
import com.qinq.tools.snatcher.net.HttpResponse;
import com.qinq.tools.snatcher.util.HtmlParser;
import com.qinq.tools.snatcher.util.HttpTypeUtil;

/**
 * 抓取接口的默认实现
 * 
 * @author qinq
 * 
 */
public class DefaultSnatcher implements Snatcher {

	private Logger logger = Logger.getLogger(DefaultSnatcher.class);
	private int peroid = 500;
	private int poolSize = 3;
	private ExceptionListener exceptionListener;
	private SnatcherStateListener stateListener;
	private OutputHandler outputHandler;
	private String baseUrl;
	private List<Link> successedList = new ArrayList<Link>();
	private List<Link> faildList = new ArrayList<Link>();

	public DefaultSnatcher(String baseUrl, int poolSize, int peroid) {
		this.baseUrl = baseUrl;
		this.peroid = peroid;
		this.poolSize = poolSize;
	}

	public SnatcherFuture start() {
		LinkQueue queue = new LinkQueue(HtmlParser.getBaseURL(baseUrl));
		queue.offer(new Link(null, baseUrl));
		List<HttpCrawlerTask> taskList = new ArrayList<HttpCrawlerTask>();
		for (int i = 0; i < poolSize; i++) {
			HttpCrawlerTask task = new HttpCrawlerTask(queue);
			task.start();
			taskList.add(task);
		}
		return new DefaultSnatcherFuture(queue, taskList);
	}

	public void setSnatcherStateListener(SnatcherStateListener listener) {
		stateListener = listener;
	}

	public void setExceptionListener(ExceptionListener listener) {
		exceptionListener = listener;
	}

	public void setOutputHandler(OutputHandler handler) {
		this.outputHandler = handler;
	}

	private void output(URL url, byte[] content) {
		if (outputHandler != null) {
			outputHandler.handler(url, content);
		}
	}

	class HttpCrawlerTask extends Thread {

		private LinkQueue queue;
		private boolean running = true;
		private boolean done = false;
		private HttpConnection conn;
		private static final int MAXCOUNT = 10;

		HttpCrawlerTask(LinkQueue queue) {
			this.queue = queue;
			this.conn = new HttpConnection();
		}

		@Override
		public void run() {
			int count = 0;
			while (running) {
				if (queue.hasMore()) {
					Link link = queue.poll();
					if (link != null) {
						try {
							if (stateListener != null) {
								stateListener.onStateChange(Constants.START,
										link);
							}
							logger.info("请求" + link.getUrl());
							HttpResponse response = conn.doGet(link.getUrl());
							if (String.valueOf(response.getCode()).startsWith(
									"40")
									|| String.valueOf(response.getCode())
											.startsWith("50")) {
								faildList.add(link);
								if (stateListener != null) {
									stateListener.onStateChange(
											Constants.FAILED, link);
								}
							} else {
								int type = HttpTypeUtil.getHttpType(response
										.getContentType());
								switch (type) {
								case Constants.TYPE_HTML:
									String html = new String(
											response.getContent(),
											response.getEncoding());
									List<Link> linkList = HtmlParser
											.getHtmlLink(html, link.getUrl());
									queue.offer(linkList);
									output(response.getUrl(),
											response.getContent());
									break;
								default:
									output(response.getUrl(),
											response.getContent());
								}
								successedList.add(link);
								if (stateListener != null) {
									stateListener.onStateChange(
											Constants.SUCCESS, link);
								}
							}
						} catch (HttpTimeoutConnection e) {
							if (exceptionListener != null) {
								exceptionListener.onException(link, e);
							}
							queue.offerForce(link);
						} catch (SnatcherException e) {
							if (exceptionListener != null) {
								exceptionListener.onException(link, e);
							}
							if (stateListener != null) {
								stateListener.onStateChange(Constants.FAILED,
										link);
							}
							faildList.add(link);
						} catch (Exception e) {
							if (exceptionListener != null) {
								exceptionListener.onException(link,
										new SnatcherException(e));
							}
							if (stateListener != null) {
								stateListener.onStateChange(Constants.FAILED,
										link);
							}
							faildList.add(link);
						}

					}
					try {
						Thread.sleep(peroid);
					} catch (InterruptedException e) {
					}
				} else {
					count++;
					if (count > MAXCOUNT) {
						running = false;
						done = true;
						logger.info("已不存在待下載連接");
					} else {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}

		public boolean isDone() {
			return done;
		}

	}

	class DefaultSnatcherFuture implements SnatcherFuture {

		private LinkQueue queue;
		private List<HttpCrawlerTask> taskList;
		private boolean canceled = false;

		DefaultSnatcherFuture(LinkQueue queue, List<HttpCrawlerTask> taskList) {
			this.queue = queue;
			this.taskList = taskList;
		}

		public void cancel() {
			for (HttpCrawlerTask task : taskList) {
				task.setRunning(false);
			}
			canceled = true;
		}

		public boolean isDone() {
			boolean done = true;
			for (HttpCrawlerTask task : taskList) {
				if (!task.isDone()) {
					done = false;
				}
			}
			return done;
		}

		public boolean isCanceled() {
			return canceled;
		}

		public List<Link> getSuccessedPages() {
			if (!isDone()) {
				return null;
			}
			return successedList;
		}

		public List<Link> getFailedPages() {
			if (!isDone()) {
				return null;
			}
			return faildList;
		}

	}

}
