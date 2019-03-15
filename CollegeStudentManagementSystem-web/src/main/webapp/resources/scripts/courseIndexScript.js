/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableCourses').DataTable();
});

function deleteCourses(courseId, url) {
    if(courseId === "" || courseId == null || courseId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this course?");

    if(action) {
        $.ajax({
            url: url + "?courseId=" + courseId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Course deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete course");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete course");
            }
        });
    }
}