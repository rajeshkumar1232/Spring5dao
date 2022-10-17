package com.java.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.FileCopyUtils;

import com.java.dto.Employee;

public class EmploeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbctemplate;
	private LobHandler lobHandler;
	public LobHandler getLobHandler() {
		return lobHandler;
	}
	public void setLobHandler(LobHandler lobHandler) {
		this.lobHandler = lobHandler;
	}
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	@Override
	public void insertEmployee(Employee emp) {
		String query = "insert into emp20 values (?,?,?,?)";
		jdbctemplate.execute(query, new AbstractLobCreatingPreparedStatementCallback(lobHandler){
			
			@Override
			protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
				try {
					ps.setInt(1, emp.getEno());
					ps.setString(2, emp.getName());
					FileInputStream fis =  new FileInputStream(emp.geteImage());
					FileReader fr = new FileReader(emp.geteResume());
					lobCreator.setBlobAsBinaryStream(ps, 3, fis, (int) emp.geteImage().length());
					lobCreator.setClobAsCharacterStream(ps, 4, fr, (int)emp.geteResume().length());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

	}

	@Override
	public Employee reademployee(int eno) {
		Employee emp = new Employee();
		String query = "select * from emp20 where eno = "+eno;
		jdbctemplate.query(query, new AbstractLobStreamingResultSetExtractor<Object>() {
			@Override
			protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
				emp.setEno(rs.getInt("eno"));
				emp.setName(rs.getString("name"));
				File file1 = new File("/home/Detox/Output/App13Recipt.jpg");
				FileOutputStream fos = new FileOutputStream(file1);
				FileCopyUtils.copy(lobHandler.getBlobAsBinaryStream(rs, 3), fos);
				emp.seteImage(file1);
				File file2 = new File("/home/Detox/Output/App13applicationContext.xml");
				FileWriter fw =  new FileWriter(file2);
				FileCopyUtils.copy(lobHandler.getClobAsCharacterStream(rs, 4), fw);
				emp.seteResume(file2);
			}
		});
		return emp;
	}

}
