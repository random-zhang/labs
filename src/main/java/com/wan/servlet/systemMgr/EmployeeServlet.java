package com.wan.servlet.systemMgr;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {


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

	protected void getAllEmployee(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/index.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void findEmployeeById(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/update.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void getDeapartAndPostionInfo(HttpServletRequest request,	HttpServletResponse response) {
		try {
			
			
			
			
			//request.setAttribute("", list1);
			//request.setAttribute("", list2);
			request.getRequestDispatcher("/WEB-INF/view/systemSettings/manage/create.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void checkParentEmployee(HttpServletRequest request,HttpServletResponse response) {
		JSONObject json = new JSONObject();
		try {
			
		String parentId  = 	request.getParameter("");
		Integer parentId1;
		
		
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void addEmployee(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()+ "/EmployeeServlet?method=getAllEmployee");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void updateEmployee(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()+ "/EmployeeServlet?method=getAllEmployee");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void updateEmployeeStatus(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/EmployeeServlet?method=getAllEmployee");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
