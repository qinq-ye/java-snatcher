package com.qinq.tools.snatcher;

import com.qinq.tools.snatcher.modle.Link;

/**
 * 
 * @author qinq
 * 
 */
public interface SnatcherStateListener {

	/**
	 * 抓取状态改变时调用
	 * 
	 * @param state
	 * @param url
	 * @see Constants
	 */
	public void onStateChange(int state, Link url);

}
