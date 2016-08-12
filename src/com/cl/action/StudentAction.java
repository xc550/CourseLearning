package com.cl.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import com.cl.dao.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StudentAction extends ActionSupport {
	private Student student;
	private final String[] columns = {"学号", "姓名", "年级"};

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public String login() throws Exception {
		ActionContext act = ActionContext.getContext();
		if (Student.check(this.getStudent().getLoginname(), this.getStudent().getPassword())) {
			String username = this.getStudent().getLoginname();
			
			act.getSession().put("username", username);
			act.getSession().put("role", "student");
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}
	
	public String getStudentList() throws Exception {
		ActionContext act = ActionContext.getContext();
		
		ArrayList<Student> studentlist = Student.getStudentList();
		int each = 2;
		int totalcolumn = (studentlist.size() % each == 0 ? studentlist.size() / each : studentlist.size() / each + 1);
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

		act.put("each", each);
		act.put("nowcolumn", nowcolumn);
		act.put("totalcolumn", totalcolumn);
		act.put("nextcolumn", nextcolumn);
		act.put("precolumn", precolumn);
		act.put("studentcolumns", columns);
		act.put("studentlist", studentlist);
		return SUCCESS;
	}
	
	public String deleteStudent() throws Exception {
		int student_id = new Integer(ServletActionContext.getRequest().getParameter("student_id")).intValue();
		Student.deleteStudentByStudentId(student_id);
		return SUCCESS;
	}
	
	public String addStudent() throws Exception {
		Student stu = getStudent();
		stu.setPassword("12345678");
		stu.setHead("null");
		Student.addStudent(stu);
		return SUCCESS;
	}
}
