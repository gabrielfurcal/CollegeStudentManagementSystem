/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(() => {
    $('#tablePositions').DataTable();
});

function deletePosition(positionId, url) {
    if(positionId === "" || positionId == null || positionId == undefined) {
        return false;
    }

    let action = confirm("Are you sure want to delete this position?");

    if(action) {
        $.ajax({
            url: url + "?positionId=" + positionId,
            type: 'GET',
            success: function(data) {
                if(data === "success") {
                    alert("Position deleted sucessfully");
                    location.reload();
                } else {
                    alert("An error has been occurred trying to delete position");
                }
            },
            error: function(data, exception) {
                alert("An error has been occurred trying to delete position");
            }
        });
    }
}