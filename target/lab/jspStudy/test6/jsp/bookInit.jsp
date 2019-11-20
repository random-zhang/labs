<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="jspStudy.Book" %>
<%@ page import="java.util.HashMap" %>
<%

%>
<%
        HashMap<String,Book> books=new HashMap<>();//假设有4本书,下面的语句用来分别给出这4本书的相关信息
String[] bookNo={"1001","1002","1003","1004"};
String[] bookCover={"book01.jpg","book02.jpg","book03.jpg","book04.jpg"};
String[] bookName={"图书1","图书2","图书3","图书4"};
double[] nowPrice={11.11,22.2,33.3,44.4};
double[] orgPrice={22.2,33.3,44.4,55.5};
int[] comments={11,22,33,44};
String[] Author={"甲","乙","丙","丁"};
String[] PressDate={"2月2日","2月2日","2月2日","2月2日"};
String[] Press={"南工程出版社","南工程出版社","南工程出版社","南工程出版社"};
int[] bookNums={11,22,33,44};
for(int i=0;i<4;i++) {
    Book book = new Book();
    book.setBookNo(bookNo[i]);
    book.setBookCover(bookCover[i]);
    book.setBookName(bookName[i]);
    book.setNowPrice(nowPrice[i]);
    book.setOrgPrice(orgPrice[i]);
    book.setComments(comments[i]);
    book.setAuthor(Author[i]);
    book.setPressDate(PressDate[i]);
    book.setPress(Press[i]);
    book.setBookNum(bookNums[i]);
    books.put(bookNo[i], book);
}
 %>
<%
    session.setAttribute("books",books);
    response.sendRedirect("bookList.jsp");
    %>