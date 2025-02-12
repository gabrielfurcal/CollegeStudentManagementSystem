<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Edit courses" pageName="Edit courses">
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="course" scope="request" value='${requestScope["course"]}'/>
        <c:set var="courseSections" scope="request" value='${requestScope["courseSections"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Courses/Edit" class="form form-horizontal" id="frmEditCourses">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save courses
                </div>
            </c:if>
            
            <input type="hidden" name="courseId" id="courseId" value="${course.courseId}"/>
            
            <h4 class="form-section">Course info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseId">Code</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseCode" id="courseCode" value="${course.courseId}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseName" id="courseName" value="${course.courseName}" min="0" step=".01"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="coursePrice">Price</label>
                        <div class="col-md-9">
                            <div class="input-group mb-2">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">US$</div>
                                </div>
                                <input type="number" class="form-control" name="coursePrice" id="coursePrice" value="${course.coursePrice.toString()}"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseAmountHours">Hours</label>
                        <div class="col-md-9">
                            <input type="number" class="form-control" name="courseAmountHours" id="courseAmountHours" value="${course.courseAmountHours}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseCreationDate" id="courseCreationDate" value="${simpleDateFormat.format(course.courseCreationDate)}" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Course sections</label>
                        <div class="col-md-9">
                            <table id="tableCourseSectionsInCourseEdit" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCourseSectionsInCourseEdit_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Schedule</th>
                                        <th>Teacher</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="courseSection" items="${course.coursesSections}">
                                        <tr>
                                            <td>${courseSection.courseSectionDay} | ${courseSection.courseSectionStartHour} - ${courseSection.courseSectionEndHour}</td>
                                            <td>${courseSection.coursesSectionsHistorical.get(0).teacher.user.getUserFirstName()} ${courseSection.coursesSectionsHistorical.get(0).teacher.user.getUserFatherLastName()} ${courseSection.coursesSectionsHistorical.get(0).teacher.user.getUserMotherLastName()}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="submit" id="btnSubmit" value="Save" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
</mt:mastertemplate>