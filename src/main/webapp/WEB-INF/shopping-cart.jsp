<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Meta -->
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
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
		<jsp:include page="header.jsp"></jsp:include>

<!-- ============================================== HEADER : END ============================================== -->
<div class="breadcrumb">
	<div class="container">
		<div class="breadcrumb-inner">
			<ul class="list-inline list-unstyled">
				<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
				<li class='active'>Shopping Cart</li>
			</ul>
		</div><!-- /.breadcrumb-inner -->
	</div><!-- /.container -->
</div><!-- /.breadcrumb -->

<div class="body-content outer-top-xs">
	<div class="container">
		<div class="row ">
			<div class="shopping-cart">
				<div class="shopping-cart-table ">
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th class="cart-romove item">Remove</th>
					<th class="cart-description item">Image</th>
					<th class="cart-product-name item">Product Name</th>
					<th class="cart-edit item">Edit</th>
					<th class="cart-qty item">Quantity</th>
					<th class="cart-sub-total item">Subtotal</th>
					<th class="cart-total last-item">Grandtotal</th>
				</tr>
			</thead><!-- /thead -->
			<tfoot>
				<tr>
					<td colspan="7">
						<div class="shopping-cart-btn">
							<span class="">
								<a href="home" class="btn btn-upper btn-primary outer-left-xs">Continue Shopping</a>
					
							</span>
						</div><!-- /.shopping-cart-btn -->
					</td>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="item" items="${cart.items}">
				<tr>
					<td style="display: none;">${item.product.id}</td>
					<td class="romove-item">
    <form action="cart" method="post">
        <input type="hidden" name="productCode" value="<c:out value='${item.product.id}'/>">
        <input type="hidden" name="quantity" value="0">
        <input type="submit" value="Xóa" class="btn btn-primary">
    </form>
</td>

					<td class="cart-image">
						<a class="entry-thumbnail" href="detail?id=${item.product.id}">
						    <img src="${item.product.image}" alt="">
						</a>
					</td>
					<td class="cart-product-name-info">
						<h4 class='cart-product-description'><a href="detail?id=${item.product.id}">${item.product.name}</a></h4>
						<div class="row">
							<div class="col-sm-4">
								<div class="rating rateit-small"></div>
							</div>
							<div class="col-sm-8">
								<div class="reviews">
									(06 Reviews)
								</div>
							</div>
						</div><!-- /.row -->
						<div class="cart-product-info">
											<span class="product-color">COLOR:<span>Blue</span></span>
						</div>
					</td>
					<td class="cart-product-edit"><a href="#" class="product-edit">Edit</a></td>
					<td class="cart-product-quantity">
						<form action="cart" method="post" class="quantity" style="display: flex; align-items: center;">
    <input type="text" name="quantity" value="<c:out value='${item.quantity}'/>" 
           id="quantity" min="1" max="100" class="qty" 
           style="width: 40px; text-align: center; margin: 0 5px;" />
    <input type="hidden" name="productCode" value="<c:out value='${item.product.id}'/>">
    <input class="btn btn-primary" type="submit" value="Cập nhật">
</form>

				      
		            </td>
					<td class="cart-product-sub-total"><span class="cart-sub-total-price">${item.product.priceCurrencyFormat}</span></td>
					<td class="cart-product-grand-total"><span class="cart-grand-total-price">${item.totalCurrencyFormat}</span></td>
				</tr>
			</c:forEach>
			</tbody><!-- /tbody -->
		</table><!-- /table -->
	</div>
</div><!-- /.shopping-cart-table -->
	
<div class="col-md-4 col-sm-12 estimate-ship-tax">

</div><!-- /.estimate-ship-tax -->

<div class="col-md-4 col-sm-12 estimate-ship-tax">
	
</div><!-- /.estimate-ship-tax -->

<div class="col-md-4 col-sm-12 cart-shopping-total">
	<table class="table">
		<thead>
			<tr>
				<th>
							<c:set var="sumprice" value="${0}" />
					<c:forEach items="${cart.items}" var="item">
						<c:set var="sumprice"
							value="${sumprice +item.quantity * item.product.price}" />
					</c:forEach>
					<div class="cart-sub-total">
						Subtotal<span class="inner-left-md">${sumprice}</span>
					</div>
					<div class="cart-grand-total">
						Grand Total<span class="inner-left-md">${sumprice}</span>
					</div>
				</th>
			</tr>
		</thead><!-- /thead -->
		<tbody>
				<tr>
					<td>
						<div class="cart-checkout-btn pull-right">
							<button type="submit" class="btn btn-primary checkout-btn">PROCCED TO CHEKOUT</button>
							<span class="">Checkout with multiples address!</span>
						</div>
					</td>
				</tr>
		</tbody><!-- /tbody -->
	</table><!-- /table -->
</div><!-- /.cart-shopping-total -->			</div><!-- /.shopping-cart -->
		</div> <!-- /.row -->
	</div><!-- /.container -->
</div><!-- /.body-content -->

<!-- ============================================================= FOOTER ============================================================= -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- ============================================================= FOOTER : END============================================================= -->


	<!-- For demo purposes – can be removed on production -->
	
	
	<!-- For demo purposes – can be removed on production : End -->

	<!-- JavaScripts placed at the end of the document so the pages load faster -->
	<jsp:include page="script.jsp"></jsp:include>
	

</body>
</html>
    