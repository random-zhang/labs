package jspStudy.test6;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDB extends DbConn{
    public int addOrder(Order order) {
        int result = 0;
        if (conn!=null) {
            try {
                PreparedStatement pst = conn.prepareStatement("insert into order1 values(?,?,now(),?)");
                pst.setString(1, order.getOrderId());
                pst.setString(2, order.getUserId());
                //order.getOrderId()
                //pst.setTimestamp(3, new java.sql.Timestamp(order.getOrderTime().getTime()));
                pst.setFloat(3, order.getTotalPrice());
                result = pst.executeUpdate();
            }
            catch(SQLException ex) {
                System.err.println(this.getClass()+ex.getMessage());
            }
        }
        return result;
    }
}
