<%@ page language="java" contentType="text/html;charset-UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jspStudy.Book"%>
<%@ page import="jspStudy.test4.Bookcar" %>
<head>
         <title>购物车页面</title>
         <link type="text/css" href="../css/myCart.css" rel="stylesheet"/>
         <script type="text/javascript" src="../js/myCart.js"></script>
    </head>
<body>
 <div>您的位置：<a href="bookInit.jsp">首页</a> >我的购物车</div>
  <div id="content">
      <form action="" method="post" name="myform" >
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr class="tableboder">
                  <td class="title" width="60";>
                      <input id="allCheckBox" type="checkbox"onclick="selectAll()" />全选
                  </td>
                  <td class="title" colspan="2">书籍名称
                  </td>
                  <td class="title" width="80">单价(元) </td>
                  <td class="title" width="80">数量</td>
                  <td class="title" width="100">小计(元) </td>
                  <td class="title" width="60">操作</td>
              </tr>
 <%
     request.setCharacterEncoding("UTF-8");
     Bookcar bookcar=(Bookcar)session.getAttribute("bookcar");
   if (bookcar == null|| bookcar.getSize()== 0) {
       %>
              <tr height="100">
                  <td colspan="7" align="center">没有商品可显示! </td>
              </tr>
                  <%} else{
                     Book book;
                     for(HashMap.Entry<String, Book> entry : bookcar.getBooks().entrySet()){
                         book=(Book)entry.getValue();
                         %>
              <tr class="tableboder">
                  <td class="cart_td_1">
                      <input name="cartCheckBox" id="cartCheckBox" type="checkbox" value="<%=entry.getKey()%>"  onclick="selectSingle()"/>
                  </td>
                  <td class="cart_td_2"><img src="../images/<%=book.getBookCover()%>" alt="<%=book.getBookCover()%>"/></td>
                  <td class="cart_td_3"><a href="#"><%=book.getBookName()%></a></td>
                  <td class="cart_td_4"><%=book.getNowPrice()%></td>
                  <td class="cart_td_5">                      <!--    minus1001,minus1002 ,num1001, -->
                      <img src="../../images/minus.jpg" class="hand" name="minus<%=book.getBookNo() %>" id="minus<%=book.getBookNo() %>" onclick="clickminus(<%=book.getBookNo()%>,<%=book.getNowPrice()%>)"/>
                      <input name="num<%=book.getBookNo() %>" id="num<%=book.getBookNo() %>" type="text" value="<%=book.getBookNum()%>" class="num_input" readonly="readonly" />
                      <img src="../../images/add.jpg" class="hand" id="add<%=book.getBookNo()%>" onclick="clickadd(<%=book.getBookNo()%>,<%=book.getNowPrice()%>)"  />
                  </td>
                  <td class="cart_td_6" id="total<%=book.getBookNo() %>"><%=book.getBookNum()*book.getNowPrice()%>元</td>
                  <td class="cart_td_7"><a href="doCar.jsp?action=removeone&bookNo=<%=entry.getKey() %>">删除</a></td>
              </tr>
              <%}
              }
              %>
          </table>
          <table width="100%" border="0" cellspacing="O" cellpadding="0">
              <tr>
                  <td bgcolor=white>
                      <!--<a class="bn" href="doCar.jsp?action=removeselect&bookNo=''">删除所选</a></td>-->
                      <input type="button" class="bn" value="删除所选"  onclick="cancelSelected()">
                  <td class="shopend"><br/>商品总价(不含运费):
                      <label id="total" class="yellow" >0 </label>元<br/><br/>
                      <!--<a class="bn" href="">立刻购买</a>-->
                  <input type="button" class="bn" value="立刻购买"onclick="purchase()">
                     </td>
              </tr>
          </table>
      </form>
  </div>
</body>
</html>