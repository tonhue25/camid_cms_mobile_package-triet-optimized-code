angular.module('ViettelMyVTG').controller('ServicePageController', function ($rootScope,$state, $scope, $http, $timeout,$translate) {
    $scope.$on('$viewContentLoaded', function() {

    });

    $scope.serviceGroupText = $translate.instant("nhom_dich_vu");
    $scope.serviceText = $translate.instant("dich_vu");
    $scope.subServiceText = $translate.instant("goi_cuoc");


    $scope.settings = $rootScope.settings;

    $scope.goToServiceGroup = function () {
      $state.go("servicePage.serviceGroups.list");
    }

    $scope.goToService = function () {
      $state.go("servicePage.services.list");
    }

    $scope.goToSubService = function () {

      $state.go("servicePage.subServices.list");
    }
});
