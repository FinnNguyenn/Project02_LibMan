

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
import java.util.*;

import Model.Fine;
import DAO.FineDAO;
import Model.BorrowedDocuments;
import Model.ExistedFine;
import Model.Invoice;
import Model.LibraryStaff;
import Model.Staff;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
@WebServlet(name = "GetListFineServlet", urlPatterns = {"/GetListFineServlet"})
public class GetListFineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FineDAO fineDAO;
    
    @Override
    public void init(){
        fineDAO = new FineDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ArrayList<Fine> kq = new ArrayList<>();
       HttpSession session = request.getSession();
       try{
           kq = fineDAO.getListFine();
           session.setAttribute("allFine", kq);
           request.setAttribute("listOfFine", kq);
           request.getRequestDispatcher("/Staff/CheckForFineView.jsp").forward(request, response);
           
       }catch(Exception e){
                    
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        String[] selectedFines = request.getParameterValues("selected_fines");
        ArrayList<Fine> allFineList = (ArrayList<Fine>) session.getAttribute("allFine");
        ArrayList<BorrowedDocuments> docsToReturn = (ArrayList<BorrowedDocuments>) session.getAttribute("listOfObjectsToReturn");
        Staff staff = (Staff) session.getAttribute("staff");
        
            for(BorrowedDocuments bd : docsToReturn){
                bd.setExistedFine(new ArrayList<>());
            }
        
            float totalAmount = 0.0f; 
            if (selectedFines != null && selectedFines.length > 0) {
            int check = 0;
            int i = 0;
            ArrayList<ExistedFine> eal = new ArrayList<>();
                for (String fineString : selectedFines){
                    String[] parts = fineString.split("_");
                    int fineId = Integer.parseInt(parts[1]);
                    int dem = Integer.parseInt(parts[0]);
                    if(i == 0) check = dem;
                    if(i > 0 && dem != check){
                        docsToReturn.get(check).setExistedFine(eal);
                        eal = new ArrayList<>();
                        check = dem;
                    }
                    
                   Fine foundFine = new Fine();
                   for(Fine tmp: allFineList){
                       if(tmp.getId() == fineId){
                           foundFine = tmp;
                       }
                   }
                    if (foundFine != null) {
                        ExistedFine exFine = new ExistedFine();
                        exFine.setFine(foundFine); 
                        exFine.setFee((float) foundFine.getFee()); 
                        eal.add(exFine);
                        totalAmount += (float) foundFine.getFee();
                    }
                    i++;
                }
                if(!eal.isEmpty()){
                    docsToReturn.get(check).setExistedFine(eal);
                    }
            }
            
            for(BorrowedDocuments bd: docsToReturn){
                totalAmount += (float) bd.getRentalPrice();
            }
        Invoice invoice = new Invoice();
        invoice.setCreateDate(new Date()); 
        invoice.setAmount(totalAmount);
        invoice.setBorrowedDocument(docsToReturn); 
        invoice.setLibraryStaff(staff); 
        session.setAttribute("currentInvoice", invoice);
        response.sendRedirect(request.getContextPath() + "/Staff/CreateInvoiceView.jsp"); 
    }
}
