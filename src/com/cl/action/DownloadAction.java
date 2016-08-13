package com.cl.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

	public String getDownloadFileName() throws UnsupportedEncodingException {
		return new String(filename.getBytes(), "ISO8859-1");
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	public InputStream getHomeworkAccessory() {
		ActionContext act = ActionContext.getContext();
		int homework_id = -1;
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int class_id = ((Integer)act.getSession().get("class_id")).intValue();
		if (ServletActionContext.getRequest().getParameter("homework_id") != null)
			homework_id = new Integer(ServletActionContext.getRequest().getParameter("homework_id")).intValue();
		System.out.println("filename: " + filename);
		String filepath = "/WEB-INF/upload" + File.separator;
		if (homework_id == -1)
			filepath = filepath + "homework_" + course_id + "_" + class_id + "_" + getFilename();
		else
			filepath = filepath + "student_" + homework_id + "_" + getFilename();
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(filepath);
		return in;
	}
	
	public InputStream getLog() {
		String filepath = "/WEB-INF/log" + File.separator + getFilename();
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(filepath);
		return in;
	}
	 
}
