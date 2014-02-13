
var app = angular.module("application", ['ngResource']);

app.factory('$cell', function ($resource) {
    return $resource('cell', {}, {
        show: { method: 'GET', isArray: true },
        update: { method: 'PUT' }, //GET
        delete: { method: 'DELETE'}
    })
});


app.controller('TableController', ['$scope', '$timeout', '$http', '$cell', function($scope, $timeout, $http, $cell)
{
    $scope.cells = [];
    $scope.cell = {name: "Nothing selected"};
    $scope.markAsSelected = function(cell)
    {
       $scope.cell = cell;
    };

    $scope.update = function()
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
    }

    $scope.save = function()
    {
        $cell.update($scope.cell,
           function(data)
           {
             $scope.update();
           },
           //error
           function( error )
           {
            console.log(error);
           }
        );
    }

    $scope.update();

    console.log("Table controller is loaded...");
}]);

console.log("Application loaded...");