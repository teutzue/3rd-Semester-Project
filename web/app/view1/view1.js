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
                {destination: "SXF", description: "Berlin-Schönefeld (SXF)"},
                {destination: "OSL", description: "Oslo (SXF)"}
            ];

//            $scope.airlines;

            $scope.getData = function () {

                var year = $scope.dDate.getFullYear();
                var month = $scope.dDate.getMonth();
                var day = $scope.dDate.getDate();
                $scope.date = new Date(year, month, day, 1);


                $http({
                    method: 'GET',
                    url: 'api/flightinfo/'
                            + $scope.dataFrom.repeatSelectFrom + '/'
                            + $scope.dataTo.repeatSelectTo + '/'
                            + $scope.date.toISOString() + '/'
//                            + $scope.dDate.toISOString() + '/'
                            + $scope.passengers

                }).then(function successCallback(response) {
                    $scope.airlines = response.data;

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