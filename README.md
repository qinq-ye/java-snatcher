# java-snatcher
#java实现的网页抓取下载程序
使用方式：
	public static void main(String[] args) {
		PropertyConfigurator.configure("config/log4j.properties");
		SnatcherFactory factory = new SnatcherFactory(500, 3,
				"http://jsoup.org/apidocs/");
		Snatcher snatcher = factory.createSnatcher();
	
		snatcher.setExceptionListener(new ExceptionListener() {

			@Override
			public void onException(Link url, SnatcherException e) {
				e.printStackTrace();
			}

		});
		
		snatcher.start();
	}
