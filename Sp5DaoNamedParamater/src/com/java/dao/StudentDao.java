package com.java.dao;

import com.java.dto.Student;

public interface StudentDao {

	public String add(Student std);
	public Student search(String std);
	public String update(Student std);
	public String delete(String std);
	
}
