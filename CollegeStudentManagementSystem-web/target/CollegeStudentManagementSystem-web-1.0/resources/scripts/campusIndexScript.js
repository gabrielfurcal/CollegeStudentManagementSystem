/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableCampus').DataTable();
});

function deleteCampus(campusId, url) {
    if(campusId === "" || campusId == null || campusId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this campus?");

    if(action) {
        $.ajax({
            url: url + "?campusId=" + campusId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Campus deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete campus");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete campus");
            }
        });
    }
}