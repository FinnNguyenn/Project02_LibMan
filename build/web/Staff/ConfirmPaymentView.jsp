<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Reader"%>
<%@page import="Model.Invoice"%>
<%@page import="Model.BorrowedDocuments"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Staff" %>
<%
    Reader reader = (Reader) session.getAttribute("reader"); 
    Invoice invoice = (Invoice) session.getAttribute("currentInvoice");

    DecimalFormat formatter = new DecimalFormat("#,### vnd");
    
    String readerName = "NaN";
    if (reader != null) {
        readerName = reader.getName();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Payment</title>
        
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        
        <style>
            body, html {
                min-height: 100vh;
                margin: 0;
                font-family: Arial, sans-serif;
                background-image: url('${pageContext.request.contextPath}/Ymage/ImageLib.jpg');
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                background-attachment: fixed;
                display: flex;
                justify-content: center;
                align-items: center;
                padding: 3rem 0;
                box-sizing: border-box;
            }
            
            .home-button {
                position: fixed;
                top: 25px;
                right: 25px;
                font-size: 1.8rem;
                color: #E8D9C8;
                text-decoration: none;
                transition: all 0.2s ease;
                padding: 5px;
                line-height: 1;
                z-index: 1000;
            }
            .home-button:hover {
                transform: scale(1.15);
                color: #FFFFFF;
            }

            .container {
                background-color: rgba(232, 217, 200, 0.95);
                padding: 2.5rem;
                border-radius: 10px;
                box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
                width: 650px;
                max-width: 90%;
                box-sizing: border-box;
                position: relative; 
            }
            
            .back-button {
                position: absolute; top: 15px; left: 15px;
                font-size: 1.5rem; font-weight: bold;
                text-decoration: none; color: #3B2B21; 
                transition: color 0.2s ease, transform 0.2s ease;
            }
            .back-button:hover {
                color: #5C4033; transform: scale(1.1);
            }
            
            .page-title {
                text-align: center;
                margin-top: 0;
                margin-bottom: 2rem;
                color: #5C4033;
                padding-top: 1.5rem;
                font-size: 2rem;
                text-transform: uppercase;
            }
            
            .payment-table {
                width: 100%;
                border-collapse: collapse;
                color: #4E3629;
            }
            
            .payment-table td {
                padding: 0.8rem 0;
            }
            
            .payment-table .label {
                font-weight: bold;
                font-size: 1.1rem;
                padding-right: 1.5rem;
                width: 160px;
                color: #5C4033;
            }
            
            .payment-table .data {
                font-weight: bold;
                font-size: 1.1rem;
                color: #4E3629;
            }
            
            .payment-table input[type="text"],
            .payment-table textarea {
                width: 100%;
                padding: 0.75rem;
                border: 1px solid #B0A195;
                border-radius: 5px;
                font-size: 1rem;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
                background-color: #FDFBF8;
            }
            
            .payment-table textarea {
                resize: vertical;
            }

            .submit-button {
                margin-top: 2rem;
                padding: 0.85rem;
                border: none;
                border-radius: 5px;
                background-color: #7A5B49;
                color: white;
                font-size: 1.1rem;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
                width: 100%;
            }
            .submit-button:hover {
                background-color: #5C4033;
            }

        </style>
    </head>
    <body>
        
        <script>
            <% 
                String toastMessage = (String) session.getAttribute("toastMessage");
                String toastType = (String) session.getAttribute("toastType");
                if(toastMessage != null && toastType != null){
                        session.removeAttribute("toastMessage");
                        session.removeAttribute("toastType");
            %>
                toastr.options = {
                    closeButton : true,
                    progressBar : true,
                    positionClass : "toast-above-left",
                    timeout : 3000

                };
                toastr["<%=toastType%>"]("<%=toastMessage%>");
            <% 
                }
            %>
        </script>
        
        <a href="${pageContext.request.contextPath}/Staff/LibManHomeView.jsp" class="home-button">
            &#127968;
        </a>
        
        <div class="container">
            
            <a href="${pageContext.request.contextPath}/Staff/CreateInvoiceView.jsp" class="back-button">
                &#8592;
            </a>
            
            <form action="${pageContext.request.contextPath}/SaveInvoiceServlet" method="post">
                <h1 class="page-title">Payment</h1>
                
                <table class="payment-table">
                    <tr>
                        <td class="label">Reader:</td>
                        <td class="data"><%= readerName %></td>
                    </tr>
                    <tr>
                        <td class="label">Total Amount:</td>
                        <td class="data"><%= formatter.format(invoice.getAmount()) %></td>
                    </tr>
                    <tr>
                        <td class="label">Type:</td>
                        <td>
                            <input type="text" name="paymentType" id="paymentType" placeholder="Cash, Card..." />
                        </td>
                    </tr>
                    <tr>
                        <td class="label" style="vertical-align: top;">Note:</td>
                        <td>
                            <textarea name="paymentNote" id="paymentNote" rows="3"></textarea>
                        </td>
                    </tr>
                </table>

                <button type="submit" class="submit-button">Submit Payment</button>
            </form>
        </div>
        
    </body>
</html>