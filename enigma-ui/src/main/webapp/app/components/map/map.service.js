/**
 * Created by Razvan.Simion on 4/22/2016.
 */
(function () {
    'use strict';
    angular
        .module('enigmaUiApp')
        .factory('MapService', MapService);

    MapService.$inject = ['$window', '$q', '$http'];

    function MapService($window, $q, $http) {
        var service = {
            initMap: initMap,
            getData: getData,
            getDirections: getDirections,
            addMarker: addMarker
        };
        return service;

        function toggleBounceMarker(marker, startToogle) {
            if (startToogle) {
                marker.setAnimation(google.maps.Animation.BOUNCE);

                //OPTIONAL - DEBOUNCE
                window.setTimeout(function () {
                    if (marker.getAnimation() !== null) {
                        marker.setAnimation(null);
                    }
                }, 3000);
            } else {
                if (marker.getAnimation() !== null) {
                    marker.setAnimation(null);
                }
            }
        }

        function addMarker(map, point) {
            if (point.lat != 0) {
                var marker = new google.maps.Marker({
                    position: {lat: point.lat, lng: point.lng},
                    map: map,
                    title: 'Click to zoom'
                });

                // IMPORTANT
                /*marker.addListener('mouseover', function () {
                 toggleBounceMarker(marker, true);
                 });

                 marker.addListener('mouseout', function () {
                 toggleBounceMarker(marker, false);
                 });*/
                marker.addListener('click', function () {
                    toggleBounceMarker(marker, true);
                    var infoWindow = new google.maps.InfoWindow({
                        content: point.name
                    });
                    infoWindow.open(map, marker);

                    window.setTimeout(function () {
                        infoWindow.close();
                    }, 3000);


                    console.log(point);
                    getDirections(map, point);
                    // map.setZoom(8);
                    //map.setCenter(marker.getPosition());
                });
            }
        }

        function getDirections(map, point) {
            /*var roadsKey = 'AIzaSyDLwOP7VdgWY5UWw2KsBz6kxzP5zEcXNyo';
             var directionsKey = 'AIzaSyDLwOP7VdgWY5UWw2KsBz6kxzP5zEcXNyo';*/
            var directionsService = new google.maps.DirectionsService;

            if (point.roads.length > 0) {
                angular.forEach(point.roads, function (road, position) {
                    calculateAndDisplayRoute(map, directionsService, point, road);
                });
            }
        }

        function calculateAndDisplayRoute(map, directionsService, point, road) {
            var waypts = [];

            var directionsDisplay = new google.maps.DirectionsRenderer(
                {
                    polylineOptions: {
                        strokeColor: "red"
                    }
                }
            );
            directionsDisplay.setMap(map);
            directionsDisplay.setPanel(document.getElementById('panel'));

            var directionsDisplay2 = new google.maps.DirectionsRenderer(
                {
                    polylineOptions: {
                        strokeColor: "blue"
                    }
                }
            );
            directionsDisplay2.setMap(map);
            directionsDisplay2.setPanel(document.getElementById('panel2'));
            //Trebuie n directiondisplays ca sa punem mai multe rute in harta.
            var request1 = {
                origin: new google.maps.LatLng(point.lat, point.lng),
                /*waypoints: [
                 {location:new google.maps.LatLng(point.lat,point.lng),stopover: true},
                 {location:new google.maps.LatLng(44.434593, 26.088251),stopover: true},
                 {location:new google.maps.LatLng(point.lat,point.lng),stopover: true}
                 ],*/
                destination: new google.maps.LatLng(44.418726, 26.080223),
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };

            directionsService.route(request1, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(response);
                }
            });


            var request2 = {
                origin: new google.maps.LatLng(point.lat, point.lng),
                /*waypoints: [
                 {location:new google.maps.LatLng(point.lat,point.lng),stopover: true},
                 {location:new google.maps.LatLng(44.434593, 26.088251),stopover: true},
                 {location:new google.maps.LatLng(point.lat,point.lng),stopover: true}
                 ],*/
                destination: new google.maps.LatLng(44.434593, 26.088251),
                travelMode: google.maps.DirectionsTravelMode.DRIVING
            };

            directionsService.route(request2, function (response, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay2.setDirections(response);
                }
            });

            /*waypts.push({
             location: new google.maps.LatLng(point.lat,point.lng),
             stopover: true
             });
             waypts.push({
             location: new google.maps.LatLng(item.lat,item.lng),
             stopover: true
             });

             directionsService.route({
             origin: new google.maps.LatLng(point.lat,point.lng),
             destination: new google.maps.LatLng(item.lat,item.lng),
             waypoints: waypts,
             optimizeWaypoints: true,
             travelMode: google.maps.TravelMode.DRIVING
             }, function (response, status) {
             if (status === google.maps.DirectionsStatus.OK) {
             directionsDisplay.setDirections(response);
             var route = response.routes[0];
             var summaryPanel = document.getElementById('directions-panel');
             summaryPanel.innerHTML = '';
             // For each route, display summary information.
             for (var i = 0; i < route.legs.length; i++) {
             var routeSegment = i + 1;
             summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment +
             '</b><br>';
             summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
             summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
             summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
             }
             } else {
             window.alert('Directions request failed due to ' + status);
             }
             });*/
        }

        function getData() {
            var resourceUrl = 'enigmagraph/api/points';

            return $http.get(resourceUrl);

            /*
             $resource(resourceUrl, {}, {
             'query': { method: 'GET', isArray: true},
             'get': {
             method: 'GET',
             transformResponse: function (data) {
             data = angular.fromJson(data);
             console.log("GRAPH",data);
             return data;
             }
             },
             'update': { method:'PUT' }
             });*/
        }


        function initMap() {
            //Google's url for async maps initialization accepting callback function
            var asyncUrl = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyCVogH2pJ03GsYsR8px1DTHhUIdp8ZtTbE&libraries=visualization&callback=',
                mapsDefer = $q.defer();

            //Callback function - resolving promise after maps successfully loaded
            $window.googleMapsInitialized = mapsDefer.resolve; // removed ()

            //Async loader
            var asyncLoad = function (asyncUrl, callbackName) {
                var script = document.createElement('script');
                script.src = asyncUrl + callbackName;
                document.body.appendChild(script);
            };
            //Start loading google maps
            asyncLoad(asyncUrl, 'googleMapsInitialized');

            //Usage: Initializer.mapsInitialized.then(callback)
            return mapsDefer.promise;
        }

    }
})
();
