<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>    

<mt:mastertemplate title="Details classroom" pageName="Details classroom"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="classroom" scope="request" value='${requestScope["classroom"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Classrooms/Delete" class="form form-horizontal" id="frmDetailsClassroom">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to delete classroom
                </div>
            </c:if>
            
            <input type="hidden" name="classroomId" id="classroomId" value="${classroom.classroomsPK.classroomId}"/>
            
            <h4 class="form-section">Classroom info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroomName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroomName" id="classroomName" value="${classroom.classroomName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroomCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroomCreationDate" id="classroomCreationDate" value="${simpleDateFormat.format(classroom.classroomCreationDate)}" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campus" id="campus" readonly="readonly" value="${classroom.build.campus.campusName}">
                        </div>
                    </div>
                        
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="build">Building</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="build" id="build" readonly="readonly" value="${classroom.build.buildName}">
                        </div>
                    </div>
                </div>   
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="button" id="btnSubmit" value="Delete" class="btn btn-danger"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/classroomDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>