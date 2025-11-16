<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Reader"%>
<%@page import="Model.LoanSlip"%>
<%@page import="Model.BorrowedDocuments"%>
<%@page import="Model.Documents"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Staff" %>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Reader currentReader = (Reader) session.getAttribute("reader");

    ArrayList<LoanSlip> currentLoanSlips = (ArrayList<LoanSlip>) session.getAttribute("currentLoanSlips");
    ArrayList<BorrowedDocuments> l1BorrowedDocument = (ArrayList<BorrowedDocuments>) session.getAttribute("l1BorrowedDocument");
    ArrayList<BorrowedDocuments> l2BorrowedDocument = (ArrayList<BorrowedDocuments>) session.getAttribute("l2BorrowedDocument");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Choose Documents to Return</title>
    
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
            width: 900px;
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
            margin-bottom: 1.5rem;
            color: #5C4033;
            padding-top: 1.5rem;
            font-size: 2rem;
            text-transform: uppercase;
        }
        
        .reader-info {
            font-size: 1.1rem;
            color: #5C4033;
            margin-bottom: 1.5rem;
            border-bottom: 2px solid #B0A195;
            padding-bottom: 1.5rem;
            text-align: center;
        }
        .reader-info strong {
            font-weight: 700;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 1.5rem;
            color: #4E3629;
        }
        
        table th, table td {
            padding: 0.85rem;
            text-align: left;
            border-bottom: 1px solid #B0A195;
        }
        
        table thead th {
            background-color: #7A5B49;
            color: white;
            font-weight: bold;
        }
        
        table tbody tr.returned {
            color: #7A5B49;
            font-style: italic;
            background-color: rgba(217, 203, 190, 0.5);
        }
        
        table tbody tr.on-loan:hover {
            background-color: rgba(255, 255, 255, 0.5);
        }
        
        table td:nth-child(3), table td:nth-child(4) {
            text-align: center;
        }
        
        table td[colspan="4"] {
            text-align: center;
            color: #7A5B49;
            padding: 2rem;
            font-style: italic;
        }
        
        form button[type="submit"] {
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

        form button[type="submit"]:hover {
            background-color: #5C4033;
        }

    </style>
</head>
<body>
    
   <a href="${pageContext.request.contextPath}/Staff/LibManHomeView.jsp" class="home-button">
       &#127968;
   </a>
    
    
    <div class="container">
        
        <a href="${pageContext.request.contextPath}/Staff/SearchReaderView.jsp" class="back-button">
            &#8592;
        </a>
        
        <h1 class="page-title">CHOOSE RETURN DOCUMENT</h1>

        <div class="reader-info">
            <strong>Reader:</strong>
            <%= (currentReader != null) ? currentReader.getName() : "Unknown Reader" %>
        </div>

        <form action="${pageContext.request.contextPath}/getBorrowedDocumentServlet" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Document</th>
                        <th>Rent Date</th>
                        <th>Status</th>
                        <th>Return</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (currentLoanSlips != null && !currentLoanSlips.isEmpty()) { %>
                        <%
                            for (LoanSlip slip : currentLoanSlips) {
                                String rentDate = (slip.getCreateDate() != null) ? sdf.format(slip.getCreateDate()) : "N/A";
                                ArrayList<BorrowedDocuments> bds = slip.getBorrowedDocuments();

                                if (bds != null) {
                                    for (BorrowedDocuments bd : bds) {
                                        if (l1BorrowedDocument != null && l1BorrowedDocument.contains(bd)) {
                                            Documents doc = bd.getDocument();
                                            String title = (doc != null) ? doc.getTitle() : "N/A";
                        %>
                                            <tr class="on-loan">
                                                <td><%= title %></td>
                                                <td><%= rentDate %></td>
                                                <td>On loan</td>
                                                <td>
                                                    <input type="checkbox" name="selectedDocumentIds" value="<%= bd.getId() %>">
                                                </td>
                                            </tr>
                        <%
                                        }
                                    }
                                }
                            }
                        %>

                        <%
                            for (LoanSlip slip : currentLoanSlips) {
                                String rentDate = (slip.getCreateDate() != null) ? sdf.format(slip.getCreateDate()) : "N/A";
                                ArrayList<BorrowedDocuments> bds = slip.getBorrowedDocuments();

                                if (bds != null) {
                                    for (BorrowedDocuments bd : bds) {
                                        if (l2BorrowedDocument != null && l2BorrowedDocument.contains(bd)) {
                                            Documents doc = bd.getDocument();
                                            String title = (doc != null) ? doc.getTitle() : "N/A";
                        %>
                                            <tr class="returned">
                                                <td><%= title %></td>
                                                <td><%= rentDate %></td>
                                                <td>Returned</td>
                                                <td>—</td>
                                            </tr>
                        <%
                                        }
                                    }
                                }
                            }
                        %>

                    <% } else { %>
                        <tr>
                            <td colspan="4">
                                No borrowed documents found.
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <button type="submit">Continue</button>
        </form>
    </div>
</body>
</html>