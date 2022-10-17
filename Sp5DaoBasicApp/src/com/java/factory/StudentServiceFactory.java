package com.java.factory;

import com.java.service.StudentService;
import com.java.service.StudentServiceImpl;

public class StudentServiceFactory {
	
	private static StudentService studentService;
	static {
		studentService = new StudentServiceImpl();
	}
	
	public static StudentService getStudentService() {
		return studentService;
	}

}
