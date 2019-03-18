<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Course sections history" pageName="View course sections history">
    <jsp:attribute name="content">
        <c:set var="coursesSectionsHistorical" scope="request" value='${requestScope["coursesSectionsHistorical"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>

        <table id="tableCoursesSectionsHistorical" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCoursesSectionsHistorical_info" role="grid">
            <thead>
            <tr>
                <th>Period</th>
                <th>Schedule</th>
                <th>Teacher</th>
                <th>Creation date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="courseSectionHistorical" items="${coursesSectionsHistorical}">
                    <tr>
                        <td>${courseSectionHistorical.period.periodsPK.periodYear} - ${courseSectionHistorical.period.periodsPK.periodQuarter}</td>
                        <td>${courseSectionHistorical.courseSectHistDay} ${courseSectionHistorical.courseSectHistStartHour} - ${courseSectionHistorical.courseSectHistEndHour}</td>
                        <td>${courseSectionHistorical.teacher.user.userFirstName} ${courseSectionHistorical.teacher.user.userFatherLastName} ${courseSectionHistorical.teacher.user.userMotherLastName}</td>
                        <td>${simpleDateFormat.format(courseSectionHistorical.courseSectHistCreationDate)}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/CoursesSectionsHistorical/Details?id=${courseSectionHistorical.courseSectHistId}">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionHistoricalIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>