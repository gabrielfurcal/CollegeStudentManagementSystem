<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Create role" pageName="New role">   
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="permissions" scope="request" value='${requestScope["permissions"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Roles/Create" class="form form-horizontal" id="frmCreateRole">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create role
                </div>
            </c:if>      
            <h4 class="form-section">Role info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="roleName" id="roleName" />
                        </div>
                    </div> 
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="roleDescription" id="roleDescription" rows="5" style="width: 100%;"></textarea>
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Permissions</label>
                        <div class="col-md-9">
                            <table id="tablePermissionsInRoleCreate" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePermissionsInRoleCreate_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Select</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="permission" items="${permissions}">
                                        <tr>
                                            <td>${permission.permissionName}</td>
                                            <td><input type="checkbox" value="${permission.permissionId}" name="permissionsSelected"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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