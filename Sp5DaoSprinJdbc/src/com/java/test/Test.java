package com.java.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.dao.EmployeeDao;
import com.java.dto.Employee;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context =  new ClassPathXmlApplicationContext("/com/java/resource/applicationContext.xml");
		EmployeeDao edao = (EmployeeDao) context.getBean("employeeDao");  
		
		/*
		 * Employee emp = new Employee(); emp.setEno(222); emp.setName("aaaaa");
		 * emp.setSal(50000); emp.setAddr("apac");
		 * 
		 * String status = edao.add(emp); System.out.println(status);
		 */
		
		
		  Employee emp = edao.search(222); if(emp == null) {
		  System.out.println("Employee not existed"); }else {
		  System.out.println("Employee existed"); System.out.println(emp.getEno());
		  System.out.println(emp.getName()); System.out.println(emp.getSal());
		  System.out.println(emp.getAddr()); }
		 
		
/*		Employee emp = new Employee();
		emp.setEno(222);
		emp.setName("bbbb");
		emp.setSal(6000);
		emp.setAddr("epac");
		String status = edao.update(emp);
		System.out.println(status);*/
		
//		String status = edao.delete(111);
//		System.out.println(status);
	}

}
