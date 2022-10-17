package com.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.java.dto.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowcount) throws SQLException {
		Employee emp = new Employee();
		emp.setEno(rs.getInt("eno"));
		emp.setName(rs.getString("name"));
		emp.setSal(rs.getFloat("sal"));
		emp.setAddr(rs.getString("addr"));
		return emp;
	}

}
