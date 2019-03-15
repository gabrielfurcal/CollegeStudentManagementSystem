<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Users" pageName="All users">    
    <jsp:attribute name="content">
        <c:set var="users" scope="request" value='${requestScope["users"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Users/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableUsers" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableUsers_info" role="grid">
            <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Username</th>
                    <th>Start date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userFirstName}</td>
                        <td>${user.userFatherLastName}&nbsp;${user.userMotherLastName}</td>
                        <td>${user.userUsername}</td>
                        <td>${simpleDateFormat.format(user.userCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Users/Edit?id=${user.userId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Users/Details?id=${user.userId}">Details</a> | <a href="#" onclick="deleteUser('${user.userId}', '${pageContext.servletContext.contextPath}/Users/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/userIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>