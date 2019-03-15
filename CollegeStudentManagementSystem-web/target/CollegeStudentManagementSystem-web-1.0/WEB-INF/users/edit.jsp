<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="models.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Edit user" pageName="Edit user">
    <jsp:attribute name="content">
        <c:set var="telephoneTypes" scope="request" value='${requestScope["telephoneTypes"]}'/>
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="user" scope="request" value='${requestScope["user"]}'/>
        <c:set var="email" scope="request" value='${requestScope["email"]}'/>
        <c:set var="telephones" scope="request" value='${requestScope["telephones"]}'/>
        <c:set var="roles" scope="request" value='${requestScope["roles"]}'/>
        <c:set var="definedRoles" scope="request" value='${requestScope["definedRoles"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Users/Edit" class="form form-horizontal" id="frmEditUser">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save teacher
                </div>
            </c:if>
            
            <input type="hidden" name="userId" id="userId" value="${user.userId}" />
            
            <div class="row">
                <div class="col-md-6">
                    <h4 class="form-section">Personal info</h4>  
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userFirstName">First name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userFirstName" value="${user.userFirstName}" id="userFirstName" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userFatherLastName">Father last name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userFatherLastName" value="${user.userFatherLastName}" name="userFatherLastName" id="userFatherLastName" />
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userMotherLastName">Mother last name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userMotherLastName" value="${user.userMotherLastName}"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userUsername">Username</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userUsername" id="userUsername" value="${user.userUsername}"/> 
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userPassword">New password</label>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-10">
                                    <input type="text" class="form-control" name="userPassword" id="userPassword" />
                                </div>
                                <div class="col-md-2">
                                    <input type="button" class="btn btn-default pull-right" id="newPasswordSetter" onclick="allowNewPasswordField()" value="Set"/>
                                </div>
                            </div>
                            
                        </div>
                    </div> 
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="userEmail">Email</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="userEmail" value="${email}"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                     <h4 class="form-section">Telephones</h4>
                    <div id="telephones_form">
                        <c:forEach items="${telephones}" var="telephone" varStatus="index">
                            <div class="form-group row" id="divTelephone${index.count - 1}">
                                <label class="col-lg-3 col-md-3 label-control" for="telephone${index.count - 1}">Telephone ${index.count}</label>
                                <div class="col-xl-7 col-md-6">
                                    <select class="form-control select-telephone" id="selectTelephone${index.count - 1}" style="float: left; margin-right: 5px;">
                                        <option value="">Select</option>
                                        <c:forEach var="telephoneType" items="${telephoneTypes}">
                                            <c:choose>
                                                <c:when test="${telephoneType.telephoneTypeId.toString().equals(telephone.telephoneType.telephoneTypeId.toString())}">
                                                    <option value="${telephoneType.telephoneTypeId.toString()}" selected="true">${telephoneType.telephoneTypeName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${telephoneType.telephoneTypeId.toString()}">${telephoneType.telephoneTypeName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <input type="text" class="form-control " id="telephone${index.count - 1}" name="telephones" value="${telephone.telephoneNumber}"/>
                                    <input type="hidden" id="hiddenTelephoneId${index.count - 1}" name="telephoneId" value="${telephone.telephoneId.toString()}"/>
                                </div>
                                <div class="col-xl-2 col-md-3">
                                    <input type="button"  style="width:100%;" id="btnDeleteTelephone${index.count - 1}" onclick="deleteTelephone('divTelephone${index.count - 1}', ${telephone.telephoneId.toString()}, '${pageContext.servletContext.contextPath}/Telephones/Delete')" value="Delete"  class="btn btn-danger pull-right"/>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="form-group row">
                        <div class="col-md-9 col-lg-9 col-xl-10"></div>
                          <div class="col-md-3 col-lg-3 col-xl-2" style="text-align: right;">
                            <input type="button" id="addTelephone" value="+ Add" class="btn btn-default pull-right" style="width: 100%;"/>
                        </div>
                    </div>
                </div>
            </div>
                     
            <div class="row">
                <div class="col-md-6">
                    <h4 class="form-section">Address info</h4>
                    
                    <div class="form-group row">
                        <input type="hidden" name="addressLatitude" id="addressLatitude" value="${user.address.addressLatitude}"/>
                        <input type="hidden" name="addressLongitude" id="addressLongitude" value="${user.address.addressLongitude}"/>
                        <label class="col-md-3 label-control">Search in map</label>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-10">
                                    <input type="text" class="form-control" id="addressBrowser" onfocus="geolocate()" placeholder="Enter an address"/>
                                </div>
                                <div class="col-md-2">
                                    <input type="button" class="btn btn-default pull-right" id="enableBrowser" onclick="enableAddressBrowser()" value="Enable"/>
                                </div>
                            </div>
                            <div class="form-group row"></div>
                            <div id="map"></div>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressStreetAndNumber">Street and number</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="addressStreetAndNumber" id="addressStreetAndNumber" rows="5" style="width: 100%;">${user.address.addressStreetAndNumber}</textarea>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressNeighborhood">Neighborhood</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressNeighborhood" id="addressNeighborhood" value="${user.address.addressNeighborhood}"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressSector">Sector</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressSector" id="addressSector" value="${user.address.addressSector}"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressMunicipality">Municipality</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressMunicipality" id="addressMunicipality" value="${user.address.addressMunicipality}"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressProvince">Province</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressProvince" id="addressProvince" value="${user.address.addressProvince}"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4 class="form-section">Work info</h4>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="position">Position</label>
                        <div class="col-md-9">
                            <select class="form-control" id="position" name="position">
                                <option value="">Select</option>
                                <c:forEach var="position" items="${positions}">
                                    <c:choose>
                                        <c:when test="${position == user.position}">
                                            <option value="${position.positionId.toString()}" selected="true">${position.positionName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${position.positionId.toString()}">${position.positionName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="col-md-3 label-control">Roles</label>
                        <div class="col-md-9">
                            <table id="tableRolesInUserEdit" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableRolesInUserEdit_info" role="grid">
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
                                            <c:choose>
                                                <c:when test="${definedRoles.contains(role)}">
                                                    <td><input type="checkbox" value="${role.roleId}" name="rolesSelected" checked="checked"/></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><input type="checkbox" value="${role.roleId}" name="rolesSelected"/></td>
                                                </c:otherwise>
                                            </c:choose>
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
                    <input type="button" id="btnSubmit" value="Save" class="btn btn-success"/>
                </div>
            </div> 
        </form>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/jquery.mask.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/userEditScript.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMMDeX_IkhAKnk549y9aZ0L4Kvh6dOB6o&libraries=places&callback=initAutocomplete" async defer></script>
    </jsp:attribute>
</mt:mastertemplate>