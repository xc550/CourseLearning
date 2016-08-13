package com.cl.util;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.cl.dao.CourseScore;
import com.cl.dao.Section;
import com.cl.dao.SectionScore;
import com.cl.dao.Student;

public class CourseCalc {
	private ArrayList<CourseScore> source;
	
	public CourseCalc(int course_id) {
		source = CourseScore.getCourseScoreList(course_id, -1);
	}

	public ArrayList<CourseScore> getSource() {
		return source;
	}

	public void setSource(ArrayList<CourseScore> source) {
		this.source = source;
	}
	
	public double getAverage() {
		double res = 0;
		for (int i = 0; i < source.size(); i++) {
			res += source.get(i).getAverage();
		}
		if (source.size() > 0)
			res /= source.size();
		return res;
	}
	
	public double getMax() {
		double max = -1.0;
		for (int i = 0; i < source.size(); i++) {
			max = Math.max(max, source.get(i).getAverage());
		}
		return max;
	}
	
	public double getMin() {
		double min = 200.0;
		for (int i = 0; i < source.size(); i++) {
			min = Math.min(min, source.get(i).getAverage());
		}
		return min;
	}
	
	public ArrayList<Student> getMaxScore() {
		BigDecimal max = new BigDecimal(getMax());
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getAverage());
			if (max.equals(score)) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
	
	public ArrayList<Student> getMinScore() {
		BigDecimal min = new BigDecimal(getMin());
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getAverage());
			if (min.equals(score)) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
	
	public ArrayList<Student> getHigher() {
		BigDecimal avg = new BigDecimal(getAverage());
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getAverage());
			if (score.equals(avg.max(score))) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
	
	private int getProblemInLower(ArrayList<SectionScore> res) {
		int section_id = -1;
		for (int i = 0; i < res.size(); i++) {
			
		}
		return section_id;
	}
	
	public ArrayList<Integer> getLower() {
		BigDecimal avg = new BigDecimal(getAverage());
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getAverage());
			if (score.equals(avg.min(score))) {
				res.add(source.get(i).getStudent_id());
				res.add(getProblemInLower(source.get(i).getSectionscore()));
			}
		}
		return res;
	}
	
	public ArrayList<Integer> suspectCheating() {
		ArrayList<Integer> res = new ArrayList<>();
		return res;
	}
	
	public ArrayList<Section> getCorePrblem() {
		ArrayList<Section> res = new ArrayList<>();
		return res;
	}
}
