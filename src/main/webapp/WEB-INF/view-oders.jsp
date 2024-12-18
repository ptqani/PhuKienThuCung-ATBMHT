<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Order Details</title>

    <!-- Include Stylesheet -->
    <jsp:include page="css.jsp"></jsp:include>

    <!-- JavaScript for Button Enable/Disable -->
    <script type="text/javascript">
        function togglePaymentButton() {
            var orderStatus = document.getElementById('order-status').innerText.trim();
            var paymentButton = document.getElementById('payment-button');

            if (orderStatus === 'Đã xác minh✅') {
                paymentButton.disabled = false; // Enable button
                paymentButton.classList.remove('disabled');
            } else {
                paymentButton.disabled = true; // Disable button
                paymentButton.classList.add('disabled');
            }
        }

        window.onload = togglePaymentButton;
    </script>
</head>
<body class="cnt-home">
<!-- Header Section -->
<jsp:include page="header.jsp"></jsp:include>

<!-- Breadcrumb Section -->
<div class="breadcrumb">
    <div class="container">
        <div class="breadcrumb-inner">
            <ul class="list-inline list-unstyled">
                <li><a href="#">Home</a></li>
                <li class="active">Order Details</li>
            </ul>
        </div>
    </div>
</div>

<!-- Order Details Section -->
<div class="order-details">
    <h3>Order Received</h3>
    <form action="downloadOders" method="get">
        <input type="hidden" name="orderId" value="${order.order_id}">
        <button type="submit">Tải xuống đơn hàng</button>
    </form>

    <!-- Order Information -->
    <div class="address">
        <form action="verification-form" method="post">
            <p><strong>Order ID:</strong> # ${order.order_id}</p>
            <input type="hidden" name="orderId" value="${order.order_id}" />
            <p>Trạng thái:
                <c:if test="${order.is_verified == 0}">
                    <span id="order-status"> ❌Chưa xác minh</span>
                </c:if>
                <c:if test="${order.is_verified == 1}">
                    <span id="order-status">Đã xác minh✅</span>
                </c:if>
            </p>
            <button type="submit" id="verify-button">Xác minh đơn hàng</button>
        </form>
        <p><strong>Date:</strong> ${order.order_date}</p>
        <p><strong>Email:</strong> ${order.email_address}</p>
        <p><strong>Total:</strong>${order.total_price} ₫</p>
    </div>

    <!-- Order Products -->
    <h3>Order Details</h3>
    <table>
        <thead>
        <tr>
            <th>Product</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderDetails}" var="orderDetails">
            <tr>
                <td>
                    <strong>${orderDetails.product_name}</strong><br>
                    × ${orderDetails.quantity}<br>
                </td>
                <td>${orderDetails.price} ₫</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td><strong>Subtotal:</strong></td>
            <td>₫</td>
        </tr>
        <tr>
            <td><strong>Payment Method:</strong></td>
            <td>Cash on delivery - COD</td>
        </tr>
        </tfoot>
    </table>

    <!-- Address Section -->
    <div class="address">
        <h3>Billing Address</h3>
        <p><strong>${order.name}</strong></p>
        <p>📞 ${order.phone}</p>
        <p>✉️ ${order.email_address}</p>
        <p>${order.shipping_address}</p>
    </div>

    <!-- Nút Thanh toán -->
    <div class="payment-button">
        <form action="process-payment" method="post">
            <input type="hidden" name="orderId" value="${order.order_id}">
            <input type="hidden" name="emailAddress" value="${order.email_address}">
            <button type="submit" id="payment-button" class="btn btn-primary" disabled>Thanh toán</button>
        </form>
    </div>
</div>

<!-- Custom CSS -->
<style>
    body {
        font-family: Arial, sans-serif;
        line-height: 1.6;
        margin: 0;
        padding: 0;
        background-color: #f8f9fa;
    }
    .breadcrumb {
        background-color: #fff;
        padding: 15px 0;
        margin-bottom: 20px;
    }
    .breadcrumb .container {
        max-width: 1200px;
        margin: 0 auto;
    }
    .breadcrumb-inner ul {
        display: flex;
        list-style: none;
        padding-left: 0;
    }
    .breadcrumb-inner ul li {
        margin-right: 10px;
    }
    .breadcrumb-inner ul li a {
        text-decoration: none;
        color: #007bff;
    }
    .breadcrumb-inner ul li.active a {
        color: #6c757d;
    }
    .order-details {
        max-width: 1200px;
        margin: 0 auto;
        padding: 30px;
        background-color: #ffffff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .order-details h3 {
        text-align: center;
        margin-bottom: 20px;
        font-size: 1.8rem;
        color: #333;
    }
    .order-details table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    .order-details table th, .order-details table td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    .order-details table th {
        background-color: #f4f4f4;
    }
    .order-details .address {
        margin-top: 30px;
    }
    .order-details .address h3 {
        font-size: 1.5rem;
        margin-bottom: 10px;
        color: #333;
    }
    .order-details .address p {
        font-size: 1.1rem;
        margin: 5px 0;
        color: #555;
    }
    .order-details .payment-button {
        text-align: center;
        margin-top: 30px;
    }
    .order-details .payment-button .btn {
        background-color: #28a745;
        color: white;
        font-size: 1.2rem;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .order-details .payment-button .btn.disabled {
        background-color: #6c757d;
        cursor: not-allowed;
    }
    .order-details .payment-button .btn:hover {
        background-color: #218838;
    }
</style>

<!-- Footer Section -->
<jsp:include page="footer.jsp"></jsp:include>

<!-- Script Section -->
<jsp:include page="script.jsp"></jsp:include>
</body>
</html>
