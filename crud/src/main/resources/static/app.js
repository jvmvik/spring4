
var app = angular.module("application", []);

//TODO Inject http service and load node.json
function TableController($scope, $timeout, $http)
{
    $scope.cells = [];
    $scope.selected = {name: "Nothing selected"};
    $scope.markAsSelected = function(cell)
    {
       $scope.selected = cell;
    };

    $scope.update = function()
    {
        $http.get('cell/all').success(function(data, status)
        {
            console.log("Pull cells from REST service");
            $scope.cells = data;
        }).error(function(data, status)
        {
            alert("Error: " + status);
        });
    }

    $scope.update();

    console.log("Table controller is loaded...");
}

console.log("Application loaded...");