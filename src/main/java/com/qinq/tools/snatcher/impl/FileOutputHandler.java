package com.qinq.tools.snatcher.impl;

import java.io.File;
import java.net.URL;

import com.qinq.tools.snatcher.OutputHandler;
import com.qinq.tools.snatcher.util.FileUtils;

public class FileOutputHandler implements OutputHandler {

	public String root;

	public FileOutputHandler(String root) {
		this.root = root;
	}

	public void handler(URL url, byte[] content) {

		try {
			URL _URL = url;
			String query = "";
			if (_URL.getQuery() != null) {
				query = "_" + _URL.getQuery();
			}
			String path = _URL.getPath();
			if (path.length() == 0) {
				path = "index.html";
			} else {
				if (path.charAt(path.length() - 1) == '/') {
					path = path + "index.html";
				} else {

					for (int i = path.length() - 1; i >= 0; i--) {
						if (path.charAt(i) == '/') {
							if (!path.substring(i + 1).contains(".")) {
								path = path + ".html";
							}
						}
					}
				}
			}
			path += query;
			File domain_path = new File(root, _URL.getHost());
			if (!domain_path.exists()) {
				domain_path.mkdirs();
			}
			File f = new File(domain_path, path);
			FileUtils.writeFileWithParent(f, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRoot() {
		return root;
	}

}
