angular.module('ViettelMyVTG').controller('JobController', function ($rootScope, $state, $scope, $http, $timeout, $q, $stateParams) {
  $scope.$on("$viewContentLoaded", function () {
    $rootScope.settings.activeTabIndex = 0;
  });
  $scope.resource = "jobs";

  $scope.data = {}

  $scope.dtConfig = {
    resource: $scope.resource,
      isInlineEdit: true,
      autoWidth: true,
      searchPlaceholder: tableTitles.searchJob,

      inlineForm: [
          {
              name: "<input class='form-control input-small' name='name-table' value='' />",
              des: "<input class='form-control input-small' name='des-table' value=''/>",
              language: config.langTableInlineForm,
              createdTime: "",
              action: "",
              isFormRow: true
          }
      ],
      buttonsConfig: {
          approveAll: false
      },
      order: [[1, "asc"]],
      unEditedColumns:[0],
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
            title: tableTitles.jobTitle, data: "name", orderable: true,
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
            title: tableTitles.description, data: "des",

            validation: {
              maxlength: 1000
          },
            orderable: false,
            render: function (data, type, full, meta) {

              if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
                return myApp.trimContent(data);
              }
              return data;
            }
          },
          { title: tableTitles.language, sWidth: "70px", data: "language", orderable: false,vtgType: "dropdown",
            vtgDataTypes: config.tableLangDropdown.vtgDataTypes
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
