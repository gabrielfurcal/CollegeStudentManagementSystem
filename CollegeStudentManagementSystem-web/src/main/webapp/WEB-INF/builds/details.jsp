<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>    

<mt:mastertemplate title="Details building" pageName="Details building"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="build" scope="request" value='${requestScope["build"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Builds/Delete" class="form form-horizontal" id="frmDetailsBuild">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to delete building
                </div>
            </c:if>
            
            <input type="hidden" name="buildId" id="buildId" value="${build.buildsPK.buildId}"/>
            
            <h4 class="form-section">Building info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="buildName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="buildName" id="buildName" value="${build.buildName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="buildCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="buildCreationDate" id="buildCreationDate" value="${simpleDateFormat.format(build.buildCreationDate)}" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campus">Campus</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campus" id="campus" readonly="readonly" value="${build.campus.campusName}">
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Classrooms</label>
                        <div class="col-md-9">
                            <table id="tableClassroomsInBuildDetails" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableClassroomsInBuildDetails_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="classroom" items="${build.classrooms}">
                                        <tr>
                                            <td>${classroom.classroomName}</td>
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
                    <input type="button" id="btnSubmit" value="Delete" class="btn btn-danger"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/buildDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>