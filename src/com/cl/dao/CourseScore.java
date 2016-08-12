package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class CourseScore {
	private ArrayList<SectionScore> sectionscore;
	private int student_id;
	private int course_id;
//	private 
	
	public ArrayList<SectionScore> getSectionscore() {
		return sectionscore;
	}

	public void setSectionscore(ArrayList<SectionScore> sectionscore) {
		this.sectionscore = sectionscore;
	}

	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	public double getAverage() {
		ArrayList<SectionScore> rs = getSectionscore();
		if (rs.size() == 0)
			return 0;
		double avg = 0;
		for (int i = 0; i < rs.size(); i++) {
			avg += Section.getSectionBySectionId(rs.get(i).getSection_id()).getSection_weight() * rs.get(i).getSum();
		}
		return avg; 
	}
	
	public Section getWorstSection() {
		Section section = null;
		double infl = 0;
		ArrayList<Section> res = Section.getSectionListByCourseId(course_id);
		for (int i = 0; i < sectionscore.size(); i++) {
			double f = sectionscore.get(i).getSum() * res.get(i).getSection_weight();
			if (f > infl) {
				section = Section.getSectionBySectionId(sectionscore.get(i).getSection_id());
				infl = f; 
			}
		}
		return section;
	}
	
	public static ArrayList<CourseScore> getCourseScoreList(int course_id, int student_id) {
		ArrayList<CourseScore> res = new ArrayList<>();
		String sql = "select section_id from section where course_id = " + course_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			CourseScore coursescore = new CourseScore();
			coursescore.setCourse_id(course_id);
			while (rs.next()) {
				int section_id = rs.getInt("section_id");
				coursescore.setSectionscore(SectionScore.getSectionScoreListBySectionId(section_id, student_id));
				res.add(coursescore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return res;
	}
}
