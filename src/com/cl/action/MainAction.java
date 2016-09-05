package com.cl.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {

	public String execute() throws Exception {
		ActionContext act = ActionContext.getContext();
		act.getSession().clear();
		return SUCCESS;
	}
	
}
