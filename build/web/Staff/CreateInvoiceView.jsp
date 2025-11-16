<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Invoice"%>
<%@page import="Model.BorrowedDocuments"%>
<%@page import="Model.ExistedFine"%>
<%@page import="Model.Fine"%>
<%@page import="Model.Documents"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>

<%
    Invoice invoice = (Invoice) session.getAttribute("currentInvoice");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat formatter = new DecimalFormat("#,### vnd");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Invoice Details</title>
    
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
            width: 800px;
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
            margin-bottom: 0.5rem;
            color: #5C4033;
            padding-top: 1.5rem;
            font-size: 2rem;
            text-transform: uppercase;
        }
        
        .return-date {
            text-align: center;
            font-size: 1.1rem;
            font-weight: normal;
            color: #5C4033;
            margin-top: 0;
            margin-bottom: 2rem;
            border-bottom: 2px solid #B0A195;
            padding-bottom: 1.5rem;
        }
        
        .invoice-table {
            width: 100%;
            border-collapse: collapse;
            color: #4E3629;
        }
        
        .invoice-table th, .invoice-table td {
            padding: 0.85rem;
            text-align: left;
            border-bottom: 1px solid #E8D9C8;
        }
        
        .invoice-table thead th {
            background-color: #7A5B49;
            color: white;
            font-weight: bold;
            border-bottom: 1px solid #B0A195;
        }
        .invoice-table thead th:nth-child(2) {
            text-align: right;
        }
        
        .invoice-table .price-col {
            text-align: right;
            font-family: monospace;
            font-size: 1.1rem;
            width: 150px;
        }
        
        .book-header-row td {
            font-weight: bold;
            font-size: 1.15rem;
            color: #4E3629;
            padding-top: 1.5rem;
            border-bottom-width: 2px;
            border-bottom-color: #D9C8B6;
        }
        
        .detail-row td:first-child {
            padding-left: 2.5rem;
            font-style: italic;
        }

        .detail-row:last-of-type td {
             border-bottom: 1px dashed #B0A195;
        }
        
        .no-fines-row td:first-child {
            padding-left: 2.5rem;
            font-style: italic;
            color: #7A5B49;
        }
        
        .invoice-table tfoot .grand-total-row td {
            font-size: 1.5rem;
            font-weight: bold;
            color: #4E3629;
            border-top: 4px double #7A5B49;
            padding-top: 1.5rem;
        }

        .confirm-button {
            display: block;
            text-align: center;
            margin-top: 2rem;
            padding: 0.85rem;
            border: none;
            border-radius: 5px;
            background-color: #7A5B49;
            color: white;
            font-size: 1.1rem;
            font-weight: bold;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
            width: 100%;
            box-sizing: border-box;
        }
        .confirm-button:hover {
            background-color: #5C4033;
        }
        
        .error-message {
            text-align: center;
            color: #A0522D;
            padding: 2rem;
            font-style: italic;
            font-size: 1.1rem;
        }

    </style>
</head>
<body>

    <a href="${pageContext.request.contextPath}/Staff/LibManHomeView.jsp" class="home-button">
        &#127968;
    </a>
    
    <div class="container">
        
        <a href="${pageContext.request.contextPath}/GetListFineServlet" class="back-button">
            &#8592;
        </a>

        <h1 class="page-title">Invoice</h1>
        <h3 class="return-date">Return Day: <%= (invoice != null) ? sdf.format(invoice.getCreateDate()) : "N/A" %></h3>

        <table class="invoice-table">
            <thead>
                <tr>
                    <th>Description</th>
                    <th class="price-col">Amount</th>
                </tr>
            </thead>

            <tbody>
            <%
            if (invoice == null || invoice.getBorrowedDocument() == null) {
            %>
                <tr>
                    <td colspan="2" class="error-message">Error: Invoice not found.</td>
                </tr>
            <%
            } else {
                for (BorrowedDocuments bd : invoice.getBorrowedDocument()) {
                    float rentFee = (float) bd.getRentalPrice();
                    float totalFineOfDoc = 0;
            %>
                    <tr class="book-header-row">
                        <td colspan="2">Document: <%= bd.getDocument().getTitle() %></td>
                    </tr>
                    
                    <tr class="detail-row">
                        <td>Rent Fee</td>
                        <td class="price-col"><%= formatter.format(rentFee) %></td>
                    </tr>

            <%
                    if (bd.getExistedFine() != null && !bd.getExistedFine().isEmpty()) {
                        for (ExistedFine ef : bd.getExistedFine()) {
                            totalFineOfDoc += ef.getFee();
            %>
                            <tr class="detail-row">
                                <td><%= ef.getFine().getName() %></td>
                                <td class="price-col"><%= formatter.format(ef.getFee()) %></td>
                            </tr>
            <%
                        }
                    } else {
            %>
                        <tr class="no-fines-row">
                            <td>(No fines)</td>
                            <td class="price-col"><%= formatter.format(0) %></td>
                        </tr>
            <%
                    }
            %>

            <%
                }
            %>
            </tbody>
            
            <tfoot>
                <tr class="grand-total-row">
                    <td>Grand Total:</td>
                    <td class="price-col"><%= formatter.format(invoice.getAmount()) %></td>
                </tr>
            </tfoot>
            
            <%
            }
            %>
        </table>

        <a href="${pageContext.request.contextPath}/Staff/ConfirmPaymentView.jsp" class="confirm-button">Continue</a>
        
    </div>
</body>
</html>