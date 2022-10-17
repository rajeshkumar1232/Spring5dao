package com.java.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.java.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private JdbcTemplate jdTemplate;
	
	public void setJdTemplate(JdbcTemplate jdTemplate) {
		this.jdTemplate = jdTemplate;
	}
	
	@Override
	public int[] insert(List<Employee> emplist) {
	
		int[] rowcount = null;
		try {
			String query = "insert into Employee values(?,?,?,?)";
			rowcount = jdTemplate.batchUpdate(query,new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int index) throws SQLException {
					Employee emp = emplist.get(index);
					ps.setInt(1, emp.getEno());
					ps.setString(2, emp.getName());
					ps.setFloat(3, emp.getSal());
					ps.setString(4, emp.getAddr());
				}
				
				@Override
				public int getBatchSize() {
					return emplist.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowcount;
	}

	@Override
	public int[] update() {
		int [] rowcout = null;
		try {
			String query1 ="insert into Employee values(555,'eee',2005,'EEE')";
			String query2 = "delete from Employee where eno = 111";
			
			rowcout = jdTemplate.batchUpdate(query1,query2);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowcout;
	}
}
