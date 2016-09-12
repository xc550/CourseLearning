package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.CourseHead;

public class CourseStudent extends CourseHead {
	public static CourseStudent getInstance() {
		return new CourseStudent();
	}
	
	public static ArrayList<Student> getStudentListByCourseIdAndClassId(int course_id, int class_id) {
		ArrayList<Student> stulist = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		String sql = "select * from coursestudent where course_id=" + course_id + " and class_id=" + class_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Student stu = Student.getStudentByStudentId(rs.getInt("student_id"));
				stulist.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return stulist;
	}
	
	public static CourseStudent getCourseStudentByCourseIdAndStudentId(int course_id, int student_id) {
		String sql = "select * from coursestudent where course_id=" + course_id + " and student_id=" + student_id + ";";
		CourseStudent res = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next()) {
				res = new CourseStudent();
				int class_id = rs.getInt("class_id");
				int coursestudent_id = rs.getInt("coursestudent_id");
				
				res.setCoursehead_id(coursestudent_id);
				res.setClass_id(class_id);
				res.setCourse_id(course_id);
				res.setId(student_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static ArrayList<Course> getCourseListByStudentId(int id) {
		String sql = "select course_id from coursestudent where student_id=" + id + ";";
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
	
	public static void addCourseStudent(int course_id, int class_id, int user_id) {
		Connection con = DBHelper.getConnection();
		String sql = "insert into coursestudent(course_id, student_id, class_id) "
				+ "values(" + course_id + ", " + user_id + ", " + class_id + ");";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteCourseStudentByStudentId(int student_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from coursestudent where student_id=" + student_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
	
	public static void deleteCourseStudentByCourseId(int course_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from coursestudent where course_id=" + course_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
	
	public static void deleteCourseStudentByCourseIdAndStudentId(int course_id, int user_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from coursestudent where course_id=" + course_id + " and student_id=" + user_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);	
	}
}
