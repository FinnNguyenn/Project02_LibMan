/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.ReaderDAO;

import Model.Reader;
import Model.Staff;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ReaderDAO readerDAO;
  
    
    @Override
    public void init(){
        readerDAO = new ReaderDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int readerCode = Integer.parseInt(request.getParameter("readerCode"));
            Reader reader = new Reader();
            try{
                reader = readerDAO.findReader(readerCode);
                if(reader == null){
                    request.setAttribute("error", "Can't find Reader");
                    request.getRequestDispatcher("/Staff/SearchReaderView.jsp").forward(request, response);
                }
                else{
                    HttpSession session = request.getSession();
                    session.setAttribute("reader", reader);
                    request.setAttribute("reader", reader);
                    request.getRequestDispatcher("/Staff/SearchReaderView.jsp").forward(request, response);
                }
            }catch(Exception e){
                
            }
    }
}
