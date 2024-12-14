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

            <!-- Right side: Key management (2/3) -->
            <div class="col-md-8">
      
                <!-- Hiển thị thông tin public key -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông Tin Public Key</h3>
                    </div>
                    <div class="panel-body">
                       
                            <p><strong>Public Key Hiện Tại:</strong></p>
                            <textarea class="form-control" rows="6" readonly>${key}</textarea>

                    
                    </div>
                </div>

                <!-- Chức năng tạo cặp key mới -->
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Tạo Cặp Key Mới</h3>
                    </div>
                    <div class="panel-body">
                        <form id="genKeyForm" action="generate-key" method="post">             
                            <button type="submit" class="btn btn-success btn-block">Tạo Key</button>
                        </form>
                    </div>
                    
                </div>
                <!-- Hiển thị liên kết tải private key -->
               
             
                <!-- Chức năng tải public key lên server và cập nhật lại -->
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Upload Public Key Mới</h3>
                    </div>
                    <div class="panel-body">
 
                               <form id="uploadPublickeyForm" action="upload-publickey" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                               <label for="publickeyFile">Choose Public Key File</label>
                               <input type="file" class="form-control-file" id="publickeyFile" name="publickeyFile" accept=".pem,.txt,.key" required>
                                <label for="public_key">Nhập Public Key Mới:</label>
                                <textarea name="public_key" id="public_key" class="form-control" rows="5" placeholder="Nhập public key mới..."></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Cập nhật</button>
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
                        
                            <p>Bạn đã mất key? Nếu bạn muốn xóa public key cũ và tạo lại key mới, vui lòng báo ở đây:</p>
                            <button type="submit" class="btn btn-danger btn-lg btn-block">Báo Mất Key</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
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
                    window.location.href = "/keygen";
                   
                },
                error: function() {
                    alert("Có lỗi khi tạo khóa.");
                }
            });
        });
    });
</script>
<script>
    document.getElementById('publickeyFile').addEventListener('change', function(event) {
        const file = event.target.files[0]; // Lấy file người dùng chọn
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                // Đưa nội dung file vào textarea
                document.getElementById('public_key').value = e.target.result;
            };
            reader.readAsText(file); // Đọc file dưới dạng văn bản
        }
    });
</script>
    <!-- Include Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Include Scripts -->
    <jsp:include page="script.jsp"></jsp:include>
</body>
</html>
