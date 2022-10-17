package com.java.dao;

import com.java.dto.Employee;

public interface EmployeeDao {
	
	public void insertEmployee(Employee emp);
	public Employee reademployee(int eno);
	

}
