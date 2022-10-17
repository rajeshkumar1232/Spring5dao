package com.java.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java.dao.StudentDao;
import com.java.dto.Student;

public class Test {

	public static void main(String[] args) throws Exception{
		
		ApplicationContext context =  new ClassPathXmlApplicationContext("/com/java/resources/applicationContext.xml");
		
		StudentDao studentDao = (StudentDao) context.getBean("student");
		/*
		 * Student stu = new Student(); stu.setId("111"); stu.setName("ccc");
		 * stu.setAddr("Apac"); String status = studentDao.add(stu);
		 * System.out.println(status);
		 */
		
		
		  Student stu = studentDao.search("111"); if(stu == null) {
		  System.out.println("Student not existed"); }else {
		  System.out.println(stu.getId()); System.out.println(stu.getName());
		  System.out.println(stu.getAddr()); }
		 
/*		Student st =  new Student();
		st.setId("222");
		st.setName("xzzz");
		st.setAddr("Epac");
		
		String status = studentDao.update(st);
		System.out.println(status);*/
		
		/*
		 * String status = studentDao.delete("111"); System.out.println(status);
		 */
	}

}
