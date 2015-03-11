package com.qinq.tools.snatcher.output;

import java.io.File;
import java.net.URL;

import com.qinq.tools.snatcher.net.HttpResponse;
import com.qinq.tools.snatcher.util.FileUtils;

/**
 * 
 * @author qinq
 * 
 */
public class FileSystemOutput {

	public String root;

	public FileSystemOutput(String root) {
		this.root = root;
	}

	public void output(HttpResponse response) {
		try {
			URL _URL = response.getUrl();
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
			FileUtils.writeFileWithParent(f, response.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
