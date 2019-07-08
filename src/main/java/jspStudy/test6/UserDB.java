package jspStudy.test6;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB extends DbConn {
    public boolean isLogin(String userName, String userPwd) {
        boolean flag=false;
        if (conn!=null) {
            try {
                PreparedStatement pst = conn.prepareStatement("select * from user where userName=? and userPwd=?");
                pst.setString(1, userName);
                pst.setString(2, userPwd);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    flag=true;
                }
                rs.close();
            }
            catch(SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return flag;
    }
}
