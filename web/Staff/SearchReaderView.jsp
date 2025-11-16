<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Reader" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Reader</title>
        
        <style>
            body, html {
                height: 100%; margin: 0; font-family: Arial, sans-serif;
                background-image: url('${pageContext.request.contextPath}/Ymage/ImageLib.jpg');
                background-position: center; background-repeat: no-repeat; background-size: cover;
                display: flex; align-items: center; justify-content: center;
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
                padding: 2.5rem; border-radius: 10px;
                box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
                width: 600px; box-sizing: border-box;
                position: relative; 
            }
            
            .back-button {
                position: absolute;
                top: 15px;
                left: 15px;
                
                font-size: 1.5rem;
                font-weight: bold;
                text-decoration: none;
                
                color: #3B2B21; 
                
                transition: color 0.2s ease, transform 0.2s ease;
            }
            
            .back-button:hover {
                color: #5C4033;
                transform: scale(1.1);
            }

            .container h1 {
                text-align: center; margin-top: 0; margin-bottom: 1.5rem;
                color: #5C4033; padding-top: 1.5rem; 
            }
            form { display: flex; flex-direction: column; }
            form h2 {
                font-size: 1rem; font-weight: bold; color: #5C4033;
                margin-top: 0; margin-bottom: 0.25rem; text-align: left;
            }
            form input[type="text"] {
                padding: 0.75rem; border: 1px solid #B0A195; border-radius: 5px;
                font-size: 1rem; box-sizing: border-box;
            }
            form button[type="submit"] {
                margin-top: 1.5rem; padding: 0.75rem; border: none; border-radius: 5px;
                background-color: #7A5B49; color: white; font-size: 1rem;
                font-weight: bold; cursor: pointer; transition: background-color 0.3s ease;
            }
            form button[type="submit"]:hover { background-color: #5C4033; }
            .error-message {
                color: #A0522D; background-color: #F8E8D8; border: 1px solid #A0522D;
                border-radius: 5px; padding: 0.75rem; margin-top: 1.5rem; text-align: center;
            }
            .reader-info {
                margin-top: 2rem; padding-top: 1.5rem;
                border-top: 2px solid #B0A195; text-align: left;
            }
            .reader-info h2 {
                font-size: 1.1rem; font-weight: 400; color: #5C4033; margin: 0.75rem 0;
            }
            .reader-info h2 strong {
                font-weight: 700; display: inline-block; min-width: 120px;
            }
            .continue-button {
                display: inline-block; text-align: center; margin-top: 1.5rem;
                padding: 0.85rem 1.75rem; border: none; border-radius: 5px;
                background-color: #7A5B49; color: white; font-size: 1rem;
                font-weight: bold; cursor: pointer; text-decoration: none;
                transition: background-color 0.3s ease;
            }
            .continue-button:hover { background-color: #5C4033; }

        </style>
        </head>
    <body>
        
        <a href="${pageContext.request.contextPath}/Staff/LibManHomeView.jsp" class="home-button">
            &#127968;
        </a>
        
        <div class="container">
            
            <a href="${pageContext.request.contextPath}/Staff/LibManHomeView.jsp" class="back-button">
                &#8592;
            </a>
            
            <h1>Search Reader</h1>
        
            <form action = "${pageContext.request.contextPath}/SearchServlet" method="post">
                <h2>Search: </h2>
                <input
                    name ="readerCode"
                    type ="text"
                    placeholder ="Reader code"
                    pattern="[0-9]*"   
                    title="Please enter only numbers for the reader code."
                    required/>
                <button type="submit">
                    Find
                </button>
            </form>
    
            <% 
                String error = (String) request.getAttribute("error");
                Reader reader = (Reader) request.getAttribute("reader");

                if(error != null) { 
            %>
                    <div class="error-message"> <%= error %> </div>
            <%  
                } else if(reader != null && reader.getName() != null && !reader.getName().trim().isEmpty()) {
            %>
                    <div class="reader-info">
                        <h2><strong>Name:</strong> <%= reader.getName() %></h2>
                        <h2><strong>Reader Code:</strong> <%= String.valueOf(reader.getReaderCode()) %></h2>
                        <h2><strong>Email:</strong> <%= reader.getEmail() %></h2>
                        <h2><strong>Address:</strong> <%= reader.getAddress() %></h2>
                    </div>
            
                    <a href = "${pageContext.request.contextPath}/getBorrowedDocumentServlet" class="continue-button">
                        Continue
                    </a>
            <%
                } else if (reader != null) {
            %>
                    <div class="error-message"> Can't found reader </div>
            <%
                }
            %>
        </div>
        
    </body>
</html>