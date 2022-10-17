package com.java.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.java.dto.Employee;
import com.java.mapper.EmployeeRowMapper;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public String add(Employee em) {
		String status = "";
		// Note :queryForObject (if record is available then only we ahve to use this
		// method)
		try {
			List<Employee> empList = jdbcTemplate.query("select * from Employee where eno = ?",
					new Object[] { em.getEno() }, new EmployeeRowMapper());
			if (empList.isEmpty() == true) {
				//Positional parameter
				//int rowcount = jdbcTemplate.update("insert into Employee values("?,?,?,?", new Object[] {222,"bbb",2000,"bokaro"});
				int rowcount = jdbcTemplate.update("insert into Employee values(" + em.getEno() + ",'" + em.getName()
						+ "' , " + em.getSal() + ",'" + em.getAddr() + "')");
				if (rowcount == 1) {
					status = "Employee inserted successfully";
				} else {
					status = "Employee insertion failed";
				}
			} else {
				status = "Employee Existed Already";
			}
		} catch (Exception e) {
			status = "employee insetion failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Employee search(int eno) {
		Employee emp = null;
		try {
			List<Employee> emplist = jdbcTemplate.query("select * from Employee where eno = " + eno, (rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEno(rs.getInt("eno"));
				emp1.setName(rs.getString("name"));
				emp1.setSal(rs.getFloat("sal"));
				emp1.setAddr(rs.getString("addr"));
				return emp1;
			});
			if (emplist.isEmpty()) {
				emp = null;
			} else {
				emp = emplist.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public String update(Employee emp) {
		String status = "";
		try {
			Employee emp1 = search(emp.getEno());
			if (emp1 == null) {
				status = "Employee not existed";
			} else {
				int rowcount = jdbcTemplate.update("update Employee set name ='" + emp.getName() + "' ,sal="
						+ emp.getSal() + ",addr ='" + emp.getAddr() + "' where eno=" + emp.getEno());
				if (rowcount == 1) {
					System.out.println("Employee updated succesfully");
				} else {
					System.out.println("Employee updating failure");
				}
			}
		} catch (Exception e) {
			status = "employee station failes";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int eno) {
		String status = "";
		try {
			Employee emp2 = search(eno);
			if (emp2 == null) {
				status = "employee not exist";
			} else {
				int rowcount = jdbcTemplate.update("delete from Employee where  eno = " + emp2.getEno());
				if (rowcount == 1) {
					status = "Employee  deleted sucussfully";
				} else {
					status = "Employee deletion failed";
				}
			}

		} catch (Exception e) {
			status = "Employe delete error";
			e.printStackTrace();
		}
		return status;

	}

}
