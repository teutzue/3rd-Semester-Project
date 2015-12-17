'use strict';
angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope, $rootScope) {
            
            $scope.showSpinner = true;
            
            $http({
                method: 'GET',
                url: 'api/reservations' + '/' + $rootScope.theUsername
            }).then(function successCallback(response) {
                
                $scope.showSpinner = false;
                $scope.data = response.data;
                
            }, function errorCallback(res) {
                
                $scope.showSpinner = false;
                $scope.error = res.status + ": " + res.data.statusText;
            });


        }); // End of Controllere