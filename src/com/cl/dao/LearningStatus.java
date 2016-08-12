package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.Pie;
import com.cl.entity.PiePart;

public class LearningStatus {
	private int learninstatus_id;
	private int class_id;
	private int student_id;
	private int event_id;
	private int classtime;
	private int inclass;
	private int outclass;
	private String method;
	private static final String[] classtimetostring = {"较短", "正好", "较长"};
	private static final String[] classtostring = {"理解", "模糊", "不懂"};
	
	public int getLearninstatus_id() {
		return learninstatus_id;
	}
	
	public void setLearninstatus_id(int learninstatus_id) {
		this.learninstatus_id = learninstatus_id;
	}
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getStudent_id() {
		return student_id;
	}
	
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	public int getEvent_id() {
		return event_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public int getClasstime() {
		return classtime;
	}
	
	public void setClasstime(int classtime) {
		this.classtime = classtime;
	}
	
	public int getInclass() {
		return inclass;
	}
	
	public void setInclass(int inclass) {
		this.inclass = inclass;
	}
	
	public int getOutclass() {
		return outclass;
	}
	
	public void setOutclass(int outclass) {
		this.outclass = outclass;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public static String[] getClasstimetostring() {
		return classtimetostring;
	}

	public static String[] getClasstostring() {
		return classtostring;
	}

	public static String getClassTimeString(int classtime) {
		if (classtime >= 3)
			return "Error";
		return classtimetostring[classtime];
	}
	
	public static String getClassString(int classnumber) {
		if (classnumber >= 3)
			return "Error";
		return classtostring[classnumber];
	}
	
	public static LearningStatus getInstance() {
		return new LearningStatus();
	}
	
	public static LearningStatus getLearingStatusListByStudentIdAndEventId(int student_id, int event_id) {
		Connection con = DBHelper.getConnection();
		String sql = "select * from learningstatus where student_id = " + student_id + " and event_id=" + event_id + ";";
		LearningStatus ls = null;
		ResultSet rs= DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int learninstatus_id = rs.getInt("learningstatus_id");
				int class_id = rs.getInt("class_id");
				int classtime = rs.getInt("classtime");
				int inclass = rs.getInt("inclass");
				int outclass = rs.getInt("outclass");
				String method = rs.getString("method");
				
				ls = new LearningStatus();
				ls.setLearninstatus_id(learninstatus_id);
				ls.setClass_id(class_id);
				ls.setStudent_id(student_id);
				ls.setEvent_id(event_id);
				ls.setClasstime(classtime);
				ls.setInclass(inclass);
				ls.setOutclass(outclass);
				ls.setMethod(method);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DBHelper.closeConnection(con);
		return ls;
	}
	
	public static ArrayList<LearningStatus> getLearingStatusListByEventId(int event_id) {
		ArrayList<LearningStatus> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		String sql = "select * from learningstatus where event_id = " + event_id + ";";
		ResultSet rs= DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				int learninstatus_id = rs.getInt("learningstatus_id");
				int class_id = rs.getInt("class_id");
				int student_id = rs.getInt("student_id");
				int classtime = rs.getInt("classtime");
				int inclass = rs.getInt("inclass");
				int outclass = rs.getInt("outclass");
				String method = rs.getString("method");
				
				LearningStatus ls = new LearningStatus();
				ls.setLearninstatus_id(learninstatus_id);
				ls.setClass_id(class_id);
				ls.setStudent_id(student_id);
				ls.setEvent_id(event_id);
				ls.setClasstime(classtime);
				ls.setInclass(inclass);
				ls.setOutclass(outclass);
				ls.setMethod(method);
				
				res.add(ls);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static Pie getPieByPieName(String piename, ArrayList<LearningStatus> ls) {
		if (piename.equals("classtime")) {
			return LearningStatus.getPieOfClassTime(ls);
		}
		else if (piename.equals("inclass")) {
			return LearningStatus.getPieOfInClass(ls);
		}
		else if (piename.equals("outclass")) {
			return LearningStatus.getPieOfOutClass(ls);
		}
		return null;
	}
	
//	getPieOfClassTime(), getPieOfInClass() and getPieOfOutClass() need to restructure
	
	public static Pie getPieOfClassTime(ArrayList<LearningStatus> ls) {
		Pie pie = new Pie();
		pie.setPiename("上课时间");
		PiePart low = new PiePart();
		PiePart middle = new PiePart();
		PiePart high = new PiePart();
		
		low.setPartname(LearningStatus.getClassTimeString(0));
		middle.setPartname(LearningStatus.getClassTimeString(1));
		high.setPartname(LearningStatus.getClassTimeString(2));
		
		low.setPartnumb(0);
		middle.setPartnumb(0);
		high.setPartnumb(0);
		
		ArrayList<PiePart> res = new ArrayList<>();
		res.add(low);
		res.add(middle);
		res.add(high);
		
		
		for (int i = 0; i < ls.size(); i++) {
			int classtime = ls.get(i).getClasstime();
			int partnumb = res.get(classtime).getPartnumb();
			res.get(classtime).setPartnumb(partnumb + 1);
		}
		
		pie.setParts(res);
		return pie;
	}
	
	public static Pie getPieOfInClass(ArrayList<LearningStatus> ls) {
		Pie pie = new Pie();
		pie.setPiename("课内教学情况");
		PiePart low = new PiePart();
		PiePart middle = new PiePart();
		PiePart high = new PiePart();
		
		low.setPartname(LearningStatus.getClassString(0));
		middle.setPartname(LearningStatus.getClassString(1));
		high.setPartname(LearningStatus.getClassString(2));
		
		low.setPartnumb(0);
		middle.setPartnumb(0);
		high.setPartnumb(0);
		
		ArrayList<PiePart> res = new ArrayList<>();
		res.add(low);
		res.add(middle);
		res.add(high);
		
		for (int i = 0; i < ls.size(); i++) {
			int classtime = ls.get(i).getInclass();
			int partnumb = res.get(classtime).getPartnumb();
			res.get(classtime).setPartnumb(partnumb + 1);
		}
		
		pie.setParts(res);
		return pie;
	}
	
	public static Pie getPieOfOutClass(ArrayList<LearningStatus> ls) {
		Pie pie = new Pie();
		pie.setPiename("课外教学情况");
		PiePart low = new PiePart();
		PiePart middle = new PiePart();
		PiePart high = new PiePart();
		
		low.setPartname(LearningStatus.getClassString(0));
		middle.setPartname(LearningStatus.getClassString(1));
		high.setPartname(LearningStatus.getClassString(2));
		
		low.setPartnumb(0);
		middle.setPartnumb(0);
		high.setPartnumb(0);
		
		ArrayList<PiePart> res = new ArrayList<>();
		res.add(low);
		res.add(middle);
		res.add(high);
		
		for (int i = 0; i < ls.size(); i++) {
			int classtime = ls.get(i).getOutclass();
			int partnumb = res.get(classtime).getPartnumb();
			res.get(classtime).setPartnumb(partnumb + 1);
		}
		
		pie.setParts(res);
		return pie;
	}
	
	public static void addLeanringStatus(LearningStatus ls) {
		String sql = "insert into learningstatus(student_id, event_id, classtime, inclass, outclass, method)"
				+ " values(" + ls.getStudent_id() + ", " + ls.getEvent_id() + ", " + ls.getClasstime()
				+ ", " + ls.getInclass() + ", " + ls.getOutclass() + ",'" + ls.getMethod() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}

	public static void deleteLearningStatusByStudentId(int student_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from learningstatus where student_id=" + student_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
	
	public static void deleteLearningStatusByEventId(int event_id) {
		Connection con = DBHelper.getConnection();
		String sql = "delete from learningstatus where event_id=" + event_id + ";";
		DBHelper.execUpdate(con, sql);
    	DBHelper.closeConnection(con);
	}
}
