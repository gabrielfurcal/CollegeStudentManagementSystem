<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Create user" pageName="New user">  
    <jsp:attribute name="content">
        <c:set var="telephoneTypes" scope="request" value='${requestScope["telephoneTypes"]}'/>
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="roles" scope="request" value='${requestScope["roles"]}'/>
        <c:set var="positions" scope="request" value='${requestScope["positions"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Users/Create" class="form form-horizontal" id="frmCreateUser">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create user
                </div>
            </c:if>      
            <div class="row">
                <div class="col-md-6">
                    <h4 class="form-section">Personal info</h4>  
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userFirstName">First name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userFirstName" id="userFirstName" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userFatherLastName">Father last name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userFatherLastName" id="userFatherLastName" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userMotherLastName">Mother last name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userMotherLastName"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userUsername">Username</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userUsername" id="userUsername"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userPassword">Password</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userPassword" id="userPassword"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userEmail">Email</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userEmail"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4 class="form-section">Telephones</h4>
                    
                    <div id="telephones_form">
                        <div class="form-group row">
                            <label class="col-lg-3 col-md-3 label-control" for="telephone0">Telephone 1</label>
                            <div class="col-xl-9 col-md-9">
                                <select class="form-control select-telephone" id="selectTelephone0" style="float: left; margin-right: 5px;">
                                    <option value="" selected="true">Select</option>
                                    <c:forEach var="telephoneType" items="${telephoneTypes}">
                                        <option value="${telephoneType.telephoneTypeId.toString()}">${telephoneType.telephoneTypeName}</option>
                                    </c:forEach>
                                </select>
                                <input type="text" class="form-control" id="telephone0" name="telephones" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-md-9 col-lg-9 col-xl-10"></div>
                         <div class="col-md-3 col-lg-3 col-xl-2" style="text-align: right;">
                            <input type="button" id="addTelephone" value="+ Add" class="btn btn-default pull-right"  style="width: 100%;" />
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-6">
                    <h4 class="form-section">Address info</h4>
                    
                    <div class="form-group row">
                        <input type="hidden" name="addressLatitude" id="addressLatitude"/>
                        <input type="hidden" name="addressLongitude" id="addressLongitude"/>
                        <label class="col-md-3 label-control">Search in map</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="addressBrowser" onfocus="geolocate()" placeholder="Enter an address"/>
                            <div id="map"></div>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressStreetAndNumber">Street and number</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="addressStreetAndNumber" id="addressStreetAndNumber" rows="5" style="width: 100%;"></textarea>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressNeighborhood">Neighborhood</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressNeighborhood" id="addressNeighborhood"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressSector">Sector</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressSector" id="addressSector"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressMunicipality">Municipality</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressMunicipality" id="addressMunicipality"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressProvince">Province</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressProvince" id="addressProvince"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4 class="form-section">Work info</h4>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="position">Position</label>
                        <div class="col-md-9">
                            <select class="form-control" id="position" name="position">
                                <option value="" selected="true">Select</option>
                                <c:forEach var="position" items="${positions}">
                                    <option value="${position.positionId.toString()}">${position.positionName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Roles</label>
                        <div class="col-md-9">
                            <table id="tableRolesInUserCreate" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableRolesInUserCreate_info" role="grid">
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
                    <input type="button" id="btnSubmit" value="Create" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/jquery.mask.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/userCreateScript.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMMDeX_IkhAKnk549y9aZ0L4Kvh6dOB6o&libraries=places&callback=initAutocomplete" async defer></script>
    </jsp:attribute>
</mt:mastertemplate>