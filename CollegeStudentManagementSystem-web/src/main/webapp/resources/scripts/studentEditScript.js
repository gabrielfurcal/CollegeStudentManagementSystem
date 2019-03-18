/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let map;
let myMarker;
let placeSearch;
let autocomplete;
let place;
let componentForm = {
    addressStreetAndNumber: 'addressStreetAndNumber',
    addressNeighborhood: 'addressNeighborhood',
    addressSector: 'addressSector',
    addressMunicipality: 'addressMunicipality',
    addressProvince: 'addressProvince',
};

$(document).ready(() => {
    $('input[name="telephones"]').mask("(000) 000-0000");
    $('#studentId').mask("0000");
    
    $('#addressBrowser').attr("disabled", "true");
});

function enableAddressBrowser() {
    let inputState = $('#enableBrowser').val();
    
    if(inputState === "Enable") {
        $('#addressBrowser').removeAttr("disabled");
        $('#enableBrowser').val("Disable");
    } else {
        $('#addressBrowser').attr("disabled", "true");
        $('#enableBrowser').val("Enable");
    }
}

function createUsername(e) {
    if ($('#studentId').val() == "") {
        $('#userUsername').val("");
    } else {
        $('#userUsername').val(e.toString() + $('#studentId').val());
    }
}

$('#addTelephone').click(function () {
    let counterTelephoneInputs = document.getElementsByName('telephones').length;
    let labelCount = document.getElementsByName('telephones').length + 1;

    let telephoneComponent = '<div class="form-group row" id="divTelephone' + counterTelephoneInputs.toString() + '"><label class="col-lg-3 col-md-3 label-control" for="telephone' + counterTelephoneInputs.toString() + '">Telephone ' + labelCount.toString() + '</label><div class="col-xl-7 col-md-6"><select class="form-control select-telephone" id="selectTelephone' + counterTelephoneInputs.toString() + '" style="float: left; margin-right: 5px;"></select><input type="text" class="form-control" id="telephone' + counterTelephoneInputs.toString() + '" name="telephones"/></div><div class="col-xl-2 col-md-3"><input type="button" style="width:100%;" id="btnDeleteTelephone' + counterTelephoneInputs.toString() + '" onclick="deleteTelephoneNoId(\'divTelephone' + counterTelephoneInputs.toString() + '\')" value="Delete" class="btn btn-danger pull-right"/></div></div>';

    $('#telephones_form').append(telephoneComponent);

    let optionsForOthersSelects = '';

    $('#selectTelephone0 > option').each(function () {
        optionsForOthersSelects += '<option value="' + this.value + '">' + this.text + '</option>';
    });

    $('#selectTelephone' + counterTelephoneInputs.toString()).append(optionsForOthersSelects);

    $('input[name="telephones"]').mask("(000) 000-0000");
});

function initMap() {
    let latitude = 0;
    let longitude = 0;

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 18
    });

    myMarker = new google.maps.Marker({
        map: map,
        draggable: true
    });
    
    if($('#addressLatitude').val() == "" && $('#addressLongitude').val() == "") {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                latitude = position.coords.latitude;
                longitude = position.coords.longitude;
                myMarker.setPosition({lat: latitude, lng: longitude});
                map.setCenter(myMarker.position);
                $('#addressLatitude').val(latitude);
                $('#addressLongitude').val(longitude);
            });
        }
    } else {
        latitude = +$('#addressLatitude').val();
        longitude = +$('#addressLongitude').val();

        myMarker.setPosition({lat: latitude, lng: longitude});
        map.setCenter(myMarker.position);
    }
    
    google.maps.event.addListener(myMarker, 'dragend', function (evt) {
        $('#addressLatitude').val(evt.latLng.lat());
        $('#addressLongitude').val(evt.latLng.lng());

        map.setCenter(myMarker.position);
        myMarker.setMap(map);
    });
}

function initAutocomplete() {
    initMap();

    autocomplete = new google.maps.places.Autocomplete((document.getElementById('addressBrowser')), {types: ['geocode']});

    autocomplete.addListener('place_changed', function () {
        place = autocomplete.getPlace();

        if (place.geometry !== undefined) {
            console.log(place);

            myMarker.setPosition({lat: place.geometry.location.lat(), lng: place.geometry.location.lng()});
            map.setCenter(myMarker.position);
            $('#addressLatitude').val(place.geometry.location.lat());
            $('#addressLongitude').val(place.geometry.location.lng());
            
            let streetAndNumber = '';
            let neighborhood = '';
            let sector = '';
            let municipality = '';
            let province = '';
            
            for (let component in componentForm) {
                document.getElementById(component).value = '';
            }
            
            let existAdministrativeAreaLevel= false;
            
            for(let i = 0; i < place.address_components.length; i++) {
                if(place.address_components[i].types[0] == 'administrative_area_level_2')
                    existAdministrativeAreaLevel = true;
            }
            
            for(let i = 0; i < place.address_components.length; i++) {
                if(place.address_components[i].types[0] == 'street_number') {
                    streetAndNumber+= streetAndNumber === '' ? place.address_components[i].long_name : ' ' + place.address_components[i].long_name;
                } 
                
                if(place.address_components[i].types[0] == 'route') {
                    if(streetAndNumber === '') {
                        streetAndNumber = place.address_components[i].long_name;
                    } else {
                        let previousValueOf = streetAndNumber;
                        streetAndNumber = place.address_components[i].long_name + ' ' + previousValueOf;
                    }
                }
                
                if(place.address_components[i].types[0] == 'neighborhood') {
                    neighborhood = place.address_components[i].long_name;
                }
                
                if(existAdministrativeAreaLevel) {
                    if(place.address_components[i].types[0] == 'locality') {
                    sector = place.address_components[i].long_name;
                    }

                    if(place.address_components[i].types[0] == 'administrative_area_level_2') {
                        municipality = place.address_components[i].long_name;
                    }

                    if(place.address_components[i].types[0] == 'administrative_area_level_1') {
                        province = place.address_components[i].long_name; 
                    }
                } else {
                    if(place.address_components[i].types[0] == 'sublocality_level_1') {
                    sector = place.address_components[i].long_name;
                    }

                    if(place.address_components[i].types[0] == 'locality') {
                        municipality = place.address_components[i].long_name;
                    }

                    if(place.address_components[i].types[0] == 'administrative_area_level_1') {
                        province = place.address_components[i].long_name; 
                    }
                }
            }
           
            $('#addressStreetAndNumber').val(streetAndNumber);
            $('#addressNeighborhood').val(neighborhood);
            $('#addressSector').val(sector);
            $('#addressMunicipality').val(municipality);
            $('#addressProvince').val(province);
        }
    });
}

function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            const geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            const circle = new google.maps.Circle({
                center: geolocation,
                radius: position.coords.accuracy
            });
            autocomplete.setBounds(circle.getBounds());
        });
    }
}

function deleteTelephone(parentDiv, telephoneId, url) {
    let action = confirm("Are you sure want to delete this telephone?");
    
    if(action) {
        $.ajax({
            url: url,
            type: 'POST',
            data: { 'id' : telephoneId },
            success: function(data) {
                console.log(data);
                if(data.toString() === "Ok") {
                    $('#' + parentDiv).remove();
                    alert("Telephone successfully removed")
                } else {
                    alert("Could not delete telephone");
                }

            },
            error: function(response, exception) {
                alert("Could not delete telephone");
            }
        });
    }
}

function deleteTelephoneNoId(parentDiv) {
    $('#' + parentDiv).remove();
}

$('#btnSubmit').click(function() {
    let telephoneInputs = document.getElementsByName('telephones').length;
    
    for (let i = 0; i < telephoneInputs; i++) {
        $('#telephone' + i.toString()).val($('#telephone' + i.toString()).val() + "|" + $('#selectTelephone' + i.toString()).val() + "|" + ($('#hiddenTelephoneId' + i.toString()).val() === undefined ? "" : $('#hiddenTelephoneId' + i.toString()).val()));
    }
    
    $('#frmEditStudent').submit();
});