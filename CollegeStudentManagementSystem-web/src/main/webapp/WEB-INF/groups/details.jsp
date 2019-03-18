<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Details group" pageName="Details group">     
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="group" scope="request" value='${requestScope["group"]}'/>
        <c:set var="definedRoles" scope="request" value='${requestScope["definedRoles"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Groups/Delete" class="form form-horizontal" id="frmDetailsGroup">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save group
                </div>
            </c:if>
            
            <input type="hidden" name="groupId" id="groupId" value="${group.groupId}"/>
            
            <h4 class="form-section"><i class="ft-user"></i> Group info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="groupName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="groupName" id="groupName" value="${group.groupName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="groupCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="groupCreationDate" id="groupCreationDate" value="${simpleDateFormat.format(group.groupCreationDate)}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="groupDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="groupDescription" id="groupDescription" rows="5" style="width: 100%;" readonly="readonly">${group.groupDescription}</textarea>
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Roles</label>
                        <div class="col-md-9">
                            <table id="tableRolesInGroupDetails" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableRolesInGroupDetails_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="role" items="${definedRoles}">
                                        <tr>
                                            <td>${role.roleName}</td>
                                            <td>${role.roleDescription}</td>
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
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/groupDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>