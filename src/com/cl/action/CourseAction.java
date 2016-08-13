package com.cl.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Course;
import com.cl.dao.CourseStudent;
import com.cl.dao.CourseTeacher;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CourseAction extends ActionSupport {
	private Course course;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getCourseList() throws Exception {
		ActionContext act = ActionContext.getContext();
		String role = (String)act.getSession().get("role");
		String username = (String)act.getSession().get("username");
		ArrayList<Course> courselist = new ArrayList<>();
		if (role.equals("manager"))
			courselist = Course.getCourseList();
		else if (role.equals("student"))
			courselist = CourseStudent.getCourseListByStudentId(Student.getStudentByLoginname(username).getId());
		else if (role.equals("teacher"))
			courselist = CourseTeacher.getCourseListByTeacherId(Teacher.getTeacherByLoginname(username).getId());
		
		act.put("courselist", courselist);
		act.getSession().remove("course_id");
		act.getSession().remove("class_id");
		act.getSession().remove("section_id");
		act.getSession().remove("event_id");
		return SUCCESS;
	}
	
	public String deleteCourse() throws Exception {
		int course_id = (new Integer(ServletActionContext.getRequest().getParameter("course_id"))).intValue();
		Course.deleteCourseByCourseId(course_id);
		return SUCCESS;
	}
	
	public String addCourse() throws Exception {
		Course.addCourse(getCourse());
		return SUCCESS;
	}
	
	public String listCourse() throws Exception {
		ActionContext act = ActionContext.getContext();
		ArrayList<Course> courselist = Course.getCourseList();
		ArrayList<Course> courseselected = new ArrayList<>();
		String role = (String)act.getSession().get("role");
		String loginname = (String)act.getSession().get("username");
		HashMap<Integer, ArrayList<CourseTeacher>> map = new HashMap<Integer, ArrayList<CourseTeacher>>();
		if (role.equals("student"))
			courseselected = CourseStudent.getCourseListByStudentId(Student.getStudentByLoginname(loginname).getId());
		else if (role.equals("teacher"))
			courseselected = CourseTeacher.getCourseListByTeacherId(Teacher.getTeacherByLoginname(loginname).getId());
		for (int i = 0; i < courselist.size(); i++) {
			for (int j = 0; j < courseselected.size(); j++) {
				if (courseselected.get(j).getCourse_id() == courselist.get(i).getCourse_id()) {
					courselist.remove(i);
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < courselist.size(); i++) {
			int course_id = courselist.get(i).getCourse_id();
			ArrayList<CourseTeacher> res = CourseTeacher.getCourseTeacherByCourseId(course_id);
			System.out.println(Course.getCourseByCourseId(course_id).getCourse_name());
			for (int j = 0; j < res.size(); j++) {
				System.out.println("teacher: " + res.get(j).getId());
			}
			map.put(new Integer(course_id), res);
		}
		act.put("courseteacher", map);
		act.put("courselistnotselected", courselist);
		act.put("courseselected", courseselected);
		return SUCCESS;
	}
	
	public String selectCourse() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = (new Integer(ServletActionContext.getRequest().getParameter("course_id"))).intValue();
		int class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		String role = (String)act.getSession().get("role");
		String loginname = (String)act.getSession().get("username");
		int user_id = -1;
		if (role.equals("student")) {
			user_id = Student.getStudentByLoginname(loginname).getId();
			CourseStudent.addCourseStudent(course_id, class_id, user_id);
		}
		else if (role.equals("teacher")) {
			
		}
		else {
//			wrong role
		}
		return SUCCESS;
	}
	
	public String notselectCourse() throws Exception {
		int course_id = (new Integer(ServletActionContext.getRequest().getParameter("course_id"))).intValue();
		String role = (String)ServletActionContext.getRequest().getSession().getAttribute("role");
		String loginname = (String)ServletActionContext.getRequest().getSession().getAttribute("username");
		int user_id = -1;
		if (role.equals("student")) {
			user_id = Student.getStudentByLoginname(loginname).getId();
			CourseStudent.deleteCourseStudentByCourseIdAndStudentId(course_id, user_id);
		}
		else if (role.equals("teacher")) {
			
		}
		else {
//			wrong role
		}
		return SUCCESS;
	}
}
