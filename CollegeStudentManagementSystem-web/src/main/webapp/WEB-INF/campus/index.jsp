<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>     

<mt:mastertemplate title="Campus" pageName="All campus"> 
    <jsp:attribute name="content">
        <c:set var="groups" scope="request" value='${requestScope["groups"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Campus/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableCampus" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCampus_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="campus" items="${campus}">
                    <tr>
                        <td>${campus.campusName}</td>
                        <td>${simpleDateFormat.format(campus.campusCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Campus/Edit?id=${campus.campusId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Campus/Details?id=${campus.campusId}">Details</a> | <a href="#" onclick="deleteCampus('${campus.campusId}', '${pageContext.servletContext.contextPath}/Campus/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/campusIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>