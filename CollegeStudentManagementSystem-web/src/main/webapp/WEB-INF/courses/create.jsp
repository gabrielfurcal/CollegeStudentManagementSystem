<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Create course" pageName="New course"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Courses/Create" class="form form-horizontal" id="frmCreateCourse">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create course
                </div>
            </c:if>      
            <h4 class="form-section">Course info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseId">Code</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseId" id="courseId" maxlength="8"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseName" id="courseName" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseAmountHours">Hours</label>
                        <div class="col-md-9">
                            <input type="number" class="form-control" name="courseAmountHours" id="courseAmountHours" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="coursePrice">Price</label>
                        <div class="col-md-9">
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">US$</div>
                                </div>
                                <input type="number" class="form-control" name="coursePrice" id="coursePrice" min="0" step=".01"/>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="submit" id="btnSubmit" value="Create" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
</mt:mastertemplate>