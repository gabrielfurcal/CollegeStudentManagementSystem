<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Create classroom" pageName="New classroom"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="classroom" scope="request" value='${requestScope["classroom"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Classrooms/Create" class="form form-horizontal" id="frmCreateClassroom">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create classroom
                </div>
            </c:if>      
            
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Classroom info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="classroomName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="classroomName" id="classroomName" />
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <select name="campus" id="campus" class="form-control">
                                <option value="" selected="true">Select</option>
                                <c:forEach var="campu" items="${campus}">
                                    <option value="${campu.campusId.toString()}">${campu.campusName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div> 
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="build">Building</label>
                        <div class="col-md-9">
                            <select name="build" id="build" class="form-control">
                                <option value="" selected="true">Select</option>
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
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/classroomCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>