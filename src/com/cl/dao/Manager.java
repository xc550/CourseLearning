package com.cl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cl.dbquery.DBHelper;
import com.cl.entity.User;

public class Manager extends User {
	public static Manager getInstance() {
		return new Manager();
	}
	
	public static boolean check(String loginname, String password) {
		String sql = "select * from manager where manager_loginname='" + loginname + "' and manager_password='" + password + "';";
		boolean checked = false;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			if (rs.next())
				checked = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return checked;
	}
	
	public static Manager getManagerByLoginname(String loginname) {
		String sql = "select * from manager where manager_loginname='" + loginname + "';";
		Manager man = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				man = Manager.getInstance();
				int id = rs.getInt("manager_id");
				String name = rs.getString("manager_name");
				
				man.setId(id);
				man.setLoginname(loginname);
				man.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return man;
	}
	
	public static Manager getManagerByManagerId(int manager_id) {
		String sql = "select * from manager where manager_id=" + manager_id + ";";
		Manager man = null;
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				man = Manager.getInstance();
				String loginname = rs.getString("manager_loginname");
				String name = rs.getString("manager_name");
				
				man.setId(manager_id);
				man.setLoginname(loginname);
				man.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return man;
	}
	
	public static ArrayList<Manager> getManagerList() {
		String sql = "select * from manager";
		ArrayList<Manager> res = new ArrayList<>();
		Connection con = DBHelper.getConnection();
		ResultSet rs = DBHelper.execQuery(con, sql);
		try {
			while (rs.next()) {
				Manager man = Manager.getInstance();
				int id = rs.getInt("manager_id");
				String loginname = rs.getString("manager_loginname");
				String name = rs.getString("manager_name");
				
				man.setId(id);
				man.setLoginname(loginname);
				man.setName(name);
				
				res.add(man);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBHelper.closeConnection(con);
		return res;
	}
	
	public static void addManager(Manager man) {
		String sql = "insert into manager(manager_loginname, manager_password, manager_name)"
				+ " values('" + man.getLoginname() + "', '" + man.getPassword() + "', '"
				+ man.getName() + "');";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteManagerByManagerId(int manager_id) {
		String sql = "delete from manager where manager_id=" + manager_id + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}
	
	public static void deleteManagerByManagerLoginname(String loginname) {
		String sql = "delete from manager where manager_loginname=" + loginname + ";";
		Connection con = DBHelper.getConnection();
		DBHelper.execUpdate(con, sql);
		DBHelper.closeConnection(con);
	}

}
