package com.qinq.tools.snatcher.net;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.qinq.tools.snatcher.exception.HttpConnectionException;
import com.qinq.tools.snatcher.exception.HttpTimeoutConnection;
import com.qinq.tools.snatcher.exception.ParseAddressException;

/**
 * HTTP连接类，采用Apache HttpClient
 * 
 * @author qinq
 * 
 */
public class HttpConnection {

	private HttpClient httpClient;

	/**
	 * 采用给定的编码方式构造一个HttpConnection
	 * 
	 * @param encoding
	 */
	public HttpConnection() {
		httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);// ����ʱ��
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				5000);// ��ݴ���ʱ��
	}

	/**
	 * 执行get请求
	 * 
	 * @param path
	 *            请求URl
	 * @return HttpResponse对象
	 */
	public com.qinq.tools.snatcher.net.HttpResponse doGet(String path) {
		HttpGet httpGet = new HttpGet(path);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			HttpResponseImpl impl = new HttpResponseImpl(
					EntityUtils.toByteArray(entity), path, response
							.getStatusLine().getStatusCode(), entity
							.getContentType().getValue(), "UTF-8");
			return impl;
		} catch (SocketTimeoutException e) {
			throw new HttpTimeoutConnection(e);
		} catch (Exception e) {
			throw new HttpConnectionException(e);
		}
	}

	class HttpResponseImpl implements com.qinq.tools.snatcher.net.HttpResponse {

		private URL url;
		private int code;
		private byte[] content;
		private String contentType;
		private String encoding;

		public HttpResponseImpl(byte[] content, String path, int code,
				String contentType, String encoding) {
			try {
				this.url = new URL(path);
			} catch (MalformedURLException e) {
				throw new ParseAddressException(path, e);
			}
			this.code = code;
			this.content = content;
			this.contentType = contentType;
			this.encoding = encoding;

		}

		public URL getUrl() {
			return url;
		}

		public int getCode() {
			return code;
		}

		public String getContentType() {
			return contentType;
		}

		public byte[] getContent() {
			return content;
		}

		public String getEncoding() {
			return encoding;
		}

	}

}
