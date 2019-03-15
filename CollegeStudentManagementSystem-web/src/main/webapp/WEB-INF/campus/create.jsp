<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags" %>

<mt:mastertemplate title="Create campus" pageName="New campus"> 
    <jsp:attribute name="content">
        <c:set var="isSuccess" scope="request" value='${requestScope["isSuccess"]}'/>
        <c:set var="roles" scope="request" value='${requestScope["roles"]}'/>
        
        <form method="POST" action="${pageContext.servletContext.contextPath}/Campus/Create" class="form form-horizontal" id="frmCreateCampus">
            <c:if test="${isSuccess != null}">
                <div class="alert alert-danger">
                    An error was occurred trying to create campus
                </div>
            </c:if>      
            <h4 class="form-section">Campus info</h4>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-md-3 label-control" for="campusName">Name</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" name="campusName" id="campusName" />
                        </div>
                    </div>
                </div>
            </div>
            
            <h4 class="form-section">Address info</h4>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <input type="hidden" name="addressLatitude" id="addressLatitude"/>
                        <input type="hidden" name="addressLongitude" id="addressLongitude"/>
                        <label class="col-md-3 label-control">Search in map</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="addressBrowser" onfocus="geolocate()" placeholder="Enter an address"/>
                            <div id="map"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
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
            </div>
            
            <div class="form-group row">
                <div class="col-md-12" style="text-align: right;">
                    <input type="button" id="btnSubmit" value="Create" class="btn btn-success"/>
                </div>
            </div>    
        </form>
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMMDeX_IkhAKnk549y9aZ0L4Kvh6dOB6o&libraries=places&callback=initAutocomplete" async defer></script>
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/campusCreateScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>