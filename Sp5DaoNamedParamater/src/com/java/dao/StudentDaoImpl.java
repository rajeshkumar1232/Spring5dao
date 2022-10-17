package com.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.java.dto.Student;

public class StudentDaoImpl implements StudentDao {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplet;
	
	public void setNamedParameterJdbcTemplet(NamedParameterJdbcTemplate namedParameterJdbcTemplet) {
		this.namedParameterJdbcTemplet = namedParameterJdbcTemplet;
		
	}
	
	@Override
	public String add(Student std) {
		String status = "";
		String query = "";
		try {
			query = "select * from Student where sid = :sid";
//Using Map Approach
			Map<String, Object> map =  new HashMap<String, Object>();
			map.put("sid", 111);
			List<Student>  studentList = namedParameterJdbcTemplet.query(query, map, (rs, index) -> {
				Student stu = new Student();
				stu.setId(rs.getString("sid"));
				stu.setName(rs.getString("name"));
				stu.setAddr(rs.getString("addr"));
				return stu;
			});
			
			if(studentList.isEmpty() == true) {
				query = "insert into Student values (:sid, :name, :addr)";
				map =  new HashMap<String, Object>();
				map.put("sid", std.getId());
				map.put("name", std.getName());
				map.put("addr", std.getAddr());
				
				int rowcount = namedParameterJdbcTemplet.update(query, map);
				if(rowcount == 1) {
					status = "Stundetn inserted successfully";
				}else {
					status =  "Student insertion failed";
				}
			}else {
				status ="studentExisted";
			}
		} catch (Exception e) {
			status = "Student insertion failed with error";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String std) {
		Student stu = null;
		try {
			String query = "select * from Student where sid = :sid";
			Map<String, Object> param =  new HashMap<String, Object>();
			param.put("sid", std);
			List<Student> studentlist = namedParameterJdbcTemplet.query(query, param, (rs, index ) -> {
				Student std1 =  new Student();
				std1.setId(rs.getString("sid"));
				std1.setName(rs.getString("sname"));
				std1.setAddr(rs.getString("addr"));
				return std1;
			} );
			
			if(studentlist.isEmpty()) {
				std = null;
			}else {
				stu = studentlist.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stu;
	}

	@Override
	public String update(Student std) {
		String status = "";
		try {
			Student stu = search(std.getId());
			if (stu == null) {
				status = "student not existed";
			}else{
				String query = "update Student set sname = :name, addr = :addr where sid = :id";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", std.getId());
				param.put("name", std.getName());
				param.put("addr", std.getAddr());
				int rowCount = namedParameterJdbcTemplet.update(query, param);
				if(rowCount == 1) {
					status = "Student update success";
				}else {
					status = "student update failure";
				}
			}
		} catch (Exception e) {
			status = "student update failuredsds";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(String std) {
		String status = "";
		try {
			Student stu = search(std);
			if(stu == null) {
				status ="Student Not Existed";
			}else {
				String query = "delete from Student where sid = :id";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id",std);
				int rowCount = namedParameterJdbcTemplet.update(query, param);
				if(rowCount == 1) {
					status = "Student deletion success";
				}else {
					status = "Student deletion success";
				}
			}
		} catch (Exception e) {
			status = "Student deletion success with error";
			e.printStackTrace();
		}
		
		return status;
	}

}
