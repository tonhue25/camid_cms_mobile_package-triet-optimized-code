angular.module('ViettelMyVTG').controller('NewsController', function ($rootScope, $state, $q, $scope, APIGateway, $timeout, $stateParams) {

  $scope.resource = "news";
  $scope.data = {};

  $scope.$on('$viewContentLoaded', function () {
    if ($state.current.name == $scope.resource + ".edit") {
      $scope.getById();
    }
  });

  $scope.goToList = function () {
    $rootScope.route.goToList($state, $scope.resource);
  }

  $scope.goToAdd = function () {
    $rootScope.route.goToAdd($state, $scope.resource);
  }

  $scope.goToEdit = function (id) {
    $rootScope.route.goToEdit($state, $scope.resource, { id: id });
  }

  $scope.add = function () {

    var d = new Date();
    var n = d.valueOf();

    //if ($scope.data.effectiveTime > $scope.data.expiredTime) {
    //  toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
    //  return;
    //}

    APIGateway.sendRequest($scope.resource + ".POST", $scope.data).then(function (resp) {
      if ($rootScope.isAdmin()) {
        APIGateway.sendRequest($scope.resource + "/approve.PUT", { ids: [resp.data.id] }).then(function () {
          $scope.goToList();
        });
      }
      else {
        $scope.goToList();
      }
      toastr.success(notiMessages.addSuccessMes);
    },
    function (response) {
    });
  };

  $scope.getById = function () {
    APIGateway.sendRequest($scope.resource + "/get.GET", { id: $stateParams.id }).then(function (resp) {
      $scope.data = resp.data;
      $scope.$apply();
    },
  function (response) {
  });
  }

  $scope.reset = function () {
    $state.reload();
  }
  $scope.update = function () {
    var d = new Date();
    var n = d.valueOf();

    //if ($scope.data.effectiveTime > $scope.data.expiredTime) {
    //  toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
    //  return;
    //}

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
    searchPlaceholder: tableTitles.searchNews,
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
          title: tableTitles.title, data: "name", orderable: true,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          }
        },
        {
          title: tableTitles.sourceLink, data: "sourceLink", orderable: false,

          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.renderLink(data, myApp.trimContent(data));
            }
            return data;
          }
        },
        {
          title: tableTitles.imgDes, data: "imgDesUrl",
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderImage(full.imgDesUrl);
          }
        },
        {
          title: tableTitles.publishingTime,
          data: "publishedTime",
          orderable: false,
          sWidth: "110px",
          render: function (data, type, full) {
            return myApp.convertDateFormat(data, true);
          }
        },
        {
          title: tableTitles.action, data: "action",
          sWidth: '110px',
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ]
  }
});
