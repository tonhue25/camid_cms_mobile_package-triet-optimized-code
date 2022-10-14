angular.module('ViettelMyVTG').controller('AnotherController', function ($rootScope, $state, $scope, $http, $timeout, $translate) {
    $scope.$on('$viewContentLoaded', function() {

    });

    $scope.jobListText = $translate.instant("danh_sach_job");
    $scope.hobbyListText = $translate.instant("danh_sach_so_thich");

    $scope.goToHobby = function () {
      $state.go("another.hobbys.list");
    }

    $scope.goToJob = function () {
      $state.go("another.jobs.list");
    }
});
