/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.Utilities;

import java.sql.*;
import java.io.Serializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBUtilities implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {
        Context ctx = new InitialContext();
        Context tomcatContext = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatContext.lookup("DBCon");
        Connection con = ds.getConnection();
        return con;
    }
}
