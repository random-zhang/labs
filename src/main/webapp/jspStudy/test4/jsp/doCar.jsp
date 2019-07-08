<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jspStudy.Book"%>
<%@ page import="jspStudy.test4.Bookcar" %>
<%
 request.setCharacterEncoding("UTF-8");
/**
 *实验4
 */
 //HashMap<String,Book>bookcar= (HashMap<String,Book>)session.getAttribute("bookcar");
  HashMap<String,Book>books =(HashMap<String,Book>)session.getAttribute("books");
    Bookcar bookcar=(Bookcar)session.getAttribute("bookcar");
 String action = request.getParameter("action");
  if (action == null)
      action ="";
  if (action.equals("buy")){//购买商品
        String bookNo = request.getParameter("bookNo");
        if (bookcar == null||bookcar.getSize()== 0) {//如果cart中不存在任何商品
            Book temp = (Book) books.get(bookNo);
            temp.setBookNum(1);
            bookcar = new Bookcar();
            bookcar.addItem(temp);
            session.setAttribute("bookcar", bookcar);
        }else {//如果cart中存在其他商品
            Book temp = (Book) bookcar.getItem(bookNo);
            if (temp == null) {
                temp = (Book) books.get(bookNo);
                temp.setBookNum(1);
                bookcar.addItem(temp);
            } else {//如果cart中存在该商品,购买数量加1
                temp.setBookNum(temp.getBookNum() + 1);
                bookcar.updateItem(bookNo, temp);
                session.setAttribute("bookcar", bookcar);
            }
        }
             response.sendRedirect("bookList.jsp");
        } else if (action.equals("removeone")){
           String bookNo = request.getParameter("bookNo");
           bookcar.removeItem(bookNo);
           response.sendRedirect("showCar.jsp");
        }else if (action.equals("removeselect")){ //该功能此处省略,放拓展练习中
           String nos=request.getParameter("bookNos");
           String[] sub= nos.split(",");//1001,1002,1003
           for(String bookNo:sub)
               bookcar.removeItem(bookNo);
            response.sendRedirect("showCar.jsp");
        }else if(action.equals("minusNum")){
            String bookNo = request.getParameter("bookNo");
            String bookNum = request.getParameter("bookNum");
            Book book=bookcar.getItem(bookNo);
            book.setBookNum(Integer.parseInt(bookNum));
            response.sendRedirect("showCar.jsp");
        }else if(action.equals("addNum")){
           String bookNo = request.getParameter("bookNo");
           String bookNum = request.getParameter("bookNum");
           Book book=bookcar.getItem(bookNo);
           book.setBookNum(Integer.parseInt(bookNum));
           response.sendRedirect("showCar.jsp");
  }
    %>