package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.cl.dbquery.DBHelper;
import com.cl.util.DateFormator;

public class Event {
	private int event_id;
	private int class_id;
	private int section_id;
	private String event_content;
	private String event_type;
	private String starttime;
	private String endtime;
	
	public int getEvent_id() {
		return event_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getSection_id() {
		return section_id;
	}
	
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	
	public String getEvent_content() {
		return event_content;
	}
	
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	
	public String getEvent_type() {
		return event_type;
	}
	
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public static Event getInstance() {
		return new Event();
	}
	
	public static Event getEventByEventId(int event_id) {
		String sql = "select * from event where event_id = " + event_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		Event event = null;
		try {
			if (rs.next()) {
				event = Event.getInstance();
				int section_id = rs.getInt("section_id");
				int class_id = rs.getInt("class_id");
				String event_content = rs.getString("event_content");
				String event_type = rs.getString("event_type");
				String starttime = rs.getString("starttime");
				String endtime = rs.getString("endtime");
				
				event.setEvent_id(event_id);
				event.setClass_id(class_id);;
				event.setSection_id(section_id);
				event.setEvent_content(event_content);
				event.setEvent_type(event_type);
				event.setStarttime(starttime);
				event.setEndtime(endtime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return event;
	}
	
	public static ArrayList<Event> getEventListBySectionId(int section_id) {
		ArrayList<Event> res = new ArrayList<>();
		String sql = "select * from event where section_id = " + section_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int event_id = rs.getInt("event_id");
				int class_id = rs.getInt("class_id");
				String event_content = rs.getString("event_content");
				String event_type = rs.getString("event_type");
				String starttime = rs.getString("starttime");
				String endtime = rs.getString("endtime");
				
				Event event = Event.getInstance();
				event.setEvent_id(event_id);
				event.setClass_id(class_id);
				event.setSection_id(section_id);
				event.setEvent_content(event_content);
				event.setEvent_type(event_type);
				event.setStarttime(starttime);
				event.setEndtime(endtime);
				
				res.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addEvent(Event event) {
		String sql = "insert into event(class_id, section_id, event_content, event_type, starttime, endtime) "
				+ "values(" + event.getClass_id() + ", " + event.getSection_id() + ", '" + event.getEvent_content() 
				+ "', '" + event.getEvent_type() + "', '" + event.getStarttime() + "', '" + event.getEndtime() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
//	"bbs"*, "learningstatus"*, "event"	
	public static void deleteEventByEventId(int id) {
		BBS.deleteBBSByEventId(id);
		LearningStatus.deleteLearningStatusByEventId(id);
		
    	Connection con = DBHelper.getConnection();
		String sql = "delete from event where event_id=" + id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
	
	public static void deleteEventBySectionId(int section_id) {
		Connection con = DBHelper.getConnection();
		String sql = "select distinct event_id from event where section_id=" + section_id + ";";
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int event_id = rs.getInt("event_id");
				BBS.deleteBBSByEventId(event_id);
				LearningStatus.deleteLearningStatusByEventId(event_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "delete from event where section_id=" + section_id + ";";
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
