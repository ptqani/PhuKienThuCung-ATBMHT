<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <title>Order Details</title>

    <!-- Include Stylesheet -->
    <jsp:include page="css.jsp"></jsp:include>
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

        .order-details .order-summary {
            margin-top: 20px;
            font-size: 1.2rem;
        }

        .order-details .order-summary strong {
            font-weight: bold;
        }

        .my-flex {
            display: flex;
            align-items: flex-end;
            justify-content: space-between;
        }

        .my-flex .input-container {
            flex-grow: 1;
        }

        .my-flex button {
            margin-left: 10px;
        }
    </style>

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
<div class="container">
<!-- Order Details Section -->
<div class="order-details">
    <h3>Order Received</h3>
    <!-- Order Information -->
    <div class="address">
        <p><strong>Order ID:</strong> # ${order.order_id}</p>
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
        <c:forEach items="${orderDetails}"
                   var="orderDetails">
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
            <td> ₫</td>
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

</div>

    <br />
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="form-group my-flex">
                        <div class="input-container">
                            <input type="text" id="hash" class="form-control" value="${hashOrder}" readonly>
                        </div>
                        <button class="btn btn-success btn-copy" onclick="copyHash()">Copy</button>
                    </div>

                </div>
                <!--panel-body end-->
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Upload Signature </h3>
                </div>
                <div class="panel-body">
                    <form action="signature-order" method="post">
                        <input type="hidden" name="orderId" value="${order.order_id}" />
                        <div class="form-group">
<%--                            <label for="public_key">:</label>--%>
                            <textarea name="signature" id="signature" class="form-control" rows="5" placeholder="Nhập public key mới..."></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Tải Lên</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function copyHash() {
        var copyText = document.getElementById("hash");
        copyText.select();
        copyText.setSelectionRange(0, 99999); // Cho phép sao chép trên các thiết bị di động
        document.execCommand("copy");
        alert("Mã hash đã được sao chép: " + copyText.value);
    }
</script>
<!-- Custom CSS -->

<br>

<!-- Footer Section -->
<jsp:include page="footer.jsp"></jsp:include>

<!-- Script Section -->
<jsp:include page="script.jsp"></jsp:include>
</body>
</html>
