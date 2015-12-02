'use strict';

angular.module('myApp.view1', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view1', {
                    templateUrl: 'app/view1/view1.html',
                    controller: 'View1Ctrl',
                    controllerAs: 'ctrl'
                });
            }])

        .controller('View1Ctrl', function ($http, $scope, dateFilter) {

//            $http({
//                method: 'GET',
//                url: 'api/demoUser/cur'
//            }).then(function successCallback(response) {
//                $scope.data.countrycodes = response.data;
//                $scope.data1.countrycodes1 = response.data;
//            }, function errorCallback(response) {
//                $scope.error = res.status + ": " + res.data.statusText;
//            });

            $scope.passengers = 1;

            $scope.dataFrom = {
                repeatSelectFrom: null,
                origins: []
            };
            // Dummy data
            $scope.dataFrom.origins = [
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
                {destination: "SXF", description: "Berlin-Schönefeld (SXF)"}
            ];

            $scope.getData = function () {

                $http({
                    method: 'GET',
                    url: 'api/flightinfo/'
                            + $scope.dataFrom.repeatSelectFrom + '/'
                            + $scope.dataTo.repeatSelectTo + '/'
                            + $scope.dDate.toISOString() + '/'
//                                + $scope.dDate + '/'
//                                + $scope.fDate + '/'
                            + $scope.passengers

                }).then(function successCallback(response) {
                    $scope.number = response.data;

                }, function errorCallback(response) {
                    alert("Error occured");
                });
            };

        }); // End of Controller






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