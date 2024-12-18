<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Flipmart premium HTML5 & CSS3 Template</title>

    <!-- Bootstrap Core CSS -->
    <jsp:include page="../css.jsp"></jsp:include>
    <jsp:include page="../header.jsp"></jsp:include>
</head>
<body>
<hr/>
<div class="container mt-5">
    <div class="row">
        <!-- Left side: My Account content (1/3) -->
        <div class="col-md-4">
            <jsp:include page="my-account.jsp"></jsp:include>
        </div>

        <!-- Right side: User info form (2/3) -->
        <div class="col-md-8">
            <h4>Welcome to Your Account</h4>
            <p>Here you can manage your personal information and orders.</p>

            <div class="user-info">
                <h5>Your Information</h5>
                <form class="outer-top-xs" role="form" method="POST" action="update-profile">
                    <div class="form-group">
                        <label class="info-title" for="username">Name</label>
                        <input type="text" class="form-control" id="username" name="username" value="${user.username}"
                               required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="email">Email Address</label>
                        <input type="email" class="form-control" id="email" name="email" value="${user.email}" readonly>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="phone">Phone Number</label>
                        <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}" required>
                    </div>
                    <button type="submit" class="btn-upper btn btn-primary">Update Info</button>
                </form>
                <!-- Add GenKey Section -->
                <hr/>
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
