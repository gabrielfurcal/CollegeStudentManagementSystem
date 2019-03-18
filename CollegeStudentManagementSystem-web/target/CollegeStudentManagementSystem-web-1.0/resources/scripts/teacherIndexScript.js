/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableTeachers').DataTable();
});

function deleteTeacher(teacherId, url) {
    if(teacherId === "" || teacherId == null || teacherId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this teacher?");

    if(action) {
        $.ajax({
            url: url + "?teacherId=" + teacherId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Teacher deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete teacher")
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete teacher");
            }
        });
    }
}