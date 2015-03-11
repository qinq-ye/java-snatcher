package com.qinq.tools.snatcher;

import java.util.List;

import com.qinq.tools.snatcher.modle.Link;

public interface SnatcherFuture {

	/**
	 * 取消执行抓取
	 * <p>
	 * 该方法不会立即取消执行，需等待当前URL抓取成功后停止
	 * </p>
	 */
	public void cancel();

	/**
	 * 是否已执行完成
	 * 
	 * @return boolean true:已停止 false:正在执行
	 */
	public boolean isDone();

	/**
	 * 是否取消
	 * 
	 * @return boolean true:已取消 false:未取消
	 */
	public boolean isCanceled();

	/**
	 * 获取成功抓取的URL列表
	 * 
	 * @return list 地址列表
	 */
	public List<Link> getSuccessedPages();

	/**
	 * 获取失败的URL列表
	 * 
	 * @return list 地址列表
	 */
	public List<Link> getFailedPages();

}
