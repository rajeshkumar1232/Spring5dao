package com.java.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.java.dto.Employee;

public class EmplyeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao {

	private DataSource ds;
	public void setDs(DataSource datasource) {
		setDataSource(datasource);
	}
	
	@Override
	public String add(Employee emp) {
		String status = null;
		try {
			Employee employee = search(emp.getEno());
			if (employee == null) {
				String query ="insert into Employee values(:eno, :name, :sal, :addr)";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("eno", emp.getEno());
				param.put("name", emp.getName());
				param.put("sal", emp.getSal());
				param.put("addr", emp.getAddr());
				
				int rowCount = getNamedParameterJdbcTemplate().update(query,param );
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
			String query = "select * from Employee where eno = :eno";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("eno", eno);
			List<Employee> empsList = getNamedParameterJdbcTemplate().query(query, param, (rs, index) -> {
				Employee emp1 = new Employee();
				emp1.setEno(rs.getInt("eno"));
				emp1.setName(rs.getString("name"));
				emp1.setSal(rs.getFloat("sal"));
				emp1.setAddr(rs.getString("addr"));
				return emp1;
			});
			if(empsList.isEmpty() == true) {
			emp = null;
			}else {
				emp = empsList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> listemp = null;
		try {
			listemp = getNamedParameterJdbcTemplate().query("select * from Employee", (rs, index) -> {
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
				String query = "update Employee set name = :name, sal = :sal, addr = :addr where eno =:eno";
				Map<String,Object> param =  new HashMap<String, Object>();
				param.put("name", emp.getName());
				param.put("sal", emp.getSal());
				param.put("addr", emp.getAddr());
				param.put("eno", emp.getEno());
				int rowCount = getNamedParameterJdbcTemplate().update(query,param);
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
				String query ="delete from Employee where eno = :eno";
				Map<String,Object> param = new HashMap<String, Object>();
				param.put("eno", eno);
				int rowcount = getNamedParameterJdbcTemplate().update(query, param);
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
