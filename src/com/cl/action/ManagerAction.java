package com.cl.action;

import com.cl.dao.Manager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport {
	private Manager manager;
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String login() throws Exception {
		ActionContext act = ActionContext.getContext();
		if (Manager.check(this.getManager().getLoginname(), this.getManager().getPassword())) {
			String username = this.getManager().getLoginname();
			
			act.getSession().put("username", username);
			act.getSession().put("role", "manager");
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}
}
