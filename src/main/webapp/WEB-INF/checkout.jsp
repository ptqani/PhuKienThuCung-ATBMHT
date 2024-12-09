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
    <body class="cnt-home">
		<!-- ============================================== HEADER ============================================== -->
        <jsp:include page="header.jsp"></jsp:include>
<!-- ============================================== HEADER : END ============================================== -->
<div class="breadcrumb">
	<div class="container">
		<div class="breadcrumb-inner">
			<ul class="list-inline list-unstyled">
				<li><a href="#">Home</a></li>
				<li class='active'>Checkout</li>
			</ul>
		</div><!-- /.breadcrumb-inner -->
	</div><!-- /.container -->
</div><!-- /.breadcrumb -->

<div class="body-content">
	<div class="container">
		<div class="checkout-box ">
			<div class="row">
				<div class="col-md-8">
					<div class="panel-group checkout-steps" id="accordion">
						<!-- checkout-step-01  -->
<div class="panel panel-default checkout-step-01">

	<!-- panel-heading -->
		<div class="panel-heading">
    	<h4 class="unicase-checkout-title">
	        <a data-toggle="collapse" class="" data-parent="#accordion" href="#collapseOne">
	          <span>1</span>Checkout Method
	        </a>
	     </h4>
    </div>
    <!-- panel-heading -->

	<div id="collapseOne" class="panel-collapse collapse in">

		<!-- panel-body  -->
	    <div class="panel-body">
			<div class="row">		

				<!-- guest-login -->			
				<div class="col-md-6 col-sm-6 guest-login">
				
    <table class="table">
        <thead>
            <tr>
                <th>
                    <span class="estimate-title">Estimate Shipping and Tax</span>
                    <p>Enter your destination and contact details to get shipping and tax estimates.</p>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                        <!-- Name Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="name">Full Name <span>*</span></label>
                            <input type="text" id="name" class="form-control unicase-form-control text-input" placeholder="Enter your full name" required>
                        </div>

                        <!-- Phone Number Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="phone">Phone Number <span>*</span></label>
                            <input type="tel" id="phone" class="form-control unicase-form-control text-input" placeholder="Enter your phone number" required pattern="^[0-9]{10}$">
                        </div>

                        <!-- Email Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="email">Email Address <span>*</span></label>
                            <input type="email" id="email" class="form-control unicase-form-control text-input" placeholder="Enter your email address" required>
                        </div>

                </td>
            </tr>
        </tbody>
    </table>
				
				
				</div>
				<!-- guest-login -->

				<!-- already-registered-login -->
				<div class="col-md-6 col-sm-6 already-registered-login">
				
	<table class="table">
		<thead>
			<tr>
			
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>
					<!-- Country Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="country">Country <span>*</span></label>
                            <select id="country" class="form-control unicase-form-control selectpicker" required>
                                <option value="">--Select options--</option>
                                <option value="india">India</option>
                                <option value="sri-lanka">Sri Lanka</option>
                                <option value="united-kingdom">United Kingdom</option>
                                <option value="saudi-arabia">Saudi Arabia</option>
                                <option value="united-arab-emirates">United Arab Emirates</option>
                            </select>
                        </div>
                        
                        <!-- State/Province Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="state">State/Province <span>*</span></label>
                            <select id="state" class="form-control unicase-form-control selectpicker" required>
                                <option value="">--Select options--</option>
                                <option value="tamilnadu">Tamil Nadu</option>
                                <option value="kerala">Kerala</option>
                                <option value="andhra-pradesh">Andhra Pradesh</option>
                                <option value="karnataka">Karnataka</option>
                                <option value="madhya-pradesh">Madhya Pradesh</option>
                            </select>
                        </div>
                        
                        <!-- Zip/Postal Code Field -->
                        <div class="form-group">
                            <label class="info-title control-label" for="zip">Zip/Postal Code</label>
                            <input type="text" id="zip" class="form-control unicase-form-control text-input" placeholder="Enter zip/postal code" pattern="^\d{5}(-\d{4})?$">
                        </div>
                        
						<div class="clearfix pull-right">
							<button type="submit" class="btn-upper btn btn-primary">APPLY COUPON</button>
						</div>
					</td>
				</tr>
		</tbody><!-- /tbody -->
	</table><!-- /table -->
					
				</div>	
				<!-- already-registered-login -->		


			</div>			
		</div>
		<!-- panel-body  -->

	</div><!-- row -->
</div>
<!-- checkout-step-01  -->
					   
					  	
					</div><!-- /.checkout-steps -->
				</div>
				<div class="col-md-4">
		<!-- checkout-progress-sidebar -->
<div class="checkout-progress-sidebar">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="unicase-checkout-title">Your Order Summary</h4>
            </div>
            <div class="panel-body">
                <!-- Bảng sản phẩm và giá -->
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Product</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Dòng sản phẩm -->
                      <c:forEach var="item" items="${cart.items}">
                        <c:set var="totalPrice" value="${totalPrice + item.product.price * item.quantity}" />
                        <tr>
                            <td>${item.product.name} x ${item.quantity}</td>
                            <td>${item.totalCurrencyFormat}</td>
                            
                        </tr>
                        		</c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td><strong>Total</strong></td>
                           <td><strong><c:out value="${totalPrice}" /></strong></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- checkout-progress-sidebar : END -->

			</div><!-- /.row -->
		</div><!-- /.checkout-box -->
		<!-- ============================================== BRANDS CAROUSEL ============================================== -->
<div id="brands-carousel" class="logo-slider wow fadeInUp">

		<div class="logo-slider-inner">	
			<div id="brand-slider" class="owl-carousel brand-slider custom-carousel owl-theme">
				<div class="item m-t-15">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand1.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item m-t-10">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand2.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand3.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand4.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand5.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand6.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand2.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand4.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand1.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->

				<div class="item">
					<a href="#" class="image">
						<img data-echo="assets/images/brands/brand5.png" src="assets\images\blank.gif" alt="">
					</a>	
				</div><!--/.item-->
		    </div><!-- /.owl-carousel #logo-slider -->
		</div><!-- /.logo-slider-inner -->
	
</div><!-- /.logo-slider -->
<!-- ============================================== BRANDS CAROUSEL : END ============================================== -->	</div><!-- /.container -->
</div><!-- /.body-content -->
<!-- ============================================================= FOOTER ============================================================= -->
<!-- ============================================================= FOOTER ============================================================= -->
<footer id="footer" class="footer color-bg">


    <div class="footer-bottom">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="module-heading">
                        <h4 class="module-title">Contact Us</h4>
                    </div><!-- /.module-heading -->

                    <div class="module-body">
        <ul class="toggle-footer" style="">
            <li class="media">
                <div class="pull-left">
                     <span class="icon fa-stack fa-lg">
                            <i class="fa fa-map-marker fa-stack-1x fa-inverse"></i>
                    </span>
                </div>
                <div class="media-body">
                    <p>ThemesGround, 789 Main rd, Anytown, CA 12345 USA</p>
                </div>
            </li>

              <li class="media">
                <div class="pull-left">
                     <span class="icon fa-stack fa-lg">
                      <i class="fa fa-mobile fa-stack-1x fa-inverse"></i>
                    </span>
                </div>
                <div class="media-body">
                    <p>+(888) 123-4567<br>+(888) 456-7890</p>
                </div>
            </li>

              <li class="media">
                <div class="pull-left">
                     <span class="icon fa-stack fa-lg">
                      <i class="fa fa-envelope fa-stack-1x fa-inverse"></i>
                    </span>
                </div>
                <div class="media-body">
                    <span><a href="#">flipmart@themesground.com</a></span>
                </div>
            </li>
              
            </ul>
    </div><!-- /.module-body -->
                </div><!-- /.col -->

                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="module-heading">
                        <h4 class="module-title">Customer Service</h4>
                    </div><!-- /.module-heading -->

                    <div class="module-body">
                        <ul class='list-unstyled'>
                            <li class="first"><a href="#" title="Contact us">My Account</a></li>
                <li><a href="#" title="About us">Order History</a></li>
                <li><a href="#" title="faq">FAQ</a></li>
                <li><a href="#" title="Popular Searches">Specials</a></li>
                <li class="last"><a href="#" title="Where is my order?">Help Center</a></li>
                        </ul>
                    </div><!-- /.module-body -->
                </div><!-- /.col -->

                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="module-heading">
                        <h4 class="module-title">Corporation</h4>
                    </div><!-- /.module-heading -->

                    <div class="module-body">
                        <ul class='list-unstyled'>
                          <li class="first"><a title="Your Account" href="#">About us</a></li>
                <li><a title="Information" href="#">Customer Service</a></li>
                <li><a title="Addresses" href="#">Company</a></li>
                <li><a title="Addresses" href="#">Investor Relations</a></li>
                <li class="last"><a title="Orders History" href="#">Advanced Search</a></li>
                        </ul>
                    </div><!-- /.module-body -->
                </div><!-- /.col -->

                <div class="col-xs-12 col-sm-6 col-md-3">
                    <div class="module-heading">
                        <h4 class="module-title">Why Choose Us</h4>
                    </div><!-- /.module-heading -->

                    <div class="module-body">
                        <ul class='list-unstyled'>
                            <li class="first"><a href="#" title="About us">Shopping Guide</a></li>
                <li><a href="#" title="Blog">Blog</a></li>
                <li><a href="#" title="Company">Company</a></li>
                <li><a href="#" title="Investor Relations">Investor Relations</a></li>
                <li class=" last"><a href="contact-us.html" title="Suppliers">Contact Us</a></li>
                        </ul>
                    </div><!-- /.module-body -->
                </div>
            </div>
        </div>
    </div>

    <div class="copyright-bar">
        <div class="container">
            <div class="col-xs-12 col-sm-6 no-padding social">
                <ul class="link">
                  <li class="fb pull-left"><a target="_blank" rel="nofollow" href="#" title="Facebook"></a></li>
                  <li class="tw pull-left"><a target="_blank" rel="nofollow" href="#" title="Twitter"></a></li>
                  <li class="googleplus pull-left"><a target="_blank" rel="nofollow" href="#" title="GooglePlus"></a></li>
                  <li class="rss pull-left"><a target="_blank" rel="nofollow" href="#" title="RSS"></a></li>
                  <li class="pintrest pull-left"><a target="_blank" rel="nofollow" href="#" title="PInterest"></a></li>
                  <li class="linkedin pull-left"><a target="_blank" rel="nofollow" href="#" title="Linkedin"></a></li>
                  <li class="youtube pull-left"><a target="_blank" rel="nofollow" href="#" title="Youtube"></a></li>
                </ul>
            </div>
            <div class="col-xs-12 col-sm-6 no-padding">
                <div class="clearfix payment-methods">
                    <ul>
                        <li><img src="assets\images\payments\1.png" alt=""></li>
                        <li><img src="assets\images\payments\2.png" alt=""></li>
                        <li><img src="assets\images\payments\3.png" alt=""></li>
                        <li><img src="assets\images\payments\4.png" alt=""></li>
                        <li><img src="assets\images\payments\5.png" alt=""></li>
                    </ul>
                </div><!-- /.payment-methods -->
            </div>
        </div>
    </div>
</footer>
<!-- ============================================================= FOOTER : END============================================================= -->
<jsp:include page="script.jsp"></jsp:include>
</body>
</html>
