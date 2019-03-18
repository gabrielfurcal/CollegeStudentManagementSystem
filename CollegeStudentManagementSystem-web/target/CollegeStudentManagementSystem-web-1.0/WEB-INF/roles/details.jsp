<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Details role" pageName="Details role">  
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="role" scope="request" value='${requestScope["role"]}'/>
        <c:set var="definedPermissions" scope="request" value='${requestScope["definedPermissions"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Roles/Delete" class="form form-horizontal" id="frmDetailsRole">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save role
                </div>
            </c:if>
            
            <input type="hidden" name="roleId" id="roleId" value="${role.roleId}"/>
            
            <h4 class="form-section">Role info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="roleName" id="roleName" value="${role.roleName}" readonly="readonly"/>
                        </div>
                    </div> 
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="roleDescription" id="roleDescription" rows="5" style="width: 100%;" readonly="readonly">${role.roleDescription}</textarea>
                        </div>
                    </div>    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="roleCreationDate" id="roleCreationDate" value="${simpleDateFormat.format(role.roleCreationDate)}" readonly="readonly"/>
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Permissions</label>
                        <div class="col-md-9">
                            <table id="tablePermissionsInRoleDetails" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePermissionsInRoleDetails_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="permission" items="${definedPermissions}">
                                        <tr>
                                            <td>${permission.permissionName}</td>
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
                    <input type="button" id="btnSubmit" value="Delete" class="btn btn-danger"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/roleDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>