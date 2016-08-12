package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.CourseHead;

public class CourseTeacher extends CourseHead {
	public static CourseTeacher getInstance() {
		return new CourseTeacher();
	}
	
	public static CourseTeacher getCourseTeacherByCourseIdAndTeacherId(int course_id, int teacher_id) {
		String sql = "select * from courseteacher where course_id=" + course_id + " and teacher_id=" + teacher_id + ";";
		CourseTeacher res = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				res = new CourseTeacher();
				int class_id = rs.getInt("class_id");
				int coursestudent_id = rs.getInt("courseteacher_id");
				
				res.setCoursehead_id(coursestudent_id);
				res.setClass_id(class_id);
				res.setCourse_id(course_id);
				res.setId(teacher_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static ArrayList<CourseTeacher> getCourseTeacherByCourseId(int course_id) {
		String sql = "select * from courseteacher where course_id = " + course_id + ";";
		ArrayList<CourseTeacher> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				CourseTeacher ct = new CourseTeacher();
				int class_id = rs.getInt("class_id");
				int teacher_id = rs.getInt("teacher_id");
				int courseteacher_id = rs.getInt("courseteacher_id");
				
				ct.setClass_id(class_id);
				ct.setCourse_id(course_id);
				ct.setId(teacher_id);
				ct.setCoursehead_id(courseteacher_id);
				res.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static ArrayList<Course> getCourseListByTeacherId(int teacher_id) {
		String sql = "select * from courseteacher where teacher_id = " + teacher_id + ";";
		ArrayList<Course> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int course_id = rs.getInt("course_id");
				res.add(Course.getCourseByCourseId(course_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addCourseTeacher(int course_id, int class_id, int user_id) {
		Connection con = DBHelper.getConnection();
		String sql = "insert into courseteacher(course_id, teacher_id, class_id) "
				+ "values(" + course_id + ", " + user_id + ", " + class_id + ");";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteCourseTeacherByTeacherId(int teacher_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from courseteacher where teacher_id=" + teacher_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
	
	public static void deleteCourseTeacherByCourseId(int course_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from courseteacher where course_id=" + course_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
	
	public static void deleteCourseTeacherOne(int course_id, int user_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from courseteacher where course_id=" + course_id + " and teacher_id=" + user_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
}
