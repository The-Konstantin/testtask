var app = angular.module("DataEntityFront", []);

app.controller("DataEntityController", function ($scope, $http) {

    $scope.key = '';
    $scope.values = [];
    $scope.dataEntity = {
        key: "",
        value: {
            generation: "",
            description: ""
        }
    };
    $scope.errorMessage = null;

    $scope.submitDataEntity = function () {

        var method = "PUT";
        var url = '/data';

        _clearErrorMessage();

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.dataEntity),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_clearFormData, _error);
    };

    $scope.getValuesByKey = function () {

        var method = "GET";
        var url = '/data';

        _clearErrorMessage();
        _clearValues();

        $http({
            method: method,
            url: url,
            params: {key: $scope.key}
        }).then(
            function(res) {$scope.values = res.data},
            _error);
    };

    function _error(res) {
        $scope.errorMessage = res.data.message;
    }

    function _clearFormData() {
        $scope.dataEntity.key = '';
        $scope.dataEntity.value.generation = '';
        $scope.dataEntity.value.description = '';
    }

    function _clearValues() {
        $scope.values = [];
    }

    function _clearErrorMessage() {
        $scope.errorMessage = '';
    }
});