package com.wan.servlet.customer;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;

@WebServlet("/LinkManServlet")
public class LinkManServlet extends HttpServlet{
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
	protected void addLinkMan(HttpServletRequest request,HttpServletResponse response) throws IOException {
		JSONObject obj=new JSONObject();
		obj.put("flag", 1);
		obj.put("msg", "添加成功");
		response.getWriter().write(obj.toString());
	}
	/*
	 * 查询
	 */
	protected void getAllLinkMan(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/index.jsp").forward(request, response);
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
			String id = request.getParameter("type");
			if(!"1".equals(id)&&id!="1"){
				request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/update.jsp").forward(request, response);
			    return;
			}else{
			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/view.jsp").forward(request, response);
		
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 修改
	 */
	protected void updateLinkMan(HttpServletRequest request,HttpServletResponse response) {
		try {
			
			JSONObject obj=new JSONObject();
			obj.put("flag", 1);
			obj.put("msg", "添加成功");
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 删除
	 */
	protected void deleteLinkMan(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()+ "/LinkManServlet?method=getAllLinkMan");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
	}
	
	/*
	 * 搜索
	 */
	protected void getOneLinkMan(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 查询列表
	 */
	protected void getAllCustomer(HttpServletRequest request,HttpServletResponse response) {
		try {
    			request.getRequestDispatcher("/WEB-INF/view/customerManage/contacts/create.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
