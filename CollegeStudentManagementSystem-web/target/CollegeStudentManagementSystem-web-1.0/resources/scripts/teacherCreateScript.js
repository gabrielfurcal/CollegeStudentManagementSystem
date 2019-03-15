/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let counterTelephoneInputs = 0;
let labelCount = 1;
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
});

function createUsername() {
    let firstName = $('#userFirstName').val();
    let fatherLastName = $('#userFatherLastName').val();
    
    let firstLetterName = '';
    
    if(firstName !== "") {
        firstLetterName = firstName.substring(0,1);
    }
    
    $('#userUsername').val(firstLetterName.toLowerCase() + fatherLastName.toLowerCase());
}

function validateUsername(url) {
    let username = $('#userUsername').val();
    
    if(username !== "") {
        $.ajax({
            url: url + '?username=' + username,
            type: 'GET',
            success: function(data) {
              if(data === "true") {
                  alert("This username already exist. Please, add another letter of the first name.");
              } else if(data === "false") {
                  alert("This username can be used.");
              } else {
                  alert("An error has been occured verifying the username.");
              }
            },
            error: function(data, exception) {
                alert("An error has been occured verifying the username.");
            }
        });
    }
}

$('#addTelephone').click(function () {
    counterTelephoneInputs++;
    labelCount++;

    let telephoneComponent = '<div class="form-group row"><label class="col-lg-3 col-md-3 label-control" for="telephone' + counterTelephoneInputs.toString() + '">Telephone ' + labelCount.toString() + '</label><div class="col-xl-9 col-md-9"><select class="form-control select-telephone" id="selectTelephone' + counterTelephoneInputs.toString() + '" style="float: left; margin-right: 5px;"></select><input type="text" class="form-control" id="telephone' + counterTelephoneInputs.toString() + '" name="telephones"/></div></div>';

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

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            myMarker.setPosition({lat: latitude, lng: longitude});
            map.setCenter(myMarker.position);
            $('#addressLatitude').val(latitude);
            $('#addressLongitude').val(longitude);
        });
    } else {
        latitude = 18.510851;
        longitude = -69.969815;

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

$('#btnSubmit').click(function() {
    let telephoneInputs = document.getElementsByName('telephones').length;
    
    for (let i = 0; i < telephoneInputs; i++) {
        $('#telephone' + i.toString()).val($('#telephone' + i.toString()).val() + "|" + $('#selectTelephone' + i.toString()).val());
    }
    
    $('#frmCreateTeacher').submit();
});