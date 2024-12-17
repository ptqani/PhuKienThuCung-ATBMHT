<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="keywords" content="MediaCenter, Template, eCommerce">
<meta name="robots" content="all">

<title>Flipmart premium HTML5 & CSS3 Template</title>

<!-- Bootstrap Core CSS -->
<jsp:include page="css.jsp"></jsp:include>

</head>
<body class="cnt-home">
	<!-- ============================================== HEADER ============================================== -->
	<jsp:include page="header.jsp" />

	</header>

	<!-- ============================================== HEADER : END ============================================== -->
	<div class="body-content outer-top-bd">
		<div class="container">
			<div class="x-page inner-bottom-sm">
				<div class="row">
					<div class="col-md-12 x-text text-center">
						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger">
								<strong>${errorMessage}!</strong>
							</div>
							<a href="${linkGoto}">Go to</a>
						</c:if>


						<c:if test="${not empty message}">
							<div class="alert alert-success">
								<strong>${message}!</strong>
							</div>
							<a href="${linkGoto}">Go to</a>
						</c:if>
						<hr>
						<a href="home.html"><i class="fa fa-home"></i> Go To Homepage</a>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.sigin-in-->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.body-content -->
	<!-- ============================================================= FOOTER ============================================================= -->
<jsp:include page="footer.jsp"></jsp:include>
	<!-- ============================================================= FOOTER : END============================================================= -->


	<!-- For demo purposes – can be removed on production -->


	<!-- For demo purposes – can be removed on production : End -->

	<!-- JavaScripts placed at the end of the document so the pages load faster -->
	<jsp:include page="script.jsp"></jsp:include>

	<!-- For demo purposes – can be removed on production -->

	<script src="switchstylesheet/switchstylesheet.js"></script>

	<script>
		$(document).ready(function() {
			$(".changecolor").switchstylesheet({
				seperator : "color"
			});
			$('.show-theme-options').click(function() {
				$(this).parent().toggleClass('open');
				return false;
			});
		});

		$(window).bind("load", function() {
			$('.show-theme-options').delay(2000).trigger('click');
		});
	</script>
	<!-- For demo purposes – can be removed on production : End -->



</body>
</html>
