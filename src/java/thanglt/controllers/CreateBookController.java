/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanglt.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
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


public class CreateBookController extends HttpServlet {
    private static final String CREATE_BOOK_PAGE = "createNewBook.jsp";
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
                    url = CREATE_BOOK_PAGE;
                    
                    String title = request.getParameter("txtTitle");
                    String description = request.getParameter("txtDescription");
                    String price = request.getParameter("txtPrice");
                    String author = request.getParameter("txtAuthor");
                    int categoryID = Integer.parseInt(request.getParameter("txtCategory"));
                    String quantity = request.getParameter("txtQuantity");
                    boolean checkImage = true;
                    
                    //upload photo
                    String imageAddress = request.getParameter("imageSource");
                        String imageName = "";
                        String imageType = "";
                        String savePath = "";
                        String saveDbPath;
                        BufferedImage bi = null;

                        if (!imageAddress.isEmpty()) {
                            imageName = imageAddress;
                            int lastIndexImage = imageName.lastIndexOf("\\");
                            imageName = imageName.substring(lastIndexImage + 1);
                            int lastIndexType = imageName.lastIndexOf(".");
                            imageType = imageName.substring(lastIndexType + 1);
                            savePath = request.getServletContext().getRealPath("");
                            File inputFile = new File(imageAddress);
                            InputStream is = new FileInputStream(inputFile);
                            bi = ImageIO.read(is);

                            if (!imageType.equals("jpg")
                                    && !imageType.equals("png")
                                    && !imageType.equals("jpeg")) {
                                checkImage = false;
                                request.setAttribute("ERRORTYPE", "This file is not image file!");
                            }
                        }

                        if (checkImage) {
                            saveDbPath = ".\\IMG\\" + imageName;
                            ImageIO.write(bi, imageType, new File(savePath + "\\IMG" + imageName));
                            File file = new File(savePath).getParentFile().getParentFile();
                            ImageIO.write(bi, imageType, new File(file.getAbsoluteFile() + "\\web\\IMG\\" + imageName));
                            Thread.sleep(500);

                        
                        

                        BookDAO bDao = new BookDAO();
                        boolean result = bDao.createBook(title, price, author, categoryID, saveDbPath, quantity, description);

                        if (result) {
                            url = INIT_SEARCH_PAGE_CONTROLLER;
                        }
                    }
                }
            }
        } catch(Exception e){
            log("Error at CreateBookController:" + e.toString());
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
