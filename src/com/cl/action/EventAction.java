package com.cl.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.BBS;
import com.cl.dao.Event;
import com.cl.dao.LearningStatus;
import com.cl.dao.Section;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EventAction extends ActionSupport {
	private Event event;
	private final String[] LearnintStatusColumns = {"学号", "学生姓名", "上课时间", "课内教学", "课外教学", "方法"};

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getEventInfoForTeacher() throws Exception {
		ActionContext act = ActionContext.getContext();
		int event_id;
		if (act.get("event_id") != null)
			event_id = ((Integer)act.get("event_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("event_id") != null)
			event_id = new Integer(ServletActionContext.getRequest().getParameter("event_id")).intValue();
		else
			event_id = ((Integer)act.getSession().get("event_id")).intValue();
		
		ArrayList<String> columns = new ArrayList<>();
		for (int i = 0; i < LearnintStatusColumns.length; i++)
			columns.add(LearnintStatusColumns[i]);
		
//		get student learning status
		ArrayList<LearningStatus> ls = LearningStatus.getLearingStatusListByEventId(event_id);
		ArrayList<Student> stu = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			Student s = Student.getStudentByStudentId(ls.get(i).getStudent_id());
			stu.add(s);
		}
		
//		get student bbs
		ArrayList<BBS> bbs = BBS.getBBSListByEventId(event_id);
		
		for (int i = 0; i < bbs.size(); i++) {
			String username = "null";
			if (bbs.get(i).getStudent_id() != -1)
				username = Student.getStudentByStudentId(bbs.get(i).getStudent_id()).getName();
			else if (bbs.get(i).getTeacher_id() != -1)
				username = Teacher.getTeacherByTeacherId(bbs.get(i).getTeacher_id()).getName();
			bbs.get(i).setUsername(username);
			if (bbs.get(i).getReply_id() != -1) {
//				回复bbs应只在该事件所有bbs之中
				boolean found = false;
				for (int j = 0; j < bbs.size(); j++) {
					if (bbs.get(i).getReply_id() == bbs.get(j).getBbs_id()) {
						bbs.get(i).setReply_id(j);
						found = true;
					}
				}
//				未找到回复对应的bbs，设置未回复
				if (!found) {
					bbs.get(i).setReply_id(-1);
				}
			}
		}
		
		Event eve = Event.getEventByEventId(event_id);
		
		act.getSession().put("event_id", new Integer(event_id));
		act.put("learningstatuscolumns", columns);
		act.getSession().put("learningstatus", ls);
		act.put("student", stu);
		act.put("bbs", bbs);
		act.put("event", eve);
		return SUCCESS;
	}
	
	public String getEventInfoForStudent() throws Exception {
		ActionContext act = ActionContext.getContext();
		int event_id;
		if (act.get("event_id") != null)
			event_id = ((Integer)act.get("event_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("event_id") != null)
			event_id = new Integer(ServletActionContext.getRequest().getParameter("event_id")).intValue();
		else
			event_id = ((Integer)act.getSession().get("event_id")).intValue();
				
		String loginname = (String)act.getSession().get("username");
		int student_id = Student.getStudentByLoginname(loginname).getId();

		ArrayList<String> columns = new ArrayList<>();
		for (int i = 2; i < LearnintStatusColumns.length; i++)
			columns.add(LearnintStatusColumns[i]);
		
//		get student learning status
		LearningStatus ls = LearningStatus.getLearingStatusListByStudentIdAndEventId(student_id, event_id);
		
//		get student bbs
		ArrayList<BBS> bbs = BBS.getBBSListByEventId(event_id);
		
		Event eve = Event.getEventByEventId(event_id);
		
		act.getSession().put("event_id", new Integer(event_id));
		act.put("learningstatuscolumns", columns);
		act.put("learningstatus", ls);
		act.put("bbs", bbs);
		act.put("event", eve);
		return SUCCESS;
	}
	
	public String saveEvent() throws Exception {
		ActionContext act = ActionContext.getContext();
		Event eve = getEvent();
		int section_id = ((Integer)act.getSession().get("section_id")).intValue();
		int class_id = ((Integer)act.getSession().get("class_id")).intValue();
		eve.setClass_id(class_id);
		eve.setSection_id(section_id);
		String datetime = (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		eve.setStarttime(datetime);
		Event.addEvent(eve);
		return SUCCESS;
	}
	
	public String deleteEvent() throws Exception {
		int event_id = new Integer(ServletActionContext.getRequest().getParameter("event_id")).intValue();
		BBS.deleteBBSByEventId(event_id);
		Event.deleteEventByEventId(event_id);
		return SUCCESS;
	}
	
	public String getEventListFromSection() throws Exception {
		ActionContext act = ActionContext.getContext();
		int section_id;
		if (act.get("section_id") != null)
			section_id = ((Integer)act.get("section_id")).intValue();
		else if (act.getSession().get("section_id") != null)
			section_id = ((Integer)act.getSession().get("section_id")).intValue();
		else
			section_id = new Integer(ServletActionContext.getRequest().getParameter("section_id")).intValue();
		
		ArrayList<Event> res = new ArrayList<>();
		String section_name;
		if (section_id > 0) {
			res = Event.getEventListBySectionId(section_id);
			section_name = Section.getSectionBySectionId(section_id).getSection_name();
		}
		else {
			section_name = "总结";
		}
		
		act.put("eventlist", res);
		act.put("section_name", section_name);
		act.getSession().put("section_id", new Integer(section_id));
		return SUCCESS;
	}
}
