<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:mastertemplate title="Periods" pageName="All periods">
    <jsp:attribute name="content">
        <c:set var="isCurrentPeriodExist" scope="request" value='${requestScope["isCurrentPeriodExist"]}'/>
        <c:set var="periods" scope="request" value='${requestScope["periods"]}'/>
        <c:set var="simpleDateFormat" scope="request" value='${requestScope["simpleDateFormat"]}'/>
        
        <c:if test="${isCurrentPeriodExist != null}">
            <div class="alert alert-warning">
                The current period hasn't been created <a href="http://localhost:8081${pageContext.servletContext.contextPath}/Periods/Create" style="line-height: 0px;">Create period</a>
            </div>
        </c:if>  
        
        <table id="tablePeriods" class="table table-striped table-bordered zero-configuration" style="width:100%;" aria-describedby="tablePeriods_info" role="grid">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Quarter</th>
                    <th>Creation date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="period" items="${periods}">
                    <tr>
                        <td>${period.periodsPK.periodYear.toString()}</td>
                        <td>${period.periodsPK.periodQuarter.toString()}</td>
                        <td>${simpleDateFormat.format(period.periodCreationDate)}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script src="${pageContext.servletContext.contextPath}/resources/scripts/periodIndexScript.js"></script>
    </jsp:attribute>
</mt:mastertemplate>