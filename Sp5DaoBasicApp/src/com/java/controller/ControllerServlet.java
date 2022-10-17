package com.java.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dto.Student;
import com.java.factory.StudentServiceFactory;
import com.java.service.StudentService;

@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String req_path = request.getRequestURI();
		System.out.println(" req_path  : " + req_path);
		StudentService studentService = StudentServiceFactory.getStudentService();
		if (req_path.endsWith("add.do")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String saddr = request.getParameter("saddr");

			Student std = new Student();
			std.setSid(sid);
			std.setSname(sname);
			std.setSaddr(saddr);
			String status = studentService.addstudent(std);
			RequestDispatcher dispatcher = null;
			if (status.equals("scuccess")) {
				dispatcher = request.getRequestDispatcher("scuccess.html");
				dispatcher.forward(request, response);
			}
			if (status.equals("failure")) {
				dispatcher = request.getRequestDispatcher("failure.html");
				dispatcher.forward(request, response);
			}
			if (status.equals("existed")) {
				dispatcher = request.getRequestDispatcher("existed.html");
				dispatcher.forward(request, response);
			}
		}

		if (req_path.endsWith("search.do")) {
			String sid = request.getParameter("sid");
			Student stu = studentService.searchStudent(sid);
			RequestDispatcher dispatcher = null;
			if (stu == null) {
				dispatcher = request.getRequestDispatcher("notexisted.html");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("std", stu);
				dispatcher = request.getRequestDispatcher("display.jsp");
				dispatcher.forward(request, response);
			}
		}
		if (req_path.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			String status = studentService.deleteStudent(sid);
			RequestDispatcher dispatcher = null;
			if(status.equals("scuccess")) {
				dispatcher = request.getRequestDispatcher("scuccess.html");
				dispatcher.forward(request, response);
			}if (status.equals("failure")) {
				dispatcher = request.getRequestDispatcher("failure.html");
				dispatcher.forward(request, response);
			}
			if (status.equals("notexisted")) {
				dispatcher = request.getRequestDispatcher("notexisted.html");
				dispatcher.forward(request, response);
			}
			

		}
	}

}
