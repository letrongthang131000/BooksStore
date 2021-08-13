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
public class UserDAO implements Serializable {

    public boolean checkLogin(String userName, String password) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT userID"
                        + " FROM tblUsers"
                        + " WHERE userID = ? AND password = ? AND status='Active' ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setString(2, password);
                
                rs= stm.executeQuery();
                
                if(rs.next()){
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

    public String getUserName(String userID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String userName = "";

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT userName"
                        + " FROM tblUsers"
                        + " WHERE userID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userName = rs.getString("userName");
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
        return userName;
    }

    public boolean isAdmin(String userID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        boolean result = false;
        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT isAdmin"
                        + " FROM tblUsers"
                        + " WHERE userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getBoolean("isAdmin");
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
        return result;
    }
}
