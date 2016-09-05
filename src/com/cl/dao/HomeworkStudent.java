package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class HomeworkStudent {
	private int homeworkstudent_id;
	private int homework_id;
	private int student_id;
	private String homeworkstudent_comment;
	private String homeworkstudent_accessory;
	
	public int getHomeworkstudent_id() {
		return homeworkstudent_id;
	}
	
	public void setHomeworkstudent_id(int homeworkstudent_id) {
		this.homeworkstudent_id = homeworkstudent_id;
	}
	
	public int getHomework_id() {
		return homework_id;
	}
	
	public void setHomework_id(int homework_id) {
		this.homework_id = homework_id;
	}
	
	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public String getHomeworkstudent_comment() {
		return homeworkstudent_comment;
	}
	
	public void setHomeworkstudent_comment(String homeworkstudent_comment) {
		this.homeworkstudent_comment = homeworkstudent_comment;
	}
	
	public String getHomeworkstudent_accessory() {
		return homeworkstudent_accessory;
	}
	
	public void setHomeworkstudent_accessory(String homeworkstudent_accessory) {
		this.homeworkstudent_accessory = homeworkstudent_accessory;
	}
	
	@Override
	public String toString() {
		return "homeworkstudent_id:" + this.getHomeworkstudent_id() + "#homework_id:" + this.getHomework_id() 
			+ "#student_id:" + this.getStudent_id() + "#homeworkstudent_comment:" + this.getHomeworkstudent_comment() 
			+ "#homeworkstudent_accessory:" + this.getHomeworkstudent_accessory() + "#";
	}
	
	public static HomeworkStudent getInstance() {
		return new HomeworkStudent();
	}
	
	public static HomeworkStudent getHomeworkStudentByHomeworkStudentId(int homeworkstudent_id) {
		String sql = "select * from homeworkstudent where homeworkstudent_id=" + homeworkstudent_id + ";";
		Connection con = DBHelper.getConnection();
		HomeworkStudent hws = null;
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next()) {
				hws = getInstance();
				int homework_id = rs.getInt("homework_id");
				int student_id = rs.getInt("student_id");
				String homeworkstudent_comment = rs.getString("homeworkstudent_comment");
				String homeworkstudent_accessory = rs.getString("homeworkstudent_accessory");
				
				hws.setHomework_id(homework_id);
				hws.setHomeworkstudent_accessory(homeworkstudent_accessory);
				hws.setHomeworkstudent_comment(homeworkstudent_comment);
				hws.setHomeworkstudent_id(homeworkstudent_id);
				hws.setStudent_id(student_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return hws;
	}
	
	public static HomeworkStudent getHomeworkStudentByHomeworkIdAndStudentId(int homework_id, int student_id) {
		String sql = "select * from homeworkstudent where homework_id=" + homework_id + " and student_id=" + student_id + ";";
		Connection con = DBHelper.getConnection();
		HomeworkStudent hws = null;
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				hws = getInstance();
				int homeworkstudent_id = rs.getInt("homeworkstudent_id");
				String homeworkstudent_comment = rs.getString("homeworkstudent_comment");
				String homeworkstudent_accessory = rs.getString("homeworkstudent_accessory");
				
				hws.setHomework_id(homework_id);
				hws.setHomeworkstudent_accessory(homeworkstudent_accessory);
				hws.setHomeworkstudent_comment(homeworkstudent_comment);
				hws.setHomeworkstudent_id(homeworkstudent_id);
				hws.setStudent_id(student_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return hws;
	}
	
	public static ArrayList<HomeworkStudent> getHomeworkStudentListByHomeworkId(int homework_id) {
		ArrayList<HomeworkStudent> res = new ArrayList<>();
		String sql = "select * from homeworkstudent where homework_id=" + homework_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				HomeworkStudent hws = getInstance();
				int homeworkstudent_id = rs.getInt("homeworkstudent_id");
				int student_id = rs.getInt("student_id");
				String homeworkstudent_comment = rs.getString("homeworkstudent_comment");
				String homeworkstudent_accessory = rs.getString("homeworkstudent_accessory");
				
				hws.setHomework_id(homework_id);
				hws.setHomeworkstudent_accessory(homeworkstudent_accessory);
				hws.setHomeworkstudent_comment(homeworkstudent_comment);
				hws.setHomeworkstudent_id(homeworkstudent_id);
				hws.setStudent_id(student_id);
				res.add(hws);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);;
		return res;
	}
	
	public static void addHomeworkStudent(HomeworkStudent hws) {
		String sql = "insert into homeworkstudent(homework_id, student_id, homeworkstudent_comment, "
				+ "homeworkstudent_accessory) values(" + hws.getHomework_id() + ", " + hws.getStudent_id()
				+ ", '" + hws.getHomeworkstudent_comment() + "', '" + hws.getHomeworkstudent_accessory() +"')";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomeworkStudentByHomeworkStudentId(int homeworkstudent_id) {
		String sql = "delete from homeworkstudent where homeworkstudent_id=" + homeworkstudent_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomeworkStudentByStudentId(int student_id) {
		String sql = "delete from homeworkstudent where student_id=" + student_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomeworkStudentByHomeworkId(int homework_id) {
		String sql = "delete from homeworkstudent where homework_id=" + homework_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
