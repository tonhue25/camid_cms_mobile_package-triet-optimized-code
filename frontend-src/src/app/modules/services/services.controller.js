angular.module('ViettelMyVTG').controller('ServiceController', function ($rootScope, $state, $scope, APIGateway, $timeout, $q, $stateParams, $translate) {
  $scope.$on('$viewContentLoaded', function () {

    $rootScope.settings.activeTabIndex = 1;

    if ($state.current.name == "servicePage.services.add") {
      $state.go("servicePage.services.add");
    }
    if ($state.current.name == "servicePage.services.edit") {
      $scope.getById($stateParams.id);
    }
  });

  $scope.$on('$stateChangeStart', function (e, toState, toParams, fromState, fromParams) {
    if (toState.name == "servicePage.services.edit") {
      window.setTimeout(function () {
        window.location.reload();
      },100);
    }
  });

  $scope.selectServiceGroupText = $translate.instant("chon_nhom_dich_vu");
  $scope.listSubServiceText = $translate.instant("danh_sach_goi_cuoc");
  $scope.listGroupServices;
  $scope.data = {}
  $scope.resource = "services";
  $scope.serviceGroupRefresh = 0;

  $scope.goToList = function () {
    $state.go('servicePage.services.list');
  }

  $scope.goToAdd = function () {
    $rootScope.route.goToAdd($state, $scope.resource);
  }

  $scope.goToEdit = function () {
    $rootScope.route.goToEdit($state, $scope.resource);
  }

  $scope.reset = function () {
      $state.reload();
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
     function (response) {
     });
  };

  $scope.getServiceGroups = function () {
    return new Promise(function (resolve, reject) {

      APIGateway.sendRequest("servicegroups.GET", { size: 1000 }).then(function (res) {
        if ($scope.data.language) {
          var data = res.content.filter(function (item) {
            return item.language == $scope.data.language;
          });
          resolve(data);
        }
        else {
          resolve(res.content);
        }
      },
      function (response) {
      });
    });
  }

  $scope.getById = function (id) {
    APIGateway.sendRequest($scope.resource + "/get.GET", { id: id }).then(
       function (res) {

         $scope.data = res.data;
         if ($scope.data.actionType == null) $scope.data.actionType = 0;
         $scope.$apply();
       },
       function (response) {
       }
    );
  }

  $scope.update = function () {

    APIGateway.sendRequest($scope.resource + ".PUT", $scope.data).then(
     function (res) {
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

  $scope.$watch('data.language', function (newValue, oldValue) {
    $scope.serviceGroupRefresh++;
  });

  $scope.dtConfig = {
    resource: $scope.resource,
    prefixRoute: 'servicePage.',
    isInlineEdit: false,
    buttonsConfig: {
      add: "servicePage.services.add"
    },
    autoWidth: false,
    searchPlaceholder: tableTitles.searchService,

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
          title: tableTitles.serviceCode, data: "code", orderable: true
        },
        {
          title: tableTitles.serviceName, data: "name", orderable: false,
          render: function (data, type, full) {
            if (data)
              return myApp.trimContent(data);
            else
              return data;
          }
        },
        { title: tableTitles.serviceGroup, data: "serviceGroupName", orderable: false,defaultContent: ""},
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
          title: tableTitles.icon, data: "iconUrl",
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderImage(full.iconUrl);
          }
        },
        {
            title: tableTitles.webLink, data: "webLink", orderable: false, sWidth: "80px",
            render: function (data, type, full) {
                return myApp.renderLink(data);
            }
        },
        {
          title: tableTitles.createdTime, data: "createdTime", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.language, data: "language", orderable: false, vtgType: "dropdown",
          vtgDataTypes: config.tableLangDropdown.vtgDataTypes
        },
        {
          title: tableTitles.action, data: "action",
          orderable: false, sWidth: "110px",
          //defaultContent: "<button  class='glyphicon glyphicon-pencil btn-edit'></button> <button class='glyphicon glyphicon-remove-circle btn-remove' ></button> <button class='glyphicon glyphicon-ok btn-approve ' ></button>"
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ],

    order: [[1, "asc"]]
  }

  $scope.subServiceDtConfig = {
    resource: "subServices",
    prefixRoute: "servicePage.",
    buttonsConfig: {
      add: false,
      approveAll: false,
      refresh: false
    },
    searchPlaceholder: tableTitles.searchSubservice,
    isInlineEdit: false,
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
          title: tableTitles.subserviceCode, data: "code", orderable: true
        },
        {
          title: tableTitles.language, data: "language", orderable: false, vtgType: "dropdown",
          vtgDataTypes: config.tableLangDropdown.vtgDataTypes
        },
        { title: tableTitles.subserviceName, data: "name", orderable: false },
        { title: tableTitles.includedInService, data: "serviceName", defaultContent: "", orderable: false },
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
          title: tableTitles.action, data: "action",
          sWidth: '110px',
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ],
    order: [[1, "asc"]],
    exWthRefresh: $scope.getSubServices,
    getParams: {
      serviceId: $stateParams.id
    },
  }
});

