package com.cl.filter;

public class LogFilter {
	public final int INVAILD = -0x3f3f3f3f;
	public final String savepath = "/WEB-INF/log"; 
	
	public String getSubAction(String action) {
		int st = -1, ed = action.length();
		for (int i = 0; i < action.length(); i++) {
			if (action.charAt(i) == '_') {
				st = i + 1;
				break;
			}
		}
		for (int i = st; i < action.length(); i++) {
			if (action.charAt(i) == '.')
				break;
			ed = i + 1;
		}
		String res = action.substring(st, ed);
		return res;
	}
}
