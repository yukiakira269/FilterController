/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.Utilities.RegistrationCreateError;
import anhnt.tbl_User.Tbl_UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateRecordServlet", urlPatterns = {"/CreateRecordServlet"})
public class CreateRecordServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String createErrorPage = "createNewAccount.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userID = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");

        String url = createErrorPage;
        boolean errFound = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            if (userID.trim().length() < 6 || userID.trim().length() > 20) {
                errFound = true;
                errors.setUsernameLengthErr("Username length has 6-20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errFound = true;
                errors.setPasswordLengthErr("Password length has 6 - 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                errFound = true;
                errors.setConfirmNotMatch("Confirmation did not matchs");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                errFound = true;
                errors.setFullNameLengthErr("Fullname length from 2 -50 chars");
            }
            if (errFound) {
                request.setAttribute("CREATEERROR", errors);
            } else {
                Tbl_UserDAO dao = new Tbl_UserDAO();
                boolean result = dao.createRecord(userID, password,
                        fullName, errFound);
                if (result) {
                    url = loginPage;
                }
            }

        } catch (SQLException ex) {
            errors.setUsernameExisted(userID + " exist!");
            request.setAttribute("CREATEERROR", errors);
            log("CreatRecordServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreatRecordServlet _ ClassNotFound" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }

        try {

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
