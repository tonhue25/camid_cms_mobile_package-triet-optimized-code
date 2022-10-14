angular.module('ViettelMyVTG').controller('HotNewsController', function ($rootScope, $state, $scope, APIGateway, $timeout, $q, $stateParams) {
  $scope.resource = "hotnews";
  $scope.data = {};
  $scope.filter = {};
  $scope.promotionRefresh = 0;
  $scope.serviceRefresh = 0;
  $scope.newRefresh = 0;

  $scope.$watch('data.language', function (newValue, oldValue) {

    switch ($scope.data.type) {
      case 0:
        $scope.serviceRefresh++;
        break;
      case 1:
        $scope.promotionRefresh++;
        break;
      case 2:
        $scope.newsRefresh++;
        break;
    }
  });

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

  $scope.goToEdit = function (id, type) {

    $state.go('hotnews.edit', { id: id, type: type });
  }


  $scope.add = function () {
      var d = new Date();
      var n = d.valueOf();

      //if ($scope.data.publishedTime > $scope.data.effectTime) {
      //    toastr.error(notiMessages.errPublicTimeLagrerThanEffectiveTime);
      //    return;
      //}
      //if ($scope.data.effectTime > $scope.data.expiredTime) {
      //    toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
      //    return;
      //}
    APIGateway.sendRequest($scope.resource + ".POST", $scope.data).then(

     function (res) {

       if ($rootScope.isAdmin()) {
         APIGateway.sendRequest($scope.resource + "/approve.PUT", { active: true, ids: [{ id: res.data.id, type: res.data.type }] }).then(function () {
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

  $scope.getServices = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("services.GET", { size: 1000 }).then(function (res) {
        var data = res.content.filter(function (item) {
          return item.language == $scope.data.language;
        });
        resolve(data);
      },
     function (response) {
     });
    })
  }

  $scope.getNews = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("news.GET", { size: 1000 }).then(function (res) {

        var data = res.content.filter(function (item) {
          return item.language == $scope.data.language;
        });
        resolve(data);
      },
     function (response) {
     });
    })
  }

  $scope.getPromotions = function () {
    return new Promise(function (resolve, reject) {
      APIGateway.sendRequest("promotions.GET", { size: 1000 }).then(function (res) {
        var data = res.content.filter(function (item) {
          return item.language == $scope.data.language;
        });
        resolve(data);
      },
     function (response) {
     });
    })
  }

  $scope.getById = function () {
    APIGateway.sendRequest($scope.resource + "/get.GET", { id: $stateParams.id, type: $stateParams.type }).then(function (res) {
      $scope.data = res.data;
      $scope.$apply();
    },
   function (response) {
   });
  }

  $scope.update = function () {
      var d = new Date();
      var n = d.valueOf();

      //if ($scope.data.publishedTime > $scope.data.effectiveTime) {
      //    toastr.error(notiMessages.errPublicTimeLagrerThanEffectiveTime);
      //    return;
      //}
      //if ($scope.data.effectiveTime > $scope.data.expiredTime) {
      //    toastr.error(notiMessages.errEffectiveTimeLagrerThanExpiredTime);
      //    return;
      //}
    $scope.data.oldId = $stateParams.id;
    $scope.data.oldType = $stateParams.type;
    APIGateway.sendRequest($scope.resource + ".PUT", $scope.data).then(function (res) {
      if ($rootScope.isAdmin()) {
        APIGateway.sendRequest($scope.resource + "/approve.PUT", {active:true, ids: [{ id: res.data.id, type: res.data.type }] }).then(function () {
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
    searchPlaceholder: tableTitles.searchHotnews,
    buttonsConfig: {
      add: $scope.resource + ".add"
    },
    order: [[3, "asc"]],
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
          title: tableTitles.type, data: "type", orderable: false, defaultContent: "",
          render: function (data, type, full, meta) {
            if (data == 0) {
              return tableTitles.serviceType;
            }
            else if (data == 1) {
              return tableTitles.promotionType;
            }
            else if (data == 2) {
              return tableTitles.newsType;
            }
          }
        },
        {
          title: tableTitles.image, data: "imgDesUrl", defaultContent: "",
          orderable: false,
          render: function (url, type, full) {
            return myApp.renderImage(full.imgDesUrl);
          }
        },
        { title: tableTitles.orderOfPrecedence, data: "priority", orderable: true, defaultContent: "" },
        {
          title: tableTitles.effectiveTime, data: "effectTime", orderable: false, defaultContent: "",
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.expiredTime, data: "expiredTime", orderable: false, defaultContent: "",
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.status, data: "state", orderable: false, defaultContent: "",
          render: function (data, type, full, meta) {
            if (data == 1) {
              return tableTitles.isShow;
            }
            else if (data == 0) {
              return tableTitles.notShow;
            }
          }
        },
        {
          title: tableTitles.createdTime, data: "createdTime", orderable: false, defaultContent: "",
          render: function (data, type, full) {
            return myApp.convertDateFormat(data);
          }
        },
        {
          title: tableTitles.action, data: "action",
          orderable: false,
          sWidth: '110px',
          render: function (url, type, full) {
            return myApp.renderActions(full);
          }
        }
    ],
    editHandler: function (id, data) {
      $state.go($scope.dtConfig.resource + ".edit", { id: id, type: data.type });
    },
    approveFunc: function (id, isApproved, data) {
      return new Promise(function (resolve, reject) {
        APIGateway.sendRequest($scope.dtConfig.resource + "/approve.PUT", { active: isApproved, ids: [{ id: data.id, type: data.type }] })
          .then(function (response) {
            resolve(response.content);
          }, function (rejection) {
            reject(rejection.content);
          });
      })
    },
    deleteFunc: function (params, data) {
      return new Promise(function (resolve, reject) {
        APIGateway.sendRequest($scope.dtConfig.resource + ".DELETE", { id: data.id, type: data.type })
          .then(function (response) {
            resolve(response);
          }, function (rejection) {
            reject(rejection);
          });
      });
    }
  }
});

