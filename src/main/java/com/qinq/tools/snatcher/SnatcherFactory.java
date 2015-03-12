package com.qinq.tools.snatcher;

import com.qinq.tools.snatcher.impl.DefaultSnatcher;
import com.qinq.tools.snatcher.impl.FileOutputHandler;

/**
 * 抓取器工厂类
 * 
 * @author qinq
 * 
 */
public class SnatcherFactory {

	private int peroid = 500;
	private int poolSize = 3;
	private String url;
	private OutputHandler outputHandler;

	public SnatcherFactory(String url, OutputHandler outputHandler) {
		this.url = url;
		this.outputHandler = outputHandler;
	}

	/**
	 * 使用默认值创建抓取工厂
	 */
	public SnatcherFactory(String url, String outPut) {
		this.url = url;
		this.outputHandler = new FileOutputHandler(outPut);
	}

	/**
	 * 创建抓取工厂
	 * 
	 * @param peroid
	 *            线程执行等待时间
	 * @param poolSize
	 *            同时抓取的URL数量
	 * @param url
	 *            抓取URL
	 */
	public SnatcherFactory(int peroid, int poolSize, String url, String outPut) {
		this.peroid = peroid;
		this.poolSize = poolSize;
		this.url = url;
		this.outputHandler = new FileOutputHandler(outPut);
	}

	public SnatcherFactory(int peroid, int poolSize, String url,
			OutputHandler outputHandler) {
		this.peroid = peroid;
		this.poolSize = poolSize;
		this.url = url;
		this.outputHandler = outputHandler;
	}

	/**
	 * 获取线程执行等待时间
	 * 
	 * @return int 单位ms
	 */
	public int getPeroid() {
		return peroid;
	}

	/**
	 * 设置线程执行等待时间，单位为毫秒
	 * 
	 * @param peroid
	 *            默认500ms
	 */
	public void setPeroid(int peroid) {
		this.peroid = peroid;
	}

	/**
	 * 获取同时抓取的URL数量
	 * 
	 * @return int
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * 设置同时抓取的URL数量
	 * 
	 * @param poolSize
	 *            int 默认为3
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * 獲取URL
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置URL
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 创建一个Snatcher实例
	 * 
	 * @return
	 */
	public Snatcher createSnatcher() {
		Snatcher snatcher = new DefaultSnatcher(url, peroid, poolSize);
		snatcher.setOutputHandler(outputHandler);
		return snatcher;
	}

}
