/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.tbl_User;

/**
 *
 * @author DELL
 */
public class Tbl_UserDTO {

    private String userId;
    private String password;
    private String fullName;
    private boolean role;

    public Tbl_UserDTO() {
    }

    public Tbl_UserDTO(String userId, String password, String fullName, boolean role) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Tbl_UserDTO{" + "userId=" + userId + ", password=" + password + ", fullName=" + fullName + ", role=" + role + '}';
    }

}
