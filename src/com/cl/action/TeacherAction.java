package com.cl.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Course;
import com.cl.dao.CourseTeacher;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TeacherAction extends ActionSupport {
	private Teacher teacher;
	private final String[] columns = {"登录名", "姓名"};
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String login() throws Exception {
		if (Teacher.check(this.getTeacher().getLoginname(), this.getTeacher().getPassword())) {
			String username = this.getTeacher().getLoginname();
			ActionContext act = ActionContext.getContext();
			
			act.getSession().put("username", username);
			act.getSession().put("role", "teacher");
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}
	
	public String getTeacherList() throws Exception {
		ActionContext act = ActionContext.getContext();
		
		ArrayList<Teacher> teacherlist = Teacher.getTeacherList();
		int each = 10;
		int totalcolumn = (teacherlist.size() % each == 0 ? teacherlist.size() / each : teacherlist.size() / each + 1);
		int nowcolumn = 1;
		if (ServletActionContext.getRequest().getParameter("column") != null)
			nowcolumn = (new Integer(ServletActionContext.getRequest().getParameter("column"))).intValue();
		int precolumn = nowcolumn - 2;
		int nextcolumn = nowcolumn + 2;
		while (precolumn < 1) {
			precolumn++;
			nextcolumn++;
		}
		while (nextcolumn > totalcolumn) {
			precolumn--;
			nextcolumn--;
		}
		while (precolumn < 1) {
			precolumn++;
		}

		act.put("nowcolumn", nowcolumn);
		act.put("totalcolumn", totalcolumn);
		act.put("nextcolumn", nextcolumn);
		act.put("precolumn", precolumn);
		act.put("teachercolumns", columns);
		act.put("teacherlist", teacherlist);
		return SUCCESS;
	}
	
	public String deleteTeacher() throws Exception {
		int teacher_id = new Integer(ServletActionContext.getRequest().getParameter("teacher_id")).intValue();
		Teacher.deleteTeacherByTeacherId(teacher_id);
		return SUCCESS;
	}
	
	public String addTeacher() throws Exception {
		Teacher t = getTeacher();
		t.setPassword("12345678");
		Teacher.addTeacher(t);
		return SUCCESS;
	}
}
