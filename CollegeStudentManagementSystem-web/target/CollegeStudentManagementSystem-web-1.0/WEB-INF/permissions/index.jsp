<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Permissions" pageName="All permissions">
    <jsp:attribute name="content">
        <c:set var="permissions" scope="request" value='${requestScope["permissions"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Permissions/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tablePermissions" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePermissions_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>URL</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="permission" items="${permissions}">
                    <tr>
                        <td>${permission.permissionName}</td>
                        <td>${permission.permissionUrl}</td>
                        <td>${simpleDateFormat.format(permission.permissionCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Permissions/Edit?id=${permission.permissionId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Permissions/Details?id=${permission.permissionId}">Details</a> | <a href="#" onclick="deletePermission('${permission.permissionId}', '${pageContext.servletContext.contextPath}/Permissions/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/permissionIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>