package com.qinq.tools.snatcher.util;

import java.util.HashMap;
import java.util.Map;

import com.qinq.tools.snatcher.Constants;

/**
 * 
 * @author qinq
 * 
 */
public class HttpTypeUtil {

	private static Map<String, Integer> typeMap = new HashMap<String, Integer>();

	static {
		typeMap.put("text/html", Constants.TYPE_HTML);
		typeMap.put("application/javascript", Constants.TYPE_SCRIPT);
		typeMap.put("text/css", Constants.TYPE_CSS);
		typeMap.put("application/x-javascript", Constants.TYPE_SCRIPT);
		typeMap.put("image/gif", Constants.TYPE_IMAGE);
		typeMap.put("image/png", Constants.TYPE_IMAGE);
		typeMap.put("image/jpeg", Constants.TYPE_IMAGE);
		typeMap.put("text/plain", Constants.TYPE_OTHER);
		typeMap.put("image/webp", Constants.TYPE_IMAGE);
		typeMap.put("application/json", Constants.TYPE_OTHER);
		typeMap.put("image/svg+xml", Constants.TYPE_IMAGE);
		typeMap.put("image/svg+xml", Constants.TYPE_IMAGE);
	}

	/**
	 * 获取HTTP文件类型
	 * 
	 * @param type
	 * @return
	 */
	public static int getHttpType(String type) {
		Integer code = typeMap.get(type.toLowerCase());
		if (code == null) {
			code = Constants.TYPE_OTHER;
		}
		return code;
	}

}
