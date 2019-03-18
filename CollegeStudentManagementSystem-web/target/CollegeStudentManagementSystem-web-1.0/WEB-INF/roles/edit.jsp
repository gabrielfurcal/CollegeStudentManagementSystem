<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Edit role" pageName="Edit role">   
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="role" scope="request" value='${requestScope["role"]}'/>
        <c:set var="definedPermissions" scope="request" value='${requestScope["definedPermissions"]}'/>
        <c:set var="permissions" scope="request" value='${requestScope["permissions"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Roles/Edit" class="form form-horizontal" id="frmEditRole">
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
                            <input type="text" class="form-control" name="roleName" id="roleName" value="${role.roleName}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="roleDescription" id="roleDescription" rows="5" style="width: 100%;">${role.roleDescription}</textarea>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="roleCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="roleCreationDate" id="roleCreationDate" value="${simpleDateFormat.format(role.roleCreationDate)}" disabled="true"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                     <div class="form-group row">
                        <label class="col-md-3 label-control">Permissions</label>
                        <div class="col-md-9" id="checkBoxSelection">
                            <table id="tablePermissionsInRoleEdit" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePermissionsInRoleEdit_info" role="grid">
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
                                            <c:choose>
                                                <c:when test="${definedPermissions.contains(permission)}">
                                                    <td><input type="checkbox" value="${permission.permissionId}" name="permissionsSelected" checked="checked"/></td> 
                                                </c:when>
                                                <c:otherwise>
                                                    <td><input type="checkbox" value="${permission.permissionId}" name="permissionsSelected"/></td>
                                                </c:otherwise>
                                            </c:choose>
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
                    <input type="submit" id="btnSubmit" value="Save" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
</mt:mastertemplate>