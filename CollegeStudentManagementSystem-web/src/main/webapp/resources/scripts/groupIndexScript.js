/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tableGroups').DataTable();
});

function deleteGroup(groupId, url) {
    if(groupId === "" || groupId == null || groupId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this group?");

    if(action) {
        $.ajax({
            url: url + "?groupId=" + groupId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Group deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete group");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete group");
            }
        });
    }
}