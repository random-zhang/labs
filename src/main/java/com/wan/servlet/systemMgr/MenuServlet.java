package com.wan.servlet.systemMgr;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void getAllMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/index.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void  getAllParentMenu(HttpServletRequest request,HttpServletResponse response){
			try {
				request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/create.jsp").forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	protected void addMenu(HttpServletRequest request,HttpServletResponse response){
			try {
				response.sendRedirect(request.getContextPath()+ "/MenuServlet?method=getAllMenu");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	protected void deleteMenuById(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/MenuServlet?method=getAllMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void getUpdateMenudetailById(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/menu/update.jsp").forward(request,response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void updateMenu(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/MenuServlet?method=getAllMenu");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
