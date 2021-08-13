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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thanglt.dtos.BookDTO;
import thanglt.utils.DBConnection;

/**
 *
 * @author Thang
 */
public class BookDAO implements Serializable {

    List<BookDTO> listBook;

    public List<BookDTO> getListBook() {
        return listBook;
    }

    public void setListBook(List<BookDTO> listBook) {
        this.listBook = listBook;
    }

    public void loadAll() throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT tblBooks.bookID , title , price , image , author,"
                        + " tblCategorys.categoryName , description , quantity , createDate , status"
                        + " FROM tblBooks , tblCategorys"
                        + " WHERE status = 'Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0";

                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");
                    String status = rs.getString("status");

                    BookDTO dto = new BookDTO(bookID, title, price, image, author, description, quantity, createDate, status, categoryName);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
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

    public void searchByName(String searchValue) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT tblBooks.bookID , title , price , image , author,"
                        + " tblCategorys.categoryName , description , quantity , createDate , status"
                        + " FROM tblBooks , tblCategorys"
                        + " WHERE status = 'Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0"
                        + " AND title like ?";

                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");
                    String status = rs.getString("status");

                    BookDTO dto = new BookDTO(bookID, title, price, image, author, description, quantity, createDate, status, categoryName);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
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

    public void searchByRangePrice(float min, float max) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql;
                if (min != -1 && max != -1) {
                    sql = " SELECT tblBooks.bookID , title , price , image , author,"
                            + " tblCategorys.categoryName , description , quantity , createDate , status"
                            + " FROM tblBooks , tblCategorys"
                            + " WHERE status = 'Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0"
                            + " AND price >= ? AND price <= ?";
                    stm = conn.prepareStatement(sql);
                    stm.setFloat(1, min);
                    stm.setFloat(2, max);
                    rs = stm.executeQuery();

                } else {
                    if (min == -1) {
                        sql = " SELECT tblBooks.bookID , title , price , image , author,"
                                + " tblCategorys.categoryName , description , quantity , createDate , status"
                                + " FROM tblBooks , tblCategorys"
                                + " WHERE status = 'Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0"
                                + " AND price <= ?";
                        stm = conn.prepareStatement(sql);

                        stm.setFloat(1, max);
                        rs = stm.executeQuery();
                    } else {
                        sql = " SELECT tblBooks.bookID , title , price , image , author,"
                                + " tblCategorys.categoryName , description , quantity , createDate , status"
                                + " FROM tblBooks , tblCategorys"
                                + " WHERE status = 'Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0"
                                + " AND price >= ?";
                        stm = conn.prepareStatement(sql);

                        stm.setFloat(1, min);
                        rs = stm.executeQuery();
                    }
                }
                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");
                    String status = rs.getString("status");

                    BookDTO dto = new BookDTO(bookID, title, price, image, author, description, quantity, createDate, status, categoryName);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
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
    
    public void searchByCategory(String categoryValue) throws NamingException, SQLException {
        
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            
            if (conn != null) {
                String sql = "SELECT tblBooks.bookID, title, price, image, author, "
                        + "tblCategorys.categoryName, description, quantity, createDate, status "
                        + "FROM tblBooks, tblCategorys "
                        + "WHERE status='Active' AND tblBooks.categoryID=tblCategorys.categoryID AND quantity > 0 "
                        + "AND tblCategorys.categoryName=?";
                
                stm = conn.prepareStatement(sql);
                stm.setString(1, categoryValue);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int bookID = rs.getInt("bookID");
                    String title = rs.getString("title");
                    float price = rs.getFloat("price");
                    String image = rs.getString("image");
                    String author = rs.getString("author");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");
                    String status = rs.getString("status");
                    
                    BookDTO dto = new BookDTO(bookID, title, price, image, author, description, quantity, createDate, status, categoryName);
                    if (listBook == null) {
                        listBook = new ArrayList<>();
                    }
                    listBook.add(dto);
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

    public boolean createBook(String title, String price, String author, int categoryID, String image, String quantity, String description) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblBooks(title, description, price, author, categoryID, quantity , image , status)"
                        + " VALUES(?,?,?,?,?,?,?,'Active')";

                stm = conn.prepareStatement(sql);
                stm.setString(1, title);
                stm.setString(2, description);
                stm.setString(3, price);
                stm.setString(4, author);
                stm.setInt(5, categoryID);
                stm.setString(6, quantity);
                stm.setString(7, image);

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

    public boolean updateBook(String bookID, String title, String price, String author, int categoryID, String image, String quantity, String description, String createDate) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblBooks SET title = ? , description = ? , price = ? ,"
                        + " author = ? , createDate = ? , quantity = ? , image = ? , categoryID= ?"
                        + " WHERE bookID = ?";

                stm = conn.prepareStatement(sql);
                stm.setString(1, title);
                stm.setString(2, description);
                stm.setString(3, price);
                stm.setString(4, author);
                stm.setString(5, createDate);
                stm.setString(6, quantity);
                stm.setString(7, image);

                stm.setInt(8, categoryID);
                stm.setString(9, bookID);
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

    public boolean deleteBook(String bookID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblBooks"
                        + " SET status = 'InActive'"
                        + " WHERE bookID = ?";

                stm = conn.prepareStatement(sql);
                stm.setString(1, bookID);

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
    
    public int getQuantity(int bookID) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantity = 0;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " SELECT quantity FROM tblBooks"
                        + " WHERE bookID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, bookID);
                

                rs = stm.executeQuery();
                if ( rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }

        } finally {
            if( rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

    public boolean setQuantity(int bookID, int quantity) throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBConnection.getConnection();

            if (conn != null) {
                String sql = " UPDATE tblBooks"
                        + " SET quantity = ?"
                        + " WHERE bookID = ?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, bookID);

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
