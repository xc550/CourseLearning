package com.cl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cl.dao.StudentDao;
import com.cl.entity.Student;
import com.cl.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Resource
	private StudentDao stuDao;
	
	
	
	@Override
	public boolean check(String loginname, String password) throws Exception {
		if (stuDao.check(loginname, password) != null)
			return true;
		return false;
	}
	
	@Override
	public Student getStudentByLoginname(String loginname) throws Exception {
		return stuDao.getStudentByLoginname(loginname);
	}

	@Override
	public Student getStudentByStudentId(int student_id) throws Exception {
		return stuDao.getStudentByStudentId(student_id);
	}
	
	@Override
	public List<Student> getStudentList() throws Exception {
		return stuDao.getStudentList();
	}
	
	@Override
	public void addStudent(Student student) throws Exception {
		stuDao.addStudent(student);
	}
	
	@Override
	public void deleteStudentByStudentId(int student_id) throws Exception {
		stuDao.deleteStudentByStudentId(student_id);
	}
	
	@Override
	public void deleteStudentByStudentLoginname(String loginname) throws Exception {
		stuDao.deleteStudentByStudentLoginname(loginname);
	}

}
