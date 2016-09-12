package com.cl.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.KnowledgeWeight;
import com.cl.dao.Section;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class WeightAction extends ActionSupport {
	private KnowledgeWeight knowledgeweight;
	private ArrayList<Section> section = new ArrayList<>();
	private final String[] Columns = {"课堂听讲", "课堂回答问题", "考勤", "作业", "实验", "复习预习", "总评"};
	
	public KnowledgeWeight getKnowledgeweight() {
		return knowledgeweight;
	}

	public void setKnowledgeweight(KnowledgeWeight knowledgeweight) {
		this.knowledgeweight = knowledgeweight;
	}
	
	public ArrayList<Section> getSection() {
		return section;
	}

	public void setSection(ArrayList<Section> section) {
		this.section = section;
	}

	public String getWeight() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = 0;
		if (act.get("section_id") != null)
			section_id = ((Integer)act.get("section_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("section_id") != null)
			section_id = (new Integer(ServletActionContext.getRequest().getParameter("section_id"))).intValue();
		else if (act.getSession().get("section_id") != null)
			section_id = ((Integer)act.getSession().get("section_id")).intValue();
		if (section_id != 0) {
			KnowledgeWeight kw = KnowledgeWeight.getKnowledgeWeightBySectionId(section_id);
			if (kw == null) {
				kw = new KnowledgeWeight();
				kw.setSection_id(section_id);
				kw.setListening_weight(0.18);
				kw.setAnswer_weight(0.24);
				kw.setAttendance_weight(0.18);
				kw.setExperiment_weight(0.2);
				kw.setHomework_weight(0.12);
				kw.setReviewandpreview_weight(0.08);
				KnowledgeWeight.addKnowledgeWeight(kw);
			}
			
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 0; i < Columns.length; i++)
				columns.add(Columns[i]);
			act.put("columns", columns);
			act.put("weightlist", kw);
			act.getSession().remove("event_id");
		}
		else {
			int course_id = ((Integer)act.getSession().get("course_id")).intValue();
			ArrayList<Section> res = Section.getSectionListByCourseId(course_id);
			
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 0; i < res.size(); i++)
				columns.add(Section.getSectionBySectionId(res.get(i).getSection_id()).getSection_name());
			act.put("columns", columns);
			act.getSession().put("weightlist", res);
			act.getSession().remove("section_id");
		}
		return SUCCESS;
	}
	
	public String updateWeight() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = 0;
		if (act.get("section_id") != null)
			section_id = ((Integer)act.get("section_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("section_id") != null)
			section_id = (new Integer(ServletActionContext.getRequest().getParameter("section_id"))).intValue();
		else if (act.getSession().get("section_id") != null)
			section_id = ((Integer)act.getSession().get("section_id")).intValue();
		if (section_id != 0) {
			KnowledgeWeight kw = getKnowledgeweight();
			kw.setSection_id(section_id);
			KnowledgeWeight.updateKnowledgeWeight(kw);
		}
		else {
			int course_id = ((Integer)act.getSession().get("course_id")).intValue();
			ArrayList<Section> former = (ArrayList<Section>)act.getSession().get("weightlist");
			ArrayList<Section> now = getSection();
			
			for (int i = 0; i < former.size(); i++) {
				now.get(i).setSection_id(former.get(i).getSection_id());
				now.get(i).setSection_name(former.get(i).getSection_name());
				now.get(i).setCourse_id(course_id);
				Section.updateSection(now.get(i));
			}
		}
		return SUCCESS;
	}
}
