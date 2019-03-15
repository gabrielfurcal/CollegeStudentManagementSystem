<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Course sections" pageName="View course sections">
    <jsp:attribute name="content">
        <form class="form form-horizontal" id="frmCoursesSectionsIndex">
            <div class="alert alert-danger" id="errorMessageBox">
                An error was occurred trying to retrieve course
            </div>
            
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            
            <h4 class="form-section">Find course</h4>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="courseIdFind" id="courseIdFind" placeholder="Type a course code"/>
                        </div>
                        <div class="col-md-3">
                            <input type="button" id="btnFind" value="Find" class="btn btn-default"/>
                        </div>
                    </div>
                </div>
            </div>
            
            <input type="hidden" id="courseId" name="courseId" />
            
            <div id="courseInfo">
                <h4 class="form-section"><i class="ft-user"></i> Course info</h4>
                
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-md-3 label-control" for="courseCode">Code</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="courseCode" id="courseCode" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 label-control" for="courseName">Name</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="courseName" id="courseName" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group row" style="margin-bottom: 0px;">
                            <label class="col-md-3 label-control" for="coursePrice">Price</label>
                            <div class="col-md-9">
                                <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">US$</div>
                                    </div>
                                    <input type="text" class="form-control" name="coursePrice" id="coursePrice" readonly="readonly"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-md-3 label-control" for="courseAmountHours">Hours</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="courseAmountHours" id="courseAmountHours" readonly="readonly"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-md-3 label-control" for="courseCreationDate">Creation date</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" name="courseCreationDate" id="courseCreationDate" readonly="readonly"/>
                            </div>
                        </div>
                    </div>   
                </div>
                
                <h5 class="form-section">Course sections</h5>
                
                <div class="row">
                    <div class="col-md-12">
                        <a class="btn btn-primary pull-right" id="createButton">Create new</a>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-12">
                        <table id="tableCoursesSections" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCoursesSections_info" role="grid">
                            <thead>
                                <tr>
                                    <th>Number</th>
                                    <th>Day</th>
                                    <th>Schedule</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody id="tableCousesSectionsBody">
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>