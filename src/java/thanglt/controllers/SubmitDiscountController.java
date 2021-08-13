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
import thanglt.daos.DiscountDAO;

/**
 *
 * @author Thang
 */
public class SubmitDiscountController extends HttpServlet {
    private static final String VIEW_CART_PAGE = "viewCart.jsp";
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
                    url = VIEW_CART_PAGE;
        
                    // input discount code 
                    int discountCode;
                    try {
                        discountCode = Integer.parseInt(request.getParameter("txtDiscountCode"));
                    } catch(NumberFormatException ex) {
                        discountCode = 0;
                    }
            
                    DiscountDAO dDao = new DiscountDAO();
                    boolean result = dDao.checkDiscountCode(discountCode);

                    if (result) {
                        float discountValue = dDao.getDiscountValue(discountCode);
                        float total = (Float) session.getAttribute("TOTAL");
                        total = total/(100);
                        total = total*((100)-(discountValue));
                        String mess = "Discount code is accepted!";

                        session.setAttribute("TOTAL", total);
                        session.setAttribute("DISCOUNTCODE", discountCode);
                        session.setAttribute("DISCOUNT", discountValue);
                        request.setAttribute("TRUECODE", mess);
                    } else {
                        String mess = "Discount code is unaccepted!";
                        request.setAttribute("WRONGCODE", mess);
                    }
                }
            }
        } catch(Exception e){
            log("Error at SubmitDiscountController:" +e.toString());
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
