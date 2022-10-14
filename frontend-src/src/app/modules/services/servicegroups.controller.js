angular.module('ViettelMyVTG').controller('ServiceGroupController', function ($rootScope, $state, $scope, APIGateway, $q, $timeout) {

  $scope.baseRoute = "servicePage.serviceGroups";

  $scope.$on('$viewContentLoaded', function () {
    $rootScope.settings.activeTabIndex = 0;
    if ($state.current.name == $scope.baseRoute + ".add") {
      $state.go($scope.baseRoute + ".add");
    }
  });

  $scope.resource = "servicegroups";

  $scope.goToList = function () {

    $state.go($scope.baseRoute + ".list");
  }

  $scope.goToAdd = function () {
    $state.go($scope.baseRoute + ".add");
  }

  $scope.goToEdit = function () {
    $state.go($scope.baseRoute + ".edit");
  }

  $scope.dtConfig = {
    resource: $scope.resource,
    isInlineEdit: true,
    searchPlaceholder: tableTitles.searchServiceGroup,
    inlineForm: [
        {
          code: "<input class='form-control input-small'  name='code-table' value='' />",
          name: "<input  class='form-control input-small' name='name-table' value=''/>",
          shortDes: "<input class='form-control input-small' name='shortDes-table' value=''/>",
          language: config.langTableInlineForm,
          createdTime: "",
          action: "",
          isFormRow: true
        }
    ],
    buttonsConfig: {
      add: "servicePage.serviceGroups.add",
      approveAll: false
    },

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
          title: tableTitles.groupCode, data: "code", defaultValue: "", orderable: true,
          validation: {
            maxlength: 20,
            required: true
          }
        },
        {
          title: tableTitles.groupName, data: "name", orderable: false, defaultContent: "",
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          },
          validation: {
            maxlength: 200,
            required: true
          }
        },
        {
          title: tableTitles.description, data: "shortDes", orderable: false,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          },
          validation: {
            maxlength: 1000,
            required: false
          },
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          }
        },
        {
          title: tableTitles.language, sWidth: '70px', data: "language", orderable: false, vtgType: "dropdown",
          vtgDataTypes: config.tableLangDropdown.vtgDataTypes
        },
        {
          title: tableTitles.createdTime, data: "createdTime", sWidth: '80px', orderable: false,
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.action,
          data: "action",
          sWidth: '110px',
          orderable: false,
          defaultContent: "",
          render: function (data, type, full, meta) {
            if (meta.row > 0) {
              return myApp.renderActions(full);
            }
          }
        }
    ],
    order: [[1, "asc"]],
    unEditedColumns: [0, 5],
  }
});
