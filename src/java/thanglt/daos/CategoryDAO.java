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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thanglt.dtos.CategoryDTO;
import thanglt.utils.DBConnection;

/**
 *
 * @author Thang
 */
public class CategoryDAO implements Serializable{
    
    List<CategoryDTO> listCategory;

    public List<CategoryDTO> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryDTO> listCategory) {
        this.listCategory = listCategory;
    }
    
    public void loadCategory() throws SQLException, NamingException{
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn= DBConnection.getConnection();
            if( conn != null) {
                String sql = " SELECT categoryID , categoryName"
                        + " FROM tblCategorys";
                
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");
                    
                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);
                    if(listCategory == null) {
                        listCategory = new ArrayList<>();
                    }
                    listCategory.add(dto);
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
    }
    
    
    
}
