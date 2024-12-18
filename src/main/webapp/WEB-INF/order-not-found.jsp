<%--
  Created by IntelliJ IDEA.
  User: vanlu
  Date: 12/18/2024
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn Hàng Không Tìm Thấy</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .message-container {
            text-align: center;
            padding: 50px;
            background-color: white;
            margin-top: 50px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin-left: auto;
            margin-right: auto;
        }

        h1 {
            color: #e74c3c;
            font-size: 24px;
        }

        p {
            color: #555;
            font-size: 16px;
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .btn-back:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<div class="message-container">
    <h1>Đơn Hàng Không Tìm Thấy</h1>
    <p>Rất tiếc, không tìm thấy thông tin đơn hàng với ID bạn đã nhập.</p>
    <p>Vui lòng kiểm tra lại và thử lại.</p>
    <a href="home.jsp" class="btn-back">Quay lại Trang Chủ</a>
</div>
</body>
</html>
