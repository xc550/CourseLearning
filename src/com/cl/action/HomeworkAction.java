package com.cl.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import org.apache.struts2.ServletActionContext;

import com.cl.dao.Homework;
import com.cl.dao.HomeworkStudent;
import com.cl.dao.Section;
import com.cl.dao.Student;
import com.cl.dao.Teacher;
import com.cl.util.FileFunc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeworkAction extends ActionSupport {
	private Homework homework;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private String homeworkcomment;

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getHomeworkcomment() {
		return homeworkcomment;
	}

	public void setHomeworkcomment(String homeworkcomment) {
		this.homeworkcomment = homeworkcomment;
	}

	/*
	 * role: student, teacher
	 */
	public String getHomeworkList() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		String username = (String)act.getSession().get("username");
		String role = (String)act.getSession().get("role");
		int user_id = -1, class_id = -1;
		if (role.equals("student"))
			user_id = Student.getStudentByLoginname(username).getId();
		else if (role.equals("teacher"))
			user_id = Teacher.getTeacherByLoginname(username).getId();
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		ArrayList<Homework> homework = Homework.getHomeworkListByCourseIdAndClassId(course_id, class_id);
		homework.sort(new Comparator<Homework>() {
			public int compare(Homework a, Homework b) {
				return a.getSection_id() - b.getSection_id();
			}
		});
		ArrayList<Section> sectionlist = Section.getSectionListByCourseId(course_id);
		
		act.put("sectionlist", sectionlist);
		act.put("homeworklist", homework);
		act.put("user_id", new Integer(user_id));
		return SUCCESS;
	}
	
	/*
	 * role: student
	 */
	public String deleteHomeworkOfStudent() throws Exception {
		ActionContext act = ActionContext.getContext();
		int homeworkstudent_id = (new Integer(ServletActionContext.getRequest().getParameter("homeworkstudent_id"))).intValue();
		String loginname = (String)act.getSession().get("username");
		int student_id = Student.getStudentByLoginname(loginname).getId();
		HomeworkStudent hws = HomeworkStudent.getHomeworkStudentByHomeworkStudentId(homeworkstudent_id);
		String accessory = hws.getHomeworkstudent_accessory();
		String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		filename += File.separator + "student_" + hws.getHomework_id() + "_" + student_id + "_" + accessory;
		
		HomeworkStudent.deleteHomeworkStudentByHomeworkStudentId(homeworkstudent_id);
		FileFunc.deleteFile(filename);
		return SUCCESS;
	}
	
	/*
	 * role: student
	 */
	public String submitHomework() throws Exception {
		int homework_id = new Integer(ServletActionContext.getRequest().getParameter("homework_id")).intValue();
		int student_id = Student.getStudentByLoginname((String)ServletActionContext.getRequest().getSession().getAttribute("username")).getId();
		HomeworkStudent hws = HomeworkStudent.getInstance();
		hws.setHomeworkstudent_comment(getHomeworkcomment());
		hws.setHomework_id(homework_id);
		hws.setStudent_id(student_id);
		
		if (getUpload() != null) {
			String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
			if (!FileFunc.directoryExist(filename))
				FileFunc.createDirectory(filename);
			filename += File.separator + "student_" + homework_id + "_" + student_id + "_" + getUploadFileName();
			System.out.println("filename: " + filename);
			FileFunc.writeFile(filename, getUpload());
			hws.setHomeworkstudent_accessory(getUploadFileName());
		}
		
		HomeworkStudent.addHomeworkStudent(hws);
		return SUCCESS;
	}
	
	
	/*
	 * role: teacher
	 */
	public String deleteHomeworkAccessory() throws Exception {
		ActionContext act = ActionContext.getContext();
		int homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		String accessory = Homework.getHomeworkByHomeworkId(homework_id).getHomework_accessory();
		int course_id = ((Integer)act.getSession().get("course_id"));
		int class_id = -1;
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		filename += File.separator + "homework_" + course_id + "_" + class_id + "_" + accessory;
		
		Homework.deleteHomeworkAccessory(homework_id);
		FileFunc.deleteFile(filename);
		return SUCCESS;
	}
	
	/*
	 * role: teacher
	 */
	public String updataHomeworkAccessory() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		int class_id = -1;
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		if (getUpload() != null) {
			String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
			if (!FileFunc.directoryExist(filename))
				FileFunc.createDirectory(filename);
			filename += File.separator + "homework_" + course_id + "_" + class_id + "_" + getUploadFileName();
			System.out.println("filename: " + filename);
			FileFunc.writeFile(filename, getUpload());
		}
		else {
			System.out.println("upload file is null");
		}
		Homework.addHomeworkAccessory(homework_id, getUploadFileName());
		return SUCCESS;
	}
	
	/*
	 * role: teacher
	 */
	public String addHomework() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int class_id = -1;
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		Homework hw = getHomework();
		
		hw.setClass_id(class_id);
		hw.setCourse_id(course_id);
		if (getUpload() != null) {
			String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
			if (!FileFunc.directoryExist(filename))
				FileFunc.createDirectory(filename);
			filename += File.separator + "homework_" + course_id + "_" + class_id + "_" + getUploadFileName();
			System.out.println("filename: " + filename);
			FileFunc.writeFile(filename, getUpload());
			hw.setHomework_accessory(getUploadFileName());
		}
		else {
			System.out.println("upload file is null");
		}
		Homework.addHomework(hw);
		return SUCCESS;
	}
	
	/*
	 * role: teacher
	 */
	public String updateHomework() throws Exception {
		ActionContext act = ActionContext.getContext();
		int course_id = ((Integer)act.getSession().get("course_id")).intValue();
		int class_id = -1;
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		int homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		Homework hw = getHomework();
		hw.setHomework_id(homework_id);
		hw.setClass_id(class_id);
		hw.setCourse_id(course_id);
		if (getUpload() != null) {
			String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
			if (!FileFunc.directoryExist(filename))
				FileFunc.createDirectory(filename);
			filename += File.separator + "homework_" + course_id + "_" + class_id + "_" + getUploadFileName();
			System.out.println("filename: " + filename);
			FileFunc.writeFile(filename, getUpload());
			hw.setHomework_accessory(getUploadFileName());
		}
		
		Homework.updateHomework(hw);
		return SUCCESS;
	}
	
	/*
	 * role: teacher
	 */
	public String deleteHomework() throws Exception {
		ActionContext act = ActionContext.getContext();
		int homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		String accessory = Homework.getHomeworkByHomeworkId(homework_id).getHomework_accessory();
		int course_id = ((Integer)act.getSession().get("course_id"));
		int class_id = -1;
		if (ServletActionContext.getRequest().getParameter("class_id") != null)
			class_id = (new Integer(ServletActionContext.getRequest().getParameter("class_id"))).intValue();
		else if (act.getSession().get("class_id") != null)
			class_id =((Integer)act.getSession().get("class_id")).intValue();
		String filename = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload");
		filename += File.separator + "homework_" + course_id + "_" + class_id + "_" + accessory;
		
		Homework.deleteHomeworkByHomeworkId(homework_id);
		FileFunc.deleteFile(filename);
		return SUCCESS;
	}
	
	/*
	 * role: teacher
	 */
	public String getHomeworkOfStudent() throws Exception {
		ActionContext act = ActionContext.getContext();
		int homework_id = -1;
		if (act.get("homework_id") != null)
			homework_id = ((Integer)act.get("homework_id")).intValue();
		else if (ServletActionContext.getRequest().getParameter("homework_id") != null)
			homework_id = (new Integer(ServletActionContext.getRequest().getParameter("homework_id"))).intValue();
		else
			homework_id = ((Integer)act.getSession().get("homework_id")).intValue();
		
		ArrayList<HomeworkStudent> res = HomeworkStudent.getHomeworkStudentListByHomeworkId(homework_id);
		Homework hw = Homework.getHomeworkByHomeworkId(homework_id);
		
		act.put("homework", hw);
		act.put("homeworklist", res);
		return SUCCESS;
	}

}
