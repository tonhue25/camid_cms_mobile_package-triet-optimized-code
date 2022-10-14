angular.module('ViettelMyVTG').controller('reportNotiCampController', function ($scope, APIGateway) {
    $scope.data = {};
    $scope.list = [];

    $scope.export = function(){
        APIGateway.sendRequest("notifications/reportByCampaign.GET", $scope.data).then(function (res) {
                $scope.list = res;

                if (res.length==0){
                    toastr.warning("Not data");
                }
                $scope.$apply();
            },
            function (response) {});
    }
});
