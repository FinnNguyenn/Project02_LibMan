/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.StaffDAO;
import Model.Staff;

/**
 *
 * @author thanh
 */

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet{
    
    private static final long serialVersionUID = 1L;
    private StaffDAO staffDAO;
    
    @Override
    public void init(){
        staffDAO = new StaffDAO();
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Staff st = new Staff();
        st.setUsername(username);
        st.setPassword(password);
        
        try{
            if(staffDAO.checkLogin(st)){
                HttpSession session = request.getSession();
                session.setAttribute("staff", st);
                response.sendRedirect(request.getContextPath() + "/Staff/LibManHomeView.jsp");
            }else{
            request.setAttribute("errorMessage", "Wrong username or password!");
            request.getRequestDispatcher("/Staff/LoginView.jsp").forward(request, response);
            }
    }catch(Exception e){
        e.printStackTrace();
    }
}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("/Staff/LoginView.jsp").forward(request, response);
    }
}
