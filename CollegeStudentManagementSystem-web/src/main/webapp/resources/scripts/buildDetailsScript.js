/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('#btnSubmit').click(function() {
    let action = confirm("Are you sure want to delete this build?");
    
    if(action) {
        $('#frmDetailsBuild').submit();
    } else {
        return false;
    }
});