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
    <title>Flipmart premium HTML5 & CSS3 Template</title>

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

            <!-- Right side: User info form (2/3) -->
            <div class="col-md-8">
                <h4>Welcome to Your Account</h4>
                <p>Here you can manage your personal information and orders.</p>

                <div class="user-info">
                    <h5>Your Information</h5>
                    <form class="outer-top-xs" role="form" method="POST" action="update-profile">
                        <div class="form-group">
                            <label class="info-title" for="username">Name</label>
                            <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
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
   <label for="publicKey">Generate Private Key</label>
<form id="genKeyForm" action="generate-key" method="POST">
    <button type="submit" id="genKeyBtn" class="btn btn-warning">Generate Key</button>
</form>

<script type="text/javascript">
    $(document).ready(function() {
        // Sự kiện submit form
        $('#genKeyForm').submit(function(e) {
            e.preventDefault();  // Ngừng việc gửi form mặc định

            $.ajax({
                type: "POST",
                url: "generate-key",  // Gọi đến servlet xử lý
                dataType: "binary",  // Dữ liệu trả về là binary (file)
                xhrFields: {
                    responseType: "blob"  // Thực tế trả về là blob
                },
                success: function(response) {
                    // Tạo một đối tượng URL từ blob nhận được
                    var url = window.URL.createObjectURL(response);
                    var a = document.createElement("a");  // Tạo một liên kết để tải file
                    a.href = url;
                    a.download = "privateKey.txt";  // Đặt tên file tải về
                    document.body.appendChild(a);
                    a.click();  // Mô phỏng việc nhấn vào link để tải
                    a.remove();  // Xóa đối tượng liên kết sau khi tải xong
                    window.URL.revokeObjectURL(url);  // Giải phóng URL
                    
                   
                },
                error: function() {
                    alert("Có lỗi khi tạo khóa.");
                }
            });
        });
    });
</script>


                    
                       <div class="form-group">
                        <label for="publicKey">Public Key:</label>
                        <input type="text" id="publicKey" class="form-control" readonly value="${key}"/>
                    </div>
                    
           <!-- Form Tải Lên Public Key -->
<form id="uploadPublickeyForm" action="upload-publickey" method="POST" enctype="multipart/form-data">
    <div class="form-group">
        <label for="publickeyFile">Choose Public Key File</label>
        <input type="file" class="form-control-file" id="publickeyFile" name="publickeyFile" accept=".pem,.txt,.key" required>
    </div>
    <button type="submit" class="btn btn-success">Upload Public Key</button>
</form>

                      <hr/>
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
