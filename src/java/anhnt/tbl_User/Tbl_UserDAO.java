/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.tbl_User;

import anhnt.Utilities.DBUtilities;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class Tbl_UserDAO implements Serializable {

    public List<String> getColumnNames() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtilities.makeConnection();
            String sql = "SELECT * FROM tbl_User";
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            List<String> columnNames = new ArrayList<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            if (!columnNames.isEmpty()) {
                return columnNames;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBUtilities.makeConnection();
            //2. Prepare sql String
            if (con != null) {
                String sql = "SELECT * FROM tbl_User "
                        + "WHERE userID = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                System.out.println(username);
                stm.setString(2, password);
                System.out.println(password);
                //3. Execute and store in ResultSet    
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }//end if con

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    private List<Tbl_UserDTO> listAccount;

    public List<Tbl_UserDTO> getListAccount() {
        return listAccount;
    }

    public void searchLastName(String lastname)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Establish DB connection
            con = DBUtilities.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT * FROM tbl_User "
                        + "WHERE fullName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + lastname + "%");
                //3. Execute and store in result set
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString(1);
                    String password = rs.getString(2);
                    String fullName = rs.getString(3);
                    boolean role = rs.getBoolean(4);
                    Tbl_UserDTO dto = new Tbl_UserDTO(userId, password,
                            fullName, role);
                    if (this.listAccount == null) {
                        this.listAccount = new ArrayList<>();
                    }
                    this.listAccount.add(dto);
                }
            }//end if con
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteRecord(String userId)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish DB connection
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "DELETE "
                        + "FROM tbl_User "
                        + "WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                int affected = stm.executeUpdate();
                if (affected > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    public boolean updateRole(String userID, String password, boolean role)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtilities.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_User "
                        + "SET password = ?, role=? "
                        + "WHERE userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, userID);
                int affected = stm.executeUpdate();
                if (affected > 0) {
                    return true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean createRecord(String userID, String password, String fullName, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish DB connection
            con = DBUtilities.makeConnection();
            //2. Create SQL String
            String sql = "INSERT INTO tbl_User "
                    + "VALUES(?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            stm.setString(3, fullName);
            stm.setBoolean(4, role);
            //3. Execute and store inresult set
            int affected = stm.executeUpdate();
            if (affected > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }
}
