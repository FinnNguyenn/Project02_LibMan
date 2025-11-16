<%-- 
    Document   : CheckForFineView
    Created on : Oct 19, 2025, 2:42:06 AM
    Author     : thanh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.BorrowedDocuments"%>
<%@page import="Model.Documents"%>
<%@page import="Model.Fine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Staff" %>
<%
    DecimalFormat formatter = new DecimalFormat("#,### vnd");
    ArrayList<BorrowedDocuments> docsToReturn = (ArrayList<BorrowedDocuments>) session.getAttribute("listOfObjectsToReturn");
    ArrayList<Fine> allFines = (ArrayList<Fine>) request.getAttribute("listOfFine");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check for Fines</title>
        
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
                margin-bottom: 2rem;
                color: #5C4033;
                padding-top: 1.5rem;
                font-size: 2rem;
                text-transform: uppercase;
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
            
            table thead th:nth-child(2) {
                text-align: right;
            }
            table thead th:nth-child(3) {
                text-align: center;
            }
            
            tr.book-header td {
                background-color: #D9C8B6;
                color: #4E3629;
                font-size: 1.15rem;
                font-weight: bold;
                border-top: 3px solid #7A5B49;
            }
            
            tr.fine-row:hover {
                background-color: rgba(255, 255, 255, 0.5);
            }
            
            tr.fine-row td:nth-child(2) {
                text-align: right;
                font-family: monospace;
                font-size: 1.1rem;
            }
            tr.fine-row td:nth-child(3) {
                text-align: center;
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
            
            .no-fines-message {
                text-align: center;
                color: #7A5B49;
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
            
            <a href="${pageContext.request.contextPath}/Staff/ChooseReturnDocumentView.jsp" class="back-button">
                &#8592;
            </a>
            
            <h1 class="page-title">Check for Fines</h1>
            
            <form action="${pageContext.request.contextPath}/GetListFineServlet" method="post">
                
                <table>
                    <thead>
                        <tr>
                            <th>Fine Type</th>
                            <th>Fee</th>
                            <th>Apply</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if (docsToReturn == null || docsToReturn.isEmpty() || allFines == null) {
                        %>
                            <tr>
                                <td colspan="3" class="no-fines-message">No documents selected for return or no fines available.</td>
                            </tr>
                        <%
                        } else {
                            int dem = 0;
                            for (BorrowedDocuments bd : docsToReturn) {
                                Documents doc = bd.getDocument(); 
                        %>
                                <tr class="book-header">
                                    <td colspan="3"><%= (doc != null) ? doc.getTitle() : "N/A" %> (ID: <%= bd.getId() %>)</td>
                                </tr>
                        <%
                                for (Fine fine : allFines) {
                        %>
                                    <tr class="fine-row">
                                        <td><%= fine.getName() %></td>
                                        <td><%= formatter.format(fine.getFee()) %></td>
                                        <td>
                                            <input type="checkbox" 
                                                   name="selected_fines" 
                                                   value="<%= dem %>_<%= fine.getId() %>" />
                                        </td>
                                    </tr>
                        <%
                                }
                                dem += 1;
                            } 
                        } 
                        %>
                    </tbody>
                </table>
                
                <button type="submit">Continue</button>
            </form>
        </div>
    </body>
</html>