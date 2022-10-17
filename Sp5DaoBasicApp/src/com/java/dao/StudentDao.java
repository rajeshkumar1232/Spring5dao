package com.java.dao;

import com.java.dto.Student;

public interface StudentDao {

	public String add(Student std);
	public Student search (String sid);
	public String dlete(String sid);
}
