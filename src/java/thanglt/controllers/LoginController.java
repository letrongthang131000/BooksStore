/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanglt.daos.UserDAO;

/**
 *
 * @author Thang
 */
public class LoginController extends HttpServlet {

    private static final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private static final String LOGIN_ERROR_PAGE = "login.jsp";

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

        String url = LOGIN_ERROR_PAGE;

        String userName = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        try {
            UserDAO dao = new UserDAO();
            boolean result = dao.checkLogin(userName, password);

            if (result) {
                url = INIT_SEARCH_PAGE_CONTROLLER;
                String user = dao.getUserName(userName);
                boolean isAdmin = dao.isAdmin(userName);

                HttpSession session = request.getSession();
                session.setAttribute("USERID", userName);
                session.setAttribute("USER", user);
                session.setAttribute("ADMIN", isAdmin);
            } else {
                String errorMess = "Usernane or password is wrong!";
                request.setAttribute("ERRORLOGIN", errorMess);
                url = LOGIN_ERROR_PAGE;
            }
        } catch(Exception e){
            log("Error at LoginController:" +e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
