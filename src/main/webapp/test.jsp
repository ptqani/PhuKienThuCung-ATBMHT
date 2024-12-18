<%--
  Created by IntelliJ IDEA.
  User: Thuan
  Date: 18-12-2024
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trang HTML với CSS</title>
<%--  <link href="<c:url value="/test.css"/>" type="text/css"/>--%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/test.css">
  <style>
    /* CSS cho toàn bộ trang */

  </style>
</head>
<body>

<!-- Phần header -->
<header>
  <h1>Chào mừng đến với trang HTML của tôi!</h1>
</header>

<!-- Phần nội dung chính -->
<div class="content">
  <h2>Đây là phần nội dung của trang</h2>
  <p>Trang này được xây dựng bằng HTML và CSS để bạn có thể hiểu cách thức hoạt động của chúng. Bạn có thể chỉnh sửa mã này để thay đổi giao diện của trang web.</p>
  <button class="btn">Click me!</button>
</div>

<!-- Phần footer -->
<footer>
  <p>© 2024 Tất cả quyền được bảo lưu.</p>
</footer>

</body>
</html>
