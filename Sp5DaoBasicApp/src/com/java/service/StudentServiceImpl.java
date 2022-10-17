package com.java.service;

import com.java.dao.StudentDao;
import com.java.dto.Student;
import com.java.factory.StudentDaoFatory;
import com.java.factory.StudentServiceFactory;

public class StudentServiceImpl implements StudentService{

	@Override
	public String addstudent(Student std) {
		StudentDao studentDao = StudentDaoFatory.getStudentDao();
		String status = studentDao.add(std);
		return status;
	}

	@Override
	public Student searchStudent(String sid) {
		StudentDao studentDao = StudentDaoFatory.getStudentDao();
		Student std = studentDao.search(sid);
		return std;
	}

	@Override
	public String deleteStudent(String std) {
		
		StudentDao studentDao = StudentDaoFatory.getStudentDao();
		String status = studentDao.dlete(std);
		return status;
	}

}
