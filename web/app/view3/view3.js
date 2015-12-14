'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            
             $http({
                method: 'GET',
                url: 'api/reservations'
            }).then(function successCallback(response) {
                $scope.datas = response.data;
                
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });
            

        });