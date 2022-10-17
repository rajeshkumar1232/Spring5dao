package com.java.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.dao.EmployeeDao;
import com.java.dto.Employee;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/java/resource/applicationContext.xml");
		EmployeeDao edao = (EmployeeDao)context.getBean("employeeDao");
		
		
//		Employee em = new Employee();
//		em.setEno(4443);
//		em.setName("hhhhUpdate");
//		em.setSal(10001);
//		em.setAddr("apac");
//		
//		String status = edao.add(em);
//		System.out.println(status);
		
/*		Employee status = edao.search(122);
		if(status == null) {
			System.out.println("employee doe't exist");
		}else {
			System.out.println(status.getEno());
			System.out.println(status.getName());
			System.out.println(status.getSal());
			System.out.println(status.getAddr());
		}*/
		
		List<Employee> lemp = edao.getAllEmployee();
		for(Employee emp :lemp) {
			System.out.println(emp.getEno());
			System.out.println(emp.getName());
			System.out.println(emp.getSal());
			System.out.println(emp.getAddr());
		}
/*		Employee e =  new Employee();
		e.setEno(111);
		e.setName("DDDD");
		e.setSal(30000);
		e.setAddr("bpac");
		String status = edao.update(e);
		System.out.println(status);*/
		
//		String status =edao.delete(555);
//		System.out.println(status);
			
	}

}
