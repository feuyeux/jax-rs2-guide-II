function Hello($scope, $http) {
    $http.get('http://localhost:8080/hi').
        success(function(data0) {
            $scope.data = data0;
        });
}
