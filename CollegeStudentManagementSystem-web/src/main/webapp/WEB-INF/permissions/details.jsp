<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>    

<mt:mastertemplate title="Details permission" pageName="Details permission"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="permission" scope="request" value='${requestScope["permission"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Permissions/Delete" class="form form-horizontal" id="frmDetailsPermission">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save permission
                </div>
            </c:if>
            
            <input type="hidden" name="permissionId" id="permissionId" value="${permission.permissionId}"/>
            
            <h4 class="form-section">Permission info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="permissionName" id="permissionName" value="${permission.permissionName}" readonly="readonly"/>
                        </div>
                    </div> 
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionUrl">URL</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="permissionUrl" id="permissionUrl" value="${permission.permissionUrl}" readonly="readonly"/>
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="permissionDescription" id="permissionDescription" rows="5" style="width: 100%;" readonly="readonly">${permission.permissionDescription}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="permissionCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="permissionCreationDate" id="permissionCreationDate" value="${simpleDateFormat.format(permission.permissionCreationDate)}" readonly="readonly"/>
                        </div>
                    </div> 
                </div>   
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="button" id="btnSubmit" value="Delete" class="btn btn-danger"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/permissionDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>