package com.java.factory;

import com.java.dao.StudentDao;
import com.java.dao.StudentDaoImpl;

public class StudentDaoFatory {
	
	private static StudentDao studentDao;
	
	static {
		studentDao = new StudentDaoImpl();
	}

	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
