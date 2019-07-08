package jspStudy.test5;

import jspStudy.Book;

import java.util.HashMap;

public class ShopCar {
    private HashMap<String, Book> buylist; //用来存储购买的商品
    private int size;
    public ShopCar() {
        this.buylist = new HashMap<String, Book>();
    }
   public Book getItem(String bookNo){
        return  buylist.get(bookNo);
   }
    public HashMap<String, Book> getBuylist() {
        return buylist;
    }

    public void addItem(Book book, int num) {
        if (book != null) {
            String bookNo = book.getBookNo();
            Book temp = (Book) buylist.get(bookNo);
            if (temp != null) {
                temp.setBuyNum(temp.getBuyNum() + num);
            } else {
                temp = book;
                temp.setBuyNum(num);
                buylist.put(bookNo, temp);
            }
        }
    }
    public void addItem(Book book) {
        addItem(book, 1);
    }

    public void removeItem(String bookNo) {
        buylist.remove(bookNo);
    }

    public void removeItem(String[] bookNos) {
        if (bookNos != null) {
            for (int i = 0; i < bookNos.length; i++)
                buylist.remove(bookNos[i]);
        }
    }

    public void minusItem(String bookNo) {
        Book temp = (Book) buylist.get(bookNo);
        if (temp != null) {
            int num = temp.getBuyNum();
            if (num > 1)
                temp.setBuyNum(num - 1);
            else if (num == 1)
                buylist.remove(bookNo);
        }
    }
    public void addNum(String bookNo) {
        Book temp = (Book) buylist.get(bookNo);
        if (temp != null) {
            int num = temp.getBuyNum();
            if (num > 1)
                temp.setBuyNum(num+1);
            else if (num == 1)
                buylist.remove(bookNo);
        }
    }
    public void clearCar() {
        buylist.clear();
    }
    public int getSize(){
        return buylist.size();
    }
}

