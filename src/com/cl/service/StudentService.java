package com.cl.service;

import java.util.List;

import com.cl.entity.Student;

public interface StudentService {
//	public void createTable() throws Exception;
	
	public boolean check(String loginname, String password) throws Exception;
	
	public Student getStudentByLoginname(String loginname) throws Exception;

	public Student getStudentByStudentId(int student_id) throws Exception;
	
	public List<Student> getStudentList() throws Exception;
	
	public void addStudent(Student student) throws Exception;
	
	public void deleteStudentByStudentId(int student_id) throws Exception;
	
	public void deleteStudentByStudentLoginname(String loginname) throws Exception;
}
