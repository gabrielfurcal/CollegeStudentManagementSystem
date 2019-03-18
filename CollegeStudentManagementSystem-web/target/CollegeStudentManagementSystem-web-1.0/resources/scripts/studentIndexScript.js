/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableStudents').DataTable();
});

function deleteStudent(studentId, url) {
    if(studentId === "" || studentId == null || studentId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this student?");

    if(action) {
        $.ajax({
            url: url + "?studentId=" + studentId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Student deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete student")
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete student");
            }
        });
    }
}