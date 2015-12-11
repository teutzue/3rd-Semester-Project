'use strict';

angular.module('myApp.view7', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view7', {
    templateUrl: 'app/view7/view7.html',
    controller: 'View7Ctrl'
  });
}])

.controller('View7Ctrl', function($http,$scope) {
//  $http.get('api/demoadmin')
//            .success(function (data, status, headers, config) {
//              $scope.data = data;
//            })
//            .error(function (data, status, headers, config) {
//              
//             });

$scope.usersi = users;

 
 });

var users = [{




   "username":"Yoana",
   
   "flightID": "COL3256x100x2016-01-10T19:00:00.000Z",

   "numberOfSeats": 2,

   "ReserveeName": "Peter Hansen",

   "ReservePhone": "12345678",
   

   "Date":"2016-01-10T19:00:00.000Z",
   "Destination":"London Stansted(STN)",
   "Origin": "Copenhagen Kastrup(CPH)",

   "Passengers": [

      {

         "firstName": "Peter",

         "lastName": "Peterson"

      },

      {

         "firstName": "Jane",

         "lastName": "Peterson"

      }

   ]
   

},{


   "username":"Krassimira",
   
   "flightID": "COL3256x100x2016-01-10T19:00:00.000Z",

   "numberOfSeats": 3,

   "ReserveeName": "Krassimira Dandarova",

   "ReservePhone": "12345678",

   "ReserveeEmail": "peter@peter.dk",
    "Date":"2016-01-10T19:00:00.000Z",
   "Destination":"London Stansted(STN)",
   "Origin": "Copenhagen Kastrup(CPH)",

   "Passengers": [

      {

         "firstName": "Krassimira",

         "lastName": "Dandarova"

      },

      {

         "firstName": "Vanessa",

         "lastName": "Dandarova"

      }

   ]

},{


   "username":"Kostadinka",
   
   "flightID": "COL3256x100x2016-01-10T19:00:00.000Z",

   "numberOfSeats": 3,

   "ReserveeName": "Krassimira Dandarova",

   "ReservePhone": "12345678",

   "ReserveeEmail": "peter@peter.dk",
    "Date":"2016-01-10T19:00:00.000Z",
   "Destination":"London Stansted(STN)",
   "Origin": "Copenhagen Kastrup(CPH)",

   "Passengers": [

      {

         "firstName": "George",

         "lastName": "Dandarov"

      },

      {

         "firstName": "Kostadinka",

         "lastName": "Dandarova"

      }

   ]

 }];