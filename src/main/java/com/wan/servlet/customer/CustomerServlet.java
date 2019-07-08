package com.wan.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerServlet")
public class CustomerServlet  extends HttpServlet{
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
	/*
	 * 新增
	 */
	protected void addCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
	}
	
	/*
	 * 查询列表
	 */
	protected void getAllCustomer(HttpServletRequest request,HttpServletResponse response) {
		try {
    		request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 查询列表
	 */
	protected void getAllZd(HttpServletRequest request,HttpServletResponse response) {
		try {
    		request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 单个数据查询
	 */
	protected void selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response) {
		try {
				request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/update.jsp").forward(request, response);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 修改
	 */
	protected void updateCustomer(HttpServletRequest request,HttpServletResponse response) {

	}
	
	/*
	 * 搜索
	 */
	protected void getSomeCustomer(HttpServletRequest request,HttpServletResponse response) throws ParseException {
		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/customer/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
