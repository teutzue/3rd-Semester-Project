'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', function ($http, $scope, dateFilter, $rootScope, InfoService) {

//            $http({
//                method: 'GET',
//                url: 'api/demoUser/cur'
//            }).then(function successCallback(response) {
//                $scope.data.countrycodes = response.data;
//                $scope.data1.countrycodes1 = response.data;
//            }, function errorCallback(response) {
//                $scope.error = res.status + ": " + res.data.statusText;
//            });

            $scope.showSpinner = false;
            $scope.feedBack = "";

            $scope.passengers = 1;
            $scope.dataFrom = {
                repeatSelectFrom: null,
                origins: []
            };
            // Dummy data
            $scope.dataFrom.origins = [
                {origin: "CDG", description: "Paris (CDG)"},
                {origin: "CPH", description: "Copenhagen (CPH)"},
                {origin: "STN", description: "London (STN)"},
                {origin: "SXF", description: "Berlin-Schönefeld (SXF)"}
            ];
            $scope.dataTo = {
                repeatSelectTo: null,
                destinations: []
            };
            // Dummy Data
            $scope.dataTo.destinations = [
                {destination: "CPH", description: "Copenhagen (CPH)"},
                {destination: "STN", description: "London (STN)"},
                {destination: "SXF", description: "Berlin-Schönefeld (SXF)"},
                {destination: "OSL", description: "Oslo (SXF)"}
            ];

//            $scope.airlines;

            $scope.getData = function () {

                $scope.feedBack = "";

                if (!(($scope.dataFrom.repeatSelectFrom === null) | $scope.date === null)) {

                    $scope.showSpinner = true;
                    $scope.showTable = false;

                    var year = $scope.dDate.getFullYear();
                    var month = $scope.dDate.getMonth();
                    var day = $scope.dDate.getDate();
                    $scope.date = new Date(year, month, day, 1);

                    if ($scope.dataTo.repeatSelectTo === null) {

                        $http({
                            method: 'GET',
                            url: 'api/flightinfo/'
                                    + $scope.dataFrom.repeatSelectFrom + '/'
                                    + $scope.date.toISOString() + '/'
                                    + $scope.passengers

                        }).then(function successCallback(response) {

                            if (response.data[0] == undefined) {
                                $scope.feedBack = "No Flight is found";
                                $scope.airlines = response.data;
                                $scope.showTable = false;

                            } else {
                                $scope.airlines = response.data;
                                $scope.showTable = true;
                            } // End of if-else
                            $scope.showSpinner = false;


                        }, function errorCallback(response) {
                            alert("Error occured");
                            $scope.showSpinner = false;
                            $scope.showTable = true;

                        });

                    } else {

                        $http({
                            method: 'GET',
                            url: 'api/flightinfo/'
                                    + $scope.dataFrom.repeatSelectFrom + '/'
                                    + $scope.dataTo.repeatSelectTo + '/'
                                    + $scope.date.toISOString() + '/'
                                    + $scope.passengers

                        }).then(function successCallback(response) {
                            if (response.data[0] == undefined) {
                                $scope.feedBack = "No Flight is found";
                                $scope.airlines = response.data;
                                $scope.showTable = false;

                            } else {
                                $scope.airlines = response.data;
                                $scope.showTable = true;
                            } // End of if-else
                            $scope.showSpinner = false;


                        }, function errorCallback(response) {
                            alert("Error occured");
                            $scope.showSpinner = false;
                            $scope.showTable = true;

                        });
                    }; // End of if-else(to)
                }; // End of if(from)
            }; // End of getData
            
            $scope.bookTicket = function (airline, flight) {
                
                var booking = {
                    airline : airline,
                    flightID : flight.flightID,
                    numberOfSeats : flight.numberOfSeats,
                    date : flight.date,
                    totalPrice : flight.totalPrice,
                    origin : flight.origin,
                    traveltime : flight.traveltime,
                    destination : flight.destination,
                    passengers: $scope.passengers
                };
                
               $rootScope.rsBooking = booking;
               InfoService.seats = booking.passengers;
               //InfoService.seats = passengers;
            };
            
            
            
        }); // End of Controller



// <td>{{airline.airline}}</td>
//                <td>{{flight.airline}}</td>
//                <td>{{flight.numberOfSeats}}</td>
//                <td>{{flight.date| date}}</td>
//                <td>&euro;{{flight.totalPrice}}</td>
//                <td>{{flight.origin}}</td>
//                <td>{{flight.traveltime}}</td>
//                <td>{{flight.destination}}</td>




//'use strict';
//
//angular.module('myApp.view1', ['ngRoute'])
//
//.config(['$routeProvider', function($routeProvider) {
//  $routeProvider.when('/view1', {
//    templateUrl: 'app/view1/view1.html',
//    controller: 'View1Ctrl',
//    controllerAs : 'ctrl'
//  });
//}])
//
//.controller('View1Ctrl', ["InfoFactory","InfoService",function(InfoFactory,InfoService) {
//  this.msgFromFactory = InfoFactory.getInfo();
//  this.msgFromService = InfoService.getInfo();
//}]);