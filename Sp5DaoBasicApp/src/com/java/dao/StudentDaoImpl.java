package com.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.dto.Student;
import com.java.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student std) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement ps =  con.prepareStatement("select * from Student where sid = ?");
			ps.setString(1, std.getSid());
			ResultSet rs = ps.executeQuery();
			boolean b = rs.next();
			if(b == true) {
				status = "existed";
			}else {
				ps = con.prepareStatement("insert into Student values(?, ?, ?)");
				ps.setString(1, std.getSid());
				ps.setString(2, std.getSname());
				ps.setString(3, std.getSaddr());
				int rowCount = ps.executeUpdate();
				if(rowCount == 1 ) {
					status = "scuccess";
				}else {
					status = "failure";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Connection con = ConnectionFactory.getConnection();
		Student std = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from Student where sid = ?");
			ps.setString(1, sid);
			ResultSet rs = ps.executeQuery();
			boolean b = rs.next();
			
			if(b == true) {
				std = new Student();
				std.setSid(rs.getString("sid"));
				std.setSname(rs.getString("sname"));
				std.setSaddr(rs.getString("addr"));
			}else {
				std = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return std;
	}

	@Override
	public String dlete(String sid) {
		String status = "";
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement ps =  con.prepareStatement("select * from Student where sid = ?");
			ps.setString(1, sid);
			ResultSet rs = ps.executeQuery();
			boolean b = rs.next();
			if(b == true) {
				ps = con.prepareStatement("delete from Student where sid = ?");
				ps.setString(1, sid);
				int rowcount = ps.executeUpdate();
				if(rowcount == 1) {
					status = "scuccess";
				}else {
					status = "failure";
				}
			}else {
				status = "notexisted";
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return status;
	}

}
