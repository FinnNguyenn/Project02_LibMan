/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.util.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.LoanSlipDAO;
import Model.BorrowedDocuments;
import Model.Reader;
import Model.LoanSlip;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
@WebServlet(name = "getBorrowedDocumentServlet", urlPatterns = {"/getBorrowedDocumentServlet"})
public class getBorrowedDocumentServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private LoanSlipDAO loanSlipDAO;
    
    @Override
    public void init(){
        loanSlipDAO = new LoanSlipDAO();
    }
    
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if(session != null){
            Reader cur = (Reader) session.getAttribute("reader"); 
            
            if(cur != null){
                int code = cur.getReaderCode();
                ArrayList<LoanSlip> al = new ArrayList<>(); 
                ArrayList<BorrowedDocuments> l1bl = new ArrayList<>();
                ArrayList<BorrowedDocuments> l2bl = new ArrayList<>();
                try{
                    al = loanSlipDAO.getBorrowedDocuments(code);
                    
                }catch(Exception e){
           
                    e.printStackTrace(); 
                    request.setAttribute("db_error", "Lỗi truy vấn cơ sở dữ liệu.");
                }
                for(int i = 0; i < al.size(); i++){
                    if(al.get(i).getBorrowedDocuments() != null){
                        for(int j = 0; j < al.get(i).getBorrowedDocuments().size(); j++){
                            if(loanSlipDAO.checkStatus(al.get(i).getBorrowedDocuments().get(j).getId())){
                                l1bl.add(al.get(i).getBorrowedDocuments().get(j));
                            }
                            else{
                                l2bl.add(al.get(i).getBorrowedDocuments().get(j));
                            }
                        }
                    }
                }
                session.setAttribute("l1BorrowedDocument", l1bl);
                session.setAttribute("l2BorrowedDocument", l2bl);
                session.setAttribute("currentLoanSlips", al);
                request.getRequestDispatcher("/Staff/ChooseReturnDocumentView.jsp").forward(request, response);

            }else{
                System.out.println("Khong tim thay reader trong session");
               
            }
        }else{
            System.out.println("No session");

        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String[] selectedIds = request.getParameterValues("selectedDocumentIds");
        HttpSession session = request.getSession();
        if (selectedIds != null && selectedIds.length != 0){
            ArrayList<LoanSlip> fullLoanSlips = (ArrayList<LoanSlip>) session.getAttribute("currentLoanSlips");
            ArrayList<BorrowedDocuments> selectedDocuments = new ArrayList<>();
            List<String> selectedIdList = Arrays.asList(selectedIds);

            if (fullLoanSlips != null) {
                for (LoanSlip slip : fullLoanSlips) {
                    for (BorrowedDocuments bd : slip.getBorrowedDocuments()) {

                        if (selectedIdList.contains(String.valueOf(bd.getId()))) {
                            selectedDocuments.add(bd);
                        }
                    }
                }
            }
            session.setAttribute("listOfObjectsToReturn", selectedDocuments);
            response.sendRedirect(request.getContextPath() + "/GetListFineServlet");
        }else{
            request.getRequestDispatcher("/Staff/ChooseReturnDocumentView.jsp").forward(request, response);
        }
    }
}
