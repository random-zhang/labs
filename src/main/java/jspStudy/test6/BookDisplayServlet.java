package jspStudy.test6;

import jspStudy.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/BookDisplayServlet")
public class BookDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        BookDB bookDb = new BookDB();
        HashMap<String, Book> books = bookDb.selectBook();
        bookDb.close();
        HttpSession session = request.getSession();
        session.setAttribute("books", books);
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        doGet(request, response);
    }
}
