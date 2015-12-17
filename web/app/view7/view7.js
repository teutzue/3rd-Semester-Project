'use strict';

angular.module('myApp.view7', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view7', {
                    templateUrl: 'app/view7/view7.html',
                    controller: 'View7Ctrl'
                });
            }])

        .controller('View7Ctrl', function ($http, $scope) {

        $http({
                method: 'GET',
                url: 'api/searchRequest'
            }).then(function successCallback(response) {
                $scope.searches = response.data;
                console.log($scope.searches);
                
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });

        
        });

