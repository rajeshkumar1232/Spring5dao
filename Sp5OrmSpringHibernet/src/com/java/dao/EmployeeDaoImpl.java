package com.java.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.java.bean.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	@Transactional
	@Override
	public String insert(Employee emp) {
		String status = "";
		try {
			Employee em = (Employee) hibernateTemplate.get(Employee.class, emp.getEno());
			if(em == null){
			int eno = (Integer) hibernateTemplate.save(emp);
			if(eno == emp.getEno()) {
				status = "Employee data inserted succesfully";
			}else {
				status = "Employee data insertion failed";
			}
			}else {
						status = "employee existed already";
			}
		} catch (Exception e) {
			status = "Employee data insertion failed with error";
			e.printStackTrace();
		}
		return status;
	}
	@Transactional
	@Override
	public Employee search(int eno) {
		Employee emp = null;
		try {
			emp = (Employee) hibernateTemplate.get(Employee.class, eno);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	@Transactional
	@Override
	public String update(Employee emp) {
		String status = "";
		try {
			Employee emp1 = search(emp.getEno());
			if(emp1 == null) {
				status = "employee  not existed";
			}else {
				hibernateTemplate.evict(emp1);//clearing the cache if not some confusion occure causing error
				hibernateTemplate.update(emp);
				status = "Employee Updated Succesfully";
			}
		} catch (Exception e) {
			status = "Error occur while updating ";
			e.printStackTrace();
		}
		return status;
	}
	@Transactional
	@Override
	public String delete(Employee emp) {
		String status = "";
		try {
			Employee emp1 = search(emp.getEno());
			if(emp1 == null) {
				status = "employee  not existed";
			}else {
				hibernateTemplate.evict(emp1);//clearing the cache if not some confusion occure causing error
				hibernateTemplate.delete(emp);
				status = "Employee deleted Succesfully";
			}
		} catch (Exception e) {
			status = "Error occur while updating ";
			e.printStackTrace();
		}
		return status;
	}

}
