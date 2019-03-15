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

$('#btnSubmit').click(() => {
   $('#frmEditCampus').submit();
});