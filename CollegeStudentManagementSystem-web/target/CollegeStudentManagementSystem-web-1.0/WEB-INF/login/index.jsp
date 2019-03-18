<%-- 
    Document   : index
    Created on : Sep 28, 2018, 4:29:40 PM
    Author     : Gabriel_Liberato
--%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalabe=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <link rel="apple-touch-icon" href="<%= request.getContextPath()%>/resources/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Quicksand:300,400,500,700" rel="stylesheet">
        <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
        <!-- BEGIN VENDOR CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/vendors.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/vendors/css/forms/icheck/icheck.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/vendors/css/forms/icheck/custom.css">
        <!-- END VENDOR CSS-->
        <!-- BEGIN MODERN CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/app.css">
        <!-- END MODERN CSS-->
        <!-- BEGIN Page Level CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/core/colors/palette-gradient.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/pages/login-register.css">
        <!-- END Page Level CSS-->
        <!-- BEGIN Custom CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/assets/css/style.css">
        <!-- END Custom CSS-->
        <title>Login - College Student Management System</title>
    </head>
    <body class="vertical-layout vertical-menu-modern 1-column  bg-cyan bg-lighten-2 menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu-modern" data-col="1-column" style="background-color: #e5f4ff !important;">
        <!-- fixed-top-->
        <nav class="header-navbar navbar-expand-md navbar navbar-with-menu navbar-without-dd-arrow fixed-top navbar-dark navbar-shadow">
            <div class="navbar-wrapper">
                <div class="navbar-header">
                    <ul class="nav navbar-nav flex-row">
                        <li class="nav-item mobile-menu d-md-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ft-menu font-large-1"></i></a></li>
                        <li class="nav-item">
                            <a class="navbar-brand" href="<%= request.getContextPath()%>/Home">
                                <img class="brand-logo" alt="modern admin logo" src="<%= request.getContextPath()%>/resources/app-assets/images/logo/logo.png">
                                <h3 class="brand-text">College Student Management System</h3>
                            </a>
                        </li>
                        <li class="nav-item d-md-none">
                            <a class="nav-link open-navbar-container" data-toggle="collapse" data-target="#navbar-mobile"><i class="la la-ellipsis-v"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="navbar-container">
                    <div class="collapse navbar-collapse justify-content-end" id="navbar-mobile">
                        <ul class="nav navbar-nav">
<!--                            <li class="nav-item"><a class="nav-link mr-2 nav-link-label" href="index.html"><i class="ficon ft-arrow-left"></i></a></li>
                            <li class="dropdown nav-item">
                                <a class="nav-link mr-2 nav-link-label" href="#" data-toggle="dropdown"><i class="ficon ft-settings"></i></a>
                            </li>-->
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <!-- ////////////////////////////////////////////////////////////////////////////-->
        <div class="app-content content">
            <div class="content-wrapper">
                <div class="content-header row">
                </div>
                <div class="content-body">
                    <section class="flexbox-container">
                        <div class="col-12 d-flex align-items-center justify-content-center">
                            <div class="col-md-4 col-10 box-shadow-2 p-0">
                                <div class="card border-grey border-lighten-3 m-0">
                                    <div class="card-header border-0">
                                        <div class="card-title text-center" style="font-family: Quicksand, Georgia, 'Times New Roman', Times, serif;">
                                            CSMS
                                        </div>
                                        <h6 class="card-subtitle line-on-side text-muted text-center font-small-3 pt-2">
                                            <span>Login</span>
                                        </h6>
                                    </div>
                                    <div class="card-content">
                                        <div class="card-body">
                                            <form class="form-horizontal" method="POST" action="<%= request.getContextPath()%>/Login" novalidate>
                                                <fieldset class="form-group position-relative has-icon-left">
                                                    <input type="text" class="form-control input-lg" id="userName" name="userName" placeholder="Your Username" tabindex="1" required data-validation-required-message="Please enter your username.">
                                                    <div class="form-control-position">
                                                        <i class="ft-user"></i>
                                                    </div>
                                                    <div class="help-block font-small-3"></div>
                                                </fieldset>
                                                <fieldset class="form-group position-relative has-icon-left">
                                                    <input type="password" class="form-control input-lg" id="password" name="password" placeholder="Enter Password" tabindex="2" required data-validation-required-message="Please enter valid passwords.">
                                                    <div class="form-control-position">
                                                        <i class="la la-key"></i>
                                                    </div>
                                                    <div class="help-block font-small-3"></div>
                                                </fieldset>
                                                <%
                                                    if (!(request.getAttribute("error-login") == null || request.getAttribute("error-login") == ""))
                                                    {
                                                %>
                                                        <fieldset class="form-group position-relative has-icon-left validate">
                                                            <div class="alert alert-danger" style="border-radius: 0.5rem;align-self: center;"><%= request.getAttribute("error-login").toString()%></div>
                                                        </fieldset>
                                                <%  } %>
                                                <div class="form-group row">
                                                    <div class="col-md-6 col-12 text-center text-md-left">
                                                        <fieldset>
                                                            <input type="checkbox" id="remember-me" class="chk-remember">
                                                            <label for="remember-me"> Remember Me</label>
                                                        </fieldset>
                                                    </div>
                                                    <div class="col-md-6 col-12 text-center text-md-right"><a href="recover-password.html" class="card-link">Forgot Password?</a></div>
                                                </div>
                                                <button type="submit" class="btn btn-danger btn-block btn-lg"><i class="ft-unlock"></i> Login</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <!-- ////////////////////////////////////////////////////////////////////////////-->
        <footer class="footer fixed-bottom footer-dark navbar-border navbar-shadow">
            <p class="clearfix blue-grey lighten-2 text-sm-center mb-0 px-2">
                <span class="float-md-left d-block d-md-inline-block">Copyright &copy; <%= java.util.Calendar.getInstance().get(Calendar.YEAR) %> <a class="text-bold-800 grey darken-2" href="#" target="_blank">College Student Management System </a>, All rights reserved. </span>
            </p>
        </footer>
        <!-- BEGIN VENDOR JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
        <!-- BEGIN VENDOR JS-->
        <!-- BEGIN PAGE VENDOR JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/forms/validation/jqBootstrapValidation.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/forms/icheck/icheck.min.js" type="text/javascript"></script>
        <!-- END PAGE VENDOR JS-->
        <!-- BEGIN MODERN JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/core/app-menu.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/core/app.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/scripts/customizer.js" type="text/javascript"></script>
        <!-- END MODERN JS-->
        <!-- BEGIN PAGE LEVEL JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/scripts/forms/form-login-register.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL JS-->
    </body>
</html>