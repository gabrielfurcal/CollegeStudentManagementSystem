<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Create group" pageName="New group"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="roles" scope="request" value='${requestScope["roles"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Groups/Create" class="form form-horizontal" id="frmCreateGroup">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create group
                </div>
            </c:if>      
            <h4 class="form-section"><i class="ft-user"></i> Group info</h4>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="groupName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="groupName" id="groupName" />
                        </div>
                    </div> 
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="groupDescription">Description</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="groupDescription" id="groupDescription" rows="5" style="width: 100%;"></textarea>
                        </div>
                    </div> 
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Roles</label>
                        <div class="col-md-9">
                            <table id="tableRolesInGroupCreate" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableRolesInGroupCreate_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Select</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="role" items="${roles}">
                                        <tr>
                                            <td>${role.roleName}</td>
                                            <td>${role.roleDescription}</td>
                                            <td><input type="checkbox" value="${role.roleId}" name="rolesSelected"/></td>
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
                    <input type="submit" id="btnSubmit" value="Create" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
</mt:mastertemplate>