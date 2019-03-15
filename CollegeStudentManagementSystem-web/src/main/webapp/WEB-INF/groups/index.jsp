<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>     

<mt:mastertemplate title="Groups" pageName="All groups"> 
    <jsp:attribute name="content">
        <c:set var="groups" scope="request" value='${requestScope["groups"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Groups/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableGroups" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableGroups_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="group" items="${groups}">
                    <tr>
                        <td>${group.groupName}</td>
                        <td>${simpleDateFormat.format(group.groupCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Groups/Edit?id=${group.groupId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Groups/Details?id=${group.groupId}">Details</a> | <a href="#" onclick="deleteGroup('${group.groupId}', '${pageContext.servletContext.contextPath}/Groups/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/groupIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>