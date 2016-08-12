package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.User;

public class Teacher extends User {
	public static Teacher getInstance() {
		return new Teacher();
	}
	
	public static boolean check(String loginname, String password) {
		String sql = "select * from teacher where teacher_loginname='" + loginname + "' and teacher_password='" + password + "';";
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
	
	public static Teacher getTeacherByLoginname(String loginname) {
		String sql = "select * from teacher where teacher_loginname='" + loginname + "';";
		Teacher tea = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				tea = Teacher.getInstance();
				int id = rs.getInt("teacher_id");
				String name = rs.getString("teacher_name");
				
				tea.setId(id);
				tea.setLoginname(loginname);
				tea.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return tea;
	}
	
	public static Teacher getTeacherByTeacherId(int teacher_id) {
		String sql = "select * from teacher where teacher_id=" + teacher_id + ";";
		Teacher tea = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				tea = Teacher.getInstance();
				String loginname = rs.getString("teacher_loginname");
				String name = rs.getString("teacher_name");
				
				tea.setId(teacher_id);
				tea.setLoginname(loginname);
				tea.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return tea;
	}
	
	public static ArrayList<Teacher> getTeacherList() {
		String sql = "select * from teacher";
		ArrayList<Teacher> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Teacher tea = Teacher.getInstance();
				int id = rs.getInt("teacher_id");
				String loginname = rs.getString("teacher_loginname");
				String name = rs.getString("teacher_name");
				
				tea.setId(id);
				tea.setLoginname(loginname);
				tea.setName(name);
				
				res.add(tea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addTeacher(Teacher tea) {
		String sql = "insert into teacher(teacher_loginname, teacher_password, teacher_name)"
				+ " values('" + tea.getLoginname() + "', '" + tea.getPassword() + "', '"
				+ tea.getName() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteTeacherByTeacherId(int teacher_id) {
		BBS.deleteBBSByTeacherId(teacher_id);
		CourseTeacher.deleteCourseTeacherByTeacherId(teacher_id);
		
		String sql = "delete from teacher where teacher_id=" + teacher_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteTeacherByTeacherLoginname(String loginname) {
		int teacher_id = Teacher.getTeacherByLoginname(loginname).getId();
		BBS.deleteBBSByTeacherId(teacher_id);
		CourseTeacher.deleteCourseTeacherByTeacherId(teacher_id);
		
		String sql = "delete from teacher where teacher_loginname=" + loginname + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}

}
