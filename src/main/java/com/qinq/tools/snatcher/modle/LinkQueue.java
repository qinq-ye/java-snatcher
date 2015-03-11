package com.qinq.tools.snatcher.modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 抓取链接队列，FIFO模式
 * 
 * @author qinq
 * 
 */
public class LinkQueue {
	private List<Link> list = new ArrayList<Link>();
	private List<Link> removeList = new ArrayList<Link>();
	private String baseUrl;

	/**
	 * 采用给定的URL作为基础链接地址构造Queue
	 * <p>
	 * 队列只存储baseUrl下的链接地址
	 * 
	 * @param baseUrl
	 */
	public LinkQueue(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * 链接放入队列末尾
	 * 
	 * @param link
	 */
	public synchronized void offer(Link link) {
		if (!list.contains(link) && !removeList.contains(link)) {
			if (link.getUrl().startsWith(baseUrl)) {
				list.add(link);
			}
		}
	}

	/**
	 * 强制放入一个链接
	 * 
	 * @param link
	 */
	public synchronized void offerForce(Link link) {
		if (!list.contains(link)) {
			if (link.getUrl().startsWith(baseUrl)) {
				list.add(link);
			}
		}
	}

	/**
	 * 存入一个链接列表
	 * 
	 * @param linkList
	 */
	public void offer(List<Link> linkList) {
		if (linkList != null) {
			for (Link entry : linkList) {
				offer(entry);
			}
		}
	}

	/**
	 * 取出第一个链接
	 * 
	 * @return
	 */
	public synchronized Link poll() {
		if (list.size() == 0) {
			return null;
		}
		Link link = list.get(0);
		list.remove(0);
		removeList.add(link);
		return link;
	}

	/**
	 * 队列中是否还存在链接
	 * 
	 * @return true:存在 false:队列已空
	 */
	public boolean hasMore() {
		return list.size() > 0;
	}

	/**
	 * 当前队列大小
	 * 
	 * @return int
	 */
	public int size() {
		return list.size();
	}

}
