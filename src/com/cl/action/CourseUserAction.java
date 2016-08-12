package com.cl.action;

import java.util.ArrayList;
import java.util.Comparator;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Course;
import com.cl.dao.CourseTeacher;
import com.cl.dao.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CourseUserAction extends ActionSupport {

	public String getCourseTeacherList() throws Exception {
		System.out.println("getcourseteacherlist");
		ActionContext act = ActionContext.getContext();
		int course_id = -1;
		if (ServletActionContext.getRequest().getParameter("course_id") != null)
			course_id = (new Integer(ServletActionContext.getRequest().getParameter("course_id"))).intValue();
		else if (act.getSession().get("course_id") != null)
			course_id = ((Integer)act.getSession().get("course_id")).intValue();
		Course course = Course.getCourseByCourseId(course_id);
		ArrayList<CourseTeacher> courseteacher = CourseTeacher.getCourseTeacherByCourseId(course_id);
		ArrayList<Teacher> teacherlist = Teacher.getTeacherList();
		
		courseteacher.sort(new Comparator<CourseTeacher>() {
			public int compare(CourseTeacher a, CourseTeacher b) {
				return a.getId() - b.getId();
			}
		});
		teacherlist.sort(new Comparator<Teacher>() {
			public int compare(Teacher a, Teacher b) {
				return a.getId() - b.getId();
			}
		});
		
		for (int i = 0, j = 0; i < courseteacher.size() && j < teacherlist.size(); j++) {
			if (courseteacher.get(i).getId() == teacherlist.get(j).getId()) {
				teacherlist.remove(j);
				i++;
			}
		}
		
		courseteacher.sort(new Comparator<CourseTeacher>() {
			public int compare(CourseTeacher a, CourseTeacher b) {
				return a.getClass_id() - b.getClass_id();
			}
		});
		
		act.put("course", course);
		act.put("courseteacher", courseteacher);
		act.put("teacherlist", teacherlist);
		act.getSession().put("course_id", course_id);
		System.out.println("course_id in session: " + act.getSession().get("course_id"));
		return SUCCESS;
	}
	
	public String updateCourseClassCapacity() throws Exception {
		ActionContext act = ActionContext.getContext();
		System.out.println("course_id in session: " + act.getSession().get("course_id"));
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int classcapacity = Course.getCourseByCourseId(course_id).getCourse_classcapacity();
		String action = ServletActionContext.getRequest().getParameter("action");
		System.out.println(classcapacity);
		if (action.equals("add"))
			Course.updateCourseClassCapacity(course_id, classcapacity + 1);
		else
			Course.updateCourseClassCapacity(course_id, classcapacity - 1);
		return SUCCESS;
	}
}
