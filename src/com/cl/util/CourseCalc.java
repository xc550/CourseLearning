package com.cl.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.cl.dao.CourseScore;
import com.cl.dao.Section;
import com.cl.dao.SectionScore;
import com.cl.dao.Student;

public class CourseCalc {
	private ArrayList<CourseScore> source;
	private final BigDecimal inv = new BigDecimal(-1);
	
	public CourseCalc(int course_id) {
		source = CourseScore.getCourseScoreList(course_id);
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
			if (new BigDecimal(source.get(i).getAverage()).equals(inv))
				return -1;
			res += source.get(i).getAverage();
		}
		if (source.size() > 0)
			res /= source.size();
		return res;
	}
	
	public double getMax() {
		BigDecimal max = new BigDecimal(-1);
		for (int i = 0; i < source.size(); i++) {
			BigDecimal val = new BigDecimal(source.get(i).getAverage());
			if (!max.equals(max.max(val)))
				max = val;
		}
		return max.doubleValue();
	}
	
	public double getMin() {
		BigDecimal min = new BigDecimal(200);
		for (int i = 0; i < source.size(); i++) {
			BigDecimal val = new BigDecimal(source.get(i).getAverage());
			if (!min.equals(min.min(val)))
				min = val;
		}
		return min.doubleValue();
	}
	
	public ArrayList<String> getMaxScore() {
		BigDecimal max = new BigDecimal(getMax());
		ArrayList<String> res = new ArrayList<>();
		if (!max.equals(inv)) {
			for (int i = 0; i < source.size(); i++) {
				BigDecimal score = new BigDecimal(source.get(i).getAverage());
				if (max.equals(score)) {
					res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()).getName());
				}
			}
		}
		return res;
	}
	
	public ArrayList<String> getMinScore() {
		BigDecimal min = new BigDecimal(getMin());
		ArrayList<String> res = new ArrayList<>();
		if (!min.equals(inv)) {
			for (int i = 0; i < source.size(); i++) {
				BigDecimal score = new BigDecimal(source.get(i).getAverage());
				if (min.equals(score)) {
					res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()).getName());
				}
			}
		}
		return res;
	}
	
	public ArrayList<String> getHigher() {
		BigDecimal avg = new BigDecimal(getAverage());
		ArrayList<String> res = new ArrayList<>();
		if (!avg.equals(inv)) {
			for (int i = 0; i < source.size(); i++) {
				BigDecimal score = new BigDecimal(source.get(i).getAverage());
				if (score.equals(avg.max(score))) {
					res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()).getName());
				}
			}
		}
		return res;
	}
	
	private ArrayList<String> getProblemInLower(ArrayList<SectionScore> avg, ArrayList<Section> weight, ArrayList<SectionScore> res) {
		ArrayList<String> sec = new ArrayList<>();
		BigDecimal maxInfl = new BigDecimal(-(0x3f3f3f3f));
		res.sort(new Comparator<SectionScore>() {
			public int compare(SectionScore a, SectionScore b) {
				return a.getSection_id() - b.getSection_id();
			}
		});
		if (res.size() == avg.size()) {
			for (int i = 0; i < res.size(); i++) {
				if (avg.get(i).getSection_id() == res.get(i).getSection_id()) {
					BigDecimal infl = new BigDecimal((avg.get(i).getSum() - res.get(i).getSum()) * weight.get(i).getSection_weight());
					String section_name = Section.getSectionBySectionId(avg.get(i).getSection_id()).getSection_name();
					if (infl.equals(maxInfl)) {
						sec.add(section_name);
					}
					else if (infl.equals(maxInfl.max(infl))) {
						maxInfl = infl;
						sec.clear();
						sec.add(section_name);
					}
				}
				else {
					return null;
				}
			}
		}
		return sec;
	}
	
	public ArrayList<String> getLower() {
		BigDecimal averge = new BigDecimal(getAverage());
		ArrayList<String> res = new ArrayList<>();
		if (!averge.equals(inv)) {
//			计算章节j的平均学习价值
			ArrayList<SectionScore> avg = new ArrayList<>();
			ArrayList<Section> weight = new ArrayList<>();
			for (int i = 0; i < source.size(); i++) {
				ArrayList<SectionScore> stu = source.get(i).getSectionscore(); 
				for (int j = 0; j < stu.size(); j++) {
					int section_id = stu.get(j).getSection_id();
					int index = -1;
					for (int k = 0; k < avg.size(); k++) {
						if (avg.get(k).getSection_id() == section_id) {
							index = k;
							break;
						}
					}
					if (index == -1) {
						SectionScore e = new SectionScore();
						e.setSection_id(section_id);
						e.setSum(0);
						weight.add(Section.getSectionBySectionId(section_id));
						avg.add(e);
						index = avg.size() - 1;
					}
					avg.get(index).setSum(avg.get(index).getSum() + stu.get(j).getSum());
				}
			}
			avg.sort(new Comparator<SectionScore>() {
				public int compare(SectionScore a, SectionScore b) {
					return a.getSection_id() - b.getSection_id();
				}
			});
			
			weight.sort(new Comparator<Section>() {
				public int compare(Section a, Section b) {
					return a.getSection_id() - b.getSection_id();
				}
			});
			
			for (int i = 0; i < source.size(); i++) {
				BigDecimal score = new BigDecimal(source.get(i).getAverage());
				if (score.equals(averge.min(score))) {
					ArrayList<String> sec = getProblemInLower(avg, weight, source.get(i).getSectionscore());
//					System.out.println("Student: " + source.get(i).getStudent_id());
					if (sec != null) {
						res.add(new Integer(sec.size()).toString());
						res.add(Student.getStudentByStudentId(source.get(i).getStudent_id()).getName());
						res.addAll(sec);
					}
				}
			}
		}
		return res;
	}
	
	public ArrayList<String> suspectCheating() {
		ArrayList<String> res = new ArrayList<>();
		HashMap<Integer, BigDecimal> hm = new HashMap<>();
		HashMap<Integer, Integer> co = new HashMap<>();
		HashMap<Integer, Integer> stu = new HashMap<>();
		for (int i = 0; i < source.size(); i++) {
			ArrayList<SectionScore> data = source.get(i).getSectionscore();
			for (int j = 0; j < data.size(); j++) {
				int section_id = data.get(j).getSection_id();
				if (hm.containsKey(section_id)) {
					hm.put(section_id, new BigDecimal(hm.get(section_id).doubleValue() + data.get(j).getSum()));
					co.put(section_id, co.get(section_id) + 1);
				}
				else {
					hm.put(section_id, new BigDecimal(data.get(j).getSum()));
					co.put(section_id, 1);
				}
			}
		}
		
		for (int i = 0; i < source.size(); i++) {
			int student_id = source.get(i).getStudent_id();
			ArrayList<SectionScore> data = source.get(i).getSectionscore();
			for (int j = 0; j < data.size(); j++) {
				int section_id = data.get(j).getSection_id();
				BigDecimal score = new BigDecimal(data.get(j).getSum());
				BigDecimal avg = hm.get(section_id).divide(new BigDecimal(co.get(section_id).intValue()));
				BigDecimal homework = new BigDecimal(data.get(j).getHomework());
				BigDecimal experiment = new BigDecimal(data.get(j).getExperiment());
				BigDecimal std = new BigDecimal(89.0);
				if (score.equals(avg.max(score)) 
						&& (homework.equals(std.max(homework)) || experiment.equals(std.max(experiment)))) {
					if (stu.containsKey(student_id))
						stu.put(student_id, stu.get(student_id) + 1);
					else
						stu.put(student_id, 1);
				}
			}
		}
		
		int maxcheating = -1;
		for (int i = 0; i < source.size(); i++) {
			int student_id = source.get(i).getStudent_id();
			if (stu.containsKey(student_id)) {
				int cheating = stu.get(student_id);
				if (cheating > maxcheating) {
					res.clear();
					res.add(stu.get(student_id).toString());
					res.add(Student.getStudentByStudentId(student_id).getName());
				}
				else if (cheating == maxcheating) {
					res.add(Student.getStudentByStudentId(student_id).getName());
				}
			}
		}
		return res;
	}
	
	public ArrayList<String> getCorePrblem() {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> data = this.getLower();
		HashMap<String, Integer> hash = new HashMap<>();
		boolean isNumb = true;
		for (int i = 0, size = 0; i < data.size();) {
			if (isNumb) {
				size = new Integer(data.get(i++)).intValue();
				i++;
				isNumb = false;
			}
			else {
				for (int j = 0; j < size; j++, i++) {
					if (hash.containsKey(data.get(i))) {
						hash.put(data.get(i), hash.get(data.get(i)) + 1);
					}
					else {
						res.add(data.get(i));
						hash.put(data.get(i), 1);
					}
				}
				isNumb = true;
			}
		}
		res.sort(new Comparator<String>() {
			public int compare(String a, String b) {
				return hash.get(a).intValue() - hash.get(b).intValue();
			}
		});
		int co = 3, ind = 0;
		for (int i = 1; i < res.size() && co > 0; i++) {
			if (!hash.get(res.get(i)).equals(hash.get(ind))) {
				ind = i;
				co--;
			}
		}
		if (co == 0) {
			for (int i = ind; i < res.size(); i++)
				res.remove(i);
		}
		return res;
	}
}
