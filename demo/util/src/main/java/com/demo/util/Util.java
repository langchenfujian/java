package com.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
	public static String getStringFromResource(String fileClassPath) throws IOException{
		InputStream is = getInputStreamByFileClassPath(fileClassPath);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		return sb.toString();
	}

	public static InputStream getInputStreamByFileClassPath(
			String fileClassPath) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileClassPath);
		return is;
	}
}
