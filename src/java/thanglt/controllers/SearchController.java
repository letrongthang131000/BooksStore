/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thanglt.daos.BookDAO;
import thanglt.dtos.BookDTO;

/**
 *
 * @author Thang
 */
public class SearchController extends HttpServlet {
    private static final String SEARCH_PAGE = "search.jsp";

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
        
        String url = SEARCH_PAGE;
        
        // search by name value 
        String searchByNameValue = request.getParameter("txtSearch");
        
        // search by range of money value
        String minPriceValue = request.getParameter("txtMinPrice");
        String maxPriceValue = request.getParameter("txtMaxPrice");
        
        // search by category
        String categoryValue = request.getParameter("category");
        
        try {
            BookDAO bDao = new BookDAO();
            
            if (searchByNameValue != null) {
                bDao.searchByName(searchByNameValue);
            } else if (minPriceValue != null && maxPriceValue != null) {
                if (minPriceValue.equals("") && maxPriceValue.equals("")) {
                    bDao.loadAll();
                } else {
                    float minPrice;
                    float maxPrice;
                    if (minPriceValue.equals("")) {
                        minPrice = -1;
                    } else {
                        minPrice = Float.parseFloat(minPriceValue);
                    }
                    if (maxPriceValue.equals("")) {
                        maxPrice = -1;
                    } else {
                        maxPrice = Float.parseFloat(maxPriceValue);
                    }
                    
                    bDao.searchByRangePrice(minPrice, maxPrice);
                }
            } else if (categoryValue != null) {
                if (categoryValue.equals("All")) {
                    bDao.loadAll();
                } else {
                    bDao.searchByCategory(categoryValue);
                }
            }
            
            List<BookDTO> listBook = bDao.getListBook();
            request.setAttribute("RESULT", listBook);    
        } catch(Exception e){
            log("Error at SearchController:" + e.toString());
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
