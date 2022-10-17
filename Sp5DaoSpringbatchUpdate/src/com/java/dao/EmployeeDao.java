package com.java.dao;

import java.util.List;

import com.java.dto.Employee;

public interface EmployeeDao {
	public int [] insert(List<Employee> emplist);
	public int[] update();

}
