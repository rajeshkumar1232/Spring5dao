package com.java.service;

import com.java.dto.Student;

public interface StudentService {
	
	public String addstudent(Student std);
	public Student searchStudent(String std);
	public String deleteStudent(String std);

}
