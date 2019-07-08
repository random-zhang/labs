package jspStudy;

public class Book {
    private String bookNo;
    private String bookCover;
    private String bookName;
    private double nowPrice;
    private double orgPrice;
    private int comments;
    private String author;
    private String pressDate;
    private String press;
    private int bookNum;
    private int buyNum;
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public double getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(double orgPrice) {
        this.orgPrice = orgPrice;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPressDate() {
        return pressDate;
    }

    public void setPressDate(String pressDate) {
        this.pressDate = pressDate;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }
    public void setBuyNum(int num){
        this.buyNum=num;
    }
    public int getBuyNum(){
        return  buyNum;
    }
}
