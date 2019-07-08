package jspStudy.test6;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDB extends DbConn{
    public int addOrderDetail(ArrayList<OrderDetail> orderDetails) {
        int result = 0;
        if (conn!=null) {
            try {
                PreparedStatement pst = conn.prepareStatement("insert into orderDetail values(?,?,?,?)");
                for (int i=0;i<orderDetails.size();i++) {
                    pst.setString(1, orderDetails.get(i).getOrderId());
                    pst.setString(2, orderDetails.get(i).getBookNo());
                    pst.setFloat(3, orderDetails.get(i).getNowPrice());
                    pst.setInt(4, orderDetails.get(i).getBuyNum());
                    pst.addBatch();
                }
                pst.executeBatch();
            }
            catch(SQLException ex) {
                System.err.println(this.getClass()+ex.getMessage());
            }
        }
        return result;
    }
}
