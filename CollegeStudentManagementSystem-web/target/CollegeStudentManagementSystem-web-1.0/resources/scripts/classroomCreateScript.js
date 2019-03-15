/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#campus').change(() => {
    if($('#campus').val() !== "") {
        const value = { campus : $('#campus').val() };
        
        $.ajax({
            url: $('#contextPath').val() + '/Classrooms/Buildings',
            type: 'POST',
            data: value,
            contentType: 'application/x-www-form-urlencoded',
            success: function(response) {
                $('#build').html('<option value="">Select</option>');
                
                $.each(response, function (index, value) {
                    $('#build').append('<option value="' + value.buildsPK.buildId + '">' + value.buildName + '</option>');
                });
            },
            error: function(data, exception) {
                alert("An error has been occurred loading buildings of this campus");
            }
        });
    } else {
        $('#build').html('<option value="">Select</option>');
    }
});