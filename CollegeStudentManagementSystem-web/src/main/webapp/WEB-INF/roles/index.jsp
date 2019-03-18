<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Roles" pageName="All roles">
    <jsp:attribute name="content">
        <c:set var="roles" scope="request" value='${requestScope["roles"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Roles/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableRoles" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableRoles_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="role" items="${roles}">
                    <tr>
                        <td>${role.roleName}</td>
                        <td>${role.roleDescription}</td>
                        <td>${simpleDateFormat.format(role.roleCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Roles/Edit?id=${role.roleId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Roles/Details?id=${role.roleId}">Details</a> | <a href="#" onclick="deleteRole('${role.roleId}', '${pageContext.servletContext.contextPath}/Roles/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/roleIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>