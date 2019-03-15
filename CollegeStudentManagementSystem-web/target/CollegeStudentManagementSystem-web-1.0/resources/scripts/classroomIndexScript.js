/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableClassrooms').DataTable();
});

function deleteClassrooms(classroomId, url) {
    if(classroomId === "" || classroomId == null || classroomId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this classroom?");

    if(action) {
        $.ajax({
            url: url + "?classroomId=" + classroomId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Classroom deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete classroom");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete classroom");
            }
        });
    }
}