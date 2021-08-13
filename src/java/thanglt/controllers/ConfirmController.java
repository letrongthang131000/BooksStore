/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thanglt.daos.BookDAO;
import thanglt.daos.DiscountDAO;
import thanglt.daos.OrderDAO;
import thanglt.daos.OrderDetailDAO;
import thanglt.dtos.CartDTO;
import thanglt.dtos.OrderDetailDTO;

/**
 *
 * @author Thang
 */
public class ConfirmController extends HttpServlet {

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
                    url = INIT_SEARCH_PAGE_CONTROLLER;
                    CartDTO cart = (CartDTO) session.getAttribute("CART");

                    if (cart != null) {
                        String userID = (String) session.getAttribute("USERID");
                        float total = (Float) session.getAttribute("TOTAL");
                        OrderDAO oDao = new OrderDAO();

                        boolean result = oDao.insertOrder(userID, total);

                        if (result) {
                            int lastedOrderID = oDao.getLastestID();

                            // insert order detail
                            for (Map.Entry<Integer, OrderDetailDTO> item : cart.getItems().entrySet()) {
                                OrderDetailDAO odDao = new OrderDetailDAO();

                                String title = item.getValue().getTitle();
                                int bookID = item.getKey();
                                int quantity = item.getValue().getQuantity();
                                float unitPrice = item.getValue().getUnitPrice();
                                float itemTotal = item.getValue().getTotal();

                                odDao.insertOrderDetail(bookID, title, quantity, unitPrice, itemTotal, lastedOrderID);
                            }

                            // update quantity of books
                            for (Map.Entry<Integer, OrderDetailDTO> item : cart.getItems().entrySet()) {
                                BookDAO bDao = new BookDAO();

                                int bookID = item.getKey();
                                int quantityInCart = item.getValue().getQuantity();
                                int quantityInStock = bDao.getQuantity(bookID);
                                int newQuantity = quantityInStock - quantityInCart;

                                bDao.setQuantity(bookID, newQuantity);
                            }

                            DiscountDAO dDao = new DiscountDAO();

                            if (session.getAttribute("DISCOUNTCODE") != null) {
                                int discountCode = (int) session.getAttribute("DISCOUNTCODE");
                                boolean isUsed = dDao.useDiscountCode(discountCode, userID);
                                if (isUsed) {
                                    session.removeAttribute("DISCOUNTCODE");
                                    session.removeAttribute("DISCOUNT");
                                }
                            }
                            cart.getItems().clear();
                        }

                        cart = null;
                        session.setAttribute("CART", cart);
                        url = INIT_SEARCH_PAGE_CONTROLLER;
                    }
                }
            }
        } catch(Exception e){
            log("Error at ConfirmController:" +e.toString());
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
