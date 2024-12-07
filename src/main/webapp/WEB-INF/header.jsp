<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <header class="header-style-1">

		<!-- ============================================== TOP MENU ============================================== -->
		<div class="top-bar animate-dropdown">
			<div class="container">
				<div class="header-top-inner">
					<div class="cnt-account">
						<ul class="list-unstyled">
							<li><a href="url?page=dashboard"><i class="icon fa fa-user"></i>My
									Account</a></li>
							<li><a href="url?page=cart"><i class="icon fa fa-shopping-cart"></i>My
									Cart</a></li>
							<li><a href="url?page=checkout"><i class="icon fa fa-check"></i>Checkout</a></li>
							<li><a href="url?page=login"><i class="icon fa fa-lock"></i>Login</a></li>
						</ul>
					</div>
					<!-- /.cnt-account -->

					<!-- /.cnt-cart -->
					<div class="clearfix"></div>
				</div>
				<!-- /.header-top-inner -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /.header-top -->
		<!-- ============================================== TOP MENU : END ============================================== -->
		<div class="main-header">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-3 logo-holder">
						<!-- ============================================================= LOGO ============================================================= -->
						<div class="logo">
							<a href="${pageContext.request.contextPath}/home"> <img src="assets\images\logo.png"
								alt="logo">
							</a>
						</div>
						<!-- /.logo -->
						<!-- ============================================================= LOGO : END ============================================================= -->
					</div>
					<!-- /.logo-holder -->

					<div class="col-xs-12 col-sm-12 col-md-7 top-search-holder">
						<!-- /.contact-row -->
						<!-- ============================================================= SEARCH AREA ============================================================= -->
						<div class="search-area">
							<form>
								<div class="control-group">
									<input class="search-field" placeholder="Search here...">
									<a class="search-button" href="#"></a>
								</div>
							</form>
						</div>
						<!-- /.search-area -->
						<!-- ============================================================= SEARCH AREA : END ============================================================= -->
					</div>
					<!-- /.top-search-holder -->

					<div
						class="col-xs-12 col-sm-12 col-md-2 animate-dropdown top-cart-row">
						<!-- ============================================================= SHOPPING CART DROPDOWN ============================================================= -->

						<div class="dropdown dropdown-cart">
							<a href="cart.jsp" class="dropdown-toggle lnk-cart"
								data-toggle="dropdown">
							  <div class="items-cart-inner">
              <div class="basket"> <i class="glyphicon glyphicon-shopping-cart"></i> </div>
              <c:set var="count" value="${0}" /> 
               <c:forEach items="${cart.items}"
							var="item">
							<c:set var="count" value="${count + item.quantity}" />
						</c:forEach>
              <div class="basket-item-count"><span class="count">${count}</span></div>
              <div class="total-price-basket"> <span class="lbl">cart</div>
            </div>
							</a>
							<ul class="dropdown-menu">
								<li>
									<div class="cart-item product-summary">
									
										<c:forEach var="item" items="${cart.items}">
										<div class="row">
											<c:set var="count" value="${count + item.quantity}" />
											<div class="col-xs-4">
												<div class="image">
													<a href="detail.html"><img src="${item.product.image}"
														alt=""></a>
												</div>
											</div>
											<div class="col-xs-7">
												<h3 class="name">
													<a href="index.php?page-detail">${item.product.name}</a>
												</h3>
												<div class="price">${item.totalCurrencyFormat}</div>
											</div>
											
											<div class="col-xs-1 action">
												<a href="#"><i class="fa fa-trash"></i></a>
											</div>
										</div>
										</c:forEach>
									</div> <!-- /.cart-item -->
									<div class="clearfix"></div>
									<hr>
									<div class="clearfix cart-total">
										<div class="pull-right">
										<c:set var="sumprice" value="${0}" />
					<c:forEach items="${cart.items}" var="item">
						<c:set var="sumprice"
							value="${sumprice +item.quantity * item.product.price}" />
					</c:forEach>
											<span class="text">Sub Total :</span><span class='price'>${sumprice}</span>
										</div>
										<div class="clearfix"></div>
										
										<a href="url?page=checkout"
											class="btn btn-upper btn-primary btn-block m-t-20">Checkout</a>
									</div> <!-- /.cart-total-->

								</li>
							</ul>
							<!-- /.dropdown-menu-->
						</div>
						<!-- /.dropdown-cart -->

						<!-- ============================================================= SHOPPING CART DROPDOWN : END============================================================= -->
					</div>
					<!-- /.top-cart-row -->
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container -->

		</div>
		<!-- /.main-header -->

		<!-- ============================================== NAVBAR ============================================== -->
		<div class="header-nav animate-dropdown">
			<div class="container">
				<div class="yamm navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button data-target="#mc-horizontal-menu-collapse"
							data-toggle="collapse" class="navbar-toggle collapsed"
							type="button">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
					<div class="nav-bg-class">
						<div class="navbar-collapse collapse"
							id="mc-horizontal-menu-collapse">
							<div class="nav-outer">
								<ul class="nav navbar-nav">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-hover="dropdown" data-toggle="dropdown">Pages</a>
										<ul class="dropdown-menu pages">
											<li>
												<div class="yamm-content">
													<div class="row">
														<div class="col-xs-12 col-menu">
															<ul class="links">
																<li><a href="home">Home</a></li>
																<li><a href="detail.html">Detail</a></li>
																<li><a href="url?page=cart">Shopping Cart
																	</a></li>
																<li><a href="url?page=checkout">Checkout</a></li>
																<li><a href="contact.html">Contact</a></li>
																<li><a href="url?page=login">Sign In</a></li>
																<li><a href="${pageContext.request.contextPath}/404.jsp">404</a></li>
															</ul>
														</div>
													</div>
												</div>
											</li>
										</ul></li>
								</ul>
								<!-- /.navbar-nav -->
								<div class="clearfix"></div>
							</div>
							<!-- /.nav-outer -->
						</div>
						<!-- /.navbar-collapse -->

					</div>
					<!-- /.nav-bg-class -->
				</div>
				<!-- /.navbar-default -->
			</div>
			<!-- /.container-class -->

		</div>
		<!-- /.header-nav -->
		<!-- ============================================== NAVBAR : END ============================================== -->

	</header>
        