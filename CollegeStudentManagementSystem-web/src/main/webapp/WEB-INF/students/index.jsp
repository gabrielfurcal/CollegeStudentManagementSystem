<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>            

<mt:mastertemplate title="Students" pageName="All students">
    <jsp:attribute name="content">
        <c:set var="students" scope="request" value='${requestScope["students"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Students/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableStudents" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableStudents_info" role="grid">
            <thead>
                <tr>
                    <th>Enrollment</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Start date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentsPK.studentId}</td>
                        <td>${student.user.userFirstName}</td>
                        <td>${student.user.userFatherLastName}&nbsp;${student.user.userMotherLastName}</td>
                        <td>${simpleDateFormat.format(student.studentCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Students/Edit?id=${student.studentsPK.studentId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Students/Details?id=${student.studentsPK.studentId}">Details</a> | <a href="#" onclick="deleteStudent('${student.studentsPK.studentId}', '${pageContext.servletContext.contextPath}/Students/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/studentIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>