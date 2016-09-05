package com.cl.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import com.cl.dao.CourseScore;
import com.cl.dao.Section;
import com.cl.dao.SectionScore;
import com.cl.dao.Student;

public class CourseCalc {
	private ArrayList<CourseScore> source;
	
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
			if (!new BigDecimal(source.get(i).getAverage()).equals(-1)) {
				res = -1;
				break;
			}
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
	
	public ArrayList<String> getMaxScore() {
		BigDecimal max = new BigDecimal(getMax());
		ArrayList<String> res = new ArrayList<>();
		if (!max.equals(-1)) {
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
		if (!min.equals(-1)) {
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
		if (!avg.equals(-1)) {
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
		if (!averge.equals(-1)) {
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
					System.out.println("Student: " + source.get(i).getStudent_id());
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
		for (int i = 0; i < source.size(); i++) {
			ArrayList<SectionScore> data = source.get(i).getSectionscore();
			for (int j = 0; j < data.size(); j++) {
				
			}
		}
		return res;
	}
	
	public ArrayList<String> getCorePrblem() {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> data = this.getLower();
		HashMap<String, Integer> hash = new HashMap<>();
		boolean isNumb = true;
		for (int i = 0, size = 0; i < data.size(); i++) {
			if (isNumb) {
				size = new Integer(data.get(i++)).intValue();
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
