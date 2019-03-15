<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Create building" pageName="New building"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="campus" scope="request" value='${requestScope["campus"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Builds/Create" class="form form-horizontal" id="frmCreateBuild">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create building
                </div>
            </c:if>      
            <h4 class="form-section">Building info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="buildName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="buildName" id="buildName" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <select name="campus" id="campus" class="form-control">
                                <option value="" selected="true">Select</option>
                                <c:forEach var="campu" items="${campus}">
                                    <option value="${campu.campusId.toString()}">${campu.campusName}</option>
                                </c:forEach>
                            </select>
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