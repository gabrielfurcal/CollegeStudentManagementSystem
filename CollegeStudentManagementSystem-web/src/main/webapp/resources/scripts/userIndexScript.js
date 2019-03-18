/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableUsers').DataTable();
});

function deleteUser(userId, url) {
    if(userId === "" || userId == null || userId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this users?");

    if(action) {
        $.ajax({
            url: url + "?userId=" + userId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("User deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete user")
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete user");
            }
        });
    }
}