package com.qinq.tools.snatcher;


/**
 * <p>
 * 该类用于网页抓取
 * </p>
 * 
 * @author qinq
 * 
 */
public interface Snatcher {

	/**
	 * 启动抓取服务
	 * 
	 * @return 异步执行结果
	 */
	public SnatcherFuture start();

	/**
	 * URL抓取状态监听器
	 * 
	 * @param listener
	 *            SnatcherStateListener实现类
	 */
	public void setSnatcherStateListener(SnatcherStateListener listener);

	/**
	 * 抓取异常监听器
	 * 
	 * @param listener
	 *            ExceptionListener实现类
	 */
	public void setExceptionListener(ExceptionListener listener);

	public void setOutputHandler(OutputHandler handler);

}
