/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    let currentCourseSectionHistoricalId = $('#currentCourseSectionHistoricalId').val();
    loadCourseSectionHistoricalStudents(currentCourseSectionHistoricalId);
});

$('#courseSectionHistorical').change(() => {
    let selectedCourseSectionHistorical = $('#courseSectionHistorical').val();
    loadCourseSectionHistoricalStudents(selectedCourseSectionHistorical);
});

function loadCourseSectionHistoricalStudents(courseSectionHistoricalId) {
    if (courseSectionHistoricalId !== "") {
        $.ajax({
            url: $('#contextPath').val() + "/CoursesSections/Participants/All?id=" + courseSectionHistoricalId,
            type: 'GET',
            success: function(response) {
                $('#errorMessageBox').attr("hidden", "hidden");
                
                $('#tableCoursesParticipants').DataTable().clear();
                $('#tableCoursesParticipants').DataTable().destroy();

                $('#tableCoursesParticipantsBody').html("");

                $.each(response, function(index, value) {
                    let actionsHtml = "";

                    if($('#courseSectionHistorical option[value="' + courseSectionHistoricalId + '"]').text() === $('#currentPeriodYear').val() + " - " + $('#currentPeriodQuarter').val()) {
                        actionsHtml = `<a href="${$('#contextPath').val()}/Students/Edit?id=${value.studentsPK.studentId}">Edit</a> |  
                                       <a href="#" onclick="unlinkStudent('${$('#contextPath').val()}/ClassParticipants/Students/Unlink?studentId=${value.studentsPK.studentId}&courseSectionHistoricalId=${courseSectionHistoricalId}')">Unlink</a>`;
                    }

                    $('#tableCoursesParticipantsBody').append(` <tr>
                                                                    <td>
                                                                        ${value.studentsPK.studentId}
                                                                    </td> 
                                                                    <td>
                                                                        ${value.user.userFirstName} ${value.user.userFatherLastName} ${value.user.userMotherLastName} 
                                                                    </td>
                                                                    <td>
                                                                         ${actionsHtml}
                                                                    </td> 
                                                                </tr>`);
                });
                
                $('#tableCoursesParticipants').DataTable();
                
                $('#courseParticipants').removeAttr('hidden', 'hidden');
            },
            error: function(data, exception) {
                $('#errorMessageBox').removeAttr("hidden");
                $('#courseParticipants').attr("hidden", "hidden");
            }
        });
    } else {
        $('#courseParticipants').attr("hidden", "hidden");
    }
}

function unlinkStudent(url) {
    let confirmation = confirm("Are you sure want to unlink the student from this class?");

    if(confirmation) {
        if (url != "") {
            $.ajax({
                url: url,
                type: 'GET',
                success: function(response) {
                    if(response == "success") {
                        alert("User unlinked successfully");
                    } else {
                        alert("Han error has been occurred trying to unlink student");
                    }
                },
                error: function (data, exception) {
                    alert("Han error has been occurred trying to unlink student");
                }
            })
        }
    }
}