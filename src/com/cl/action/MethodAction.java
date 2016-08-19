package com.cl.action;

import java.io.File;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Method;
import com.cl.util.FileFunc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MethodAction extends ActionSupport {
	private Method method;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String homeworkcomment;
	private final String savepath = "/WEB-INF/method";

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getHomeworkcomment() {
		return homeworkcomment;
	}

	public void setHomeworkcomment(String homeworkcomment) {
		this.homeworkcomment = homeworkcomment;
	}

	public String getMethodList() throws Exception {
		ActionContext act = ActionContext.getContext();
		ArrayList<Method> methodlist = Method.getMethodList();
		
		act.put("methodlist", methodlist);
		return SUCCESS;
	}
	
	public String addMethod() throws Exception {
		System.out.println("in addMethod");
		String filepath = ServletActionContext.getServletContext().getRealPath(savepath);
		if (!FileFunc.directoryExist(filepath))
			FileFunc.createDirectory(filepath);
		filepath += File.separator + getUploadFileName();
		FileFunc.writeFile(filepath, getUpload());
		Method m = getMethod();
		m.setMehtod_path(getUploadFileName());
		Method.addMethod(m);
		return SUCCESS;
	}
	
	public String deleteMethod() throws Exception {
		int method_id = (new Integer(ServletActionContext.getRequest().getParameter("method_id"))).intValue();
		String path = Method.getMethodByMethodId(method_id).getMehtod_path();
		String filename = ServletActionContext.getServletContext().getRealPath(savepath);
		filename += File.separator + path;
		FileFunc.deleteFile(filename);
		Method.deleteMethodByMethodId(method_id);
		return SUCCESS;
	}
}
