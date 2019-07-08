package com.wan.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdvanceLogServlet")
public class AdvanceLogServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected void selectBuines(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/createTj.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 新增
	 */
	protected void addAdvanceLog(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
	}
	
	/*
	 * 查询
	 */
	protected void getAllAdvanceLog(HttpServletRequest request,HttpServletResponse response) {

		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/view.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 删除
	 */ 
	protected void deleteAdvanceLog(HttpServletRequest request,HttpServletResponse response) {

		try {
			response.sendRedirect(request.getContextPath()+ "/AdvanceLogServlet?method=getAllAdvanceLog");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
