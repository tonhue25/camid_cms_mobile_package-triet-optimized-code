angular.module('ViettelMyVTG').controller('WebserviceController', function ($rootScope, $state, $scope, APIGateway, $timeout, $q, $stateParams) {
  $scope.resource = "webservices";
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
    $rootScope.route.goToEdit($scope.resource, { id: id });
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

  $scope.reset = function () {
    $state.reload();
  }
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

  $scope.dtConfig = {
    resource: $scope.resource,
    isInlineEdit: false,
    autoWidth: true,
    searchPlaceholder: tableTitles.searchWebservice,
    buttonsConfig: {
      add: $scope.resource + ".add",
      approveAll: false
    },
    order: [[1, "asc"]],
    unEditedColumns: [0],
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
          title: tableTitles.name, data: "wsName", orderable: true,
          render: function (data, type, full) {
            if (data)
              return myApp.trimContent(data);
            else
              return data;
          }
        },
        {
          title: tableTitles.url, data: "url", orderable: false,
          render: function (data, type, full) {
            if (data)
              return myApp.renderLink(data);
            else
              return data;
          }
        },
        {
          title: tableTitles.parameter, data: "params", orderable: false,
          render: function (data, type, full) {
            if (data)
              return myApp.trimContent(data);
            else
              return data;
          }
        },
        {
          title: tableTitles.xmlString, data: "rawXml", orderable: false,
          render: function (data, type, full) {
            if (data)
              return myApp.trimContent(data);
            else
              return full.rawXml;
          }
        },

        {
          title: tableTitles.description, data: "des", orderable: false,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          }
        },
        {
          title: tableTitles.createdTime, data: "createdTime", width: "80px", orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.action, data: "action",
          width: '70px',
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ]
  }
});
