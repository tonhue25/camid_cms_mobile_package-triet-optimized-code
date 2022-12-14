angular.module('ViettelMyVTG').controller('ExperiencelinksController', function ($rootScope, $state, APIGateway, $scope, $http, $timeout, $q, $stateParams) {
  $scope.resource = "experiencelinks";
  $scope.data = {};

  $scope.$on('$viewContentLoaded', function () {
    if ($state.current.name == $scope.resource + ".edit") {
      $scope.getById();
    }
  });

  $scope.reset = function () {
    $state.reload();
  }

  $scope.goToList = function () {
    $rootScope.route.goToList($state, $scope.resource);
  }

  $scope.goToAdd = function () {
    $rootScope.route.goToAdd($state, $scope.resource);
  }

  $scope.goToEdit = function () {
    $rootScope.route.goToEdit($state, $scope.resource);
  }


  $scope.add = function () {
    APIGateway.sendRequest($scope.resource + ".POST", $scope.data).then(
       function (res) {
         if ($rootScope.isAdmin()) {
           APIGateway.sendRequest($scope.resource + "/approve.PUT", { ids: [res.data.id] }).then(function () {
             $scope.goToList();
           });
         }
         else {
           $scope.goToList();
         }
         toastr.success(notiMessages.addSuccessMes)
       },
       function (res) {
       });
  };

  $scope.getById = function () {
    var url = configuration.hostAPI + $scope.resource + $stateParams.id;
    APIGateway.sendRequest($scope.resource + "/get.GET", { id: $stateParams.id }).then(function (res) {

      $scope.data = res.data;
      $scope.$apply();
    },
   function (response) {
   });
  }

  $scope.update = function () {
    var url = configuration.hostAPI + $scope.resource + $stateParams.id;
    APIGateway.sendRequest($scope.resource + ".PUT", $scope.data).then(function (res) {
      if ($rootScope.isAdmin()) {
        APIGateway.sendRequest($scope.resource + "/approve.PUT", { ids: [res.data.id] }).then(function () {
          $scope.goToList();
        });
      }
      else {
        $scope.goToList();
      }
      toastr.success(notiMessages.putSuccessMes);
    },
     function (response) {
     });
  }

  $scope.dtConfig = {
    resource: $scope.resource,
    isInlineEdit: false,
    autoWidth: true,
    searchPlaceholder: tableTitles.searchExprLink,
    buttonsConfig: {
      add: $scope.resource + ".add"
    },
    order: [[1, "asc"]],
    columns: [
        {
          title: tableTitles.order, data: null, defaultContent: "",
          orderable: false, render: function (data, e, full, meta) {
            if (meta.settings.isInlineEdit) {
              if (meta.row > 0) {
                return meta.row + meta.settings._iDisplayStart;
              }
            }
            else {
              return meta.row + meta.settings._iDisplayStart + 1;
            }
          }
        },
    {
      title: tableTitles.linkName, data: "name", orderable: true,
      render: function (data, type, full, meta) {
        if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
          return myApp.trimContent(data);
        }
        return data;
      }
    },
    {
      title: tableTitles.shortDescription, data: "shortDes",
      sWidth: "300px",
      orderable: false,
      render: function (data, type, full, meta) {
        if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
          return myApp.trimContent(data);
        }
        return data;
      }
    },
    {
      title: tableTitles.icon, data: "imgDesUrl",
      orderable: false,
      render: function (url, type, full) {
        return myApp.renderImage(full.imgDesUrl);
      }
    },
    {
      title: tableTitles.createdTime, data: "createdTime", sWidth: "80px", orderable: false,
      render: function (data, type, full) {
        return myApp.convertDateFormat(data);
      }
    },
    {
      title: tableTitles.action, data: "action",
      sWidth: '110px',
      orderable: false,
      render: function (url, type, full) {
        return myApp.renderActions(full);
      }
    }]
  }
});
