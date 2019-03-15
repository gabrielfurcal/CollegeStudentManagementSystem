<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Edit course section" pageName="Edit course section"> 
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
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/CoursesSections/Edit" class="form form-horizontal" id="frmEditCourseSection">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to edit course section
                </div>
            </c:if>   
            
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
                            <select class="form-control" name="courseSectionDay" id="courseSectionDay">
                                <option value="">Select</option>
                                <c:forEach var="dayOfWeek" items="${daysOfWeek}">
                                    <c:choose>
                                        <c:when test="${dayOfWeek.substring(0,3).toUpperCase() == courseSection.courseSectionDay}">
                                            <option value="${dayOfWeek.substring(0,3).toUpperCase()}" selected="true">${dayOfWeek}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${dayOfWeek.substring(0,3).toUpperCase()}">${dayOfWeek}</option>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionStartHour">Start hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionStartHour" id="courseSectionStartHour" value="${courseSection.courseSectionStartHour}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionEndHour">End hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionEndHour" id="courseSectionEndHour" value="${courseSection.courseSectionEndHour}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="quota">Quota</label>
                        <div class="col-md-9">
                            <input type="number" class="form-control" name="quota" id="quota" min="0" value="${courseSectionHistorical.courseSectHistQuota.toString()}"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="teacher">Teacher</label>
                        <div class="col-md-9">
                            <select class="form-control" name="teacher" id="teacher">
                                <option value="">Select</option>
                                <c:forEach var="teacher" items="${teachers}">
                                    <c:choose>
                                        <c:when test="${teacher == courseSectionHistorical.teacher}">
                                            <option value="${teacher.teachersPK.teacherId.toString()}" selected="true">${teacher.user.userFirstName} ${teacher.user.userFatherLastName} ${teacher.user.userMotherLastName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${teacher.teachersPK.teacherId.toString()}">${teacher.user.userFirstName} ${teacher.user.userFatherLastName} ${teacher.user.userMotherLastName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <select class="form-control" name="campus" id="campus">
                                <option value="">Select</option>
                                <c:forEach var="campus" items="${campus}">
                                    <c:choose>
                                        <c:when test="${campus == courseSectionHistorical.classroom.build.campus}">
                                            <option value="${campus.campusId.toString()}" selected="true">${campus.campusName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${campus.campusId.toString()}">${campus.campusName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="build">Building</label>
                        <div class="col-md-9">
                            <select class="form-control" name="build" id="build">
                                <option value="">Select</option>
                                <c:forEach var="build" items="${builds}">
                                    <c:choose>
                                        <c:when test="${build == courseSectionHistorical.classroom.build}">
                                            <option value="${build.buildsPK.buildId.toString()}" selected="true">${build.buildName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${build.buildsPK.buildId.toString()}">${build.buildName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroom">Classroom</label>
                        <div class="col-md-9">
                            <select class="form-control" name="classroom" id="classroom">
                                <option value="">Select</option>
                                <c:forEach var="classroom" items="${classrooms}">
                                    <c:choose>
                                        <c:when test="${classroom == courseSectionHistorical.classroom}">
                                            <option value="${classroom.classroomsPK.classroomId.toString()}" selected="true">${classroom.classroomName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${classroom.classroomsPK.classroomId.toString()}">${classroom.classroomName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionActive">Status</label>
                        <div class="col-md-9">
                            <select class="form-control" name="courseSectionActive" id="courseSectionActive">
                                <option value="">Select</option>
                                    <c:choose>
                                        <c:when test="${courseSection.courseSectionActive == 1}">
                                            <option value="1" selected="true">Active</option>
                                            <option value="0">Inactive</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1">Active</option>
                                            <option value="0" selected="true">Inactive</option>
                                        </c:otherwise>
                                    </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="viewHistory">History</label>
                        <div class="col-md-9">
                            <a href="${pageContext.servletContext.contextPath}/CoursesSectionsHistorical?id=${courseSection.coursesSectionsPK.courseSectionId}" class="btn btn-primary" name="viewHistory" id="viewHistory" value="${courseSection.courseSectionEndHour}">View history</a>
                        </div>
                    </div>
                </div>
            </div>

            <br/>
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="submit" id="btnSubmit" value="Save" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>