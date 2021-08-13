/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import thanglt.utils.DBConnection;

/**
 *
 * @author Thang
 */
public class OrderDetailDAO implements  Serializable{
    
    public boolean insertOrderDetail(int bookID , String title , int quantity , float unitPrice , float itemTotal , int orderID) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBConnection.getConnection();
            
            if ( conn != null) {
                String sql = " INSERT INTO tblOrderDetails(bookID , title, quantity, unitPrice, total , orderID)"
                        + " VALUES(?,?,?,?,?,?)";
                
                stm = conn.prepareStatement(sql);
                stm.setInt(1, bookID);
                stm.setString(2, title);
                stm.setInt(3, quantity);
                stm.setFloat(4, unitPrice);
                stm.setFloat(5, itemTotal);
                stm.setInt(6, orderID);
                
                int row = stm.executeUpdate();
                
                if(row > 0){
                    return true;
                }
            }
        }finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
    }
        return false;
    }
    
}
