package com.cl.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.BBS;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.cl.util.DateFormator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BBSAction extends ActionSupport {
	private BBS bbs;
	
	public BBS getBbs() {
		return bbs;
	}

	public void setBbs(BBS bbs) {
		this.bbs = bbs;
	}

	public String submitBBS() throws Exception {
		ActionContext act = ActionContext.getContext();
		String loginname = (String)act.getSession().get("username");
		String role = (String)act.getSession().get("role");
		int student_id = -1, teacher_id = -1;
		if (role.equals("student"))
			student_id = Student.getStudentByLoginname(loginname).getId();
		else if (role.equals("teacher"))
			teacher_id = Teacher.getTeacherByLoginname(loginname).getId();
		
		
		int class_id = ((Integer)act.getSession().get("class_id")).intValue();
		int event_id = ((Integer)act.getSession().get("event_id")).intValue();
		int reply_id = new Integer(ServletActionContext.getRequest().getParameter("reply_id")).intValue();
			
		
		bbs.setStudent_id(student_id);
		bbs.setTeacher_id(teacher_id);
		bbs.setClass_id(class_id);
		bbs.setEvent_id(event_id);
		bbs.setReply_id(reply_id);
		String datetime = (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		bbs.setBbs_date(datetime);
		BBS.addBBS(bbs);
		return SUCCESS;
	}
}
