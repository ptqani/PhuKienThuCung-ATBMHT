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
    <title>Your Orders</title>

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

            <!-- Right side: Orders Table (2/3) -->
            <div class="col-md-8">
                <h5>Your Orders</h5>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#12345</td>
                            <td>12/12/2024</td>
                            <td>Shipped</td>
                            <td>$150.00</td>
                            <td><a href="order-details.jsp?orderId=12345" class="btn btn-info">View</a></td>
                        </tr>
                        <tr>
                            <td>#12346</td>
                            <td>15/12/2024</td>
                            <td>Processing</td>
                            <td>$80.00</td>
                            <td><a href="order-details.jsp?orderId=12346" class="btn btn-info">View</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="script.jsp"></jsp:include>
</body>
</html>
