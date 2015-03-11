package com.qinq.tools.snatcher.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.qinq.tools.snatcher.modle.Link;

public class HtmlParser {

	public static List<Link> getHtmlLink(String html, String url) {
		Document doc = Jsoup.parse(html);
		doc.setBaseUri(url);
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");
		List<Link> linkList = new ArrayList<Link>();
		for (Element src : media) {
			String path = src.attr("abs:src");
			String anchor = null;
			if (path.contains("#")) {
				path = path.split("#")[0];
			}
			Link link = new Link(anchor, path);
			linkList.add(link);
		}

		for (Element ln : imports) {
			String path = ln.attr("abs:href");
			String anchor = null;
			if (path.contains("#")) {
				path = path.split("#")[0];
			}
			Link link = new Link(anchor, path);
			linkList.add(link);
		}

		for (Element ln : links) {
			String path = ln.attr("abs:href");
			String anchor = null;
			if (path.contains("#")) {
				path = path.split("#")[0];
			}
			Link link = new Link(anchor, path);
			linkList.add(link);
		}

		return linkList;
	}

	public static String getBaseURL(String path) {
		String url = path;
		if (path.lastIndexOf("/") == path.indexOf("//") + 1) {
			url = path;
		} else {
			url = path.substring(0, path.lastIndexOf("/"));
		}
		return url;
	}

	public static void main(String[] args) {
		System.out.println(getBaseURL("http://www.open-open.com/"));
	}

}
