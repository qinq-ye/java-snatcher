package com.qinq.tools.snatcher.net;

import java.net.URL;

/**
 * http请求接口
 * 
 * @author qinq
 * 
 */
public interface HttpRequest {

	/**
	 * 获取请求URL
	 * 
	 * @return url
	 */
	public URL getURL();

	/**
	 * 设置请求URL
	 * 
	 * @param url
	 */
	public void setURL(URL url);

	/**
	 * 获取请求返回结果
	 * 
	 * @return HttpResponse
	 * @throws Exception
	 *             请求异常
	 */
	public HttpResponse getResponse() throws Exception;

}
