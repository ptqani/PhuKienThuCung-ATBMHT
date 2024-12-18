<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="keywords" content="MediaCenter, Template, eCommerce">
    <meta name="robots" content="all">
    <title>Change Password</title>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/key.css">--%>
    <!-- Bootstrap Core CSS -->
    <jsp:include page="../css.jsp"></jsp:include>
    <jsp:include page="../header.jsp"></jsp:include>
</head>
<body>
    <div class="container mt-5">

        <div class="row">
            <!-- Left side: My Account content (1/3) -->
            <div class="col-md-4">
                <jsp:include page="my-account.jsp"></jsp:include>
            </div>

            <!-- Right side: Key management (2/3) -->
            <div class="col-md-8">
                <!-- Thông báo -->
                <c:if test="${not empty message}">
                    <div class="alert alert-info">
                        <strong>${message}</strong>
                    </div>
                </c:if>
                <!-- Hiển thị thông tin public key -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông Tin Public Key</h3>
                    </div>
                    <div class="panel-body">
                        <c:if test="${not empty sessionScope.user.publicKey}">
                            <p><strong>Public Key Hiện Tại:</strong></p>
                            <textarea class="form-control" rows="6" readonly>${sessionScope.user.publicKey}</textarea>
                        </c:if>
                        <c:if test="${empty sessionScope.user.publicKey}">
                            <p>Chưa có public key. Vui lòng tạo một cặp key mới.</p>
                        </c:if>
                    </div>
                </div>

                <!-- Chức năng tạo cặp key mới -->
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Tạo Cặp Key Mới</h3>
                    </div>
                    <div class="panel-body">
                        <form action="genkey" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope.user.id}" />
                            <button type="submit" class="btn btn-success btn-block">Tạo Key</button>
                        </form>
                    </div>
                </div>
                <!-- Hiển thị liên kết tải private key -->
                <c:if test="${not empty privateKeyFileName}">
                    <div class="alert alert-success mt-3">
                        <strong>Private Key đã được tạo thành công!</strong><br>
                        <a href="download-key?file=${privateKeyFileName}" class="btn btn-warning">Tải về Private Key</a>
                    </div>
                </c:if>
                <!-- Chức năng tải public key lên server và cập nhật lại -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Upload Public Key Mới</h3>
                    </div>
                    <div class="panel-body">
                        <form action="upload-key" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope.user.id}" />
                            <div class="form-group">
                                <label for="public_key">Nhập Public Key Mới:</label>
                                <textarea name="public_key" id="public_key" class="form-control" rows="5" placeholder="Nhập public key mới..."></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Tải Lên</button>
                        </form>
                    </div>
                </div>

                <!-- Chức năng báo mất key -->
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title">Báo Mất Key</h3>
                    </div>
                    <div class="panel-body">
                        <form action="reportkey" method="post">
                            <input type="hidden" name="user_id" value="${sessionScope.user.id}" />
                            <p>Bạn đã mất key? Nếu bạn muốn xóa public key cũ và tạo lại key mới, vui lòng báo ở đây:</p>
                            <button type="submit" class="btn btn-danger btn-lg btn-block">Báo Mất Key</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="../footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="../script.jsp"></jsp:include>
</body>
</html>
