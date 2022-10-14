angular.module('ViettelMyVTG').controller('SubServiceController', function ($rootScope, $state, $scope, APIGateway, $timeout, $stateParams, $q, $translate) {
  $scope.$on('$viewContentLoaded', function () {

    $rootScope.settings.activeTabIndex = 2;
    if ($state.current.name == "servicePage.subServices.add") {
      $state.go("servicePage.subServices.add");
    }
    if ($state.current.name == "servicePage.subServices.edit") {
      $scope.getById();
      $scope.getWebservices();
    }
  });

  $scope.$on('$stateChangeStart', function (e, toState, toParams, fromState, fromParams) {
    if (toState.name == "servicePage.subServices.edit") {
      window.setTimeout(function () {
        window.location.reload();
      }, 100);
    }
  });


  $scope.selectServiceText = $translate.instant("chon_dich_vu");
  $scope.actionText = $translate.instant("hoat_dong");

  $scope.resource = "subServices";
  $scope.subServiceRefresh = 0;

  $scope.$watch('data.language', function (newValue, oldValue) {
    $scope.subServiceRefresh++;
  });

  $scope.$on('$viewContentLoaded', function () {
    if ($state.current.name == $scope.resource + ".edit") {
      $scope.getById();
    }
  });

  $scope.data = {};
  $scope.currentSubservice = {}
  $scope.reset = function () {
    $state.reload();
  }
  $scope.goToList = function () {

    $state.go('servicePage.subServices.list');
  }

  $scope.goToAdd = function () {
    $state.go('servicePage.subServices.add');
  }

  $scope.goToEdit = function (id) {
    $state.go('servicePage.subServices.edit', { id: id });
  }

  $scope.add = function () {
    APIGateway.sendRequest($scope.resource + ".POST", $scope.data).then(function (res) {

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
      function (response) {
      });
  };

  $scope.getById = function () {

    APIGateway.sendRequest($scope.resource + "/get.GET", { id: $stateParams.id }).then(function (resp) {
      $scope.data = resp.data;
      $scope.currentSubservice = resp.data;
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
      toastr.success(notiMessages.putSuccessMes)
    },
     function (response) {
     });
  }

  $scope.getServices = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("services.GET", { size: 1000 }).then(function (res) {
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
    })
  }

  $scope.dtConfig = {
    resource: $scope.resource,
    prefixRoute: "servicePage.",
    buttonsConfig: {
      add: "servicePage.subServices.add"
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
        {
          title: tableTitles.subserviceName, data: "name", orderable: false,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          },
        },
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
          title: tableTitles.icon, data: "iconUrl",
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
        }
    ],
    order: [[1, "asc"]]
  }

  $scope.webservices = [];
  $scope.getWebservices = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("webservices/list.GET", { page: 0, size: 1000 }).then(function (res) {
        $scope.webservices = res;
        $scope.wsIdSelectHtml = "";
        res.forEach(function (item, index) {
          $scope.webservices[index] = {};
          $scope.webservices[index].text = item.wsName;
          $scope.webservices[index].value = item.id;
          $scope.wsIdSelectHtml += '<option value="' + item.id + '">' + item.wsName + '</option>';
        });

      },
      function (response) {
        toastr.error(tableTitles.errorLoad);
      });
    })
  }

  $scope.wsIdSelectHtml = "";

  $scope.actionDtConfig = {
    resource: 'actions',
    bPaginate: false,
    dom: "<'row'<'col-md-1 col-sm-12'l><'col-md-5 col-sm-12 text-right'><'col-md-6 col-sm-12'B<'table-group-actions pull-right'>>r>t<'row'<'col-md-12 col-sm-12 text-center'p><'col-md-4 col-sm-12'>>",
    inlineForm: [
        {
          name: "<input class='form-control input-small' name='name-table' value='' />",
          id: {

            //actionType: "<select class='form-control input-small' name='id.actionType-table'><option value='" + 0 + "'>"
            //  + tableTitles.register + "</option><option value='" +
            //  1 + "'>" + tableTitles.cancel + "</option> <option value='" +
            //  2 + "'>" + tableTitles.otherAd + "</option> </select>",

            actionType: "<input class='form-control input-small' name='id.actionType-table' value='' title='0: register, 1: un-register, 2: other action' />",

            channelType: "<select class='form-control input-small' name='id.channelType-table'>"
            + "<option value='" + 0 + "'>" + 'Fake MO USSD' + "</option>"
            + "<option value='" + 1 + "'>" + "Fake MO SMS" + "</option>"
            + "<option value='" + 2 + "'>" + "Webservice" + "</option>"
            + "<option value='" + 3 + "'>" + "MO" + "</option>"
            + "</select>",
          },
          syntax: "<input class='form-control input-small' name='syntax-table' value='' />",
          channel: "<input class='form-control input-small' name='channel-table' value='' />",
          wsId: '<select name="wsId-table" class="form-control select2-table">' + $scope.wsIdSelectHtml + '</select>',
          action: "",
        }
    ],
    buttonsConfig: {
      add: "servicePage.subServices.add",
      approveAll: false,
      refresh: false,
    },
    getParams: {
      subServiceCode: $scope.currentSubservice.code
    },
    isInlineEdit: true,
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
          title: tableTitles.actionName, data: "name", orderable: true,
          render: function (data, type, full, meta) {
            if ((meta.settings.isInlineEdit && meta.row > 0) || !meta.settings.isInlineEdit) {
              return myApp.trimContent(data);
            }
            return data;
          },
          validation: {
            required: true,
            maxlength: 200
          }
        },
        {
          title: tableTitles.actionType,
          data: "id.actionType",
          orderable: false,
          validation: {
            required: true,
            maxlength: 200
          }
          //vtgType: "dropdown",
          //vtgDataTypes: [{ text: tableTitles.register, value: 0 },
          //    { text: tableTitles.cancel, value: 1 },
          //    { text: tableTitles.otherAd, value: 2 },
          //],
          //render: function (data, type, row) {
          //    //switch (data) {
          //    //    case 0: return tableTitles.register;
          //    //    case 1: return tableTitles.cancel;
          //    //    case 2: return tableTitles.otherAd;
          //    //    default:
          //    //        return data;
          //    //}

          //    return data;
          //},
        },
        {
          title: tableTitles.channelType, data: "id.channelType", orderable: false,
          vtgType: "dropdown",
          vtgDataTypes: [
              { text: 'Fake MO USSD', value: 0 },
              { text: 'Fake MO SMS', value: 1 },
              { text: 'Webservice', value: 2 },
              { text: 'MO', value: 3 }
          ],
          render: function (data, type, row) {
            switch (data) {
              case 0: return 'Fake MO USSD';
              case 1: return 'Fake MO SMS';
              case 2: return 'Webservice';
              case 3: return 'MO';
              default:
                return data;
            }
          },
        },
        {
          title: tableTitles.syntax, data: "syntax", orderable: false,
          validation: {
            required: true,
            maxlength: 200
          }
        },
        {
          title: tableTitles.channel, data: "channel", sWidth: "10%",
          orderable: false,
          validation: {
            required: true,
            maxlength: 10
          }
        },
        {
          title: tableTitles.webservice,
          data: "wsId", orderable: false,
          validation: {
            required: true
          },
          vtgType: "dropdown",
          plusClass: "select2-table",
          vtgDataTypes: $scope.webservices,
          render: function (data, type, row) {
            var txt = data;
            $scope.webservices.forEach(function (item, index) {
              if (item.value == data) {
                txt = item.text;
              }
            });
            return txt;
          }
        },
        {
          title: tableTitles.action, data: "action",
          orderable: false,
          sWidth: '100px',
          defaultContent: "<button  class='glyphicon btn btn-success btn-sm glyphicon-pencil btn-edit'></button><button class='glyphicon btn btn-danger btn-sm glyphicon-remove-circle btn-remove' ></button>"
        }
    ],
    order: [[1, "asc"]],
    addFunc: function (data, config, dt) {
      return new Promise(function (resolve, reject) {
        var postData = {};
        postData.name = data.name
        postData.id = {};
        postData.id.actionType = parseInt(data['id.actionType']);
        postData.id.channelType = parseInt(data['id.channelType']);
        postData.id.subServiceCode = $scope.currentSubservice.code;
        postData.syntax = data.syntax;
        postData.channel = data.channel;
        postData.wsId = data.wsId;
        APIGateway.sendRequest($scope.actionDtConfig.resource + ".POST", postData)
          .then(function (response) {
            toastr.success(tableTitles.successAddMessage);
            resolve(response);
          }, function (rejection) {
            toastr.error(tableTitles.errorMessage);
            reject(rejection);
          });
      });

    },
    updateFunc: function (oldValue, updatedValue, config) {
      var postData = {};
      postData.name = updatedValue.name
      postData.newId = {};
      postData.newId.actionType = parseInt(updatedValue['id.actionType']);
      postData.newId.channelType = parseInt(updatedValue['id.channelType']);
      postData.newId.subServiceCode = oldValue.id.subServiceCode;

      postData.id = oldValue.id;

      postData.syntax = updatedValue.syntax;
      postData.channel = updatedValue.channel;
      postData.wsId = updatedValue.wsId;
      var deferred = $q.defer();
      APIGateway.sendRequest($scope.actionDtConfig.resource + ".PUT", postData)
          .then(function (response) {
            deferred.resolve(response.content);
          }, function (rejection) {
            deferred.reject(rejection.content);
          });
      return deferred.promise;
    },
    deleteFunc: function (data) {
      var params = {
        actionType: data.id.actionType,
        channelType: data.id.channelType,
        subServiceCode: data.id.subServiceCode
      }
      var deferred = $q.defer();
      APIGateway.sendRequest($scope.actionDtConfig.resource + ".DELETE", params)
          .then(function (response) {
            deferred.resolve(response);
          }, function (rejection) {
            deferred.reject(rejection);
          });
      return deferred.promise;
    },
    drawCallback: function (settings) {
      $(document).ready(function () {
        $(".select2-table").select2();
      });
    },
    exWthRefresh: $scope.getWebservices,
    watchFunc: function (table) {
      $scope.$watch('webservices', function () {
        $scope.actionDtConfig.inlineForm[0].wsId = '<select name="wsId-table" class="form-control select2-table">' + $scope.wsIdSelectHtml + '</select>';
        $scope.actionDtConfig.columns[6].vtgDataTypes = $scope.webservices;
        table.fnDraw();
      });
      $scope.$watch('currentSubservice', function () {
        $scope.actionDtConfig.getParams.subServiceCode = $scope.currentSubservice.code;
        table.fnDraw();
      });
    }
  }
});
