package jspStudy.test5;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(
        urlPatterns = { "/jspStudy/test6/bookAdd.html", "/jspStudy/test5/bookAdd.html" },
        initParams = {
                @WebInitParam(name = "backurl", value = "jsp/login.jsp")
        })
public class LoginFilter implements Filter {
    String backUrl;
    public void destroy(){
    }
    public void init(FilterConfig filterConfig)throws ServletException
    {
        if(filterConfig.getInitParameter("backurl")!=null)
        {
            backUrl = filterConfig.getInitParameter("backurl");
        }
        else
            backUrl = "index.jsp";
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        HttpServletResponse httpResponse=(HttpServletResponse)response;
        HttpSession session = httpRequest.getSession();
        String flag = (String)session.getAttribute("isLogin");
        if(flag == null || !flag.equals("true"))
        {
            httpResponse.sendRedirect(backUrl);
        }
        else
            //лањ Filter
            chain.doFilter(request,response);
    }
}