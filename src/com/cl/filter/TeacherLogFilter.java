package com.cl.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.cl.dao.Course;
import com.cl.dao.Event;
import com.cl.dao.Section;
import com.cl.dao.Teacher;
import com.cl.entity.Log;
import com.cl.util.DateFormator;
import com.cl.util.FileFunc;

public class TeacherLogFilter extends LogFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig config) {
		this.config = config;
	}
	
	public void destroy() {
		this.config = null;
	}
	
	private Log createLog(Calendar nowtime, HttpServletRequest req) {
		Log log = new Log();
		log.setLog_id(0);
		
//		get loginname
		String loginname = "";
		if (req.getParameter("teacher.loginname") != null)
			loginname = req.getParameter("teacher.loginname");
		else if (req.getSession().getAttribute("username") != null)
			loginname = (String)req.getSession().getAttribute("username");
		
//		get teacher
		if (loginname != "") {
			Teacher te = Teacher.getTeacherByLoginname(loginname);
			log.setUser_id(te.getId());
			log.setUser_name(te.getName());
		}
		else {
			log.setUser_id(-1);
			log.setUser_name("");
		}
		
		log.setAction("");
		log.setTime(nowtime);
		
		int course_id = INVAILD, section_id = INVAILD, event_id = INVAILD;
		if (req.getParameter("course_id") != null)
			course_id = new Integer(req.getParameter("course_id")).intValue();
		else if (req.getSession().getAttribute("course_id") != null)
			course_id = ((Integer)req.getSession().getAttribute("course_id")).intValue();
		if (req.getParameter("section_id") != null)
			section_id = new Integer(req.getParameter("section_id")).intValue();
		else if (req.getSession().getAttribute("section_id") != null)
			section_id = ((Integer)req.getSession().getAttribute("section_id")).intValue();
		if (req.getParameter("event_id") != null)
			event_id = new Integer(req.getParameter("event_id")).intValue();
		else if (req.getSession().getAttribute("event_id") != null)
			event_id = ((Integer)req.getSession().getAttribute("event_id")).intValue();
		
		log.setCourse_id(course_id);
		log.setSection_id(section_id);
		log.setEvent_id(event_id);
		
		if (course_id != INVAILD)
			log.setCourse_name(Course.getCourseByCourseId(course_id).getCourse_name());
		else
			log.setCourse_name("");
		if (section_id != INVAILD) {
//			教师删除时顺序出错
//			if (section_id != 0)
//				log.setSection_name(Section.getSectionBySectionId(section_id).getSection_name());
//			else
				log.setSection_name("课程总结");
		}
		else {
			log.setSection_name("");
		}
		if (event_id != INVAILD) {
			Event eve = Event.getEventByEventId(event_id);
			log.setEvent_name(eve.getEvent_content());
			log.setEvent_type(eve.getEvent_type());
		}
		else {
			log.setEvent_name("");
			log.setEvent_type("");
		}
		return log;
	}
	
	public void doFilter(ServletRequest req,
			ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
//		ServletContext context = this.config.getServletContext();
		HttpServletRequest hreq = (HttpServletRequest)req;
		chain.doFilter(req, res);
		String[] r = hreq.getRequestURI().split("/");
		String action = r[r.length - 1];
		String role = (String)hreq.getSession().getAttribute("role");
		Pattern p = Pattern.compile("^(teacher)_(.)*");
		
		if (p.matcher(action).matches() && (role == null || role.equals("teacher"))) {
			String subaction = getSubAction(action);
			System.out.println("##########Start##########");
			System.out.println("action: " + action + " subaction: " + subaction);
			
			String datetime = (String) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			Calendar nowtime = DateFormator.getDateByPattern(datetime);
			Log log = createLog(nowtime, hreq);
			
			boolean show = true;
			String propertiespath = (TeacherLogFilter.class.getResource("") + "teacherlog.properties").substring(5);
			String actioninchinese = FileFunc.readFromPropertiesFile(propertiespath, subaction);
			if (actioninchinese == "")
				show = false;
			else
				log.setAction(actioninchinese);
			
			if (show) {
				log.show();
				String path = hreq.getServletContext().getRealPath(savepath);
//				System.out.println("path: " + path);
				if (!FileFunc.directoryExist(path))
					FileFunc.createDirectory(path);
//				Log.saveLog(path, log);
			}
			System.out.println("##########End##########\n");
		}
	}
}
