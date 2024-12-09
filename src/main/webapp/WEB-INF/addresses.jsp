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
    <title>Manage Address</title>

    <!-- Bootstrap Core CSS -->
    <jsp:include page="css.jsp"></jsp:include>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
  <hr/>
    <div class="container mt-5">
        <div class="row">
            <!-- Left side: My Account content (1/3) -->
            <div class="col-md-4">
                <jsp:include page="my-account.jsp"></jsp:include>
            </div>

            <!-- Right side: Manage Address Form (2/3) -->
            <div class="col-md-8">
                <h5>Manage Your Address</h5>
                
                <!-- Address Display -->
                <div class="address-display">
                    <h6>Current Address</h6>
                    <p><strong>Street Address:</strong> 123 Main St</p>
                    <p><strong>City:</strong> New York</p>
                    <p><strong>State:</strong> NY</p>
                    <p><strong>ZIP Code:</strong> 10001</p>
                    <p><strong>Country:</strong> USA</p>
                
                </div>
                      <hr/>
                <!-- Address Edit Form (if editing) -->
                <form method="POST" action="update-address" class="address-form mt-4">
                    <div class="form-group">
                        <label class="info-title" for="streetAddress">Street Address</label>
                        <input type="text" class="form-control" id="streetAddress" name="streetAddress" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="city">City</label>
                        <input type="text" class="form-control" id="city" name="city" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="state">State</label>
                        <input type="text" class="form-control" id="state" name="state" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="zipCode">ZIP Code</label>
                        <input type="text" class="form-control" id="zipCode" name="zipCode" required>
                    </div>
                    <div class="form-group">
                        <label class="info-title" for="country">Country</label>
                        <input type="text" class="form-control" id="country" name="country" required>
                    </div>
                    <button type="submit" class="btn btn-success">Update Address</button>
                </form>
                  <hr/>
            </div>
        </div>
    </div>

    <!-- Include Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="script.jsp"></jsp:include>
</body>
</html>
