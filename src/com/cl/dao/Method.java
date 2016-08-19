package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;

public class Method {
	private int method_id;
	private String method_name;
	private String method_path;
	private String method_comment;
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getMehtod_path() {
		return method_path;
	}
	public void setMehtod_path(String method_path) {
		this.method_path = method_path;
	}
	public String getMethod_comment() {
		return method_comment;
	}
	public void setMethod_comment(String method_comment) {
		this.method_comment = method_comment;
	}
	
	public static Method getInstance() {
		return new Method();
	}
	
	public static Method getMethodByMethodId(int method_id) {
		Method m = null;
		String sql = "select * from method where method_id=" + method_id + ";";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next()) {
				m = getInstance();
				String method_name = rs.getString("method_name");
				String method_path = rs.getString("method_path");
				String method_comment = rs.getString("method_comment");
				
				m.setMehtod_path(method_path);;
				m.setMethod_id(method_id);;
				m.setMethod_name(method_name);
				m.setMethod_comment(method_comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return m;
	}
	
	public static ArrayList<Method> getMethodList() {
		String sql = "select * from method;";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		ArrayList<Method> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Method m = getInstance();
				int method_id = rs.getInt("method_id");
				String method_name = rs.getString("method_name");
				String method_path = rs.getString("method_path");
				String method_comment = rs.getString("method_comment");
				
				m.setMehtod_path(method_path);;
				m.setMethod_id(method_id);;
				m.setMethod_name(method_name);
				m.setMethod_comment(method_comment);
				res.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addMethod(Method m) {
		String sql = "insert into method(method_name, method_path, method_comment) values('" + m.getMethod_name() 
				+ "', '" + m.getMehtod_path() + "', '" + m.getMethod_comment() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteMethodByMethodId(int method_id) {
		String sql = "delete from method where method_id=" + method_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
}
