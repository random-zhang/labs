<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jspStudy.Book" %>
<%@ page import="java.util.HashMap" %>
<%
    HashMap<String, Book> books =(HashMap<String,Book>) session.getAttribute("books");
%>
<head>
    <title>图书网</title>
     <link type="text/css" href="../css/booklist.css" rel="stylesheet"/>
</head>
 <body>
<%
        if (books == null||books.size() == 0){
%>
        <div>没有商品可显示!</div>
 <%
}else{
    Book book;
    %>
<table style="float: left" width="30%">
      <%
    for (HashMap.Entry<String,Book> entry:books.entrySet()){
        book=(Book)entry.getValue();
 %>
    <tr>
        <th>
            <div class="pic" href="" target="_blank">
                --<img src='../../images/<%=book.getBookCover()%>'/>
            </div>
        </th>
        <th>
            <div class="desc">
                <p>
                    <a href="" target="_blank"><%=book.getBookName()%>
                    </a>
                </p>
                <p>
                    <span>现价: &yen;<%=book.getNowPrice()%></span>
                    <span class="price">&nbsp;&nbsp;定价:<span>&yen;<%=book.getOrgPrice()%></span>
                    </span>
                </p>
                <p style="height: 10px">
                    <span class="star"></span>
                    <a href="" target="_blank"> <%=book.getComments()%></a>
                </p>
                <p>
                    <span><a href=""><%=book.getAuthor()%></a>著</span>
                    <span>/<%=book.getPressDate()%></span>
                    <span>/<a href=""><%=book.getPress()%></a></span>
                </p>
                <p class="detail"></p>
                <div>
                    <p>
                        <a class="bn" href="../../../DoCarServlet?action=buy&bookNo=<%=entry.getKey()%>">加入购物车</a>
                        <a class="bn" href="">收藏</a>
                    <p>
                </div>
            </div>
        </th>
    </tr>
     <%
        }
        }
        %>
</table>
    <div style="float: left"><a class="bn" href='showCar.jsp'>查看购物车</a>
    </div>
 </body>