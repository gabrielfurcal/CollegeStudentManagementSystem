<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>            

<mt:mastertemplate title="Create permission" pageName="New permission"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <form method="POST" action="${pageContext.servletContext.contextPath}/Permissions/Create" class="form form-horizontal" id="frmCreatePermission">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create permission
                </div>
            </c:if>      
            <h4 class="form-section">Permission info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="permissionName" id="permissionName" />
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionUrl">URL</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="permissionUrl" id="permissionUrl" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="permissionDescription" id="permissionDescription" rows="5" style="width: 100%;"></textarea>
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