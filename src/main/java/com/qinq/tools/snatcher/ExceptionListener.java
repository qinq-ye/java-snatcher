package com.qinq.tools.snatcher;

import com.qinq.tools.snatcher.exception.SnatcherException;
import com.qinq.tools.snatcher.modle.Link;

/**
 * 
 * @author qinq
 * 
 */
public interface ExceptionListener {

	/**
	 * 抓取出现异常时调用该方法(如果设置监听类)
	 * 
	 * @param url
	 * @param e
	 */
	public void onException(Link url, SnatcherException e);

}
