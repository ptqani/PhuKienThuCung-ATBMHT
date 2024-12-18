<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Meta -->
<meta charset="utf-8">
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
	<jsp:include page="header.jsp"></jsp:include>
	<!-- ============================================== HEADER : END ============================================== -->
	<div class="body-content outer-top-xs" id="top-banner-and-menu">
		<div class="container">
			<div class="row">
				<!-- ============================================== SIDEBAR ============================================== -->
				<div class="col-xs-12 col-sm-12 col-md-3 sidebar">

					<!-- ================================== TOP NAVIGATION ================================== -->
					<div class="side-menu animate-dropdown outer-bottom-xs">
						<div class="head">
							<i class="icon fa fa-align-justify fa-fw"></i> Categories
						</div>
						<nav class="yamm megamenu-horizontal">
							<ul class="nav">
								<c:forEach begin="0" end="3" items="${listCategory}"
									var="listCategory">
									<li><a href="cate?categoryId=${listCategory.id}"><i
											class="icon fa fa-paw" aria-hidden="true"></i>${listCategory.name}</a>
										<!-- /.dropdown-menu --></li>

								</c:forEach>

							</ul>
							<!-- /.nav -->
						</nav>
						<!-- /.megamenu-horizontal -->
					</div>
					<!-- /.side-menu -->
					<!-- ================================== TOP NAVIGATION : END ================================== -->

					<!-- ============================================== HOT DEALS ============================================== -->
					<div class="sidebar-widget hot-deals wow fadeInUp outer-bottom-xs">
						<h3 class="section-title">hot deals</h3>
						<div
							class="owl-carousel sidebar-carousel custom-carousel owl-theme outer-top-ss">
							<c:forEach begin="0" end="3" items="${listRandomProductc}"
								var="listRandomProductc">
								<div class="item">
									<div class="products">
										<div class="hot-deal-wrapper">
											<div class="image">
												<img
													src="${pageContext.request.contextPath}/${listRandomProductc.image}"
													alt="">
											</div>
											<div class="sale-offer-tag">
												<span>49%<br> off
												</span>
											</div>
											<div class="timing-wrapper">
												<div class="box-wrapper">
													<div class="date box">
														<span class="key">120</span> <span class="value">DAYS</span>
													</div>
												</div>
												<div class="box-wrapper">
													<div class="hour box">
														<span class="key">20</span> <span class="value">HRS</span>
													</div>
												</div>
												<div class="box-wrapper">
													<div class="minutes box">
														<span class="key">36</span> <span class="value">MINS</span>
													</div>
												</div>
												<div class="box-wrapper hidden-md">
													<div class="seconds box">
														<span class="key">60</span> <span class="value">SEC</span>
													</div>
												</div>
											</div>
										</div>
										<!-- /.hot-deal-wrapper -->

										<div class="product-info text-left m-t-20">
											<h3 class="name">
												<a href="detail?id=${listRandomProductc.id}">${listRandomProductc.name}</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="product-price">
												<span class="price"> ${listRandomProductc.price} </span> <span
													class="price-before-discount">${listRandomProductc.sellprice}</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->

										<div class="cart clearfix animate-effect">
											<div class="action">
												<div class="add-cart-button btn-group">
															<form action="cart" method="post">
																			<input type="hidden" name="productCode"
																				value="<c:out value='${listRandomProductc.id}'/>">
																			<button class="btn btn-primary cart-btn" type="submit">Add
														to cart</button>
																			
																		</form>
													
												</div>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
								</div>
							</c:forEach>

						</div>
						<!-- /.sidebar-widget -->
					</div>
					<!-- ============================================== HOT DEALS: END ============================================== -->

					<!-- ============================================== SPECIAL OFFER ============================================== -->

					<div class="sidebar-widget outer-bottom-small wow fadeInUp">
						<h3 class="section-title">Special Offer</h3>
						<div class="sidebar-widget-body outer-top-xs">
							<div
								class="owl-carousel sidebar-carousel special-offer custom-carousel owl-theme outer-top-xs">
								<div class="item">
									<div class="products special-product">
										<c:forEach begin="0" end="3" items="${listProductoldThree}"
											var="listProductoldThree">
											<div class="product">
												<div class="product-micro">
													<div class="row product-micro-row">
														<div class="col col-xs-5">
															<div class="product-image">
																<div class="image">
																	<a href="detail?id=${listProductoldThree.id}"> <img
																		src="${pageContext.request.contextPath}/${listProductoldThree.image}"
																		alt="">
																	</a>
																</div>
																<!-- /.image -->

															</div>
															<!-- /.product-image -->
														</div>
														<!-- /.col -->
														<div class="col col-xs-7">
															<div class="product-info">
																<h3 class="name">
																	<a href="detail?id=${listProductoldThree.id}">${listProductoldThree.name}</a>
																</h3>
																<div class="rating rateit-small"></div>
																<div class="product-price">
																	<span class="price">
																		${listProductoldThree.price} </span>
																</div>
																<!-- /.product-price -->

															</div>
														</div>
														<!-- /.col -->
													</div>
													<!-- /.product-micro-row -->
												</div>
												<!-- /.product-micro -->

											</div>
										</c:forEach>
									</div>
								</div>

							</div>
						</div>
						<!-- /.sidebar-widget-body -->
					</div>
					<!-- /.sidebar-widget -->
					<!-- ============================================== SPECIAL OFFER : END ============================================== -->
					<!-- ============================================== PRODUCT TAGS ============================================== -->

					<!-- /.sidebar-widget -->
					<!-- ============================================== PRODUCT TAGS : END ============================================== -->
					<!-- ============================================== SPECIAL DEALS ============================================== -->

					<div class="sidebar-widget outer-bottom-small wow fadeInUp">
						<h3 class="section-title">Special Deals</h3>
						<div class="sidebar-widget-body outer-top-xs">
							<div
								class="owl-carousel sidebar-carousel special-offer custom-carousel owl-theme outer-top-xs">
								<div class="item">
									<div class="products special-product">
										<c:forEach begin="0" end="2" items="${listProductThree}"
											var="listProductThree">
											<div class="product">
												<div class="product-micro">
													<div class="row product-micro-row">
														<div class="col col-xs-5">
															<div class="product-image">
																<div class="image">
																	<a href="detail?id=${listProductThree.id}"> <img
																		src="${pageContext.request.contextPath}/${listProductThree.image}"
																		alt="">
																	</a>
																</div>
																<!-- /.image -->

															</div>
															<!-- /.product-image -->
														</div>
														<!-- /.col -->
														<div class="col col-xs-7">
															<div class="product-info">
																<h3 class="name">
																	<a href="detail?id=${listProductThree.id}">${listProductThree.name}</a>
																</h3>
																<div class="rating rateit-small"></div>
																<div class="product-price">
																	<span class="price"> ${listProductThree.price} </span>
																</div>
																<!-- /.product-price -->

															</div>
														</div>
														<!-- /.col -->
													</div>
													<!-- /.product-micro-row -->
												</div>
												<!-- /.product-micro -->

											</div>
										</c:forEach>
									</div>
								</div>



							</div>
						</div>
						<!-- /.sidebar-widget-body -->
					</div>



					<!-- ============================================== Testimonials: END ============================================== -->

					<div class="home-banner">
						<img src="assets\images\banners/LHS-banner.jpg" alt="Image">
					</div>
				</div>
				<!-- /.sidemenu-holder -->
				<!-- ============================================== SIDEBAR : END ============================================== -->

				<!-- ============================================== CONTENT ============================================== -->
				<div class="col-xs-12 col-sm-12 col-md-9 homebanner-holder">
					<!-- ========================================== SECTION – HERO ========================================= -->

					<div id="hero">
						<div id="owl-main" class="owl-carousel owl-inner-nav owl-ui-sm">
							<div class="item"
								style="background-image: url(assets/images/sliders/01.jpg);">
								<div class="container-fluid">
									<div class="caption bg-color vertical-center text-left">
										<div class="slider-header fadeInDown-1">Top Brands</div>
										<div class="big-text fadeInDown-1">New Collections</div>
										<div class="excerpt fadeInDown-2 hidden-xs">
											<span>Lorem ipsum dolor sit amet, consectetur
												adipisicing elit.</span>
										</div>
										<div class="button-holder fadeInDown-3">
											<a href="index.php?page=single-product"
												class="btn-lg btn btn-uppercase btn-primary shop-now-button">Shop
												Now</a>
										</div>
									</div>
									<!-- /.caption -->
								</div>
								<!-- /.container-fluid -->
							</div>
							<!-- /.item -->

							<div class="item"
								style="background-image: url(assets/images/sliders/02.jpg);">
								<div class="container-fluid">
									<div class="caption bg-color vertical-center text-left">
										<div class="slider-header fadeInDown-1">Spring 2016</div>
										<div class="big-text fadeInDown-1">
											Women <span class="highlight">Fashion</span>
										</div>
										<div class="excerpt fadeInDown-2 hidden-xs">
											<span>Nemo enim ipsam voluptatem quia voluptas sit
												aspernatur aut odit aut fugit</span>
										</div>
										<div class="button-holder fadeInDown-3">
											<a href="index.php?page=single-product"
												class="btn-lg btn btn-uppercase btn-primary shop-now-button">Shop
												Now</a>
										</div>
									</div>
									<!-- /.caption -->
								</div>
								<!-- /.container-fluid -->
							</div>
							<!-- /.item -->

						</div>
						<!-- /.owl-carousel -->
					</div>

					<!-- ========================================= SECTION – HERO : END ========================================= -->

					<!-- ============================================== INFO BOXES ============================================== -->
					<div class="info-boxes wow fadeInUp">
						<div class="info-boxes-inner">
							<div class="row">
								<div class="col-md-6 col-sm-4 col-lg-4">
									<div class="info-box">
										<div class="row">
											<div class="col-xs-12">
												<h4 class="info-box-heading green">money back</h4>
											</div>
										</div>
										<h6 class="text">30 Days Money Back Guarantee</h6>
									</div>
								</div>
								<!-- .col -->

								<div class="hidden-md col-sm-4 col-lg-4">
									<div class="info-box">
										<div class="row">
											<div class="col-xs-12">
												<h4 class="info-box-heading green">free shipping</h4>
											</div>
										</div>
										<h6 class="text">Shipping on orders over $99</h6>
									</div>
								</div>
								<!-- .col -->

								<div class="col-md-6 col-sm-4 col-lg-4">
									<div class="info-box">
										<div class="row">
											<div class="col-xs-12">
												<h4 class="info-box-heading green">Special Sale</h4>
											</div>
										</div>
										<h6 class="text">Extra $5 off on all items</h6>
									</div>
								</div>
								<!-- .col -->
							</div>
							<!-- /.row -->
						</div>
						<!-- /.info-boxes-inner -->

					</div>
					<!-- /.info-boxes -->
					<!-- ============================================== INFO BOXES : END ============================================== -->
					<!-- ============================================== SCROLL TABS ============================================== -->
					<div id="product-tabs-slider"
						class="scroll-tabs outer-top-vs wow fadeInUp">
						<div class="more-info-tab clearfix ">
							<h3 class="new-product-title pull-left">New Products</h3>
							<ul class="nav nav-tabs nav-tab-line pull-right"
								id="new-products-1">
								<li class="active"><a data-transition-type="backSlide"
									href="#all" data-toggle="tab">All</a></li>
								<li><a data-transition-type="backSlide" href="#smartphone"
									data-toggle="tab">Clothing</a></li>
								<li><a data-transition-type="backSlide" href="#laptop"
									data-toggle="tab">Electronics</a></li>
								<li><a data-transition-type="backSlide" href="#apple"
									data-toggle="tab">Shoes</a></li>
							</ul>
							<!-- /.nav-tabs -->
						</div>
						<div class="tab-content outer-top-xs">
							<div class="tab-pane in active" id="all">
								<div class="product-slider">
									<div
										class="owl-carousel home-owl-carousel custom-carousel owl-theme"
										data-item="4">
										<c:forEach begin="0" end="6" items="${listProductdesc}"
											var="lsproductdesc">
											<div class="item item-carousel">
												<div class="products">
													<div class="product">
														<div class="product-image">
															<div class="image">
																<a href="detail?id=${lsproductdesc.id}"><img
																	src="${pageContext.request.contextPath}/${lsproductdesc.image}"
																	alt=""></a>
															</div>
															<!-- /.image -->

															<div class="tag new">
																<span>new</span>
															</div>
														</div>
														<!-- /.product-image -->

														<div class="product-info text-left">
															<h3 class="name">
																<a href="detail?id=${lsproductdesc.id}">${lsproductdesc.name}</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="description"></div>
															<div class="product-price">
																<span class="price"> ${lsproductdesc.price} </span> <span
																	class="price-before-discount">${lsproductdesc.sellprice}</span>
															</div>
															<!-- /.product-price -->

														</div>
														<!-- /.product-info -->
														<div class="cart clearfix animate-effect">
															<div class="action">
																<ul class="list-unstyled">
																	<li class="add-cart-button btn-group">


																		<form action="cart" method="post">
																			<input type="hidden" name="productCode"
																				value="<c:out value='${lsproductdesc.id}'/>">
																			<button data-toggle="tooltip"
																				class="btn btn-primary icon" type="submit"
																				title="Add Cart">
																				<i class="fa fa-shopping-cart"></i>
																			</button>
																		</form>

																	</li>

																</ul>
															</div>
															<!-- /.action -->
														</div>
														<!-- /.cart -->
													</div>
													<!-- /.product -->

												</div>
												<!-- /.products -->
											</div>
										</c:forEach>
										<!-- /.item -->

									</div>
									<!-- /.home-owl-carousel -->
								</div>
								<!-- /.product-slider -->
							</div>
							<!-- /.tab-pane -->

							<div class="tab-pane" id="smartphone">
								<div class="product-slider">
									<div
										class="owl-carousel home-owl-carousel custom-carousel owl-theme">
										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p5.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p6.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p7.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p8.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p9.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p10.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->
									</div>
									<!-- /.home-owl-carousel -->
								</div>
								<!-- /.product-slider -->
							</div>
							<!-- /.tab-pane -->

							<div class="tab-pane" id="laptop">
								<div class="product-slider">
									<div
										class="owl-carousel home-owl-carousel custom-carousel owl-theme">
										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p11.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p12.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p13.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p14.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p15.jpg" alt="image"></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p16.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Apple Iphone 5s 32GB</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->
									</div>
									<!-- /.home-owl-carousel -->
								</div>
								<!-- /.product-slider -->
							</div>
							<!-- /.tab-pane -->

							<div class="tab-pane" id="apple">
								<div class="product-slider">
									<div
										class="owl-carousel home-owl-carousel custom-carousel owl-theme">
										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p18.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p18.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p17.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag sale">
															<span>sale</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p16.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p13.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag new">
															<span>new</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Floral Print Buttoned</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->

										<div class="item item-carousel">
											<div class="products">
												<div class="product">
													<div class="product-image">
														<div class="image">
															<a href="detail.html"><img
																src="assets\images\products\p14.jpg" alt=""></a>
														</div>
														<!-- /.image -->

														<div class="tag hot">
															<span>hot</span>
														</div>
													</div>
													<!-- /.product-image -->

													<div class="product-info text-left">
														<h3 class="name">
															<a href="detail.html">Samsung Galaxy S4</a>
														</h3>
														<div class="rating rateit-small"></div>
														<div class="description"></div>
														<div class="product-price">
															<span class="price"> $450.99 </span> <span
																class="price-before-discount">$ 800</span>
														</div>
														<!-- /.product-price -->

													</div>
													<!-- /.product-info -->
													<div class="cart clearfix animate-effect">
														<div class="action">
															<ul class="list-unstyled">
																<li class="add-cart-button btn-group">
																	<button class="btn btn-primary icon"
																		data-toggle="dropdown" type="button">
																		<i class="fa fa-shopping-cart"></i>
																	</button>
																	<button class="btn btn-primary cart-btn" type="button">Add
																		to cart</button>
																</li>
																<li class="lnk wishlist"><a class="add-to-cart"
																	href="detail.html" title="Wishlist"> <i
																		class="icon fa fa-heart"></i>
																</a></li>
																<li class="lnk"><a class="add-to-cart"
																	href="detail.html" title="Compare"> <i
																		class="fa fa-signal" aria-hidden="true"></i>
																</a></li>
															</ul>
														</div>
														<!-- /.action -->
													</div>
													<!-- /.cart -->
												</div>
												<!-- /.product -->

											</div>
											<!-- /.products -->
										</div>
										<!-- /.item -->
									</div>
									<!-- /.home-owl-carousel -->
								</div>
								<!-- /.product-slider -->
							</div>
							<!-- /.tab-pane -->

						</div>
						<!-- /.tab-content -->
					</div>
					<!-- /.scroll-tabs -->
					<!-- ============================================== SCROLL TABS : END ============================================== -->
					<!-- ============================================== WIDE PRODUCTS ============================================== -->

					<!-- /.wide-banners -->

					<!-- ============================================== WIDE PRODUCTS : END ============================================== -->
					<!-- ============================================== FEATURED PRODUCTS ============================================== -->
					<section class="section featured-product wow fadeInUp">
						<h3 class="section-title">Featured products</h3>
						<div
							class="owl-carousel home-owl-carousel custom-carousel owl-theme outer-top-xs">
							<c:forEach begin="0" end="6" items="${listProductold}"
								var="listProductold">
								<div class="item item-carousel">
									<div class="products">
										<div class="product">
											<div class="product-image">
												<div class="image">
													<a href="detail?id=${listProductold.id}"><img
														src="${pageContext.request.contextPath}/${listProductold.image}"
														alt=""></a>
												</div>
												<!-- /.image -->

												<div class="tag hot">
													<span>hot</span>
												</div>
											</div>
											<!-- /.product-image -->

											<div class="product-info text-left">
												<h3 class="name">
													<a href="detail?id=${listProductold.id}">${listProductold.name}</a>
												</h3>
												<div class="rating rateit-small"></div>
												<div class="description"></div>
												<div class="product-price">
													<span class="price"> ${listProductold.sellprice} </span> <span
														class="price-before-discount">${listProductold.price}</span>
												</div>
												<!-- /.product-price -->

											</div>
											<!-- /.product-info -->
											<div class="cart clearfix animate-effect">
												<div class="action">
													<ul class="list-unstyled">
														<li class="add-cart-button btn-group">
																<form action="cart" method="post">
																			<input type="hidden" name="productCode"
																				value="<c:out value='${listProductold.id}'/>">
																			<button data-toggle="tooltip"
																				class="btn btn-primary icon" type="submit"
																				title="Add Cart">
																				<i class="fa fa-shopping-cart"></i>
																			</button>
																		</form>
														</li>


													</ul>
												</div>
												<!-- /.action -->
											</div>
											<!-- /.cart -->
										</div>
										<!-- /.product -->

									</div>
									<!-- /.products -->
								</div>
								<!-- /.item -->
							</c:forEach>
						</div>
						<!-- /.home-owl-carousel -->
					</section>
					<!-- /.section -->
					<!-- ============================================== FEATURED PRODUCTS : END ============================================== -->
					<!-- ============================================== WIDE PRODUCTS ============================================== -->
					<div class="wide-banners wow fadeInUp outer-bottom-xs">
						<div class="row">
							<div class="col-md-12">
								<div class="wide-banner cnt-strip">
									<div class="image">
										<img class="img-responsive"
											src="assets\images\banners\home-banner.jpg" alt="">
									</div>
									<div class="strip strip-text">
										<div class="strip-inner">
											<h2 class="text-right">
												New Mens Fashion<br> <span class="shopping-needs">Save
													up to 40% off</span>
											</h2>
										</div>
									</div>
									<div class="new-label">
										<div class="text">NEW</div>
									</div>
									<!-- /.new-label -->
								</div>
								<!-- /.wide-banner -->
							</div>
							<!-- /.col -->

						</div>
						<!-- /.row -->
					</div>
					<!-- /.wide-banners -->
					<!-- ============================================== WIDE PRODUCTS : END ============================================== -->
					<!-- ============================================== BEST SELLER ============================================== -->

					<div class="best-deal wow fadeInUp outer-bottom-xs">
						<h3 class="section-title">Best seller</h3>
						<div class="sidebar-widget-body outer-top-xs">
							<div
								class="owl-carousel best-seller custom-carousel owl-theme outer-top-xs">
								<div class="item">
									<div class="products best-product">
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p20.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p21.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
									</div>
								</div>
								<div class="item">
									<div class="products best-product">
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p22.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p23.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
									</div>
								</div>
								<div class="item">
									<div class="products best-product">
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p24.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p25.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
									</div>
								</div>
								<div class="item">
									<div class="products best-product">
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p26.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
										<div class="product">
											<div class="product-micro">
												<div class="row product-micro-row">
													<div class="col col-xs-5">
														<div class="product-image">
															<div class="image">
																<a href="#"> <img
																	src="assets\images\products\p27.jpg" alt="">
																</a>
															</div>
															<!-- /.image -->

														</div>
														<!-- /.product-image -->
													</div>
													<!-- /.col -->
													<div class="col2 col-xs-7">
														<div class="product-info">
															<h3 class="name">
																<a href="#">Floral Print Buttoned</a>
															</h3>
															<div class="rating rateit-small"></div>
															<div class="product-price">
																<span class="price"> $450.99 </span>
															</div>
															<!-- /.product-price -->

														</div>
													</div>
													<!-- /.col -->
												</div>
												<!-- /.product-micro-row -->
											</div>
											<!-- /.product-micro -->

										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.sidebar-widget-body -->
					</div>
					<!-- /.sidebar-widget -->
					<!-- ============================================== BEST SELLER : END ============================================== -->

					<!-- ============================================== BLOG SLIDER ============================================== -->
					<!-- /.section -->
					<!-- ============================================== BLOG SLIDER : END ============================================== -->

					<!-- ============================================== FEATURED PRODUCTS ============================================== -->
					<section class="section wow fadeInUp new-arriavls">
						<h3 class="section-title">New Arrivals</h3>
						<div
							class="owl-carousel home-owl-carousel custom-carousel owl-theme outer-top-xs">
							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p19.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag new">
												<span>new</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->

							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p28.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag new">
												<span>new</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->

							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p30.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag hot">
												<span>hot</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->

							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p1.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag hot">
												<span>hot</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->

							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p2.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag sale">
												<span>sale</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->

							<div class="item item-carousel">
								<div class="products">
									<div class="product">
										<div class="product-image">
											<div class="image">
												<a href="detail.html"><img
													src="assets\images\products\p3.jpg" alt=""></a>
											</div>
											<!-- /.image -->

											<div class="tag sale">
												<span>sale</span>
											</div>
										</div>
										<!-- /.product-image -->

										<div class="product-info text-left">
											<h3 class="name">
												<a href="detail.html">Floral Print Buttoned</a>
											</h3>
											<div class="rating rateit-small"></div>
											<div class="description"></div>
											<div class="product-price">
												<span class="price"> $450.99 </span> <span
													class="price-before-discount">$ 800</span>
											</div>
											<!-- /.product-price -->

										</div>
										<!-- /.product-info -->
										<div class="cart clearfix animate-effect">
											<div class="action">
												<ul class="list-unstyled">
													<li class="add-cart-button btn-group">
														<button class="btn btn-primary icon"
															data-toggle="dropdown" type="button">
															<i class="fa fa-shopping-cart"></i>
														</button>
														<button class="btn btn-primary cart-btn" type="button">Add
															to cart</button>
													</li>


												</ul>
											</div>
											<!-- /.action -->
										</div>
										<!-- /.cart -->
									</div>
									<!-- /.product -->

								</div>
								<!-- /.products -->
							</div>
							<!-- /.item -->
						</div>
						<!-- /.home-owl-carousel -->
					</section>
					<!-- /.section -->
					<!-- ============================================== FEATURED PRODUCTS : END ============================================== -->

				</div>
				<!-- /.homebanner-holder -->
				<!-- ============================================== CONTENT : END ============================================== -->
			</div>
			<!-- /.row -->
			<!-- ============================================== BRANDS CAROUSEL ============================================== -->

			<!-- /.logo-slider -->
			<!-- ============================================== BRANDS CAROUSEL : END ============================================== -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /#top-banner-and-menu -->

	<!-- ============================================================= FOOTER ============================================================= -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- ============================================================= FOOTER : END============================================================= -->

	<!-- For demo purposes – can be removed on production -->

	<!-- For demo purposes – can be removed on production : End -->

	<!-- JavaScripts placed at the end of the document so the pages load faster -->
	<jsp:include page="script.jsp"></jsp:include>
</body>
</html>