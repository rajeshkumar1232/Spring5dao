package com.java.test;
/*
 * Mysql Table : create table emp20(eno int, name varchar(10), emp_image BLOB, emp_resume TEXT);
 * */

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.dao.EmployeeDao;
import com.java.dto.Employee;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/java/resources/applicationContext.xml");
		EmployeeDao edao = (EmployeeDao)context.getBean("empdao");
		
/*		Employee emp = new Employee();
		emp.setEno(111);
		emp.setName("aaa");
		emp.seteImage(new File("/home/Detox/Output/Recipt.jpg"));
		emp.seteResume(new File("/home/Detox/Output/applicationContext.xml"));
		
		edao.insertEmployee(emp);
		System.out.println("success");
		*/

		Employee emp = edao.reademployee(111);
		System.out.println(emp.getEno());
		System.out.println(emp.getName());
		System.out.println(emp.geteImage());
		System.out.println(emp.geteResume());
		
		
	}

}
