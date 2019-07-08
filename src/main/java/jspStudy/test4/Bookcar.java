package jspStudy.test4;

import jspStudy.Book;

import java.util.HashMap;

public class Bookcar {//购物车
     HashMap<String, Book> books=new HashMap<>();
    public void addItem(Book book){
            books.put(book.getBookNo(),book);
    }
    public void removeItem(String bookNo){
           books.remove(bookNo);
    }
    public int getSize(){
        return books.size();
    }
    public Book getItem(String bookNo){
        return books.get(bookNo);
    }
    public void updateItem(String bookNo,Book book){//更新图书数据
        books.put(bookNo,book);
    }
    public  HashMap<String, Book> getBooks(){
        return books;
    }
}
