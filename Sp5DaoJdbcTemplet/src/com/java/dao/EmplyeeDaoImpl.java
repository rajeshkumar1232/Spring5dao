package com.java.dao;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.java.dto.Employee;

public class EmplyeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	@Override
	public String add(Employee emp) {
		String status = null;
		try {
			Employee employee = search(emp.getEno());
			if (employee == null) {
				int rowCount = getJdbcTemplate().update("insert into Employee values(?,?,?,?)", emp.getEno(),
						emp.getName(), emp.getSal(), emp.getAddr());
				if (rowCount == 1) {
					status = "Employee inserted succesfully";
				} else {
					status = "Employee inserted failes";
				}
			} else {
				status = "Employee existed already";
			}

		} catch (Exception e) {
			status = "Employee inserted failes with error";
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public Employee search(int eno) {
		Employee emp = null;
		try {
			String query = "select * from Employee where eno = ?";
			List<Employee> empsList = getJdbcTemplate().query(query, new Object[] { eno }, (rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEno(rs.getInt("eno"));
				emp1.setName(rs.getString("name"));
				emp1.setSal(rs.getFloat("sal"));
				emp1.setAddr(rs.getString("addr"));
				return emp1;
			});
			emp = empsList.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> listemp = null;
		try {
			listemp = getJdbcTemplate().query("select * from Employee", (rs, index) -> {
				Employee emp = new Employee();
				emp.setEno(rs.getInt("eno"));
				emp.setName(rs.getString("name"));
				emp.setSal(rs.getFloat("sal"));
				emp.setAddr(rs.getString("addr"));
				return emp;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listemp;
	}

	@Override
	public String update(Employee emp) {
		String status = "";
		try {
			Employee em = search(emp.getEno());
			if(em == null) {
				status = "employee Doees't exist";
			}else {
				String query = "update Employee set name = ?, sal = ?, addr = ? where eno =?";
				int rowCount = getJdbcTemplate().update(query,em.getName(),em.getSal(),em.getAddr(),em.getEno());
				if(rowCount == 1) {
					status = "Employee updated succesfully";
				}else {
					status = "employee does't exist";
				}
			}
			
		} catch (Exception e) {
			status = "employee does't exist with error";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(int eno) {
		String status = "";
		try {
			Employee em = search(eno);
			if(em ==  null) {
				status = "Exmplpyee not existed";
			}else {
				int rowcount = getJdbcTemplate().update("delete from Employee where eno=?", eno);
				if(rowcount == 1) {
					status = "Employee delted succesfully";
				}else {
					status = "Exmplpyee not existed";
				}
			}
		} catch (Exception e) {
			status = "Exmplpyee not existed with error";
			e.printStackTrace();
		}
		return status;
	}

}
