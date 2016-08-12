package com.cl.action;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Course;
import com.cl.dao.CourseScore;
import com.cl.dao.CourseStudent;
import com.cl.dao.CourseTeacher;
import com.cl.dao.KnowledgeWeight;
import com.cl.dao.Section;
import com.cl.dao.SectionScore;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.cl.util.CourseCalc;
import com.cl.util.SectionCalc;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SectionAction extends ActionSupport {
	private Section section;
	private final String[] SectionColumns = {"学号", "学生姓名", "课堂听讲", "课堂回答问题", "考勤", "作业", "实验", "复习预习", "总评"};
		
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getSectionListFromCourse() throws Exception {
		ActionContext act = ActionContext.getContext();
		String role = (String)act.getSession().get("role");
		String username = (String)act.getSession().get("username");
		
		
		int course_id, class_id;
		if (act.get("course_id") != null)
			course_id = ((Integer)act.get("course_id"));
		else if (ServletActionContext.getRequest().getParameter("course_id") != null)
			course_id = new Integer(ServletActionContext.getRequest().getParameter("course_id")).intValue();
		else 
			course_id = ((Integer)act.getSession().get("course_id")).intValue();
		if (act.getSession().get("class_id") != null) {
			class_id = ((Integer)act.getSession().get("class_id")).intValue();
		}
		else if (role.equals("student")) {
			int user_id = Student.getStudentByLoginname(username).getId();
			class_id = CourseStudent.getCourseStudentByCourseIdAndStudentId(course_id, user_id).getClass_id();
		}
		else {
			int user_id = Teacher.getTeacherByLoginname(username).getId();
			class_id = CourseTeacher.getCourseTeacherByCourseIdAndTeacherId(course_id, user_id).getClass_id();
		}
		ArrayList<Section> res = Section.getSectionListByCourseId(course_id);
		
		act.put("sectionlist", res);
		act.getSession().put("class_id", class_id);
		act.getSession().put("course_id", course_id);
		act.getSession().remove("section_id");
		act.getSession().remove("event_id");
		return SUCCESS;
	}
	
	public String deleteSection() throws Exception {
		int section_id = (new Integer(ServletActionContext.getRequest().getParameter("section_id"))).intValue();
		Section.deleteSectionBySectionId(section_id);
		return SUCCESS;
	}
	
	public String addSection() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		Section sec = getSection();
		sec.setCourse_id(course_id);
		sec.setSection_weight(0);
		Section.addSection(sec);
		return SUCCESS;
	}
	
	public String getSectionSummaryForTeacher() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = ((Integer)act.getSession().get("section_id")).intValue();
		if (section_id != 0) {
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 0; i < SectionColumns.length; i++)
				columns.add(SectionColumns[i]);
			SectionCalc sectioncalc = new SectionCalc(section_id, -1);
			ArrayList<SectionScore> res = sectioncalc.getAl();
			for (int i = 0; i < res.size(); i++) {
				res.get(i).setListening(new BigDecimal(res.get(i).getListening()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setAnswer(new BigDecimal(res.get(i).getAnswer()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setAttendance(new BigDecimal(res.get(i).getAttendance()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setHomework(new BigDecimal(res.get(i).getHomework()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setExperiment(new BigDecimal(res.get(i).getExperiment()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setReviewandpreview(new BigDecimal(res.get(i).getReviewandpreview()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setSum(new BigDecimal(res.get(i).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			
			act.put("sectioncolumns", columns);
			act.put("scorearray", res);
			act.put("average", new BigDecimal(sectioncalc.getAverage()));
			act.put("maxscore", sectioncalc.getMaxScore());
			act.put("minscore", sectioncalc.getMinScore());
			act.put("higher", sectioncalc.getHigher());
			act.put("lower", sectioncalc.getLower());
			act.put("cheating", sectioncalc.suspectCheating());
		}
		else {
			int course_id = ((Integer)act.getSession().get("course_id")).intValue();
			CourseCalc coursecalc = new CourseCalc(course_id);
			ArrayList<Section> section = Section.getSectionList(course_id);
			ArrayList<String> columns = new ArrayList<>();
			columns.add("学号");
			columns.add("姓名");
			for (int i = 0; i < section.size(); i++)
				columns.add(section.get(i).getSection_name());
			columns.add("总评");
			ArrayList<CourseScore> res = coursecalc.getAl();
			for (int i = 0; i < res.size(); i++) {
				for (int j = 0; j < res.get(i).getSectionscore().size(); j++) {
					res.get(i).getSectionscore().get(j).setSum(new BigDecimal(res.get(i).getSectionscore().get(j).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
			}
			
			act.put("sectioncolumns", columns);
			act.put("scorearray", res);
			act.put("average", new BigDecimal(coursecalc.getAverage()));
			act.put("maxscore", coursecalc.getMaxScore());
			act.put("minscore", coursecalc.getMinScore());
			act.put("higher", coursecalc.getHigher());
			act.put("lower", coursecalc.getLower());
			act.put("cheating", coursecalc.suspectCheating());
			act.put("coreproblem", coursecalc.getCorePrblem());
		}
		return SUCCESS;
	}
	
	public String getSectionSummaryForStudent() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id = ((Integer)act.getSession().get("section_id")).intValue();
		String loginname = (String)act.getSession().get("username");
		String section_name = (section_id == 0 ? "课程总结" : Section.getSectionBySectionId(section_id).getSection_name());
		int student_id = Student.getStudentByLoginname(loginname).getId();
		if (section_id != 0) {
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 2; i < SectionColumns.length; i++)
				columns.add(SectionColumns[i]);
			ArrayList<SectionScore> res = SectionScore.getSectionScoreListBySectionId(section_id, student_id);
			for (int i = 0; i < res.size(); i++) {
				res.get(i).setListening(new BigDecimal(res.get(i).getListening()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setAnswer(new BigDecimal(res.get(i).getAnswer()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setAttendance(new BigDecimal(res.get(i).getAttendance()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setHomework(new BigDecimal(res.get(i).getHomework()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setExperiment(new BigDecimal(res.get(i).getExperiment()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setReviewandpreview(new BigDecimal(res.get(i).getReviewandpreview()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				res.get(i).setSum(new BigDecimal(res.get(i).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			
			act.put("sectioncolumns", columns);
			act.put("scorearray", res);
		}
		else {
			int course_id = ((Integer)act.getSession().get("course_id")).intValue();
			ArrayList<Section> section = Section.getSectionListByCourseId(course_id);
			ArrayList<String> columns = new ArrayList<>();
			for (int i = 0; i < section.size(); i++)
				columns.add(section.get(i).getSection_name());
			columns.add("总评");
			
			ArrayList<SectionScore> tmp = SectionScore.getSectionScoreListByStudentIdAndCourseId(student_id, course_id);
			ArrayList<CourseScore> res = new ArrayList<>();
			CourseScore e = new CourseScore();
			e.setCourse_id(course_id);
			e.setStudent_id(student_id);
			e.setSectionscore(tmp);
			res.add(e);
			
			for (int i = 0;  i < res.size(); i++) {
				for (int j = 0; j < res.get(i).getSectionscore().size(); j++) {
					res.get(i).getSectionscore().get(j).setSum(new BigDecimal(res.get(i).getSectionscore().get(j).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
			}
			
			act.put("sectioncolumns", columns);
			act.put("scorearray", res);
		}
		act.put("section_name", section_name);
		if (act.getSession().get("event_id") != null)
			act.getSession().remove("event_id");
		return SUCCESS;
	}
}
