package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class Course {
	private int course_id;
	private String course_name;
	private int course_classcapacity;
	
	public int getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	public String getCourse_name() {
		return course_name;
	}
	
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getCourse_classcapacity() {
		return course_classcapacity;
	}

	public void setCourse_classcapacity(int course_classcapacity) {
		this.course_classcapacity = course_classcapacity;
	}

	public static Course getInstance() {
		return new Course();
	}
	
	public static Course getCourseByCourseId(int course_id) {
		String sql = "select * from course where course_id = " + course_id + ";";
		Connection con = DBHelper.getConnection();
		Course course = null;
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next()) {
				course = Course.getInstance();
				String course_name = rs.getString("course_name");
				int course_classcapacity = rs.getInt("course_classcapacity");
				
				course.setCourse_id(course_id);
				course.setCourse_name(course_name);
				course.setCourse_classcapacity(course_classcapacity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return course;
	}
	
	public static ArrayList<Course> getCourseList() {
		String sql = "select * from course";
		ArrayList<Course> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Course course = Course.getInstance();
				String course_name = rs.getString("course_name");
				int course_id = rs.getInt("course_id");
				int course_classcapacity = rs.getInt("course_classcapacity");
				
				course.setCourse_id(course_id);
				course.setCourse_name(course_name);
				course.setCourse_classcapacity(course_classcapacity);
				res.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addCourse(Course course) {
		String sql = "insert into course(course_name, course_classcapacity) values('" + course.getCourse_name() + "', " 
					+ course.getCourse_classcapacity() + ");";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteCourseByCourseId(int id) {
		Section.deleteSectionByCourseId(id);
    	CourseTeacher.deleteCourseTeacherByCourseId(id);
		CourseStudent.deleteCourseStudentByCourseId(id);
    	
    	Connection con = DBHelper.getConnection();
    	String sql = "delete from course where course_id=" + id + ";";
    	DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
	
	public static void updateCourseClassCapacity(int course_id, int classcapacity) {
		String sql = "update course set course_classcapacity=" + classcapacity + " where course_id=" + course_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
}
