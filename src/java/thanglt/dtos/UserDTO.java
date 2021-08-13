/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.dtos;

import java.io.Serializable;

/**
 *
 * @author Thang
 */
public class UserDTO implements Serializable{
    private String userID;
    private String password;
    private String userName;
    private boolean isAdmin;
    private String status;

    public UserDTO() {
    }

    public UserDTO(String userID, String password, String userName, boolean isAdmin, String status) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
