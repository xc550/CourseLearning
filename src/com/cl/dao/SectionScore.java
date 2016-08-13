package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class SectionScore {
	private int sectionscore_id;
	private int section_id;
	private int student_id;
	private double listening;
	private double answer;
	private double attendance;
	private double homework;
	private double experiment;
	private double reviewandpreview;
	private double sum;
	
	public int getSectionscore_id() {
		return sectionscore_id;
	}

	public void setSectionscore_id(int sectionscore_id) {
		this.sectionscore_id = sectionscore_id;
	}

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}

	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public double getListening() {
		return listening;
	}
	
	public void setListening(double listening) {
		this.listening = listening;
	}
	
	public double getAnswer() {
		return answer;
	}
	
	public void setAnswer(double answer) {
		this.answer = answer;
	}
	
	public double getAttendance() {
		return attendance;
	}
	
	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}
	
	public double getHomework() {
		return homework;
	}
	
	public void setHomework(double homework) {
		this.homework = homework;
	}
	
	public double getExperiment() {
		return experiment;
	}
	
	public void setExperiment(double experiment) {
		this.experiment = experiment;
	}
	
	public double getReviewandpreview() {
		return reviewandpreview;
	}
	
	public void setReviewandpreview(double reviewandpreview) {
		this.reviewandpreview = reviewandpreview;
	}
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public static SectionScore getInstance() {
		return new SectionScore();
	}
	
	public static ArrayList<SectionScore> getSectionScoreListBySectionId(int section_id, int student_id) {
		String sql = "select * from sectionscore where section_id = " + section_id;
		if (student_id != -1)
			sql = sql + " and student_id = " + student_id;
		sql += ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		ArrayList<SectionScore> al = new ArrayList<>();
		KnowledgeWeight sectionweight = KnowledgeWeight.getKnowledgeWeightBySectionId(section_id);
		
		try {
			while (rs.next()) {
				SectionScore sectionscore = SectionScore.getInstance();
				student_id = rs.getInt("student_id");
				double listening = rs.getFloat("listening");
				double answer = rs.getFloat("answer");
				double attendance = rs.getFloat("attendance");
				double homework = rs.getFloat("homework");
				double experiment = rs.getFloat("experiment");
				double reviewandpreview = rs.getFloat("reviewandpreview");
			
				sectionscore.setSection_id(section_id);
				sectionscore.setStudent_id(student_id);
				sectionscore.setListening(listening);
				sectionscore.setAnswer(answer);
				sectionscore.setAttendance(attendance);
				sectionscore.setHomework(homework);
				sectionscore.setExperiment(experiment);
				sectionscore.setReviewandpreview(reviewandpreview);
				
				double sum = sectionscore.getListening() * sectionweight.getListening_weight()
						+ sectionscore.getAnswer() * sectionweight.getAnswer_weight()
						+ sectionscore.getAttendance() * sectionweight.getAttendance_weight()
						+ sectionscore.getHomework() * sectionweight.getHomework_weight()
						+ sectionscore.getExperiment() * sectionweight.getExperiment_weight()
						+ sectionscore.getReviewandpreview() * sectionweight.getReviewandpreview_weight();
				sectionscore.setSum(sum);
				
				al.add(sectionscore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return al;
	}
	
	public static ArrayList<SectionScore> getSectionScoreListByStudentIdAndCourseId(int student_id, int course_id) {
		ArrayList<SectionScore> res = new ArrayList<>();
		String sql = "select * from sectionscore, section where sectionscore.student_id = " 
					+ student_id + " and section.course_id = " + course_id + " and sectionscore.section_id "
					+ "= section.section_id;";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				SectionScore sectionscore = SectionScore.getInstance(); 
				int section_id = rs.getInt("section_id");
				double listening = rs.getFloat("listening");
				double answer = rs.getFloat("answer");
				double attendance = rs.getFloat("attendance");
				double homework = rs.getFloat("homework");
				double experiment = rs.getFloat("experiment");
				double reviewandpreview = rs.getFloat("reviewandpreview");
				
				KnowledgeWeight sectionweight = KnowledgeWeight.getKnowledgeWeightBySectionId(section_id);
				sectionscore.setStudent_id(student_id);
				sectionscore.setSection_id(section_id);
				sectionscore.setListening(listening);
				sectionscore.setAnswer(answer);
				sectionscore.setAttendance(attendance);
				sectionscore.setHomework(homework);
				sectionscore.setExperiment(experiment);
				sectionscore.setReviewandpreview(reviewandpreview);
				double sum = sectionscore.getListening() * sectionweight.getListening_weight()
						+ sectionscore.getAnswer() * sectionweight.getAnswer_weight()
						+ sectionscore.getAttendance() * sectionweight.getAttendance_weight()
						+ sectionscore.getHomework() * sectionweight.getHomework_weight()
						+ sectionscore.getExperiment() * sectionweight.getExperiment_weight()
						+ sectionscore.getReviewandpreview() * sectionweight.getReviewandpreview_weight();
				sectionscore.setSum(sum);
				res.add(sectionscore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void updateSectionScoreList(SectionScore ss) {
		Connection con = DBHelper.getConnection();
		String sql = "update sectionscore set listening=" + ss.getListening() + ", answer="
					+ ss.getAnswer() + ", attendance=" + ss.getAttendance() + ", homework="
					+ ss.getHomework() + ", experiment=" + ss.getExperiment() + ", reviewandpreview="
					+ ss.getReviewandpreview() + " where student_id=" + ss.getStudent_id()
					+ " and section_id=" + ss.getSection_id() + ";";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteSectionScoreBySectionId(int section_id) {
    	Connection con = DBHelper.getConnection();
		String sql = "delete from sectionscore where section_id=" + section_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
	
	public static void deleteSectionScoreByStudentId(int student_id) {
    	Connection con = DBHelper.getConnection();
		String sql = "delete from sectionscore where student_id=" + student_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
	
	public static double getHomeworkScoreByStudentIdAndSectionId(int section_id, int student_id) {
		double res = -1;
		String sql = "select homework from sectionscore where student_id=" + student_id + " and section_id=" + section_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				res = rs.getFloat("homework");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
}
