<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Courses" pageName="All courses">
    <jsp:attribute name="content">
        <c:set var="courses" scope="request" value='${requestScope["courses"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Courses/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableCourses" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCourses_info" role="grid">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Creation date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>${course.courseId}</td>
                        <td>${course.courseName}</td>
                        <td><span>US$</span>${course.coursePrice}</td>
                        <td>${simpleDateFormat.format(course.courseCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Courses/Edit?id=${course.courseId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Courses/Details?id=${course.courseId}">Details</a> | <a href="#" onclick="deleteBuilds('${course.courseId}', '${pageContext.servletContext.contextPath}/Courses/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>