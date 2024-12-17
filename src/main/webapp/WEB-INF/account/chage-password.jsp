<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

            <!-- Right side: Change Password Form (2/3) -->
            <div class="col-md-8">
                <h5>Change Your Password</h5>
                <form method="POST" action="update-password">
                    <div class="form-group">
                        <label class="info-title" for="currentPassword">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="newPassword">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="confirmPassword">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn-upper btn btn-primary">Change Password</button>
                </form>
                 
            </div>
             
        </div>
            <hr/>
    </div>

    <!-- Include Footer -->
    <jsp:include page="../footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="../script.jsp"></jsp:include>
</body>
</html>
