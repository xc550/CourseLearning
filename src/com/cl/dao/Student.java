package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.User;

public class Student extends User {
	private String gender;
	private String head;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	public static Student getInstance() {
		return new Student();
	}
	
	public static boolean check(String loginname, String password) {
		String sql = "select * from student where student_number='" + loginname + "' and student_password='" + password + "';";
		boolean checked = false;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next())
				checked = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return checked;
	}
	
	public static Student getStudentByLoginname(String loginname) {
		String sql = "select * from student where student_number='" + loginname + "';";
		Student stu = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				stu = Student.getInstance();
				int id = rs.getInt("student_id");
				String name = rs.getString("student_name");
				String gender = rs.getString("student_gender");
				String head = rs.getString("student_head");
				
				stu.setId(id);
				stu.setLoginname(loginname);
				stu.setName(name);
				stu.setGender(gender);
				stu.setHead(head);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return stu;
	}
	
	public static Student getStudentByStudentId(int student_id) {
		String sql = "select * from student where student_id=" + student_id + ";";
		Student stu = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				stu = Student.getInstance();
				String loginname = rs.getString("student_number");
				String name = rs.getString("student_name");
				String gender = rs.getString("student_gender");
				String head = rs.getString("student_head");
				
				stu.setId(student_id);
				stu.setLoginname(loginname);
				stu.setName(name);
				stu.setGender(gender);
				stu.setHead(head);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return stu;
	}
	
	public static ArrayList<Student> getStudentList() {
		String sql = "select * from student";
		ArrayList<Student> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Student stu = Student.getInstance();
				int id = rs.getInt("student_id");
				String loginname = rs.getString("student_number");
				String name = rs.getString("student_name");
				String gender = rs.getString("student_gender");
				String head = rs.getString("student_head");
				
				stu.setId(id);
				stu.setLoginname(loginname);
				stu.setName(name);
				stu.setGender(gender);
				stu.setHead(head);
				
				res.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addStudent(Student stu) {
		String sql = "insert into student(student_number, student_password, student_name, student_gender"
				+ ", student_head) values('" + stu.getLoginname() + "', '" + stu.getPassword() + "', '"
				+ stu.getName() + "', '" + stu.getGender() + "', '" + stu.getHead() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteStudentByStudentId(int student_id) {
		BBS.deleteBBSByStudentId(student_id);
		SectionScore.deleteSectionScoreByStudentId(student_id);
		LearningStatus.deleteLearningStatusByStudentId(student_id);
		CourseStudent.deleteCourseStudentByStudentId(student_id);
		
		String sql = "delete from student where student_id=" + student_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteStudentByStudentLoginname(String loginname) {
		int student_id = Student.getStudentByLoginname(loginname).getId();
		BBS.deleteBBSByStudentId(student_id);
		SectionScore.deleteSectionScoreByStudentId(student_id);
		LearningStatus.deleteLearningStatusByStudentId(student_id);
		CourseStudent.deleteCourseStudentByStudentId(student_id);
		
		String sql = "delete from student where student_number=" + loginname + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
