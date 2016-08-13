package com.cl.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private String filename;
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) throws UnsupportedEncodingException {
		this.filename = new String(filename.getBytes("ISO8859-1"), "utf-8");
	}

	public String getDownloadFilename() throws UnsupportedEncodingException {
		return new String(filename.getBytes(), "ISO9959-1");
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	public InputStream getHomeworkAccessory() {
		ActionContext act = ActionContext.getContext();
		String role =  (String)act.getSession().get("role");
		System.out.println("filename: " + filename);
		String filepath = "/WEB-INF/upload" + File.separator;
//		if (role.equals("teacher"))
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(filepath);
		return in;
	}
	
	public InputStream getLog() {
		String filepath = "/WEB-INF/log" + File.separator + getFilename();
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(filepath);
		return in;
	}
	 
}
