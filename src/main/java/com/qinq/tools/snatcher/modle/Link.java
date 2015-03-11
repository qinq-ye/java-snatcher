package com.qinq.tools.snatcher.modle;


/**
 * 保存网页链接的类
 * 
 * @author qinq
 * 
 */
public class Link {

	/**
	 * 链接的锚文本
	 */
	private String anchor;

	/**
	 * 链接的url
	 */
	private String url;

	/**
	 * 创建一个空链接
	 */
	public Link() {

	}

	/**
	 * 使用给定的 锚文本和url创建一个链接
	 * 
	 * @param anchor
	 * @param url
	 */
	public Link(String anchor, String url) {
		this.anchor = anchor;
		this.url = url;
	}

	/**
	 * 获取锚文本
	 * 
	 * @return
	 */
	public String getAnchor() {
		return anchor;
	}

	/**
	 * 设置锚文本
	 * 
	 * @param anchor
	 */
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	/**
	 * 获取链接地址
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置链接地址
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return url;
	}

}
