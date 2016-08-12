package com.cl.action;

import java.io.File;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Student;
import com.cl.entity.Log;
import com.cl.util.PageManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogAction extends ActionSupport {
	private final String savepath = "/WEB-INF/log"; 
	
	public String getLogList() throws Exception {
		ActionContext act = ActionContext.getContext();
		
		String path = ServletActionContext.getServletContext().getRealPath(savepath);
		String[] files = Log.getLogList(path);
		ArrayList<String> res = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			String file = files[i];
			String[] part = file.split("_");
			String logtime = part[0];
			int student_id = new Integer(part[1]).intValue();
			if (student_id != -1) {
				String student_name = Student.getStudentByStudentId(student_id).getName();
				
				res.add(logtime);
				res.add(student_name);
				res.add(file);
			}
		}
		PageManager pm = new PageManager();
		pm.setTotalsize(res.size() / 3);
		pm.setEach(10);
		int nowcolumn = 1;
		if (ServletActionContext.getRequest().getParameter("column") != null)
			nowcolumn = (new Integer(ServletActionContext.getRequest().getParameter("column"))).intValue();
		pm.setNowcolumn(nowcolumn);
		pm.calcPreColumnAndNextColumn();
		
		act.put("loglist", res);
		act.put("each", pm.getEach());
		act.put("nowcolumn", pm.getNowcolumn());
		act.put("totalcolumn", pm.getTotalcolumn());
		act.put("nextcolumn", pm.getNextcolumn());
		act.put("precolumn", pm.getPrecolumn());
		return SUCCESS;
	}
	
	public String deleteLog() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(savepath);
		String logname = ServletActionContext.getRequest().getParameter("logname");
		String filename = path + File.separator + logname;
		
		Log.deleteLog(filename);
		return SUCCESS;
	}
}
