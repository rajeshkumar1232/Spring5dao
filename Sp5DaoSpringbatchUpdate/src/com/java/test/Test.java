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
		EmployeeDao edao = (EmployeeDao)context.getBean("empdao");
		
		Employee e1 = new Employee();
		e1.setEno(111);
		e1.setName("aaa");
		e1.setSal(2000);
		e1.setAddr("AAA");
		
		Employee e2 = new Employee();
		e2.setEno(222);
		e2.setName("bbb");
		e2.setSal(2001);
		e2.setAddr("BBB");
		
		/*		Employee e3 = new Employee();
		e3.setEno(333);
		e3.setName("ccc");
		e3.setSal(2003);
		e3.setAddr("CCC");
		
		Employee e4 = new Employee();
		e4.setEno(444);
		e4.setName("ddd");
		e4.setSal(2004);
		e4.setAddr("DDD");
		
		List<Employee> elist = new ArrayList<Employee>();
		elist.add(e1);
		elist.add(e2);
		elist.add(e3);
		elist.add(e4);
		
		int[] rowcount = edao.insert(elist);
		for(int row : rowcount) {
			System.out.println(row);
		}*/
		
		int[] rowcount = edao.update();
		for(int row : rowcount) {
			System.out.println(row);
		}
	}

}
