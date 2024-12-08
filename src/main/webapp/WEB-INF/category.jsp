<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>Flipmart premium HTML5 & CSS3 Template</title>
<!-- Bootstrap Core CSS -->
<jsp:include page="css.jsp"></jsp:include>
</head>
<body>
<!-- ============================================== HEADER ============================================== -->
	<jsp:include page="header.jsp"></jsp:include>
<!-- ============================================== HEADER : END ============================================== --> 

<!-- ============================================== HEADER : END ============================================== -->
<div class="breadcrumb">
  <div class="container">
    <div class="breadcrumb-inner">
      <ul class="list-inline list-unstyled">
        <li><a href="#">Home</a></li>
        <li class='active'>Category</li>
      </ul>
    </div>
    <!-- /.breadcrumb-inner --> 
  </div>
  <!-- /.container --> 
</div>
<!-- /.breadcrumb -->
<div class="body-content outer-top-xs">
  <div class='container'>
    <div class='row'>
      <div class='col-md-3 sidebar'> 
        <!-- ================================== TOP NAVIGATION ================================== -->
        <div class="side-menu animate-dropdown outer-bottom-xs">
          <div class="head"><i class="icon fa fa-align-justify fa-fw"></i> Categories</div>
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
        <div class="sidebar-module-container">
          <div class="sidebar-filter"> 
            
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
													<button class="btn btn-primary icon" data-toggle="dropdown"
														type="button">
														<i class="fa fa-shopping-cart"></i>
													</button>
													<button class="btn btn-primary cart-btn" type="button">Add
														to cart</button>
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
   
            
            <div class="home-banner"> <img src="assets\images\banners\LHS-banner.jpg" alt="Image"> </div>
          </div>
          <!-- /.sidebar-filter --> 
        </div>
        <!-- /.sidebar-module-container --> 
      </div>
      <!-- /.sidebar -->
      <div class='col-md-9'> 
        <!-- ========================================== SECTION – HERO ========================================= -->
        
      
     
        <div class="clearfix filters-container m-t-10">
          <div class="row">
            <div class="col col-sm-6 col-md-2">
              <div class="filter-tabs">
                <ul id="filter-tabs" class="nav nav-tabs nav-tab-box nav-tab-fa-icon">
                  <li class="active"> <a data-toggle="tab" href="#grid-container"><i class="icon fa fa-th-large"></i>Grid</a> </li>
                  <li><a data-toggle="tab" href="#list-container"><i class="icon fa fa-th-list"></i>List</a></li>
                </ul>
              </div>
              <!-- /.filter-tabs --> 
            </div>
            <!-- /.col -->
            <div class="col col-sm-12 col-md-6">
              <div class="col col-sm-3 col-md-6 no-padding">
                <div class="lbl-cnt"> <span class="lbl">Sort by</span>
                  <div class="fld inline">
                    <div class="dropdown dropdown-small dropdown-med dropdown-white inline">
                      <button data-toggle="dropdown" type="button" class="btn dropdown-toggle"> Position <span class="caret"></span> </button>
                      <ul role="menu" class="dropdown-menu">
                        <li role="presentation"><a href="#">position</a></li>
                        <li role="presentation"><a href="#">Price:Lowest first</a></li>
                        <li role="presentation"><a href="#">Price:HIghest first</a></li>
                        <li role="presentation"><a href="#">Product Name:A to Z</a></li>
                      </ul>
                    </div>
                  </div>
                  <!-- /.fld --> 
                </div>
                <!-- /.lbl-cnt --> 
              </div>
              <!-- /.col -->
              <div class="col col-sm-3 col-md-6 no-padding">
                <div class="lbl-cnt"> <span class="lbl">Show</span>
                  <div class="fld inline">
                    <div class="dropdown dropdown-small dropdown-med dropdown-white inline">
                      <button data-toggle="dropdown" type="button" class="btn dropdown-toggle"> 1 <span class="caret"></span> </button>
                      <ul role="menu" class="dropdown-menu">
                        <li role="presentation"><a href="#">1</a></li>
                        <li role="presentation"><a href="#">2</a></li>
                        <li role="presentation"><a href="#">3</a></li>
                        <li role="presentation"><a href="#">4</a></li>
                        <li role="presentation"><a href="#">5</a></li>
                        <li role="presentation"><a href="#">6</a></li>
                        <li role="presentation"><a href="#">7</a></li>
                        <li role="presentation"><a href="#">8</a></li>
                        <li role="presentation"><a href="#">9</a></li>
                        <li role="presentation"><a href="#">10</a></li>
                      </ul>
                    </div>
                  </div>
                  <!-- /.fld --> 
                </div>
                <!-- /.lbl-cnt --> 
              </div>
              <!-- /.col --> 
            </div>
            <!-- /.col -->
            <div class="col col-sm-6 col-md-4 text-right">
              <div class="pagination-container">
                <ul class="list-inline list-unstyled">
                  <li class="prev"><a href="#"><i class="fa fa-angle-left"></i></a></li>
                  <li><a href="#">1</a></li>
                  <li class="active"><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li class="next"><a href="#"><i class="fa fa-angle-right"></i></a></li>
                </ul>
                <!-- /.list-inline --> 
              </div>
              <!-- /.pagination-container --> </div>
            <!-- /.col --> 
          </div>
          <!-- /.row --> 
        </div>
        <div class="search-result-container ">
          <div id="myTabContent" class="tab-content category-list">
            <div class="tab-pane active " id="grid-container">
              <div class="category-product">
                <div class="row">
                	<c:forEach items="${productList}"
									var="productList">
                  <div class="col-sm-6 col-md-4 wow fadeInUp">
                    <div class="products">
                      <div class="product">
                        <div class="product-image">
                          <div class="image"> <a href="detail?id=${productList.id}"><img src="${pageContext.request.contextPath}/${productList.image}" alt=""></a> </div>
                          <!-- /.image -->
                                         
                        </div>
                        <!-- /.product-image -->
                        
                        <div class="product-info text-left">
                          <h3 class="name"><a href="detail?id=${productList.id}">${productList.name}</a></h3>
                          <div class="rating rateit-small"></div>
                          <div class="description"></div>
                          <div class="product-price"> <span class="price"> ${productList.sellprice} </span> <span class="price-before-discount">${productList.price}</span> </div>
                          <!-- /.product-price --> 
                          
                        </div>
                        <!-- /.product-info -->
                        <!-- /.cart --> 
                      </div>
                      <!-- /.product --> 
                      
                    </div>
                    <!-- /.products --> 
                  </div>

                  <!-- /.item -->
				</c:forEach>
                </div>
                <!-- /.row --> 
              </div>
              <!-- /.category-product --> 
              
            </div>
            <!-- /.tab-pane -->
            
            <div class="tab-pane " id="list-container">
              <div class="category-product">
              	<c:forEach begin="0" end="12" items="${productList}"
									var="productList">
                <div class="category-product-inner wow fadeInUp">
                  <div class="products">
                    <div class="product-list product">
                      <div class="row product-list-row">
                        <div class="col col-sm-4 col-lg-4">
                          <div class="product-image">
                            <div class="image"> <img src="${pageContext.request.contextPath}/${productList.image}" alt=""> </div>
                          </div>
                          <!-- /.product-image --> 
                        </div>
                        <!-- /.col -->
                        <div class="col col-sm-8 col-lg-8">
                          <div class="product-info">
                            <h3 class="name"><a href="detail.html">${productList.name}</a></h3>
                            <div class="rating rateit-small"></div>
                            <div class="product-price"> <span class="price"> ${productList.sellprice} </span> <span class="price-before-discount">${productList.price}</span> </div>
                            <!-- /.product-price -->
                            <div class="description m-t-10">${productList.description}</div>
                            <div class="cart clearfix animate-effect">
                              <div class="action">
                                <ul class="list-unstyled">
                                  <li class="add-cart-button btn-group">
                                    <button class="btn btn-primary icon" data-toggle="dropdown" type="button"> <i class="fa fa-shopping-cart"></i> </button>
                                    <button class="btn btn-primary cart-btn" type="button">Add to cart</button>
                                  </li>
          
                                </ul>
                              </div>
                              <!-- /.action --> 
                            </div>
                            <!-- /.cart --> 
                            
                          </div>
                          <!-- /.product-info --> 
                        </div>
                        <!-- /.col --> 
                      </div>
                      <!-- /.product-list-row -->
         
                    </div>
                    <!-- /.product-list --> 
                  </div>
                  <!-- /.products --> 
                </div>
                <!-- /.category-product-inner -->
            </c:forEach>
                
              </div>
              <!-- /.category-product --> 
            </div>
            <!-- /.tab-pane #list-container --> 
          </div>
          <!-- /.tab-content -->
          <div class="clearfix filters-container">
            <div class="text-right">
              <div class="pagination-container">
                <ul class="list-inline list-unstyled">
                  <li class="prev"><a href="#"><i class="fa fa-angle-left"></i></a></li>
                  <li><a href="#">1</a></li>
                  <li class="active"><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li class="next"><a href="#"><i class="fa fa-angle-right"></i></a></li>
                </ul>
                <!-- /.list-inline --> 
              </div>
              <!-- /.pagination-container --> </div>
            <!-- /.text-right --> 
            
          </div>
          <!-- /.filters-container --> 
          
        </div>
        <!-- /.search-result-container --> 
        
      </div>
      <!-- /.col --> 
    </div>
    <!-- /.row --> 
 
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
</body>
</html>