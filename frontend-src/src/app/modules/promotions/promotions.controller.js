angular.module('ViettelMyVTG').controller('PromotionController', function ($rootScope, $state, $scope, APIGateway, $timeout, $stateParams) {
  $scope.resource = "promotions";
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

  $scope.reset = function () {
    $state.reload();
  }
  $scope.add = function () {
    var d = new Date();
    var n = d.valueOf();

    //if ($scope.data.publishedTime > $scope.data.effectiveTime) {
    //    toastr.error(notiMessages.errPublicTimeLagrerThanEffectiveTime);
    //    return;
    //}

    //if ($scope.data.effectiveTime > $scope.data.expiredTime) {
    //  toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
    //  return;
    //}

    var data = $scope.data;
    var url = configuration.hostAPI + $scope.resource;
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
       toastr.success(notiMessages.addSuccessMes);
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

    //if ($scope.data.effectiveTime > $scope.data.expiredTime) {
    //  toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
    //  return;
    //}
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
    buttonsConfig: {
      add: $scope.resource + ".add"
    },
    order: [[1, "asc"]],
    searchPlaceholder: tableTitles.searchPromotion,
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
          title: tableTitles.shortDescription, data: "shortDes", orderable: false,
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
          title: tableTitles.publishingTime, data: "publishedTime", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data, true);
          }
        },
        {
          title: tableTitles.effectiveDate, data: "effectiveTime", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.expiredDate, data: "expiredTime", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        { title: tableTitles.language, sWidth: "70px", data: "language", orderable: false },
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
