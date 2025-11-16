<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign in</title>
        
        <style>
            body, html {
                height: 100%;
                margin: 0;
                font-family: Arial, sans-serif;
                
                background-image: url('${pageContext.request.contextPath}/Ymage/ImageLib.jpg');
                
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
                
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .login-container {
                /* Màu nâu pastel nhẹ: #E8D9C8 */
                background-color: rgba(232, 217, 200, 0.95); 
                
                padding: 2.5rem;
                border-radius: 10px;
                box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
                width: 650px;
                box-sizing: border-box;
            }

            .login-container h2 {
                text-align: center;
                margin-top: 0;
                margin-bottom: 1.5rem;
                color: #5C4033;
            }

            .login-content {
                display: flex;          
                align-items: center;   
                justify-content: space-between; 
                gap: 2rem;
            }

            .login-content form {
                flex: 1; 
                display: flex;
                flex-direction: column;
            }

            .login-content form label {
                margin-top: 0.75rem;
                margin-bottom: 0.25rem;
                font-weight: bold;
                color: #5C4033; 
            }

            .login-content form input[type="text"],
            .login-content form input[type="password"] {
                padding: 0.75rem;
                border: 1px solid #B0A195; 
                border-radius: 5px;
                font-size: 1rem;
                box-sizing: border-box;
            }

            .login-content form button {
                margin-top: 1.5rem;
                padding: 0.75rem;
                border: none;
                border-radius: 5px;
                background-color: #7A5B49;
                color: white;
                font-size: 1rem;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .login-content form button:hover {
                background-color: #5C4033; 
            }

            .login-logo {
                flex-basis: 200px;
                text-align: center;
            }

            .login-logo img {
                max-width: 100%;
                height: auto;
                border-radius: 5px;
            }

            .error-message {
                color: #A0522D;
                background-color: #F8E8D8; 
                border: 1px solid #A0522D;
                border-radius: 5px;
                padding: 0.75rem;
                margin-top: 1rem;
                text-align: center;
            }
        </style>
        </head>
    <body>
        
        <div class="login-container">
            <h2>
                <strong>Welcome Staff</strong>
            </h2>
            <div class="login-content">
                
                <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                    <label>Username</label>
                    <input
                        name="username"
                        type="text"
                        placeholder="username"
                        required />
                    
                    <label>Password</label>
                    <input
                        name="password"
                        type="password"
                        placeholder="password"
                        required />
                    
                    <button type="submit">
                        Login
                    </button>
                    
                    <% String error = (String) request.getAttribute("errorMessage"); %>
                    <% if(error != null){%>
                        <div class="error-message"><%= error %></div>
                    <% } %>
                </form>
                
                <div class="login-logo">
                    <img 
                        src="${pageContext.request.contextPath}/Ymage/Logo.jpg" 
                        alt="Logo">
                </div>
                
            </div> 
        </div> 
        
    </body>
</html>