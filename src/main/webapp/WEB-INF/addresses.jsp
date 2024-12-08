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
    <title>Your Addresses</title>

    <!-- Bootstrap Core CSS -->
    <jsp:include page="css.jsp"></jsp:include>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <!-- Left side: My Account content (1/3) -->
            <div class="col-md-4">
                <jsp:include page="my-account.jsp"></jsp:include>
            </div>

            <!-- Right side: Manage Addresses Form (2/3) -->
            <div class="col-md-8">
                <h5>Your Addresses</h5>
                <p>Manage your shipping and billing addresses here.</p>

                <!-- Form to manage addresses -->
                <form method="POST" action="update-addresses">
                    <div class="form-group">
                        <label class="info-title" for="shippingAddress">Shipping Address</label>
                        <input type="text" class="form-control" id="shippingAddress" name="shippingAddress" value="${user.shippingAddress}" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="billingAddress">Billing Address</label>
                        <input type="text" class="form-control" id="billingAddress" name="billingAddress" value="${user.billingAddress}" required>
                    </div>
                    <button type="submit" class="btn-upper btn btn-primary">Update Addresses</button>
                </form>

                <!-- List of existing addresses (optional) -->
                <div class="existing-addresses mt-4">
                    <h6>Existing Addresses</h6>
                    <p><strong>Shipping Address:</strong> ${user.shippingAddress}</p>
                    <p><strong>Billing Address:</strong> ${user.billingAddress}</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="script.jsp"></jsp:include>
</body>
</html>
