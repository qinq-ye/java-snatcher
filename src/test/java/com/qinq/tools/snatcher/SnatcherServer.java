package com.qinq.tools.snatcher;

import org.apache.log4j.PropertyConfigurator;

import com.qinq.tools.snatcher.exception.SnatcherException;
import com.qinq.tools.snatcher.modle.Link;

/**
 * ץȡ�����
 * 
 * @author qinq
 * 
 */
public class SnatcherServer {

	/**
	 * ��ں���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configure(SnatcherTest.class.getClassLoader()
				.getResourceAsStream("log4j.properties"));
		SnatcherFactory factory = new SnatcherFactory(500, 3,
				"http://jsoup.org/apidocs/", "F:/temp");
		Snatcher snatcher = factory.createSnatcher();
		snatcher.setExceptionListener(new ExceptionListener() {

			public void onException(Link url, SnatcherException e) {
				e.printStackTrace();
			}

		});
		snatcher.start();

	}
}
