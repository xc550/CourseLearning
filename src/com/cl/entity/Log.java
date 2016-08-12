package com.cl.entity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.cl.util.DateFormator;
import com.cl.util.FileFunc;

public class Log {
	private int log_id;
	private int user_id;
	private String user_name;
	private String user_gender;
	private String action;
	private Calendar time;
	private int course_id;
	private int class_id;
	private String course_name;
	private int section_id;
	private String section_name;
	private int event_id;
	private String event_name;
	private String event_type;
	
	public int getLog_id() {
		return log_id;
	}
	
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public Calendar getTime() {
		return time;
	}
	
	public void setTime(Calendar time) {
		this.time = time;
	}
	
	public int getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getCourse_name() {
		return course_name;
	}
	
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	public int getSection_id() {
		return section_id;
	}
	
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	
	public String getSection_name() {
		return section_name;
	}
	
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	
	public int getEvent_id() {
		return event_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public String getEvent_name() {
		return event_name;
	}
	
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	
	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	public String getLog() {
		String log = this.getLog_id() + "\t" + this.getUser_id() + "\t" + this.getUser_name()
		+ "\t" + this.getUser_gender() + "\t" + DateFormator.getDateCalendarToString(this.getTime())
		+ "\t" + this.getAction() + "\t" + this.getCourse_id() + "\t" + this.getClass_id() 
		+ "\t"+ this.getCourse_name() + "\t" + this.getSection_id() + "\t" + this.getSection_name() 
		+ "\t" + this.getEvent_id() + "\t" + this.getEvent_name() + "\n";
		return log;
	}
	
	public void show() {
		System.out.println(this.getLog_id() + "\t" + this.getUser_id() + "\t" + this.getUser_name()
					+ "\t" + this.getUser_gender() + "\t" + DateFormator.getDateCalendarToString(this.getTime())
					+ "\t" + this.getAction() + "\t" + this.getCourse_id() + "\t" + this.getClass_id() 
					+ "\t" + this.getCourse_name() + "\t" + this.getSection_id() + "\t" + this.getSection_name() 
					+ "\t" + this.getEvent_id() + "\t" + this.getEvent_name());
	}
	
	public static void saveLog(String savepath, Log log) {
		String datetime = (String) new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String path = savepath + File.separator + datetime + "_" + log.getUser_id() + "_" + log.getLog_id() + ".txt";
		String content = log.getLog();
		FileFunc.writeFile(path, content);
	}
	
	public static String[] getLogList(String path) {
		File file = new File(path);
		return file.list();
	}
	
	public static void deleteLog(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
	}
}
