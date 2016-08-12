package com.cl.util;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.cl.dao.SectionScore;
import com.cl.dao.Student;

public class SectionCalc {
	ArrayList<SectionScore> source;
	private final double stdscore = 89.0;
	
	public SectionCalc(int section_id) {
		source = SectionScore.getSectionScoreListBySectionId(section_id, -1);
	}

	public ArrayList<SectionScore> getSource() {
		return source;
	}

	public void setSource(ArrayList<SectionScore> source) {
		this.source = source;
	}
	
	public double getAverage() {
		double res = 0;
		for (int i = 0; i < source.size(); i++) {
			res += source.get(i).getSum();
		}
		if (source.size() > 0)
			res /= source.size();
		return res;
	}
	
	public double getMax() {
		double max = -1.0;
		for (int i = 0; i < source.size(); i++) {
			max = Math.max(max, source.get(i).getSum());
		}
		return max;
	}
	
	public double getMin() {
		double min = 200.0;
		for (int i = 0; i < source.size(); i++) {
			min = Math.min(min, source.get(i).getSum());
		}
		return min;
	}
	
	public ArrayList<Student> getMaxScore() {
		BigDecimal max = new BigDecimal(getMax());
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getSum());
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
			BigDecimal score = new BigDecimal(source.get(i).getSum());
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
			BigDecimal score = new BigDecimal(source.get(i).getSum());
			if (score.equals(avg.max(score))) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
	
	public ArrayList<Student> getLower() {
		BigDecimal avg = new BigDecimal(getAverage());
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getSum());
			if (score.equals(avg.min(score))) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
	
	public ArrayList<Student> suspectCheating() {
		BigDecimal avg = new BigDecimal(getAverage());
		BigDecimal std = new BigDecimal(stdscore);
		ArrayList<Student> res = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			BigDecimal score = new BigDecimal(source.get(i).getSum());
			BigDecimal homework = new BigDecimal(source.get(i).getHomework());
			BigDecimal experiment = new BigDecimal(source.get(i).getExperiment());
			if (score.equals(avg.max(score)) 
					&& (homework.equals(std.max(homework)) || experiment.equals(std.max(experiment)))) {
				res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()));
			}
		}
		return res;
	}
}
