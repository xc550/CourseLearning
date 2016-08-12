package com.cl.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileFunc {
	public static void writeFile(String path, File file) throws Exception {
		FileInputStream in = new FileInputStream(file);
		FileOutputStream out = new FileOutputStream(path);
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) > 0) {
			out.write(b, 0, len);
		}
		out.close();
		in.close();
	}
	
	public static boolean directoryExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static void createDirectory(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();
	}
	
	public static void writeFile(String path, String content) {
		try {
			FileOutputStream out = new FileOutputStream(path, true);
			byte[] b = content.getBytes();
			int len = b.length;
			out.write(b, 0, len);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteFile(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			System.out.println("File: " + filename + " exist!");
			file.delete();
		}
		else {
			System.out.println("File: " + filename + " not exist!");
		}
	}
	
	public static String readFromPropertiesFile(String filename, String key) {
		String res = "";
		Properties p = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filename));
			if (in == null)
				return res;
			p.load(in);
			if (p.containsKey(key))
				res = p.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
