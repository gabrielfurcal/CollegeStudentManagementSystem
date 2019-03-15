<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Edit position" pageName="Edit position">
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="position" scope="request" value='${requestScope["position"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Positions/Edit" class="form form-horizontal" id="frmEditPosition">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save position
                </div>
            </c:if>
            
            <input type="hidden" name="positionId" id="positionId" value="${position.positionId}"/>
            
            <h4 class="form-section">Position info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="positionName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="positionName" id="positionName" value="${position.positionName}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="positionCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="positionCreationDate" id="positionCreationDate" value="${simpleDateFormat.format(position.positionCreationDate)}" disabled="true"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="positionDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="positionDescription" id="positionDescription" rows="5" style="width: 100%;">${position.positionDescription}</textarea>
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