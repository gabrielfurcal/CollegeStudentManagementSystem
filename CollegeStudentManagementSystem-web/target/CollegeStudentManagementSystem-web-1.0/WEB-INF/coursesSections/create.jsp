<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Create course section" pageName="New course section"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="course" scope="request" value='${requestScope["course"]}'/>
        <c:set var="teachers" scope="request" value='${requestScope["teachers"]}'/>
        <c:set var="campus" scope="request" value='${requestScope["campus"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/CoursesSections/Create" class="form form-horizontal" id="frmCreateCourseSection">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create course section
                </div>
            </c:if>   
            
            <input type="hidden" value="${course.courseId}" name="courseId" id="courseId"/>
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Course section info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseId">Couse</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseName" id="courseName" value="${course.courseName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionAmountHours">Day</label>
                        <div class="col-md-9">
                            <select class="form-control" name="courseSectionDay" id="courseSectionDay">
                                <option value="">Select</option>
                                <option value="MON">Monday</option>
                                <option value="TUE">Tuesday</option>
                                <option value="WED">Wednesday</option>
                                <option value="FRI">Friday</option>
                                <option value="SAT">Saturday</option>
                                <option value="SUN">Sunday</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionStartHour">Start hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionStartHour" id="courseSectionStartHour"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseSectionEndHour">End hour</label>
                        <div class="col-md-9">
                            <input type="time" class="form-control" name="courseSectionEndHour" id="courseSectionEndHour"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="quota">Quota</label>
                        <div class="col-md-9">
                            <input type="number" class="form-control" name="quota" id="quota" min="0"/>
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
                                    <option value="${teacher.teachersPK.teacherId.toString()}">${teacher.user.userFirstName} ${teacher.user.userFatherLastName} ${teacher.user.userMotherLastName}</option>
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
                                    <option value="${campus.campusId.toString()}">${campus.campusName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="build">Building</label>
                        <div class="col-md-9">
                            <select class="form-control" name="build" id="build">
                                <option value="">Select</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroom">Classroom</label>
                        <div class="col-md-9">
                            <select class="form-control" name="classroom" id="classroom">
                                <option value="">Select</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="submit" id="btnSubmit" value="Create" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>