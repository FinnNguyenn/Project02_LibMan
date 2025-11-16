/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.InvoiceDAO;
import DAO.DAO;
import DAO.PaymentDAO;
import Model.Invoice;
import Model.Payment;
import com.sun.jdi.connect.spi.Connection;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author thanh
 */
@WebServlet(name = "SaveInvoiceServlet", urlPatterns = {"/SaveInvoiceServlet"})
public class TransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private InvoiceDAO invoiceDAO;
    private PaymentDAO paymentDAO;
    
    @Override
    public void init(){
        invoiceDAO = new InvoiceDAO();
        paymentDAO = new PaymentDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        HttpSession session = request.getSession();
        new DAO(); 
        java.sql.Connection conn = DAO.con;

        String type = request.getParameter("paymentType");
        String note = request.getParameter("paymentNote");
        Invoice invoice = (Invoice) session.getAttribute("currentInvoice");

        try {
            conn.setAutoCommit(false);
        
            if (!invoiceDAO.saveInvoice(invoice)) {
                conn.rollback();
                session.setAttribute("toastMessage", "Save Invoice Fail");
                session.setAttribute("toastType", "error");
                response.sendRedirect(request.getContextPath() + "/Staff/ConfirmPaymentView.jsp");
                return;
            }

            Payment payment = new Payment();
            payment.setInvoice(invoice);
            payment.setNote(note);
            payment.setType(type);
            payment.setDate(invoice.getCreateDate());

            if (!paymentDAO.savePayment(payment)) {
                conn.rollback();
                session.setAttribute("toastMessage", "Save Payment Fail");
                session.setAttribute("toastType", "error");
                response.sendRedirect(request.getContextPath() + "/Staff/ConfirmPaymentView.jsp");
                return;
            }

        conn.commit(); 

            session.setAttribute("toastMessage", "Save Payment and Invoice Success");
            session.setAttribute("toastType", "success");
            response.sendRedirect(request.getContextPath() + "/Staff/LibManHomeView.jsp");

        } catch (Exception e) {
            try { conn.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
        } finally {
            try { conn.setAutoCommit(true); } catch (Exception ex) {ex.printStackTrace();}
        }
    }
}
