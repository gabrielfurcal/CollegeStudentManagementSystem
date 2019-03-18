/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(() => {
    let table = $('#tableCoursesSectionsHistoricalInCourseSection');

    if (table !== "undefined" && table !== null && table !== "") {
        $('#tableCoursesSectionsHistoricalInCourseSection').DataTable();
    }
});

$('#campus').change(() => {
    if($('#campus').val() !== "") {
        let requestInfo = { campus: $('#campus').val() }

        $.ajax({
            url: $('#contextPath').val() + '/Classrooms/Buildings',
            type: 'POST',
            data: requestInfo,
            contentType: 'application/x-www-form-urlencoded',
            success: function(response) {
                $('#build').html('<option value="">Select</option>');
                $('#classroom').html('<option value="">Select</option>');
                
                $.each(response, function(index, value) {    
                    $('#build').append('<option value="' + value.buildsPK.buildId + '">' + value.buildName + '</option>');
                });
            },
            error: function(data, exception) {
                alert("An error has been occurred loading buildings of this campus");
            }
        });
    } else {
        $('#build').html('<option value="">Select</option>');
        $('#classroom').html('<option value="">Select</option>');
    }
});

$('#build').change(() => {
    if($('#build').val() !== "") {
        let requestInfo = { build: $('#build').val() }

        $.ajax({
            url: $('#contextPath').val() + '/Classrooms/All',
            type: 'POST',
            data: requestInfo,
            contentType: 'application/x-www-form-urlencoded',
            success: function(response) {
                $('#classroom').html('<option value="">Select</option>');
                
                $.each(response, function(index, value) {
                    $('#classroom').append('<option value="' + value.classroomsPK.classroomId + '">' + value.classroomName + '</option>');
                });
            },
            error: function(data, exception) {
                alert("An error has been occurred loading classrooms of this build");
            }
        });
    } else {
        $('#classroom').html('<option value="">Select</option>');
    }
});