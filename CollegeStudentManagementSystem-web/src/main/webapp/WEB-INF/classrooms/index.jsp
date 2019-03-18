<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Classrooms" pageName="All classrooms">
    <jsp:attribute name="content">
        <c:set var="classrooms" scope="request" value='${requestScope["classrooms"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Classrooms/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableClassrooms" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableClassrooms_info" role="grid">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Building</th>
                    <th>Campus</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="classroom" items="${classrooms}">
                    <tr>
                        <td>${classroom.classroomName}</td>
                        <td>${classroom.build.buildName}</td>
                        <td>${classroom.build.campus.campusName}</td>
                        <td>${simpleDateFormat.format(classroom.classroomCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Classrooms/Edit?id=${classroom.classroomsPK.classroomId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Classrooms/Details?id=${classroom.classroomsPK.classroomId}">Details</a> | <a href="#" onclick="deleteClassrooms('${classroom.classroomsPK.classroomId}', '${pageContext.servletContext.contextPath}/Classrooms/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/classroomIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>