package jspStudy.test5;

import jspStudy.Book;
import jspStudy.test6.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
@WebServlet(name = "DoCarServlet",
        urlPatterns = "/DoCarServlet"
)
public class DoCarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println();
        response.setHeader("Refresh","5;login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<String, Book> books = (HashMap<String, Book>) session.getAttribute("books");//获取图书信息
        ShopCar myCar = (ShopCar) session.getAttribute("myCar");
        if (myCar==null)
            myCar = new ShopCar();
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        if (action.equals("buy")) {
            String bookNo = request.getParameter("bookNo");
            Book temp = (Book) books.get(bookNo);
            myCar.addItem(temp);
            session.setAttribute("myCar", myCar);
            response.sendRedirect("jspStudy/test6/jsp/bookList.jsp");
            return;
        }
        if (action.equals("add")) {//加
            String bookNo = request.getParameter("bookNo");
            Book temp = (Book) books.get(bookNo);
            myCar.addItem(temp);
            session.setAttribute("myCar", myCar);
            response.sendRedirect("jspStudy/test6/jsp/showCar.jsp");
        }else if (action.equals("minus")) {
            String bookNo = request.getParameter("bookNo");
            myCar.minusItem(bookNo);
            session.setAttribute("myCar", myCar);
            response.sendRedirect("jspStudy/test6/jsp/showCar.jsp");
        } else if (action.equals("removeone")) {
            String bookNo = request.getParameter("bookNo");
            myCar.removeItem(bookNo);
            session.setAttribute("myCar",myCar);
        }
        else if (action.equals("removeselect")){
            String nos=request.getParameter("bookNos");
            String[] sub= nos.split(",");//1001,1002,1003
            for(String bookNo:sub)
                myCar.removeItem(bookNo);
            response.sendRedirect("jspStudy/test6/jsp/showCar.jsp");
        } else if (action.equals("立即购买")) {
            String nos=request.getParameter("carCheckBox");
            String[] bookNos =nos.split(",");
            if (bookNos.length==0)
                return;
            String flag = (String)session.getAttribute("isLogin");
            if(flag == null || !flag.equals("true"))
            {
                response.sendRedirect("jspStudy/test6/jsp/login.jsp");
                return;
            }else {
                String userId = (String)session.getAttribute("username");
                float totalPrice=0;
                Order order = new Order();
                order.createOrderId();
                order.setUserId(userId);
                order.setOrderTime(new Date());
                BookDB bookDb = new BookDB();
                HashMap<String, Book> orgBooks = bookDb.selectBook();

                ArrayList<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
                for (int i=0;i<bookNos.length;i++) {
                    OrderDetail orderDetail = new OrderDetail();
                    Book book = myCar.getBuylist().get(bookNos[i]);
                    orderDetail.setOrderId(order.getOrderId());
                    orderDetail.setBookNo(book.getBookNo());
                    orderDetail.setNowPrice((float)book.getNowPrice());
                    orderDetail.setBuyNum(book.getBuyNum());
                    orderDetails.add(orderDetail);
                    totalPrice+=orderDetail.getNowPrice()*orderDetail.getBuyNum();
                    book.setBookNum(book.getBookNum()-book.getBuyNum());
                    bookDb.updateBook(book);
                }
                bookDb.close();

                order.setTotalPrice(totalPrice);
                OrderDetailDB orderDetailDB = new OrderDetailDB();
                orderDetailDB.addOrderDetail(orderDetails);
                orderDetailDB.close();
                OrderDB orderDB = new OrderDB();
                orderDB.addOrder(order);
                orderDB.close();
                myCar.removeItem(bookNos);
                session.setAttribute("myCar", myCar);
                response.sendRedirect("jspStudy/test6/buySuccess.html");
                return;
            }
        }

    }
}
