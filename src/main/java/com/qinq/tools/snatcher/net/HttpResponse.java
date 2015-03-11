package com.qinq.tools.snatcher.net;

import java.net.URL;

/**
 * Http响应的接口，如果用户需要自定义http响应，需要实现这个接口
 * 
 * @author qinq
 * 
 */
public interface HttpResponse {

	public URL getUrl();

	/**
	 * 返回http响应码
	 * 
	 * @return http响应码
	 */
	public int getCode();

	/**
	 * 返回http响应中的content-type，返回的content-type会影响到爬取/解析流程中 对状态的判断
	 * 
	 * @return http响应中的content-type
	 */
	public String getContentType();

	/**
	 * 返回网页/文件的内容(byte数组)
	 * 
	 * @return 网页/文件的内容(byte数组)
	 */
	public byte[] getContent();

	/**
	 * 返回网页/文件的编码方式(如果存在)
	 * 
	 * @return 网页/文件的编码方式
	 */
	public String getEncoding();

}
