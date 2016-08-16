package com.cl.action;

import java.io.File;
import java.util.ArrayList;

import com.cl.dao.Method;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MethodAction extends ActionSupport {
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String homeworkcomment;
	
	public String getMethodList() throws Exception {
		ActionContext act = ActionContext.getContext();
		ArrayList<Method> methodlist = Method.getMethodList();
		
		act.put("methodlist", methodlist);
		return SUCCESS;
	}
	
	public String addMethod() throws Exception {
		
		return SUCCESS;
	}
	
	public String deleteMethod() throws Exception {
		retur
	}
}
