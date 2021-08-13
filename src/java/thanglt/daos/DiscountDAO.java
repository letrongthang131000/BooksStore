/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thanglt.utils.DBConnection;

/**
 *
 * @author Thang
 */
public class DiscountDAO implements Serializable {

    public boolean checkDiscountCodeExist(int discountCode) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT code FROM tblDiscounts"
                        + " WHERE code = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, discountCode);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean createDiscount(int code, float discountValue) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " INSERT INTO tblDiscounts(code,discount, isUsed)"
                        + " VALUES(?,?,0)";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, code);
                stm.setFloat(2, discountValue);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean checkDiscountCode(int discountCode) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT code FROM tblDiscounts"
                        + " WHERE code = ? AND isUsed= 0";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, discountCode);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }
    
    public float getDiscountValue(int discountCode) throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        float discountValue = 0;
        try {
            conn = DBConnection.getConnection();
            
            if(conn != null) {
                String sql = " SELECT discount FROM tblDiscounts"
                        + " WHERE code = ?";
                
                stm= conn.prepareStatement(sql);
                stm.setInt(1, discountCode);
                
                rs = stm.executeQuery();
                
                if (rs.next()) {
                    discountValue = rs.getFloat("discount");
                }
            }
        }finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
    }
        return discountValue;
    }

    public boolean useDiscountCode(int code, String userID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " UPDATE tblDiscounts"
                        + " SET userID = ? , isUsed = 1 "
                        + " WHERE code = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setInt(2, code);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }

            }
        } finally {
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
