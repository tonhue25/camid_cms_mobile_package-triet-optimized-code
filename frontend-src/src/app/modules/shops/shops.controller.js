angular.module('ViettelMyVTG').controller('ShopController', function ($rootScope, $translate, $state, $scope, APIGateway, $timeout, $q, $stateParams) {
  $scope.resource = "shops";
  $scope.data = {};

  $scope.districtPlaceholder = $translate.instant("chon_quan_huyen");
  $scope.provincePlaceholder = $translate.instant("chon_tinh_thanh_pho");

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
    APIGateway.sendRequest($scope.resource + ".POST", $scope.data).then(
       function (res) {
         toastr.success(notiMessages.addSuccessMes);
         $scope.goToList();
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
      toastr.success(notiMessages.putSuccessMes);
      $scope.goToList();
    },
     function (response) {
     });
  }

  $scope.getListProvince = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("wsGetProvinces", {}).then(function (resp) {
        var result = resp.wsResponse.map(function (e) {
          return { id: e.id, name: e.name };
        });
        resolve(result);
      });
    });
  }

  $scope.getListDistrict = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("wsGetDistricts", {}).then(function (resp) {
        var result = resp.wsResponse.map(function (e) {
          return { id: e.id, name: e.name };
        });
        resolve(result);
      });
    });
  }

  $scope.dtConfig = {
    resource: $scope.resource,
    searchPlaceholder: tableTitles.searchShop,
    isInlineEdit: false,
    autoWidth: true,
    buttonsConfig: {
      add: $scope.resource + ".add",
      approveAll: false
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
          title: tableTitles.name, data: "name", orderable: true,
          render: function (data, type, full) {
            if (data)
              return myApp.trimContent(data);
            else
              return data;
          }
        },
        {
          title: tableTitles.phoneNumber, data: "isdn",
          orderable: false
        },
        {
          title: tableTitles.address, data: "addr", orderable: true,
          render: function (data, type, full, meta) {
            return myApp.trimContent(data);
          }
        },
        { title: tableTitles.openTime, data: "openTime", orderable: false },
        {
          title: tableTitles.createdTime, data: "createdTime", sWidth: "80px", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.action, data: "action",
          sWidth: '70px',
          orderable: false,
          defaultContent: "",
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }

        }
    ]
  }
});
