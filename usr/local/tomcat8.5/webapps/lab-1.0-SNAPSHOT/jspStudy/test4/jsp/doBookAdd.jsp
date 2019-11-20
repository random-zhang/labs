<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*,com.jspsmart.upload.*" %>
<%@ page import="jspStudy.Book" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>кՐ᮷Ԧ</title>
</head>
<body>
<%
        try{
            SmartUpload upload = new SmartUpload();
            upload.initialize(pageContext);
            upload.setCharset("utf-8");
            upload.setAllowedFilesList("jpg,jpeg,bmp,png");
            upload.upload();
            upload.save("jspStudy/images");
            String bookNo = upload.getRequest().getParameter("bookNo");
            String bookName = upload.getRequest().getParameter("bookName");
            String author = upload.getRequest().getParameter("author");
            String press = upload.getRequest().getParameter("press");
            String pressDate = upload.getRequest().getParameter("pressDate");
            String bookNum = upload.getRequest().getParameter("bookNum");
            String orgPrice = upload.getRequest().getParameter("orgPrice");
            String nowPrice = upload.getRequest().getParameter("nowPrice");
            File file = upload.getFiles().getFile(0);
            Book book = new Book();
            book.setBookNo(bookNo);
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setPress(press);
            book.setPressDate(pressDate);
            int num;
            if (bookNum==null || bookNum.trim().equals(""))
                num = 0;
            else
                num = Integer.parseInt(bookNum);
            float oPrice,nPrice;
            if (orgPrice==null || orgPrice.trim().equals(""))
                oPrice = 0;
            else
                oPrice = Float.parseFloat(orgPrice);
            if (nowPrice==null || nowPrice.trim().equals(""))
                nPrice = 0;
            else
                nPrice = Float.parseFloat(nowPrice);
            book.setBookNum(num);
            book.setOrgPrice(oPrice);
            book.setNowPrice(nPrice);
            book.setBookCover(file.getFileName());
            HashMap<String, Book> books = (HashMap<String, Book>)session.getAttribute("books");
            if (books==null)
                books = new HashMap<>();
            books.put(book.getBookNo(), book);
            session.setAttribute("books",books);
            response.sendRedirect("../../test3/jsp/bookList.jsp");
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }

%>
</body>
</html>