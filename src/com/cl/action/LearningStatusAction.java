package com.cl.action;

import com.cl.dao.LearningStatus;
import com.cl.dao.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LearningStatusAction extends ActionSupport {
	private LearningStatus learningstatus;
	
	public LearningStatus getLearningstatus() {
		return learningstatus;
	}

	public void setLearningstatus(LearningStatus learningstatus) {
		this.learningstatus = learningstatus;
	}

	public String submitLearningStatus() {
		ActionContext act = ActionContext.getContext();
		String loginname = (String)act.getSession().get("username");
		Student stu = Student.getStudentByLoginname(loginname);
		int event_id = ((Integer)act.getSession().get("event_id")).intValue();
		int class_id = ((Integer)act.getSession().get("class_id")).intValue();
		learningstatus.setStudent_id(stu.getId());
		learningstatus.setEvent_id(event_id);
		learningstatus.setClass_id(class_id);
		System.out.println(learningstatus.getClasstime() + " " + learningstatus.getInclass() 
						+ " " + learningstatus.getOutclass() + " " + learningstatus.getMethod());
		
		LearningStatus.addLeanringStatus(learningstatus);
		return SUCCESS;
	}
}
