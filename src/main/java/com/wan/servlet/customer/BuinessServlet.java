package com.wan.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
@WebServlet("/BuinessServlet")
public class BuinessServlet extends HttpServlet {
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
	protected void addBusiness(HttpServletRequest request,HttpServletResponse response) throws IOException {

	}
	/*
	 * 查询
	 */
	protected void getAllBusiness(HttpServletRequest request,HttpServletResponse response) {

		try {
					
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 查看
	 */
	protected void selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response) {
		try {
		
				request.getRequestDispatcher("/WEB-INF/view/customerManage/business/update.jsp").forward(request, response);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 查询联系人
	 */
	protected void getAllLinkMan(HttpServletRequest request,HttpServletResponse response) {

		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 修改
	 */
	protected void updateBuiness(HttpServletRequest request,HttpServletResponse response) throws IOException {
		JSONObject obj=new JSONObject();
		obj.put("flag", 1);
		obj.put("msg", "添加成功");
		response.getWriter().write(obj.toString());
	}
	
	/*
	 * 搜索
	 */
	protected void getSomeBuiness(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/business/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
