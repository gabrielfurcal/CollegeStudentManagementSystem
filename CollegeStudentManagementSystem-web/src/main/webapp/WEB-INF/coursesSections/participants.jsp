<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>            

<mt:mastertemplate title="Class participants" pageName="Class participants">
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="currentCourseSectionHistoricalId" scope="request" value='${requestScope["currentCourseSectionHistoricalId"]}'/>
        <c:set var="courseSectionsHistorical" scope="request" value='${requestScope["courseSectionsHistorical"]}'/>
        <c:set var="currentPeriod" scope="request" value='${requestScope["currentPeriod"]}'/>

        <form class="form form-horizontal" id="frmCoursesSectionsParticipants">
            <div class="alert alert-danger" id="errorMessageBox">
                An error was occurred trying to retrieve course
            </div>
            
            <input type="hidden" id="contextPath" value="${pageContext.servletContext.contextPath}"/>
            <input type="hidden" id="currentCourseSectionHistoricalId" value="${currentCourseSectionHistoricalId}"/>
            <input type="hidden" id="currentPeriodYear" value="${currentPeriod[0]}"/>
            <input type="hidden" id="currentPeriodQuarter" value="${currentPeriod[1]}"/>

            <h4 class="form-section">Choose record</h4>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="courseCode">History record</label>
                        <div class="col-md-9">
                            <select name="courseSectionHistorical" id="courseSectionHistorical" class="form-control">
                                <option value="">Select</option>
                                <c:forEach var="courseSectionHistorical" items="${courseSectionsHistorical}">
                                    <c:choose>
                                        <c:when test="${courseSectionHistorical.courseSectHistId == currentCourseSectionHistoricalId}">
                                            <option value="${courseSectionHistorical.courseSectHistId}" selected="true">${courseSectionHistorical.period.periodsPK.periodYear} - ${courseSectionHistorical.period.periodsPK.periodQuarter}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${courseSectionHistorical.courseSectHistId}">${courseSectionHistorical.period.periodsPK.periodYear} - ${courseSectionHistorical.period.periodsPK.periodQuarter}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="courseParticipants">
                <h4 class="form-section">Course section participants</h4>

                <div class="row">
                    <div class="col-md-12">
                        <table id="tableCoursesParticipants" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableCoursesParticipants_info" role="grid">
                            <thead>
                                <tr>
                                    <th>Enrollment</th>
                                    <th>Fullname</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody id="tableCoursesParticipantsBody">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/courseSectionParticipantsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>