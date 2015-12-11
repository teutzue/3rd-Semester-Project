'use strict';
angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope, $rootScope) {
            
            $http({
                method: 'GET',
                url: 'api/reservations' + '/' + $rootScope.theUsername
            }).then(function successCallback(response) {
                $scope.data = response.data.message;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });


        }); // End of Controllere