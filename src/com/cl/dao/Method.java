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
	
	public static ArrayList<Method> getMethodList() {
		String sql = "select * from method;";
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		ArrayList<Method> res = new ArrayList<>();
		try {
			while (rs.next()) {
				Method m = new Method();
				int method_id = rs.getInt("method_id");
				String method_name = rs.getString("method_name");
				String method_path = rs.getString("method_name");
				
				m.setMehtod_path(method_path);;
				m.setMethod_id(method_id);;
				m.setMethod_name(method_name);
				res.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
}
