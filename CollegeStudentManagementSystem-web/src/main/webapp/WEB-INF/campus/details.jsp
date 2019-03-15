<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Details campus" pageName="Details campus">     
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="campus" scope="request" value='${requestScope["campus"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Campus/Delete" class="form form-horizontal" id="frmDetailsCampus">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to save campus
                </div>
            </c:if>
            
            <input type="hidden" name="campusId" id="campusId" value="${campus.campusId}"/>
            
            <h4 class="form-section">Campus info</h4>  
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campusName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campusName" id="campusName" value="${campus.campusName}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campusCreationDate">Creation date</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campusCreationDate" id="campusCreationDate" value="${simpleDateFormat.format(campus.campusCreationDate)}" readonly="readonly"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                   <div class="form-group row">
                        <label class="col-md-3 label-control">Buildings</label>
                        <div class="col-md-9">
                            <table id="tableBuildsInCampusDetails" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tableBuildsInCampusDetails_info" role="grid">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="build" items="${campus.builds}">
                                        <tr>
                                            <td>${build.buildName}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>   
            </div>
            
            <h4 class="form-section">Address info</h4>
                        
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <input type="hidden" name="addressLatitude" id="addressLatitude" value="${campus.address.addressLatitude}"/>
                        <input type="hidden" name="addressLongitude" id="addressLongitude" value="${campus.address.addressLongitude}"/>
                        <label class="col-md-3 label-control">Map location</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="addressBrowser" onfocus="geolocate()" placeholder="Enter an address" hidden="true"/>
                            <div id="map"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressStreetAndNumber">Street and number</label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="addressStreetAndNumber" id="addressStreetAndNumber" rows="5" style="width: 100%;" readonly="readonly">${campus.address.addressStreetAndNumber}</textarea>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressNeighborhood">Neighborhood</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressNeighborhood" id="addressNeighborhood" value="${campus.address.addressNeighborhood}" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressSector">Sector</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressSector" id="addressSector" value="${campus.address.addressSector}" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressMunicipality">Municipality</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressMunicipality" id="addressMunicipality" value="${campus.address.addressMunicipality}" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="addressProvince">Province</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="addressProvince" id="addressProvince" value="${campus.address.addressProvince}" readonly="readonly"/>
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
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMMDeX_IkhAKnk549y9aZ0L4Kvh6dOB6o&libraries=places&callback=initAutocomplete" async defer></script>
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/campusDetailsScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>