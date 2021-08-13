/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanglt.daos.BookDAO;

/**
 *
 * @author Thang
 */
public class DeleteController extends HttpServlet {
    private static final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";

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
        
        String url = INIT_SEARCH_PAGE_CONTROLLER;
        
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("USER") != null) {
                boolean isAdmin = (boolean) session.getAttribute("ADMIN");
                if (isAdmin) {

                    String searchByNameValue = request.getParameter("searchValue");

                    String minPriceValue = request.getParameter("txtMinPrice");
                    String maxPriceValue = request.getParameter("txtMaxPrice");

                    String categoryValue = request.getParameter("category");

                    String bookID = request.getParameter("txtId");
                
                    BookDAO bDao = new BookDAO();
                    boolean result = bDao.deleteBook(bookID);

                    if (result) {
                        if (!searchByNameValue.isEmpty()) {
                            url = "ServletDispatcherController?"
                                    + "btAction=Search"
                                    + "&txtSearch="
                                    + searchByNameValue;
                        } else if (!minPriceValue.isEmpty() || !maxPriceValue.isEmpty()) {
                            url = "ServletDispatcherController?"
                                    + "btAction=Search"
                                    + "&txtMinPrice="
                                    + minPriceValue
                                    + "&txtMaxPrice="
                                    + maxPriceValue;
                        } else if (!categoryValue.isEmpty()) {
                            url = "ServletDispatcherController?"
                                    + "btAction=Search"
                                    + "&category="
                                    + categoryValue;
                        }
                    }
                }
            }
        } catch(Exception e){
            log("Error at DeleteController:" +e.toString());
        } finally {
            response.sendRedirect(url);
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
