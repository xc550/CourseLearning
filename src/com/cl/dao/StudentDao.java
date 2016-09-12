package com.cl.dao;

import java.util.ArrayList;
import com.cl.entity.Student;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
	public void createTable() throws Exception;
	
	public Student check(String loginname, String password) throws Exception;
	
	public Student getStudentByLoginname(String loginname) throws Exception;

	public Student getStudentByStudentId(int student_id) throws Exception;
	
	public ArrayList<Student> getStudentList() throws Exception;
	
	public void addStudent(Student student) throws Exception;
	
	public void deleteStudentByStudentId(int student_id) throws Exception;
	
	public void deleteStudentByStudentLoginname(String loginname) throws Exception;
}
