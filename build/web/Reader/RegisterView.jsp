<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat, java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Registration</title>
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
            /* ĐÃ SỬA: Tăng padding và max-width */
            padding: 3rem 3.5rem; 
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.4);
            width: 100%;
            max-width: 950px; 
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

        .form-row {
            display: flex;
            gap: 1.5rem;
            margin-bottom: 1rem;
        }

        .form-group {
            flex: 1;
            min-width: 0; 
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #5C4033;
        }

        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group input[type="email"],
        .form-group input[type="tel"],
        .form-group input[type="date"] {
            width: 100%;
            padding: 0.75rem;
            box-sizing: border-box;
            border: 1px solid #B0A195;
            border-radius: 5px;
            background-color: #fff;
            color: #3B2B21;
            font-size: 1rem;
        }

        .form-group input[readonly] {
            background-color: #DBCFBC;
            cursor: not-allowed;
            color: #7A5B49;
        }
        
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 20px;
            text-align: center;
        }

        .button-group {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }

        .button-group button {
            flex: 1; 
            padding: 0.85rem;
            border-radius: 5px;
            font-size: 1.1rem;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            box-sizing: border-box; 
        }

        .btn-register {
            background-color: #7A5B49;
            color: #E8D9C8; 
            border: 2px solid #7A5B49;
        }
        .btn-register:hover {
            background-color: #5C4033;
            border-color: #5C4033;
            transform: translateY(-2px);
        }

        .btn-reset {
            background-color: #E8D9C8;
            color: #5C4033; 
            border: 2px solid #5C4033;
        }
        .btn-reset:hover {
            background-color: #DBCFBC;
            transform: translateY(-2px);
        }
        
    </style>
</head>
<body>

    <%
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDateStr = sdf.format(today);

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.YEAR, 1);
        Date expirationDate = cal.getTime();
        String expirationDateStr = sdf.format(expirationDate);

        String errorMessage = (String) request.getAttribute("errorMessage");
    %>

    <a href="#" class="home-button">🏠</a>

    <div class="container">
        
        <a href="javascript:history.back()" class="back-button">←</a>

        <h2 class="page-title">Register Account</h2>

        <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/AddNewReaderServlet" method="post">
            
            <div class="form-row">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="name">Full Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="studentCode">Student Code:</label>
                    <input type="text" id="studentCode" name="studentCode" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="birth">Date of Birth:</label>
                    <input type="date" id="birth" name="birth" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="tel">Phone Number:</label>
                    <input type="tel" id="tel" name="tel" required 
                           pattern="[0-9]+" 
                           oninput="this.value = this.value.replace(/[^0-9]/g, '')"
                           title="Please enter only numbers">
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label for="createDate">Card Create Date:</label>
                    <input type="date" id="createDate" name="createDate" value="<%= createDateStr %>" readonly>
                </div>
                <div class="form-group">
                    <label for="endDate">Expiration Date:</label>
                    <input type="date" id="endDate" name="endDate" value="<%= expirationDateStr %>" readonly>
                </div>
            </div>

            <div class="button-group">
                <button type="reset" class="btn-reset">Reset</button>
                <button type="submit" class="btn-register">Register</button>
            </div>
        </form>
    </div>

</body>
</html>