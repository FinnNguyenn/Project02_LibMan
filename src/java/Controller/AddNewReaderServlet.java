/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ReaderCardDAO;
import Model.ReaderCard;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AddNewReaderServlet", urlPatterns = {"/AddNewReaderServlet"})
public class AddNewReaderServlet extends HttpServlet {
private ReaderCard reader;
    private ReaderCardDAO readerCardDAO;
    
    @Override
    public void init(){
        reader = new ReaderCard();
        readerCardDAO = new ReaderCardDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birth = sdf.parse(request.getParameter("birth"));
            
            Date createDate = sdf.parse(request.getParameter("createDate"));
            Date endDate = sdf.parse(request.getParameter("endDate"));
            String email = request.getParameter("email");
            String tel = request.getParameter("tel");
            String studentCode = request.getParameter("studentCode");
            reader.setUsername(username);
            reader.setPassword(password);
            reader.setName(name);
            reader.setAddress(address);
            reader.setBirth(birth);
            reader.setCreateDate(createDate);
            reader.setExpirationDate(endDate);
            reader.setEmail(email);
            reader.setTel(tel);
            reader.setStudentCode(studentCode);
            if(readerCardDAO.addNewReader(reader)){
                HttpSession session = request.getSession();
                session.setAttribute("toastMessage","Register sucessfully");
                session.setAttribute("toastType","success");
                response.sendRedirect(request.getContextPath() + "/Reader/LoginView.jsp");
            }
            else{
                request.setAttribute("errorMessage","Username or Student code is already used");
                request.getRequestDispatcher("Reader/RegisterView.jsp").forward(request, response);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage","An error occurred during registration");
            request.getRequestDispatcher("Reader/RegisterView.jsp").forward(request,response);
        }
          
        
    }

}
