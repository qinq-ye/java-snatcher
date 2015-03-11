package com.qinq.tools.snatcher;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;

import com.qinq.tools.snatcher.exception.SnatcherException;
import com.qinq.tools.snatcher.modle.Link;

public class SnatcherTest extends TestCase {

	public void testSnatcher() {
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
