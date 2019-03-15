<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>    

<mt:mastertemplate title="Details course section" pageName="Details course section"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="courseSection" scope="request" value='${requestScope["courseSection"]}'/>
        <c:set var="courseSectionHistorical" scope="request" value='${requestScope["courseSectionHistorical"]}'/>
        <c:set var="coursesSectionsHistorical" scope="request" value='${requestScope["coursesSectionsHistorical"]}'/>
        <c:set var="teachers" scope="request" value='${requestScope["teachers"]}'/>
        <c:set var="campus" scope="request" value='${requestScope["campus"]}'/>
        <c:set var="builds" scope="request" value='${requestScope["builds"]}'/>
        <c:set var="classrooms" scope="request" value='${requestScope["classrooms"]}'/>
        <c:set var="daysOfWeek" scope="request" value='${requestScope["daysOfWeek"]}'/>
        
        <form class="form form-horizontal" id="frmDetailsCourseSection">
            
            <input type="hidden" value="${courseSection.coursesSectionsPK.courseSectionId}" name="courseSectionId" id="courseSectionId"/>
            <input type="hidden" value="${course.courseId}" name="courseId" id="courseId"/>
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Course section info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseId">Couse</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseName" id="courseName" value="${courseSection.course.courseName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionAmountHours">Day</label>
                        <div class="col-md-9">
                            <c:forEach var="dayOfWeek" items="${daysOfWeek}">
                                <c:if test="${dayOfWeek.substring(0,3).toUpperCase() == courseSection.courseSectionDay}">
                                    <input type="text" class="form-control" name="courseSectionDay" id="courseSectionDay" value="${dayOfWeek}" readonly="readonly"/>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionStartHour">Start hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionStartHour" id="courseSectionStartHour" value="${courseSection.courseSectionStartHour}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionEndHour">End hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionEndHour" id="courseSectionEndHour" value="${courseSection.courseSectionEndHour}" readonly="readonly"/>
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
            <h5 class="form-section"><i class="ft-user"></i> Course sections historical</h5>
            
            <div class="row">
                <div class="col-md-12">
                    <table id="tableCoursesSectionsHistoricalInCourseSection" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCoursesSectionsHistoricalInCourseSection_info" role="grid">
                        <thead>
                            <tr>
                                <th>Teacher</th>
                                <th>Quota</th>
                                <th>Period</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="courseSectionHistorical2" items="${coursesSectionsHistorical}">
                                <tr>
                                    <td>${courseSectionHistorical2.teacher.user.userFirstName} ${courseSectionHistorical2.teacher.user.userFatherLastName} ${courseSectionHistorical2.teacher.user.userMotherLastName}</td>
                                    <td>${courseSectionHistorical2.courseSectHistQuota.toString()}</td>
                                    <td>${courseSectionHistorical2.period.periodsPK.periodYear} - 0${courseSectionHistorical2.period.periodsPK.periodQuarter}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>