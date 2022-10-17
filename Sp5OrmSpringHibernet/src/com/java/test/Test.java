 package com.java.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.bean.Employee;
import com.java.dao.EmployeeDao;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("/com/java/resource/applicationContext.xml");
		EmployeeDao edao = (EmployeeDao) context.getBean("employeeDao");
		
		Employee emp = new Employee();
		emp.setEno(111);
		emp.setName("aaa");
		emp.setSal(3000);
		emp.setAddr("blr");
		
		String status = edao.insert(emp);
		System.out.println(status);
		
/*		Employee emp = edao.search(111);
		System.out.println(emp.getEno());
		System.out.println(emp.getName());
		System.out.println(emp.getSal());
		System.out.println(emp.getAddr());*/
		
/*		Employee emp = new Employee();
		emp.setEno(111);
		emp.setName("BBBU");
		emp.setSal(3000);
		emp.setAddr("BLR U");
		
		String status = edao.update(emp);
		System.out.println(status);*/
		
/*		Employee emp = new Employee();
		emp.setEno(111);
		emp.setName("BBBU");
		emp.setSal(3000);
		emp.setAddr("BLR U");
		
		String status = edao.delete(emp);
		System.out.println(status);*/
		

	}

}
