package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cl.dbquery.DBHelper;

public class KnowledgeWeight {
	private int knowledgeweight_id;
	private int section_id;
	private double listening_weight;
	private double answer_weight;
	private double attendance_weight;
	private double homework_weight;
	private double experiment_weight;
	private double reviewandpreview_weight;
	
//	未区分不同老师所设置的权值
	public int getKnowledgeweight_id() {
		return knowledgeweight_id;
	}
	public void setKnowledgeweight_id(int knowledgeweight_id) {
		this.knowledgeweight_id = knowledgeweight_id;
	}
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public double getListening_weight() {
		return listening_weight;
	}
	public void setListening_weight(double listening_weight) {
		this.listening_weight = listening_weight;
	}
	public double getAnswer_weight() {
		return answer_weight;
	}
	public void setAnswer_weight(double answer_weight) {
		this.answer_weight = answer_weight;
	}
	public double getAttendance_weight() {
		return attendance_weight;
	}
	public void setAttendance_weight(double attendance_weight) {
		this.attendance_weight = attendance_weight;
	}
	public double getHomework_weight() {
		return homework_weight;
	}
	public void setHomework_weight(double homework_weight) {
		this.homework_weight = homework_weight;
	}
	public double getExperiment_weight() {
		return experiment_weight;
	}
	public void setExperiment_weight(double experiment_weight) {
		this.experiment_weight = experiment_weight;
	}
	public double getReviewandpreview_weight() {
		return reviewandpreview_weight;
	}
	public void setReviewandpreview_weight(double reviewandpreview_weight) {
		this.reviewandpreview_weight = reviewandpreview_weight;
	}
	
	public static KnowledgeWeight getInstance() {
		return new KnowledgeWeight();
	}
	
	public static KnowledgeWeight getKnowledgeWeightBySectionId(int section_id) {
		String sql = "select * from knowledgeweight where section_id=" + section_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		KnowledgeWeight kw = null;
		try {
			if (rs.next()) {
				kw = getInstance();
				int knowledgeweight_id = rs.getInt("knowledgeweight_id");
				double listening_weight = rs.getFloat("listening_weight");
				double answer_weight = rs.getFloat("answer_weight");
				double attendance_weight = rs.getFloat("attendance_weight");
				double homework_weight = rs.getFloat("homework_weight");
				double experiment_weight = rs.getFloat("experiment_weight");
				double reviewandpreview_weight = rs.getFloat("reviewandpreview_weight");
				
				kw.setKnowledgeweight_id(knowledgeweight_id);
				kw.setSection_id(section_id);
				kw.setListening_weight(listening_weight);
				kw.setAnswer_weight(answer_weight);
				kw.setAttendance_weight(attendance_weight);
				kw.setHomework_weight(homework_weight);
				kw.setExperiment_weight(experiment_weight);
				kw.setReviewandpreview_weight(reviewandpreview_weight);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return kw;
	}
	
	public static void addKnowledgeWeight(KnowledgeWeight kw) {
		String sql = "insert into knowledgeweight(section_id, listening_weight, answer_weight, attendance_weight, homework_weight"
				+ ", experiment_weight, reviewandpreview_weight) values(" + kw.getSection_id() + ", " + kw.getListening_weight()
				+ ", " + kw.getAnswer_weight() + ", " + kw.getAttendance_weight() + ", " + kw.getHomework_weight() + ", "
				+ kw.getExperiment_weight() + ", " + kw.getReviewandpreview_weight() + ");";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void updateKnowledgeWeight(KnowledgeWeight kw) {
		String sql = "update knowledgeweight set section_id=" + kw.getSection_id() + ", listening_weight=" + kw.getListening_weight()
				+ ", answer_weight=" + kw.getAnswer_weight() + ", attendance_weight=" + kw.getAttendance_weight() + ", homework_weight="
				+ kw.getHomework_weight() + ", experiment_weight=" + kw.getExperiment_weight() + ", reviewandpreview_weight="
				+ kw.getReviewandpreview_weight() + " where knowledgeweight_id=" + kw.getKnowledgeweight_id() + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteKnowledgeWeightBySectionId(int section_id) {
		String sql = "delete from knowledgeweight where section_id=" + section_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
