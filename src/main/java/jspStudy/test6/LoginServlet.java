package jspStudy.test6;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userVCode=request.getParameter("userVCode");
        HttpSession session=request.getSession();
        String validateCode=(String)session.getAttribute("validateCode");
        session.setAttribute("username",username);
        if (userVCode.equalsIgnoreCase(validateCode)) {
            UserDB userDB= new UserDB();
            if(userDB.isLogin(username, password) ) {
                session.setAttribute("isLogin", "true");
                response.sendRedirect("jspStudy/test6/jsp/success.jsp");
            }
            else {
                session.setAttribute("isLogin", "false");
                response.sendRedirect("jspStudy/test6/jsp/error.jsp");
            }
            userDB.close();
        }
        else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('验证码错误');history.go(-1);</script>");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
           doGet(request, response);
    }
}