<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Positions" pageName="All positions">
    <jsp:attribute name="content">
        <c:set var="positions" scope="request" value='${requestScope["positions"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Positions/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tablePositions" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePositions_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="position" items="${positions}">
                    <tr>
                        <td>${position.positionName}</td>
                        <td>${simpleDateFormat.format(position.positionCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Positions/Edit?id=${position.positionId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Positions/Details?id=${position.positionId}">Details</a> | <a href="#" onclick="deletePosition('${position.positionId}', '${pageContext.servletContext.contextPath}/Positions/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/positionIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>