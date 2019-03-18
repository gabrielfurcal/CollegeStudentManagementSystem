<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Buildings" pageName="All buildings">
    <jsp:attribute name="content">
        <c:set var="builds" scope="request" value='${requestScope["builds"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Builds/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableBuilds" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableBuilds_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Campus</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="build" items="${builds}">
                    <tr>
                        <td>${build.buildName}</td>
                        <td>${build.campus.campusName}</td>
                        <td>${simpleDateFormat.format(build.buildCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Builds/Edit?id=${build.buildsPK.buildId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Builds/Details?id=${build.buildsPK.buildId}">Details</a> | <a href="#" onclick="deleteBuilds('${build.buildsPK.buildId}', '${pageContext.servletContext.contextPath}/Builds/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/buildIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>