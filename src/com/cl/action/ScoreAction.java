package com.cl.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Section;
import com.cl.dao.SectionScore;
import com.cl.util.SectionCalc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ScoreAction extends ActionSupport {
	private ArrayList<SectionScore> sectionscore = new ArrayList<>();
	private final String[] SectionColumns = {"学号", "学生姓名", "课堂听讲", "课堂回答问题", "考勤", "作业", "实验", "复习预习"};
	
	public ArrayList<SectionScore> getSectionscore() {
		return sectionscore;
	}

	public void setSectionscore(ArrayList<SectionScore> sectionscore) {
		this.sectionscore = sectionscore;
	}
	
	public String getScore() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = ((Integer)act.getSession().get("section_id")).intValue();
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 0; i < SectionColumns.length; i++)
			columns.add(SectionColumns[i]);
		SectionCalc sectioncalc = new SectionCalc(section_id);
		ArrayList<SectionScore> res = sectioncalc.getSource();
		String section_name = Section.getSectionBySectionId(section_id).getSection_name();
		
		for (int i = 0; i < res.size(); i++) {
			res.get(i).setListening(new BigDecimal(res.get(i).getListening()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			res.get(i).setAnswer(new BigDecimal(res.get(i).getAnswer()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			res.get(i).setAttendance(new BigDecimal(res.get(i).getAttendance()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			res.get(i).setHomework(new BigDecimal(res.get(i).getHomework()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			res.get(i).setExperiment(new BigDecimal(res.get(i).getExperiment()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			res.get(i).setReviewandpreview(new BigDecimal(res.get(i).getReviewandpreview()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		
		act.put("section_name", section_name);
		act.put("sectioncolumns", columns);
		act.put("scorearray", res);
		return SUCCESS;
	}
	
	public String updateScore() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = ((Integer)act.getSession().get("section_id")).intValue();
		ArrayList<SectionScore> sectionscore = getSectionscore();
		
		for (int i = 0; i < sectionscore.size(); i++) {
			sectionscore.get(i).setSection_id(section_id);
			SectionScore.updateSectionScoreList(sectionscore.get(i));
		}
		return SUCCESS;
	}
	
	public String updateHomeworkScore() throws Exception {
		ActionContext act = ActionContext.getContext();
		int homework_id = -1;
		if (act.get("homework_id") != null)
			homework_id = ((Integer)act.get("homework_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("homework_id") != null)
			homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		else
			homework_id = ((Integer)act.getSession().get("homework_id")).intValue();
		int section_id = (new Integer(ServletActionContext.getRequest().getParameter("section_id"))).intValue();
		ArrayList<SectionScore> sectionscore = getSectionscore();
		SectionCalc sectioncalc = new SectionCalc(section_id);
		ArrayList<SectionScore> res = sectioncalc.getSource();
		sectionscore.sort(new Comparator<SectionScore>() {
			public int compare(SectionScore a, SectionScore b) {
				return a.getStudent_id() - b.getStudent_id();
			}
		});
		res.sort(new Comparator<SectionScore>() {
			public int compare(SectionScore a, SectionScore b) {
				return a.getStudent_id() - b.getStudent_id();
			}
		});
		
		for (int i = 0, j = 0; j < sectionscore.size() && i < res.size(); i++) {
			if (res.get(i).getStudent_id() == sectionscore.get(j).getStudent_id()) {
				res.get(i).setHomework(sectionscore.get(j).getHomework());
				System.out.println(res.get(i).getSection_id() + " ");
				j++;
			}
		}
		
		for (int i = 0; i < res.size(); i++) {
			SectionScore.updateSectionScoreList(res.get(i));
		}
//		ServletActionContext.getRequest().setAttribute("homework_id", homework_id);
		act.getSession().put("homework_id", homework_id);
		return SUCCESS;
	}
}
