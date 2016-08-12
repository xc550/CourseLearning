package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.cl.dbquery.DBHelper;
import com.cl.util.DateFormator;

public class Homework {
	private int homework_id;
	private int course_id;
	private int class_id;
	private int section_id;
	private String homework_title;
	private String homework_content;
	private Calendar homework_starttime;
	private Calendar homework_endtime;
	private String homework_accessory;
	
	public int getHomework_id() {
		return homework_id;
	}

	public void setHomework_id(int homework_id) {
		this.homework_id = homework_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public String getHomework_title() {
		return homework_title;
	}

	public void setHomework_title(String homework_title) {
		this.homework_title = homework_title;
	}

	public String getHomework_content() {
		return homework_content;
	}

	public void setHomework_content(String homework_content) {
		this.homework_content = homework_content;
	}

	public Calendar getHomework_starttime() {
		return homework_starttime;
	}

	public void setHomework_starttime(Calendar homework_starttime) {
		this.homework_starttime = homework_starttime;
	}

	public Calendar getHomework_endtime() {
		return homework_endtime;
	}

	public void setHomework_endtime(Calendar homework_endtime) {
		this.homework_endtime = homework_endtime;
	}

	public String getHomework_accessory() {
		return homework_accessory;
	}

	public void setHomework_accessory(String homework_accessory) {
		this.homework_accessory = homework_accessory;
	}

	public static Homework getInstance() {
		return new Homework();
	}
	
	public static Homework getHomeworkByHomeworkId(int homework_id) {
		Homework hw = null;
		String sql = "select * from homework where homework_id=" + homework_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				hw = Homework.getInstance();
				int course_id = rs.getInt("course_id");
				int class_id = rs.getInt("class_id");
				int section_id = rs.getInt("section_id");
				String homework_title = rs.getString("homework_title");
				String homework_content = rs.getString("homework_content");
				Calendar homework_starttime = DateFormator.getDateByPattern(rs.getString("homework_starttime"));
				Calendar homework_endtime = DateFormator.getDateByPattern(rs.getString("homework_endtime"));
				String homework_accessory = rs.getString("homework_accessory");
				
				hw.setHomework_id(homework_id);
				hw.setCourse_id(course_id);
				hw.setClass_id(class_id);
				hw.setSection_id(section_id);
				hw.setHomework_title(homework_title);
				hw.setHomework_content(homework_content);
				hw.setHomework_starttime(homework_starttime);
				hw.setHomework_endtime(homework_endtime);
				hw.setHomework_accessory(homework_accessory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return hw;
 	}
	
	public static ArrayList<Homework> getHomeworkListByCourseIdAndClassId(int course_id, int class_id) {
		ArrayList<Homework> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		String sql = "select * from homework where course_id=" + course_id + " and class_id=" + class_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Homework homework = Homework.getInstance();
				int homework_id = rs.getInt("homework_id");
				int section_id = rs.getInt("section_id");
				String homework_title = rs.getString("homework_title");
				String homework_content = rs.getString("homework_content");
				Calendar homework_starttime = DateFormator.getDateByPattern(rs.getString("homework_starttime"));
				Calendar homework_endtime = DateFormator.getDateByPattern(rs.getString("homework_endtime"));
				String homework_accessory = rs.getString("homework_accessory");
				
				homework.setHomework_id(homework_id);
				homework.setCourse_id(course_id);
				homework.setClass_id(class_id);
				homework.setSection_id(section_id);
				homework.setHomework_title(homework_title);
				homework.setHomework_content(homework_content);
				homework.setHomework_starttime(homework_starttime);
				homework.setHomework_endtime(homework_endtime);
				homework.setHomework_accessory(homework_accessory);
				res.add(homework);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addHomework(Homework homework) {
		String sql = "insert into homework(course_id, class_id, section_id, homework_title, homework_content, homework_starttime"
				+ ", homework_endtime, homework_accessory) values(" + homework.getCourse_id() + ", " + homework.getClass_id()
				+ ", " + homework.getSection_id() + ", '" + homework.getHomework_title() + "', '" + homework.getHomework_content() 
				+ "', '" + DateFormator.getDateCalendarToString(homework.getHomework_starttime()) + "', '"
				+ DateFormator.getDateCalendarToString(homework.getHomework_endtime()) + "'";
		if (homework.getHomework_accessory() == null)
			sql = sql + ", null);";
		else
			sql = sql + ", '" + homework.getHomework_accessory() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void updateHomework(Homework homework) {
		String sql = "update homework set course_id=" + homework.getCourse_id() + ", class_id=" + homework.getClass_id() + ", section_id=" 
				+ homework.getSection_id() + ", homework_title='" + homework.getHomework_title() + "', homework_content='"
				+ homework.getHomework_content() + "', homework_starttime='" + DateFormator.getDateCalendarToString(homework.getHomework_starttime())
				+ "', homework_endtime='" + DateFormator.getDateCalendarToString(homework.getHomework_endtime()) + "', homework_accessory='"
				+ homework.getHomework_accessory() + "' where homework_id=" + homework.getHomework_id() + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomeworkByHomeworkId(int homework_id) {
		String sql = "delete from homework where homework_id=" + homework_id + ";"; 
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomewrokByCourseIdAndClassIdAndSectionId(int course_id, int class_id, int section_id) {
		String sql = "delete from homework where course_id=" + course_id + " and class_id=" + class_id + " and section_id=" + section_id + ";"; 
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomewrokByCourseIdAndClassId(int course_id, int class_id) {
		String sql = "delete from homework where course_id=" + course_id + " and class_id=" + class_id + ";"; 
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomewrokByCourseId(int course_id) {
		String sql = "delete from homework where course_id=" + course_id + ";"; 
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteHomeworkAccessory(int homework_id) {
		String sql = "update homework set homework_accessory=null where homework_id=" + homework_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void addHomeworkAccessory(int homework_id, String accessory) {
		String sql = "update homework set homework_accessory='" + accessory + "' where homework_id=" + homework_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
