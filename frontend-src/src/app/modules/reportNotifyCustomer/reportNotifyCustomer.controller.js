angular.module('ViettelMyVTG').controller('reportNotifyCustomerController', function ($scope, APIGateway) {
    $scope.data = {};
    $scope.list = [];
    $scope.moment = moment;
    $scope.export = function(){
        APIGateway.sendRequest("notifications/reportByCustomerUsed.GET", $scope.data).then(function (res) {
                $scope.list = res;
                if (res.length==0){
                    toastr.warning("Not data");
                }
                $scope.$apply();
            },
            function (response) {});
    }
});
