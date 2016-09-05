package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import com.cl.dbquery.DBHelper;

public class CourseScore {
	private ArrayList<SectionScore> sectionscore;
	private int student_id;
	private int course_id; 
	
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
			if (rs.get(i).getSum() == -1) {
				avg = -1;
				break;
			}
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
	
	public static ArrayList<CourseScore> getCourseScoreList(int course_id) {
		ArrayList<CourseScore> res = new ArrayList<>();
		ArrayList<Integer> sectionlist = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		
		String sql = "select * from section where course_id=" + course_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int section_id = rs.getInt("section_id");
				sectionlist.add(section_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sectionlist.sort(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
		
		sql = "select distinct student_id from coursestudent where course_id=" + course_id + ";";
		rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				CourseScore coursescore = new CourseScore();
				int student_id = rs.getInt("student_id");
				coursescore.setCourse_id(course_id);
				coursescore.setStudent_id(student_id);
				ArrayList<SectionScore> tmp = SectionScore.getSectionScoreListBySectionIdAndStudentId(-1, student_id);
				tmp.sort(new Comparator<SectionScore>() {
					public int compare(SectionScore a, SectionScore b) {
						return a.getSection_id() - b.getSection_id();
					}
				});
				
				System.out.println("before: " + tmp.size() + " " + sectionlist.size());
				for (int i = 0, j = 0; i < sectionlist.size(); i++) {
					boolean ins = true;
					if (j < tmp.size() && tmp.get(j).getSection_id() == sectionlist.get(i)) {
						j++;
						ins = false;
					}
					if (ins) {
						SectionScore e = SectionScore.getInstance();
						e.setStudent_id(student_id);
						e.setSection_id(sectionlist.get(i));
						e.setSum(-1);
						tmp.add(j, e);
						j++;
					}
				}
				System.out.println("after: " + tmp.size() + " " + sectionlist.size());
				coursescore.setSectionscore(tmp);
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
