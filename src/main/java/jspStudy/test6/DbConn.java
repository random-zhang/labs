package jspStudy.test6;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    private String sDBDriver = "com.mysql.jdbc.Driver";
    private String sConnStr = "jdbc:mysql://47.100.47.134:3306/study?useUnicode=true&amp;characterEncoding=utf-8";
    private String sUsername="root",sPassword="zqt19980805";
    protected Connection conn = null;
    /*public DbConn() {
        Connection con = null;
        try {
            Class.forName(sDBDriver);
            con = DriverManager.getConnection(sConnStr,sUsername,sPassword);
        } catch(java.lang.ClassNotFoundException e) {
           e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        this.conn=con;
    }*/
    public void close(){
        try{
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DbConn(){
        Connection conn=null;
        try {
            Context context=new InitialContext();
            DataSource  dataSource=(DataSource)context.lookup("java:comp/env/jdbc/booklib");
            conn=dataSource.getConnection();
            if(conn==null) System.out.println("连接池为空");
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        this.conn=conn;
    }
}
