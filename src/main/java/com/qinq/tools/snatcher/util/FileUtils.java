package com.qinq.tools.snatcher.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件操作公共类
 * 
 * @author qinq
 * 
 */
public class FileUtils {

	/**
	 * 删除文件夹
	 * 
	 * @param dir
	 */
	public static void deleteDir(File dir) {
		File[] filelist = dir.listFiles();
		for (File file : filelist) {
			if (file.isFile()) {
				file.delete();
			} else {
				deleteDir(file);
			}
		}
		dir.delete();
	}

	/**
	 * 复制文件
	 * 
	 * @param origin
	 * @param newfile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copy(File origin, File newfile)
			throws FileNotFoundException, IOException {
		if (!newfile.getParentFile().exists()) {
			newfile.getParentFile().mkdirs();
		}
		FileInputStream fis = new FileInputStream(origin);
		FileOutputStream fos = new FileOutputStream(newfile);
		byte[] buf = new byte[2048];
		int read;
		while ((read = fis.read(buf)) != -1) {
			fos.write(buf, 0, read);
		}
		fis.close();
		fos.close();
	}

	/**
	 * 将内容写入文件
	 * 
	 * @param filename
	 * @param content
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFile(String filename, byte[] content)
			throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		fos.write(content);
		fos.close();
	}

	/**
	 * 将内容写入文件,文件夹不存在会自动创建
	 * 
	 * @param filename
	 * @param content
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFileWithParent(String filename, byte[] content)
			throws FileNotFoundException, IOException {
		File file = new File(filename);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	/**
	 * 将内容写入文件,文件夹不存在会自动创建
	 * 
	 * @param file
	 * @param content
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFileWithParent(File file, byte[] content)
			throws FileNotFoundException, IOException {

		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[2048];
		int read;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((read = fis.read(buf)) != -1) {
			bos.write(buf, 0, read);
		}

		fis.close();
		return bos.toByteArray();
	}

}
