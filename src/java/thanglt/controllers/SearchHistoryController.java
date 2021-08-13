/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanglt.daos.OrderDAO;
import thanglt.dtos.OrderDTO;

/**
 *
 * @author Thang
 */
public class SearchHistoryController extends HttpServlet {
    private static final String HISTORY_PAGE = "history.jsp";
    private static final String INIT_HISTORY_CONTROLLER = "InitHistoryController";
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
                if (!isAdmin) {
                    url = INIT_HISTORY_CONTROLLER;
        
                    // search by name
                    String searchValue = request.getParameter("txtSearch");

                    // search by date
                    String date = request.getParameter("txtDate");
            
                    String userID = (String) session.getAttribute("USERID");
                    OrderDAO oDao = new OrderDAO();
                    List<OrderDTO> result = new ArrayList<>();

                    if (searchValue != null) {
                        oDao.searchByName(userID, searchValue);
                        result = oDao.getListOrder();
                        url = HISTORY_PAGE;
                    } else if (date != null) {
                        if (date.isEmpty()) {
                            oDao.loadAllOrder(userID);
                            result = oDao.getListOrder();
                        } else {
                            oDao.searchByDate(userID, date);
                            result = oDao.getListOrder();
                            url = HISTORY_PAGE;
                        }
                    }
                    
                    request.setAttribute("LENGTH", result.size());
                    request.setAttribute("RESULT", result);
                }
            }
        } catch(Exception e){
            log("Error at SearchHistoryController:" +e.toString());
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
