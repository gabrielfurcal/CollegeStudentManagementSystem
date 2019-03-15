/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(() => {
   $('#errorMessageBox').attr("hidden", "true");
   $('#courseInfo').attr("hidden", "true");
   $('#tableCoursesSections').DataTable();
});

$('#btnFind').click(() => {
   $.ajax({
        url: $('#contextPath').val() + '/Courses/Find?courseId=' + $('#courseIdFind').val(),
        type: 'GET',
        success: function(response) {
            $('#courseCode').val(response.courseId);
            $('#courseName').val(response.courseName);
            $('#coursePrice').val(response.coursePrice);
            $('#courseAmountHours').val(response.courseAmountHours);
            $('#courseCreationDate').val(response.courseCreationDate);
            $('#courseId').val(response.courseId);

            $('#createButton').attr("href", $('#contextPath').val() + "/CoursesSections/Create?id=" + response.courseId);

            $('#courseInfo').removeAttr("hidden");
            $('#errorMessageBox').attr("hidden", "hidden");
            
            $('#tableCoursesSections').DataTable().clear();
            $('#tableCoursesSections').DataTable().destroy();
            
            $.each(response.coursesSections, function (index, value) {
                $('#tableCousesSectionsBody').append(`<tr>
                                                        <td>${value.coursesSectionsPK.courseSectionId}</td>
                                                        <td>${toFullNameDayOfWeek(value.courseSectionDay)}</td>
                                                        <td>${value.courseSectionStartHour + " - " + value.courseSectionEndHour}</td>
                                                        <td>
                                                            <a href="${$('#contextPath').val()}/CoursesSections/Edit?id=${value.coursesSectionsPK.courseSectionId}">Edit</a> | 
                                                            <a href="${$('#contextPath').val()}/CoursesSections/Details?id=${value.coursesSectionsPK.courseSectionId}">Details</a> | 
                                                            <a href="${$('#contextPath').val()}/CoursesSections/Historical?id=${value.coursesSectionsPK.courseSectionId}">History</a> | 
                                                            <a href="${$('#contextPath').val()}/CoursesSections/Participants?id=${value.coursesSectionsPK.courseSectionId}">Participants</a> 
                                                        </td>
                                                      </tr>`);
                });
                
            $('#tableCoursesSections').DataTable();
      },
      error: function(data, exception) {
            $('#errorMessageBox').removeAttr("hidden");
            $('#courseInfo').attr("hidden", "hidden");
      }
   }); 
});

function toFullNameDayOfWeek(abbreviation) {
    if(abbreviation == null || abbreviation === "" || abbreviation == undefined)
        return null;
        
    switch(abbreviation.toUpperCase())
    {
        case "MON":
            return "Monday";
        case "TUE":
            return "Tuesday";
        case "WED":
            return "Wednesday";
        case "THU":
            return "Thursday";
        case "FRI":
            return "Friday";
        case "SAT":
            return "Saturday";
        case "SUN":
            return "Sunday";
        default:
            return "Day abbreviation does not exist";
    }
}