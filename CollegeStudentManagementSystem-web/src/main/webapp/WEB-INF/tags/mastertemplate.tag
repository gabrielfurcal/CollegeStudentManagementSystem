<%-- 
    Document   : mastertemplate
    Created on : Oct 2, 2018, 11:18:20 AM
    Author     : Gabriel_Liberato
--%>

<%--<%@tag description="put the tag description here" pageEncoding="UTF-8"%>--%>
<%@tag import="java.util.Calendar"%>
<%@ tag import="javax.inject.Inject" %>
<%@ tag import="interfaces.IPermissionRepository" %>
<%@ tag import="java.math.BigDecimal" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" required="true" rtexprvalue="true"%>
<%@attribute name="pageName" required="true" rtexprvalue="true"%>
<%@attribute name="content" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title} - College Student Management System</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
        <link rel="apple-touch-icon" href="<%= request.getContextPath()%>/resources/app-assets/images/ico/apple-icon-120.png">
        <link rel="shortcut icon" type="image/x-icon" href="<%= request.getContextPath()%>/resources/app-assets/images/ico/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Quicksand:300,400,500,700" rel="stylesheet">
        <link href="https://maxcdn.icons8.com/fonts/line-awesome/1.1/css/line-awesome.min.css" rel="stylesheet">
        <!-- BEGIN VENDOR CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/vendors.css">
        <!-- END VENDOR CSS-->
        <!-- BEGIN MODERN CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/app.css">
        <!-- END MODERN CSS-->
        <!-- BEGIN Page Level CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/core/menu/menu-types/vertical-menu-modern.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/core/colors/palette-gradient.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/vendors/css/charts/jquery-jvectormap-2.0.3.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/vendors/css/charts/morris.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/fonts/simple-line-icons/style.css">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/app-assets/css/core/colors/palette-gradient.css">
        <!-- END Page Level CSS-->
        <!-- BEGIN Custom CSS-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resources/assets/css/style.css">
        <!-- END Custom CSS-->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/studentCreateStyle.css"/>
        <jsp:invoke fragment="css"/>
    </head>
    <body class="vertical-layout vertical-menu-modern 2-columns   menu-expanded fixed-navbar" data-open="click" data-menu="vertical-menu-modern" data-col="2-columns">
        <!-- fixed-top-->
        <nav class="header-navbar navbar-expand-md navbar navbar-with-menu navbar-without-dd-arrow fixed-top navbar-semi-dark navbar-shadow">
            <div class="navbar-wrapper">
                <div class="navbar-header">
                    <ul class="nav navbar-nav flex-row">
                        <li class="nav-item mobile-menu d-md-none mr-auto"><a class="nav-link nav-menu-main menu-toggle hidden-xs" href="#"><i class="ft-menu font-large-1"></i></a></li>
                        <li class="nav-item mr-auto">
                            <a class="navbar-brand" href="<%= request.getContextPath() %>/Home">
                                <img class="brand-logo" alt="modern admin logo" src="<%= request.getContextPath()%>/resources/app-assets/images/logo/logo.png">
                                <h3 class="brand-text">CSMS</h3>
                            </a>
                        </li>
                        <li class="nav-item d-none d-md-block float-right"><a class="nav-link modern-nav-toggle pr-0" data-toggle="collapse"><i class="toggle-icon ft-toggle-right font-medium-3 white" data-ticon="ft-toggle-right"></i></a></li>
                        <li class="nav-item d-md-none">
                            <a class="nav-link open-navbar-container" data-toggle="collapse" data-target="#navbar-mobile"><i class="la la-ellipsis-v"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="navbar-container content">
                    <div class="collapse navbar-collapse" id="navbar-mobile">
                        <ul class="nav navbar-nav mr-auto float-left">
                            <li class="nav-item d-none d-md-block">College Student Management System</li>
                        </ul>
                        <ul class="nav navbar-nav float-right">
                            <li class="dropdown dropdown-user nav-item">
                                <a class="dropdown-toggle nav-link dropdown-user-link" href="#" data-toggle="dropdown">
                                    <span class="mr-1" style="line-height: 2;">
                                        <%= session.getAttribute("userName").toString()%>
                                    </span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" href="<%= request.getContextPath() %>/Users/Details?id=<%= session.getAttribute("userId") %>"><i class="ft-user"></i> View profile</a>
                                    <%--<a class="dropdown-item" href="#"><i class="ft-mail"></i> My Inbox</a>
                                    <a class="dropdown-item" href="#"><i class="ft-check-square"></i> Task</a>
                                    <a class="dropdown-item" href="#"><i class="ft-message-square"></i> Chats</a>--%>
                                    <div class="dropdown-divider"></div><a class="dropdown-item" href="<%= request.getContextPath() %>/Logout"><i class="ft-power"></i> Logout</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <!-- Sidebar -->
        <div class="main-menu menu-fixed menu-dark menu-accordion menu-shadow" data-scroll-to-active="true">
            <div class="main-menu-content">
                <ul class="navigation navigation-main" id="main-menu-navigation" data-menu="menu-navigation">
                    <li class=" nav-item">
                        <a href="<%= request.getContextPath() %>/Home"><i class="la la-home"></i><span class="menu-title" data-i18n="">Dashboard</span></a>
                    </li>

                    <li class=" navigation-header">
                        <span data-i18n="nav.category.academic">Academic</span><i class="la la-ellipsis-h ft-minus" data-toggle="tooltip" data-placement="right" data-original-title="Academic"></i>
                    </li>

                        <li class=" nav-item has-sub"><a href="#"><i class="la la-graduation-cap"></i><span class="menu-title" data-i18n="nav.color_palette.main">Students</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Students" data-i18n="nav.color_palette.color_palette_primary">All students</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Students/Create" data-i18n="nav.color_palette.color_palette_danger">Create student</a>
                                </li>
                            </ul>
                        </li>

                        <li class=" nav-item has-sub"><a href="#"><i class="la la-pencil"></i><span class="menu-title" data-i18n="nav.color_palette.main">Teachers</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Teachers" data-i18n="nav.color_palette.color_palette_primary">All teachers</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Teachers/Create" data-i18n="nav.color_palette.color_palette_danger">Create teacher</a>
                                </li>
                            </ul>
                        </li>

                    <li class=" navigation-header">
                        <span data-i18n="nav.category.academic">Classes</span><i class="la la-ellipsis-h ft-minus" data-toggle="tooltip" data-placement="right" data-original-title="Classes"></i>
                    </li>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-history"></i><span class="menu-title" data-i18n="nav.color_palette.main">Periods</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Periods" data-i18n="nav.color_palette.color_palette_primary">All periods</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-pencil-square-o"></i><span class="menu-title" data-i18n="nav.color_palette.main">Courses</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Courses" data-i18n="nav.color_palette.color_palette_primary">All courses</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Courses/Create" data-i18n="nav.color_palette.color_palette_danger">Create course</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/CoursesSections" data-i18n="nav.color_palette.color_palette_danger">Courses sections</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                    
                    <li class=" navigation-header">
                        <span data-i18n="nav.category.administrative">Administrative</span><i class="la la-ellipsis-h ft-minus" data-toggle="tooltip" data-placement="right" data-original-title="Administrative"></i>
                    </li>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-sort-alpha-asc"></i><span class="menu-title" data-i18n="nav.color_palette.main">Positions</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Positions" data-i18n="nav.color_palette.color_palette_primary">All positions</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Positions/Create" data-i18n="nav.color_palette.color_palette_danger">Create position</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-building"></i><span class="menu-title" data-i18n="nav.color_palette.main">Infrastructure</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Campus" data-i18n="nav.color_palette.color_palette_primary">All campus</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Campus/Create" data-i18n="nav.color_palette.color_palette_danger">Create campus</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Builds" data-i18n="nav.color_palette.color_palette_primary">All buildings</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Builds/Create" data-i18n="nav.color_palette.color_palette_danger">Create building</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Classrooms" data-i18n="nav.color_palette.color_palette_primary">All classrooms</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Classrooms/Create" data-i18n="nav.color_palette.color_palette_danger">Create classroom</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>

                    <li class=" navigation-header">
                        <span data-i18n="nav.category.security">Security</span><i class="la la-ellipsis-h ft-minus" data-toggle="tooltip" data-placement="right" data-original-title="Security"></i>
                    </li>

                    <%-- if(request.isUserInRole("admin")) { --%>
<!--                        <li class=" nav-item has-sub"><a href="#"><i class="la la-group"></i><span class="menu-title" data-i18n="nav.color_palette.main">Groups</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Groups" data-i18n="nav.color_palette.color_palette_primary">All groups</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Groups/Create" data-i18n="nav.color_palette.color_palette_danger">Create group</a>
                                </li>
                            </ul>
                        </li>-->
                    <%-- } --%>

                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-users"></i><span class="menu-title" data-i18n="nav.color_palette.main">Users</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Users" data-i18n="nav.color_palette.color_palette_primary">All users</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Users/Create" data-i18n="nav.color_palette.color_palette_danger">Create user</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-lock"></i><span class="menu-title" data-i18n="nav.color_palette.main">Roles</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Roles" data-i18n="nav.color_palette.color_palette_primary">All roles</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Roles/Create" data-i18n="nav.color_palette.color_palette_danger">Create role</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                    
                    <%-- if(request.isUserInRole("admin")) { --%>
                        <li class=" nav-item has-sub"><a href="#"><i class="la la-lock"></i><span class="menu-title" data-i18n="nav.color_palette.main">Permissions</span></a>
                            <ul class="menu-content">
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Permissions" data-i18n="nav.color_palette.color_palette_primary">All permissions</a>
                                </li>
                                <li><a class="menu-item" href="<%= request.getContextPath() %>/Permissions/Create" data-i18n="nav.color_palette.color_palette_danger">Create permission</a>
                                </li>
                            </ul>
                        </li>
                    <%-- } --%>
                </ul>
            </div>
        </div>
        
        <!--Content-->
        <div class="app-content content">
            <div class="content-wrapper" style="padding: 2.2rem;">
                <div class="content-header row">
                    <div class="content-header-left col-md-6 col-12 mb-2 breadcrumb-new">
                        <h3 class="content-header-title mb-0 d-inline-block" style="border-right: 0px;">${title}</h3>
                    </div>                    
                </div>
                <div class="content-body">
                    <section id="configuration">
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">${pageName}</h4>
                                    </div>
                                    <div class="card-content collapse show">
                                        <div class="card-body card-dashboard">
                                            <jsp:invoke fragment="content"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <footer class="footer footer-static footer-light navbar-border navbar-shadow">
            <p class="clearfix blue-grey lighten-2 text-sm-center mb-0 px-2">
                <span class="float-md-left d-block d-md-inline-block">Copyright &copy; <%= java.util.Calendar.getInstance().get(Calendar.YEAR) %> <a class="text-bold-800 grey darken-2" href="#" target="_blank">College Student Management System</a>, All rights reserved. </span>
            </p>
        </footer>

        <!-- BEGIN VENDOR JS-->
        <script src="<%= request.getContextPath()%>/resources/scripts/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/vendors.min.js" type="text/javascript"></script>
        <!-- BEGIN VENDOR JS-->
        <!-- BEGIN PAGE VENDOR JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/charts/chart.min.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/charts/raphael-min.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/charts/morris.min.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/charts/jvector/jquery-jvectormap-2.0.3.min.js"
        type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/vendors/js/charts/jvector/jquery-jvectormap-world-mill.js"
        type="text/javascript"></script>
        <!-- END PAGE VENDOR JS-->
        <!-- BEGIN MODERN JS-->
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/core/app-menu.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/core/app.js" type="text/javascript"></script>
        <script src="<%= request.getContextPath()%>/resources/app-assets/js/scripts/customizer.js" type="text/javascript"></script>
        <!-- END MODERN JS-->
        <!-- BEGIN PAGE LEVEL JS-->
<!--        <script src="<%= request.getContextPath()%>/resources/app-assets/js/scripts/pages/dashboard-sales.js" type="text/javascript"></script>-->
        <!-- END PAGE LEVEL JS-->
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/dataTableInstances.js"></script>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>