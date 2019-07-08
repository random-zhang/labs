package com.wan.servlet.systemMgr;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;

@WebServlet("/EmmPositionServlet")
public class EmmPositionServlet extends HttpServlet {
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

	protected void getAllEmmPosition(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/index.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	protected void addPosition(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void positionNameCheck(HttpServletRequest request,HttpServletResponse response){
		JSONObject json = new JSONObject();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void deletePostionCheck(HttpServletRequest request,HttpServletResponse response) throws IOException{
		JSONObject json = new JSONObject();
		response.getWriter().write(json.toString());
	}
	
	
	protected void deletePosition(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void getUpdatePostionDetailById(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void updatePosition(HttpServletRequest request,HttpServletResponse response){
	
		try {	
			response.sendRedirect(request.getContextPath()+ "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void getLinkPostionDetailById(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/position/references.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void positionLinks(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/EmmPositionServlet?method=getAllEmmPosition");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
