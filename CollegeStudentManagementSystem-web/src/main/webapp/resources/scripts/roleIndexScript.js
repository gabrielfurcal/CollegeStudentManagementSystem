/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableRoles').DataTable();
});

function deleteRole(roleId, url) {
    if(roleId === "" || roleId == null || roleId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this role?");

    if(action) {
        $.ajax({
            url: url + "?roleId=" + roleId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Role deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete role");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete role");
            }
        });
    }
}