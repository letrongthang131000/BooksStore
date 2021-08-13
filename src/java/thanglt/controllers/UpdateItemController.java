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
import thanglt.dtos.CartDTO;
import thanglt.dtos.OrderDetailDTO;

/**
 *
 * @author Thang
 */
public class UpdateItemController extends HttpServlet {
    private static final String INIT_SEARCH_PAGE_CONTROLLER = "InitSearchPageController";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";

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
        
                    int bookID = Integer.parseInt(request.getParameter("txtBookId"));

                    float totalValue = 0;CartDTO cart = (CartDTO) session.getAttribute("CART");
            
                    if (cart != null) {
                        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

                        if (quantity == 0) {
                            cart.removeItem(bookID);
                        } else {
                            OrderDetailDTO oDto = cart.getItems().get(bookID);
                            BookDAO bDao = new BookDAO();
                            int quantityInStock = bDao.getQuantity(bookID);

                            if (quantity > quantityInStock) {
                                String errStr = "The "
                                        + oDto.getTitle()
                                        + " quantity chosen is greater than the quantity in stock! Stock: "
                                        + quantityInStock;
                                request.setAttribute("QUANERR", errStr);
                            } else {
                                float pricePerBook = oDto.getTotal()/(oDto.getQuantity());
                                oDto.setQuantity(quantity);
                                float total = pricePerBook *quantity;                    
                                oDto.setTotal(total);
                                cart.updateItemInCart(bookID, oDto);
                            }
                        }

                        //set total
                        for (Map.Entry<Integer, OrderDetailDTO> item : cart.getItems().entrySet()) {
                            totalValue = totalValue+(item.getValue().getTotal());
                        }

                        session.setAttribute("CART", cart);
                        session.removeAttribute("DISCOUNTCODE");
                        session.removeAttribute("DISCOUNT");
                        session.setAttribute("TOTAL", totalValue);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            String errStr = "Please input the number";
            request.setAttribute("QUANERR", errStr);
        } catch(Exception e){
            log("Error at UpdateItemController");
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
