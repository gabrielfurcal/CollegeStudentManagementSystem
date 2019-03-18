<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Edit classroom" pageName="Edit classroom">
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="classroom" scope="request" value='${requestScope["classroom"]}'/>
        <c:set var="campus" scope="request" value='${requestScope["campus"]}'/>
        <c:set var="campusBuilds" scope="request" value='${requestScope["campusBuilds"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Classrooms/Edit" class="form form-horizontal" id="frmEditClassrooms">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save classroom
                </div>
            </c:if>
            
            <input type="hidden" name="classroomId" id="classroomId" value="${classroom.classroomsPK.classroomId}"/>
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Classroom info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroomName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroomName" id="classroomName" value="${classroom.classroomName}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroomCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroomCreationDate" id="classroomCreationDate" value="${simpleDateFormat.format(classroom.classroomCreationDate)}" disabled="true"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <select name="campus" id="campus" class="form-control">
                                <option value="">Select</option>
                                <c:forEach var="campu" items="${campus}">
                                    <c:choose>
                                        <c:when test="${classroom.build.campus == campu}">
                                            <option value="${campu.campusId}" selected="true">${campu.campusName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${campu.campusId}">${campu.campusName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Building</label>
                        <div class="col-md-9">
                            <select name="build" id="build" class="form-control">
                                <option value="">Select</option>
                                <c:forEach var="campusBuild" items="${campusBuilds}">
                                    <c:choose>
                                        <c:when test="${classroom.build == campusBuild}">
                                            <option value="${campusBuild.buildsPK.buildId}" selected="true">${campusBuild.buildName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${campusBuild.buildsPK.buildId}">${campusBuild.buildName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
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
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/classroomEditScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>