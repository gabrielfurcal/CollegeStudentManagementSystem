/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableBuilds').DataTable();
});

function deleteBuilds(buildId, url) {
    if(buildId === "" || buildId == null || buildId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this build?");

    if(action) {
        $.ajax({
            url: url + "?buildId=" + buildId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Build deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete build");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete build");
            }
        });
    }
}