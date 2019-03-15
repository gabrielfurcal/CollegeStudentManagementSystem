/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tablePermissions').DataTable();
});

function deletePermission(permissionId, url) {
    if(permissionId === "" || permissionId == null || permissionId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this permission?");

    if(action) {
        $.ajax({
            url: url + "?permissionId=" + permissionId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Permission deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete permission");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete permission");
            }
        });
    }
}