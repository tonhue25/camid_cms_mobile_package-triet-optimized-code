angular.module('ViettelMyVTG').controller('BannerController', function ($rootScope, $state, $scope, APIGateway, $timeout, $q, $stateParams) {

  $scope.resource = "advertbanners";
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

  $scope.goToEdit = function (id) {
    $rootScope.route.goToEdit($state, $scope.resource, { id: id });
  }

  $scope.add = function () {
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
    APIGateway.sendRequest($scope.resource + "/get.GET", { id: $stateParams.id }).then(function (res) {
      $scope.data = res.data;
      $scope.$apply();
    },
   function (response) {
   });
  }

  $scope.update = function () {
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
    buttonsConfig: {
      add: $scope.resource + ".add"
    },
    searchPlaceholder: tableTitles.searchBanner,
    order: [[1, "desc"]],
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
          title: tableTitles.shortDescription, data: "des",
          orderable: true,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          }
        },
        {
          title: tableTitles.bannerImage, data: "advImgUrl",
          orderable: false,
          render: function (data, type, full) {
            return myApp.renderImage(data);
          }
        },
        {
          title: tableTitles.newsOfType, data: "type", orderable: false,
          render: function (url, type, full) {
            switch (full.type) {
              case 0: return tableTitles.serviceAd;
              case 1: return tableTitles.promotionAd;
              case 2: return tableTitles.newsAd;
              default: return full.type;
            }
          }
        },
        {
          title: tableTitles.createdTime, data: "createdTime", sWidth: "80px", orderable: true,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.action, data: "action",
          orderable: false,
          sWidth: "110px",
          //defaultContent: "<button  class='glyphicon glyphicon-pencil btn-edit'></button> <button class='glyphicon glyphicon-remove-circle btn-remove' ></button> <button class='glyphicon glyphicon-ok btn-approve ' ></button>"
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ]
  }
});
