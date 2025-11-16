<%-- 
    Document   : LibManHomeView
    Created on : Oct 19, 2025, 2:41:07 AM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Staff" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    HttpSession ses = request.getSession(false);
    Staff staff = new Staff();
    if (ses != null) {
        staff = (Staff) ses.getAttribute("staff");
    }
    
    if (staff == null) {
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LibMan Home View</title>
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        
        <style>
            body, html {
                height: 100%; margin: 0; font-family: Arial, sans-serif;
                background-image: url('${pageContext.request.contextPath}/Ymage/ImageLib.jpg');
                background-position: center; background-repeat: no-repeat; background-size: cover;
                display: flex; align-items: center; justify-content: center;
            }
            .logout-button {
                position: fixed; top: 25px; right: 25px;
                color: #D90429; font-weight: bold; font-size: 1.1rem;
                text-decoration: none; background-color: rgba(255, 255, 255, 0.2);
                padding: 8px 12px; border-radius: 5px;
                transition: all 0.2s ease;
            }
            .logout-button:hover {
                background-color: #D90429; color: white;
                transform: scale(1.05);
            }
            .home-container {
                background-color: rgba(232, 217, 200, 0.95);
                padding: 3rem; border-radius: 10px;
                box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
                box-sizing: border-box; text-align: center; width: 600px;
            }
            .home-container h1 {
                margin-top: 0; margin-bottom: 2rem;
                color: #5C4033; font-size: 2rem;
            }
            .center-btn a {
                display: inline-block; padding: 0.85rem 1.75rem;
                border: none; border-radius: 5px;
                background-color: #7A5B49; color: white;
                font-size: 1rem; font-weight: bold;
                cursor: pointer; text-decoration: none;
                transition: background-color 0.3s ease;
            }
            .center-btn a:hover { background-color: #5C4033; }
        </style>
        
    </head>
    <body>
        <form action = "${pageContext.request.contextPath}/LoginServlet" method = "get">
            <button class="logout-button">
            Logout
            </button>
        </form>
        
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
        
        <div class="home-container">
            <h1> <%= "WELCOME" + " " + staff.getName() %> </h1>
            
            <div class="center-btn">
                <a href="${pageContext.request.contextPath}/Staff/SearchReaderView.jsp">Return Documents</a>
            </div>
        </div>
        
    </body>
</html>