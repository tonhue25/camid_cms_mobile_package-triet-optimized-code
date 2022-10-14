angular.module('ViettelMyVTG').controller('NotificationsController', function ($rootScope, $state, $q, $scope, $compile, APIGateway, $timeout, $stateParams) {
  $scope.resource = "notifications";
  $scope.data = {};
  var dataNotChange =  {};
  $scope.data.notificationType = '1';
  $scope.data.notificationProgramType = 0
  $scope.selectSearch = "";
  $scope.listDate = {
    startTime: ""
  }

  jQuery.validator.addMethod("listPhone", function (value, element) {
    if ($.trim(value) == "") return true;

    var _self = this;
    var arr = value.split(",");
    var check = true;
    $.each(arr, function (i, val) {
      if (!($.trim(val) != "" && (_self.optional(element) || /^\+*\d{1,20}$/.test($.trim(val))))) {
        check = false;
        return false;
      }
    });
    // allow any non-whitespace characters as the host part
    return check;
  }, 'Please enter a valid phone number.');

  $scope.itemSelected = [];
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
    $rootScope.route.goToEdit($state, $scope.resource, {
      id: id
    });
  }

  $scope.add = function () {
    var timeStart = $("#timeStart").val();
    var timeEnd = $("#timeEnd").val();
    var d = new Date();
    var n = d.valueOf();

    var obj = angular.copy($scope.data);
    if ($scope.data.isSave) {
      obj.isSave = 1;
    } else {
      obj.isSave = 0;
    }
     
    if ($scope.data.notificationProgramType) {
      obj.notificationProgramType = 1;
    } else {
      obj.notificationProgramType = 0;
    }

    if ($scope.data.contentFromCampaign) {
      obj.contentFromCampaign = 1;
    } else {
      obj.contentFromCampaign = 0;
    }

    obj.endTime =  timeEnd ? obj.endTime + moment.duration(timeEnd)._milliseconds : obj.endTime;
    obj.startTime =  timeStart ? obj.startTime + moment.duration(timeStart)._milliseconds : obj.startTime;

    if( obj.startTime >= obj.endTime){
        toastr.error("Expire date should be larger than Effect date");
        return;
    }

    APIGateway.sendRequest("notifications/checkCode.GET", {code:obj.code}).then(function (res) {
        if (res){
          toastr.error("Program code is exist!");
          return;
        }

    APIGateway.sendRequest($scope.resource + ".POST", obj).then(function (resp) {
        if (resp.code == 1) {
          toastr.error(notiMessages.duplicate);
          return false;
        }

        $scope.goToList();
        toastr.success(notiMessages.addSuccessMes);
      },
      function (response) {});
    });
  };

  $scope.$watch("data.notificationProgramType",function(newVal, oldVal) {
      if (newVal == undefined || !newVal){
        $scope.data.contentFromCampaign = false;
      }
  });

  $scope.cancelNoti = function() {
    dataNotChange.status = 4;
    APIGateway.sendRequest($scope.resource + ".PUT", dataNotChange).then(function (res) {
      if (res.code == 1) {
        toastr.error(notiMessages.cancelFail);
        return false;
      } else {
        $scope.data.status = 4;
        toastr.success(notiMessages.cancelSuccess);
      }
    },
    function (response) {});
  }

  $scope.getById = function (data) {
    if (data) {
      var id = data;
    } else {
      var id = $stateParams.id;
    }

    APIGateway.sendRequest($scope.resource + "/get.GET", {
      id: id
    }).then(function (resp) {
        var timeStart = $("#timeStart");
        var timeEnd = $("#timeEnd");

        dataNotChange = angular.copy(resp.data);
        $scope.data = angular.copy(resp.data);
        $scope.data.isSave = false;

        if (resp.data.isSave == 1) {
          $scope.data.isSave = true;
        }
        
        $scope.data.notificationProgramType = false;
        if (resp.data.notificationProgramType == 1) {
          $scope.data.notificationProgramType = true;
        }

        $scope.data.contentFromCampaign = false;
        if (resp.data.contentFromCampaign == 1) {
          $scope.data.contentFromCampaign = true;
        }

        timeStart.val(moment($scope.data.startTime).format("HH:mm:ss"));
        timeEnd.val(moment($scope.data.endTime).format("HH:mm:ss"));

        $scope.data.startTime = $scope.data.startTime - moment.duration(moment($scope.data.startTime).format("HH:mm"))._milliseconds;
        $scope.data.endTime =  $scope.data.endTime - moment.duration(moment($scope.data.endTime).format("HH:mm"))._milliseconds;
        $scope.$apply();
      },
      function (response) {});
  }

  $scope.changeNotificationType = function (type) {
    $scope.data.serviceCode = "";
    $scope.data.serviceName = "";
  }

  $scope.reset = function () {
    $state.reload();
  }

  $scope.update = function () {
    var timeStart = $("#timeStart").val();
    var timeEnd = $("#timeEnd").val();
    var d = new Date();
    var n = d.valueOf();
    var obj = angular.copy($scope.data);

    obj.endTime =  timeEnd ? obj.endTime + moment.duration(timeEnd)._milliseconds : obj.endTime;
    obj.startTime =  timeStart ? obj.startTime + moment.duration(timeStart)._milliseconds : obj.startTime;
    if( obj.startTime >= obj.endTime){
        toastr.error("Expire date should be larger than Effect date");
        return;
    }
    if ($scope.data.isSave) {
      obj.isSave = 1;
    } else {
      obj.isSave = 0;
    }

    if ($scope.data.notificationProgramType) {
      obj.notificationProgramType = 1;
    } else {
      obj.notificationProgramType = 0;
    }

    if ($scope.data.contentFromCampaign) {
      obj.contentFromCampaign = 1;
    } else {
      obj.contentFromCampaign = 0;
    }

    obj.test_status = 0;
    APIGateway.sendRequest("notifications/checkCode.GET", {code:obj.code,id:obj.id}).then(function (res) {
      if (res){
        toastr.error("Program code is exist!");
        return;
      }
    APIGateway.sendRequest($scope.resource + ".PUT", obj).then(function (res) {
        if (res.code == 1) {
          toastr.error(notiMessages.duplicate);
          return false;
        } else {
          toastr.success(notiMessages.putSuccessMes);
          $scope.goToList();
        }
      },
      function (response) {});
    })
  }

  $scope.dataSubservice = [];
  $scope.chosenSubService;
  $scope.getDataForSelect2 = function () {
    return new Promise(function (resolve) {
      APIGateway.sendRequest("subservices/getAll.GET", {isApproved: 1}).then(function (resp) {
        $scope.dataSubservice = resp.data.map(function (item) {
          item.nameData = item.name;
          item.name = item.nameData + " - " + item.code;
          return item;
        });
        $scope.$apply()
        resolve($scope.dataSubservice);
      })
    })
  }

  $scope.getDataForSelect2New = function () {
    return new Promise(function (resolve) {
      APIGateway.sendRequest("news/getAll.GET").then(function (resp) {
        $scope.dataSubservice = resp.data;
        $scope.$apply()
        resolve(resp.data);
      })
    })
  }

  $scope.getDataForSelect2Appication = function () {
    return new Promise(function (resolve) {
      APIGateway.sendRequest("applications/getAll.GET").then(function (resp) {
        $scope.dataSubservice = resp.data;
        $scope.$apply()
        resolve(resp.data);
      })
    })
  }

  $scope.getNotifyProgramType = function () {
    return new Promise(function (resolve, reject) {
      resolve([{id:'0','name':'Normal'},{id:'1','name':'Campain'}]);
    })
  }

  $scope.getDataNotiType = function () {
    return new Promise(function (resolve, reject) {
      resolve([{id:'1','name':'Service'},{id:'2','name':'News'},
              {id:'3','name':'Application'},{id:'4','name':'Utility'}]);
    })
  }

  $scope.addSubservice = function (subId) {
    $.each($scope.dataSubservice, function (i, item) {
      if (item.code && item.id == subId) {
        $scope.data.serviceCode = item.nameData;
        $scope.data.serviceName = item.code;
        return false;
      } else if (!item.code && item.id == subId) {
        $scope.data.serviceCode = item.id;
        $scope.data.serviceName = item.name;
        $scope.$apply;
        return false;
      }
    });
    $('#chosenSubservice').modal("hide");
  }

  $scope.fnExport = function () {
    if ( window._tableSelect &&  window._tableSelect.length > 0) {
      var arr = [];
      $.each( window._tableSelect, function (i, val) {
        arr.push(val.id);
      });

      var objParam = {
        ids: arr.join("-")
      };

      var win = window.open(window.location.origin + "/myvtg-frontend/api/1.0/notifications/getDataExport?" + $.param(objParam), '_blank');
      $scope.itemSelected = [];
    } else {
      toastr.error("Please select Notification items to export report!");
    }
    window._tableSelect = [];
  }

  $scope.testing = function () {
    if ($.trim($scope.data.listTestPhone).length == 0) {
      toastr.error("Please enter Test phone");
    } else {
      var check = true;
      var arr = $.trim($scope.data.listTestPhone).split(",");

      $.each(arr, function (i, val) {
        if ( !(/^\+*\d{1,20}$/.test(val))) {
          check = false;
          return false;
        }
      });

      if (check) {
        var obj = angular.copy($scope.data);
        if ($scope.data.isSave) {
          obj.isSave = 1;
        } else {
          obj.isSave = 0;
        }
        obj.test_status = 1;
        
        if ($scope.data.notificationProgramType) {
          obj.notificationProgramType = 1;
        } else {
          obj.notificationProgramType = 0;
        }

        if ($scope.data.contentFromCampaign) {
          obj.contentFromCampaign = 1;
        } else {
          obj.contentFromCampaign = 0;
        }
        APIGateway.sendRequest("notifications.PUT", obj).then(function (res) {
          toastr.success(notiMessages.putSuccessMes);
          $scope.goToList();
        });
      }else {
        toastr.error("Test phone not valid");
      }
    }
  }

  $scope.dtConfig = {
    "scrollX": false,
    resource: $scope.resource,
    isInlineEdit: false,
    searchPlaceholder: tableTitles.search,
    isMutilSelect: true,
    buttonsConfig: {
      add: $scope.resource + ".add",
      approveAll: false,
      exportButton: true
    },
    order: [
      [3, "desc"]
    ],
    columns: [
      {
        title: tableTitles.title,
        data: "title",
        sWidth: "10%",
        orderable: true,
        render: function (data, type, full, meta) {
          if (!data || data == "null") return "";

          if (data.length > 85) {
            return data.substr(0, 83) + "...";
          } else return data;
        },
        fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
          if (sData) {
            $(nTd).attr('title', sData);
          }
        }
      },
      {
        title: tableTitles.message,
        data: "message",
        orderable: true,
        sWidth: "100px",
        render: function (data, type, full, meta) {
          if (!data || data == "null") return "";

          if (data.length > 100) {
            return data.substr(0, 100) + "...";
          } else return data;
        },
        fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
          if (sData) {
            $(nTd).text(sData);
            $(nTd).attr('title', sData);
          }
        }
      },
      {
        title: tableTitles.startTime,
        data: "startTime",
        orderable: true,
        width: "20px",
        render: function (data, e, full, meta) {
          return moment(data).format("DD/MM/YYYY HH:mm:ss");
        },
        fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
          if (sData) {
            $(nTd).attr('title',  moment(sData).format("DD/MM/YYYY HH:mm:ss"));
          }
        }
      },
      {
        title: tableTitles.endTime,
        data: "endTime",
        width: "20px",
        orderable: true,
        render: function (data, e, full, meta) {
          return moment(data).format("DD/MM/YYYY HH:mm:ss");
        },
        fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
          if (sData) {
            $(nTd).attr('title', moment(sData).format("DD/MM/YYYY HH:mm:ss"));
          }
        }
      },
      {
        title: tableTitles.status,
        data: "status",
        width: "1%",
        className: "text-center",
        render: function (data) {
          if (data == 0) return '<img src="assets/global/img/icon-new.png" width="20px" height="20px" ng-if="data.test_status == 1" />';
          if (data == 1) return '<img src="assets/global/img/icon-aporived.svg" width="25px" height="25px" ng-if="data.test_status == 1" />';
          if (data == 2) return ' <img src="assets/global/img/running.png" width="20px" height="20px" ng-if="data.test_status == 2" />';
          if (data == 3)
            return '<img src="assets/global/img/tick-icon.jpg" width="20px" height="20px" ng-if="data.test_status == 3" />';
          if (data == 4)
            return '<i class="fa fa-ban fa-1x" title="Is Cancel"></i>';
        }
      },
      {
        title: "Test status",
        data: "test_status",
        width: "1%",
        className: "text-center",
        render: function (data) {
          if (data == 1)
            return '<img src="assets/global/img/icon-test.png" width="20px" height="20px" ng-if="data.test_status == 1" />';
          if (data == 2)
            return ' <img src="assets/global/img/running.png" width="20px" height="20px" ng-if="data.test_status == 2" />';
          if (data == 3)
            return '<img src="assets/global/img/tick-icon.jpg" width="20px" height="20px" ng-if="data.test_status == 3" />';
          if (data == 0) return "";
        }
      },
      {
        title: tableTitles.action,
        data: "action",
        width: "10%",
        class: "text-center",
        orderable: false,
        render: function (url, type, full) {
          var rt = "";
          if (full.status == 1) {
            rt += "<button class='glyphicon btn btn-primary btn-xs glyphicon-ok btnActive'  style='color:white'></button>";
          } else {
            rt += "<button class='glyphicon btn btn-secondary btn-xs glyphicon-ok btnActive' style='color:black' ></button>";
          }

          rt += myApp.renderActions(full);
          return rt;
        },
        fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
          $compile(nTd)($scope);
        },
        defaultContent: ""
      }
    ]
  }
});
