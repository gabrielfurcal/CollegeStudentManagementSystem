<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Details course section historical" pageName="Details course section historical">
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="courseSectionHistorical" scope="request" value='${requestScope["courseSectionHistorical"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>

        <form class="form form-horizontal" id="frmDetailsCourseSectionHistorical">
            
            <input type="hidden" value="${courseSectionHistorical.courseSectHistId}" name="courseSectHistId" id="courseSectHistId"/>
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Course section historical info</h4>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseName">Couse</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseName" id="courseName" value="${courseSectionHistorical.course.courseName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSection">Section ID</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseSection" id="courseSection" value="${courseSectionHistorical.courseSection.courseSectionsPK.courseSectionId}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseName">Period</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="period" id="period" value="${courseSectionHistorical.period.periodsPK.periodYear} - ${courseSectionHistorical.period.periodsPK.periodQuarter}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionEndHour">Teacher</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionEndHour" id="courseSectionEndHour" value="${courseSection.courseSectionEndHour}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionSchedule">Schedule</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseSectionSchedule" id="courseSectionSchedule" value="${courseSectionHistorical.courseSectHistDay} ${courseSectionHistorical.courseSectionStartHour} - ${courseSectionHistorical.courseSectionEndHour}" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="quota">Quota</label>
                        <div class="col-md-9">
                            <input type="number" class="form-control" name="quota" id="quota" min="0" value="${courseSectionHistorical.courseSectHistQuota.toString()}" readonly="readonly"/>
                        </div>
                    </div>   
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="teacher">Teacher</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="teacher" id="teacher" value="${courseSectionHistorical.teacher.user.userFirstName} ${teacher.user.userFatherLastName} ${teacher.user.userMotherLastName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campus" id="campus" value="${courseSectionHistorical.classroom.build.campus.campusName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="build">Building</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="build" id="build" value="${courseSectionHistorical.classroom.build.buildName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroom">Classroom</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroom" id="classroom" value="${courseSectionHistorical.classroom.classroomName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionActive">Status</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseSectionActive" id="courseSectionActive" value='${courseSection.courseSectionActive == 1 ? "Active" : "Inactive" }' readonly="readonly"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>