package com.cl.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Student;
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
		String[] res = filename.split("_");
		filename = res[res.length - 1];
		return new String(filename.getBytes(), "ISO8859-1");
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	public InputStream getInputStream() {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int class_id = ((Integer)act.getSession().get("class_id")).intValue();
		String type = ServletActionContext.getRequest().getParameter("type");

//		System.out.println("type " + type + " " + getFilename());
		String filepath = "";
		if (type.equals("homeworkaccessory")) {
			filepath = "/WEB-INF/upload" + File.separator;
			filepath = filepath + "homework_" + course_id + "_" + class_id + "_" + getFilename();
		}
		else if (type.equals("studentaccessory")) {
			int homework_id = new Integer(ServletActionContext.getRequest().getParameter("homework_id")).intValue();
			filepath = "/WEB-INF/upload" + File.separator;
			filepath = filepath + "student_" + homework_id + "_" + getFilename();			
		}
		else if (type.equals("method")) {
			filepath = "/WEB-INF/method" + File.separator + getFilename();
		}
		else if (type.equals("log")) {
			filepath = "/WEB-INF/log" + File.separator + getFilename();
		}
		InputStream in = ServletActionContext.getServletContext().getResourceAsStream(filepath);
		return in;
	}
	 
}
