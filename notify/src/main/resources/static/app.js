
var app = angular.module("application", []);

app.service('$stomp', ['$location', function($location)
{
    var stompClient = null;
    var setConnected = null;
    var onMessage = null;

    this.init = function(onConnect, onMessageCallback)
    {
        if(typeof onConnect != "function")
            console.log("onConnect must be function");
       if(typeof onMessage != "function")
                console.log("onMessage must be function");

        setConnected = onConnect;
        onMessage = onMessageCallback;
    };

    this.connect = function()
    {
        var socket = new SockJS('/update');
        stompClient = Stomp.over(socket);
        stompClient.connect('', '', function(frame)
        {
            setConnected(true);
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/cell', function(msg)
            {
                var twitt = JSON.parse(msg.body);
                onMessage(twitt);
            });
        });
    };

    this.disconnect = function()
    {
        stompClient.disconnect();
        setConnected(false);
        console.log("Disconnected");
    };

    this.send = function(name)
    {
        stompClient.send("/app/update", {}, JSON.stringify({ 'name': name }));
    };
}]);

/*
 * Inject $stomp service
 */
app.controller('MainController', [ '$scope', '$stomp', function($scope, $stomp)
{
    $scope.twitts = [];
    $scope.selected = {message: "Nothing selected"};
    $scope.markAsSelected = function(twitt)
    {
       $scope.selected = twitt;
    };

    $scope.name = "";

    $scope.isOnline = false;
    $scope.connectName = "Connect";

    $scope.connectOrDisconnect = function()
    {
        if($scope.isOnline)
        {
            $scope.connectName = "Connect";
            $stomp.disconnect();
             $scope.isOnline = false;
        }
        else
        {
            $stomp.connect();
            $scope.connectName = "Disconnect"
            $scope.isOnline = true;
        }
    }

    $scope.sendName = function(name)
    {
        $stomp.send(name);
    }

    $stomp.init(function(online)
            {
                $scope.$apply(function()
                {
                    $scope.isOnline = online;
                });
            },
            function (twitt)
            {
                $scope.$apply(function()
                {
                    $scope.twitts.push(twitt);
                });
            });

    console.log("Controller loaded...");
}]);

console.log("Application loaded...");
