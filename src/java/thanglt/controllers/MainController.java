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

/**
 *
 * @author Thang
 */
public class MainController extends HttpServlet {
    private static final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private static final String SEARCH_CONTROLLER = "SearchController";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String CREATE_BOOK_CONTROLLER = "CreateBookController";
    private static final String UPDATE_BOOK_CONTROLLER = "UpdateBookController";
    private static final String DELETE_CONTROLLER = "DeleteController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private static final String UPDATE_ITEM_CONTROLLER = "UpdateItemController";
    private static final String CONFIRM_CONTROLLER = "ConfirmController";
    private static final String REMOVE_ITEM_CONTROLLER = "RemoveItemController";
    private static final String INIT_DISCOUNT_CONTROLLER = "InitDiscountController";
    private static final String CREATE_DISCOUNT_CONTROLLER = "CreateDiscountController";
    private static final String SUBMIT_DISCOUNT_CONTROLLER = "SubmitDiscountController";
    private static final String INIT_HISTORY_CONTROLLER = "InitHistoryController";
    private static final String SEARCH_HISTORY_CONTROLLER = "SearchHistoryController";

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
        String action = request.getParameter("btAction");
        
        try {
            if (action == null) {

            } else if ("Search".equals(action)) {
                url = SEARCH_CONTROLLER;
            }else if ("Login".equals(action)) {
                url = LOGIN_CONTROLLER;
            }else if ("Logout".equals(action)) {
                url = LOGOUT_CONTROLLER;
            }else if ("Create".equals(action)) {
                url = CREATE_BOOK_CONTROLLER;
            }else if ("Update".equals(action)) {
                url = UPDATE_BOOK_CONTROLLER;
            }else if ("Delete".equals(action)) {
                url = DELETE_CONTROLLER;
            }else if ("Add to Cart".equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            }else if ("Update Item".equals(action)) {
                url = UPDATE_ITEM_CONTROLLER;
            }else if ("Confirm".equals(action)) {
                url = CONFIRM_CONTROLLER;
            }else if ("Pay momo".equals(action)) {
                url = CONFIRM_CONTROLLER;
            }else if ("RemoveItem".equals(action)) {
                url = REMOVE_ITEM_CONTROLLER;
            }else if ("Create new discount".equals(action)) {
                url = INIT_DISCOUNT_CONTROLLER;
            }else if ("Create discount code".equals(action)) {
                url = CREATE_DISCOUNT_CONTROLLER;
            }else if ("Use code".equals(action)) {
                url = SUBMIT_DISCOUNT_CONTROLLER;
            }else if ("History".equals(action)) {
                url = INIT_HISTORY_CONTROLLER;
            }else if ("Search history".equals(action)) {
                url = SEARCH_HISTORY_CONTROLLER;
            }
            
        }catch(Exception e){
            log("Error at MainController:" + e.toString());
        }finally{
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
