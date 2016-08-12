package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class Section {
	private int section_id;
	private int course_id;
	private String section_name;
	private double section_weight;
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public double getSection_weight() {
		return section_weight;
	}
	public void setSection_weight(double section_weight) {
		this.section_weight = section_weight;
	}
	
	public static Section getInstance() {
		return new Section();
	}
	
	public static Section getSectionBySectionId(int section_id) {
		String sql = "select * from section where section_id=" + section_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		Section section = null;
		try {
			if (rs.next()) {
				section = getInstance();
				int course_id = rs.getInt("course_id");
				String section_name = rs.getString("section_name");
				double section_weight = rs.getFloat("section_weight");
				
				section.setCourse_id(course_id);
				section.setSection_id(section_id);
				section.setSection_name(section_name);
				section.setSection_weight(section_weight);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return section;
	}
	
	public static ArrayList<Section> getSectionListByCourseId(int course_id) {
		String sql = "select * from section where course_id=" + course_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		ArrayList<Section> res = new ArrayList<>();
		try {
			if (rs.next()) {
				Section section = getInstance();
				int section_id = rs.getInt("section_id");
				String section_name = rs.getString("section_name");
				double section_weight = rs.getFloat("section_weight");
				
				section.setCourse_id(course_id);
				section.setSection_id(section_id);
				section.setSection_name(section_name);
				section.setSection_weight(section_weight);
				
				res.add(section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addSection(Section section) {
		String sql = "insert into section(course_id, section_name, section_weight) values(" + section.getCourse_id()
					+ ", '" + section.getSection_name() + "', " + section.getSection_weight() + ");";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void updateSection(Section section) {
		String sql = "update section set course_id=" + section.getCourse_id() + ", section_name='" 
					+ section.getSection_name() + "', section_weight=" + section.getSection_weight() 
					+ " where section_id=" + section.getSection_id() + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteSectionBySectionId(int section_id) {
		String sql = "delete from section where section_id=" + section_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteSectionByCourseId(int course_id) {
		String sql = "delete from section where course_id=" + course_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
