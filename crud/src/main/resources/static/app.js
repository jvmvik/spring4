
var app = angular.module("application", ['ngResource']);

app.factory('$cell', function ($resource) {
    return $resource('cell', {}, {
        show: { method: 'GET', isArray: true },
        update: { method: 'PUT' }, //GET
        remove: { method: 'DELETE'}
    })
});


app.controller('ApplicationController', ['$scope', '$timeout', '$http', '$cell', function($scope, $timeout, $http, $cell)
{
    $scope.cells = [];
    $scope.cell = {name: "Nothing selected"};
    $scope.markAsSelected = function(cell)
    {
       $scope.cell = cell;
    };

    $scope.refresh = function()
    {
        $cell.show(function(data)
        {
            console.log("Pull cells from REST service");
            $scope.cells = data;
        },
        function(data, status)
        {
            alert("Error: " + status);
        });
    };

    $scope.create = function()
    {
         $scope.cell = {name: "", status: "New"};
         $scope.markAsSelected($scope.cell);
    } ;

    $scope.save = function()
    {
        $cell.update($scope.cell,
           function(data)
           {
             $scope.refresh();
           },
           //error
           function( error )
           {
            console.log(error);
           }
        );
    };

    $scope.remove = function(cell)
    {
        $cell.remove(cell,
            function(data)
            {
                $scope.refresh();
            },
            function(error)
            {
                console.log(error);
            });
    };

    $scope.refresh();

    console.log("Table controller is loaded...");
}]);

console.log("Application loaded...");