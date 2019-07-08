package com.wan.servlet.login;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wan.dto.LoginDto;
import com.wan.exception.ServiceException;
import com.wan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebServlet(name = "LoginServlet",
		urlPatterns = "/LoginServlet"
)
public class LoginServlet extends HttpServlet{
	/*LoginServiceI loginServiceI = (LoginServiceI) ObjectFactory
			.getObject("loginServiceI");*/
	@Autowired
	LoginService loginService=null;
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
	
	protected void loginCheck(HttpServletRequest request,HttpServletResponse response){
		/*在数据库中比对

		 */
		String employeeId=request.getParameter("employeeId ");
		String password=request.getParameter("password");
		try {
			 LoginDto dto=loginService.getUser(employeeId,password);
			 if(dto!=null){
               if(!password.equals("123456")){
               	  loginToCms(request,response);
			   }else{
				   try {
					   response.sendRedirect(request.getContextPath()+ "/wanho/updatepassword.jsp");
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
			   }
			 }
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println("方法执行了2");
		
	
	}
	
	protected void loginToCms(HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println("方法执行了");
			response.sendRedirect(request.getContextPath()+ "/ControlServlet?method=countAll");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void updatePassword(HttpServletRequest request,HttpServletResponse response){
		
	}
	protected void logout(HttpServletRequest request,HttpServletResponse response){
		try {
			response.sendRedirect(request.getContextPath()+ "/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
