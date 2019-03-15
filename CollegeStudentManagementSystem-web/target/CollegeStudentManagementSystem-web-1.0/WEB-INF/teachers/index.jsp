<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Teachers" pageName="All teachers">  
    <jsp:attribute name="content">
        <c:set var="teachers" scope="request" value='${requestScope["teachers"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <a href="${pageContext.servletContext.contextPath}/Teachers/Create" class="btn btn-primary pull-right">Create new</a>
        
        <br/><br/>
        
        <table id="tableTeachers" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableStudents_info" role="grid">
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
                <c:forEach var="teacher" items="${teachers}">
                    <tr>
                        <td>${teacher.user.userFirstName}</td>
                        <td>${teacher.user.userFatherLastName}&nbsp;${teacher.user.userMotherLastName}</td>
                        <td>${teacher.user.userUsername}</td>
                        <td>${simpleDateFormat.format(teacher.teacherCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/Teachers/Edit?id=${teacher.teachersPK.teacherId}">Edit</a> | <a href="${pageContext.servletContext.contextPath}/Teachers/Details?id=${teacher.teachersPK.getTeacherId()}">Details</a> | <a href="#" onclick="deleteTeacher('${teacher.teachersPK.teacherId}', '${pageContext.servletContext.contextPath}/Teachers/Delete')">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/teacherIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>