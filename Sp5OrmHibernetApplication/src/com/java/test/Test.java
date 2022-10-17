package com.java.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.java.entity.Employee;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transection = null;
		try {
			Configuration conf = new Configuration();
			conf.configure("/com/java/resources/hibernate.cfg.xml");
			sessionFactory = conf.buildSessionFactory();
			session = sessionFactory.openSession();
			Employee emp = (Employee)session.get("com.java.entity.Employee", 111);
			System.out.println(emp.getEno());
			System.out.println(emp.getName());
			System.out.println(emp.getSal());
			System.out.println(emp.getAddr());
/*			transection = session.beginTransaction();
			
			Employee emp = new Employee();
			emp.setEno(222);
			emp.setName("bbb");
			emp.setSal(5000);
			emp.setAddr("BBB");
			
			session.save(emp);
			transection.commit();
			System.out.println("Data inserted succesfully");*/
		} catch (Exception e) {
			transection.rollback();
			System.out.println("Data insertion failed with error");
			e.printStackTrace();
		}finally {
			session.close();
			sessionFactory.close();
		}
	}

}

