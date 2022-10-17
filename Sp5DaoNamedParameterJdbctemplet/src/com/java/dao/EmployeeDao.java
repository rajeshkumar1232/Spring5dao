package com.java.dao;

import java.util.List;

import com.java.dto.Employee;

public interface EmployeeDao {
	
	public String add(Employee emp);
	public Employee search(int eno);
	public List<Employee> getAllEmployee();
	public String update(Employee emp);
	public String delete(int eno);

}
