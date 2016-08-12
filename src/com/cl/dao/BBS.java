package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.cl.dbquery.DBHelper;
import com.cl.util.DateFormator;

public class BBS {
	private int bbs_id;
	private int class_id;
	private int event_id;
	private int student_id;
	private int teacher_id;
	private String bbs_content;
	private Calendar bbs_date;
	private int reply_id;
	
	public int getBbs_id() {
		return bbs_id;
	}
	
	public void setBbs_id(int bbs_id) {
		this.bbs_id = bbs_id;
	}
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getEvent_id() {
		return event_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getBbs_content() {
		return bbs_content;
	}
	
	public void setBbs_content(String bbs_content) {
		this.bbs_content = bbs_content;
	}
	
	public Calendar getBbs_date() {
		return bbs_date;
	}
	
	public void setBbs_date(Calendar bbs_date) {
		this.bbs_date = bbs_date;
	}
	
	public int getReply_id() {
		return reply_id;
	}
	
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public static BBS getInstance() {
		return new BBS();
	}
	
	
	
	public static BBS getBBSByBBSId(int bbs_id) {
		BBS bbs = null;
		Connection con = DBHelper.getConnection();
		String sql = "select * from bbs where bbs_id = " + bbs_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next()) {
				bbs = BBS.getInstance();
				int event_id = rs.getInt("event_id");
				int class_id = rs.getInt("class_id");
				int student_id = rs.getInt("student_id"); 
				int teacher_id = rs.getInt("teacher_id");
				String bbs_content = rs.getString("bbs_content");
				String bbs_date = rs.getString("bbs_date");
				int reply_id = rs.getInt("reply_id");
				
				bbs.setBbs_id(bbs_id);
				bbs.setClass_id(class_id);
				bbs.setEvent_id(event_id);
				bbs.setStudent_id(student_id);
				bbs.setTeacher_id(teacher_id);
				bbs.setBbs_content(bbs_content);
				bbs.setBbs_date(DateFormator.getDateByPattern(bbs_date));
				bbs.setReply_id(reply_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return bbs;
	}
	
	public static ArrayList<BBS> getBBSListByEventId(int event_id) {
		ArrayList<BBS> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		String sql = "select * from bbs where event_id = " + event_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				BBS bbs = BBS.getInstance();
				int bbs_id = rs.getInt("bbs_id");
				int student_id = rs.getInt("student_id"); 
				int teacher_id = rs.getInt("teacher_id");
				String bbs_content = rs.getString("bbs_content");
				String bbs_date = rs.getString("bbs_date");
				int reply_id = rs.getInt("reply_id");
				
				bbs.setBbs_id(bbs_id);
				bbs.setEvent_id(event_id);
				bbs.setStudent_id(student_id);
				bbs.setTeacher_id(teacher_id);
				bbs.setBbs_content(bbs_content);
				bbs.setBbs_date(DateFormator.getDateByPattern(bbs_date));
				bbs.setReply_id(reply_id);
				res.add(bbs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addBBS(BBS bbs) {
		String sql = "insert into bbs(class_id, event_id, bbs_content, student_id, teacher_id, bbs_date, reply_id)"
				+ " values(" + bbs.getClass_id() + ", " + bbs.getEvent_id() + ", '" + bbs.getBbs_content() + "', " 
				+ bbs.getStudent_id() + ", " + bbs.getTeacher_id() + ", '" + DateFormator.getDateCalendarToString(bbs.getBbs_date()) 
				+ "', " + bbs.getReply_id() + ");";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteBBSByEventId(int event_id) {
		String sql = "delete from bbs where event_id = " + event_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteBBSByStudentId(int student_id) {
		Connection con = DBHelper.getConnection();
		String sql = "select distinct bbs_id from bbs where student_id=" + student_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int bbs_id = rs.getInt("bbs_id");
				String ssql = "delete from bbs where reply_id=" + bbs_id + ";";
				DBHelper.execUpdate(con, ssql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from bbs where student_id = " + student_id + ";";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteBBSByTeacherId(int teacher_id) {
		Connection con = DBHelper.getConnection();
		String sql = "select distinct bbs_id from bbs where teacher_id=" + teacher_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int bbs_id = rs.getInt("bbs_id");
				String ssql = "delete from bbs where reply_id=" + bbs_id + ";";
				DBHelper.execUpdate(con, ssql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "delete from bbs where teacher_id = " + teacher_id + ";";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
