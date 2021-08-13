/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.daos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thanglt.dtos.OrderDTO;
import thanglt.utils.DBConnection;

/**
 *
 * @author Thang
 */
public class OrderDAO implements Serializable {

    List<OrderDTO> listOrder;

    public List<OrderDTO> getListOrder() {
        return listOrder;
    }

    public boolean insertOrder(String userID, float total) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblOrders(userID,total)"
                        + " VALUES (?,?) ";

                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setFloat(2, total);

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

    public int getLastestID() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int orderID = 0;
        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT orderID= max(orderID)"
                        + " FROM tblOrders";

                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    orderID = rs.getInt("orderID");
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
        return orderID;
    }

    public void loadAllOrder(String userID) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = "SELECT o.total AS totalOrder, o.orderDate, o.orderDay, o.orderID, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "FROM tblOrders o, tblOrderDetails od "
                        + "WHERE o.orderID = od.orderID and o.userID = ? "
                        + "ORDER BY o.orderDate DESC";

                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    float total = rs.getFloat("total");
                    float totalOrder = rs.getFloat("totalOrder");

                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }

                    OrderDTO dto = new OrderDTO(orderID, orderDate, orderDay, bookID, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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

    }

    public void searchByName(String userID, String searchValue) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = "SELECT o.total AS totalOrder, o.orderDate, o.orderDay, o.orderID, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "FROM tblOrders o, tblOrderDetails od "
                        + "WHERE o.orderID = od.orderID AND o.userID = ? "
                        + "AND od.title like ? "
                        + "ORDER BY o.orderDate DESC";

                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, "%" + searchValue + "%");

                rs = stm.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    float total = rs.getFloat("total");
                    float totalOrder = rs.getFloat("totalOrder");

                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }

                    OrderDTO dto = new OrderDTO(orderID, orderDate, orderDay, bookID, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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

    }

    public void searchByDate(String userID, String date) throws SQLException, NamingException {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = "SELECT o.total AS totalOrder, o.orderDate, o.orderDay, o.orderID, od.bookID, od.title, od.quantity,od.unitPrice, od.total "
                        + "FROM tblOrders o, tblOrderDetails od "
                        + "WHERE o.orderID = od.orderID AND o.userID = ? "
                        + "AND o.orderDay = ?  "
                        + "ORDER BY o.orderDate DESC";

                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, date);

                rs = stm.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String orderDay = rs.getString("orderDay");
                    String bookID = rs.getString("bookID");
                    String title = rs.getString("title");
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    float total = rs.getFloat("total");
                    float totalOrder = rs.getFloat("totalOrder");

                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }

                    OrderDTO dto = new OrderDTO(orderID, orderDate, orderDay, bookID, title, quantity, unitPrice, total, totalOrder);
                    listOrder.add(dto);
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

    }
}
