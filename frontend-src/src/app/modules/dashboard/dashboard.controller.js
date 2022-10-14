angular.module('ViettelMyVTG').controller('DashboardController', function ($rootScope, $scope, APIGateway, $timeout, $translate) {

    //$scope.$on('$viewContentLoaded', function() {
    //    // initialize core components
    //    App.initAjax();
    //});

  $scope.info = {}
  $scope.getInfo = function () {

    APIGateway.sendRequest("dashboardInfo.GET", {}).then(function (resp) {
      $scope.info = resp.data;
      var subList = {
        x: $scope.info.subList.map(function (e) {
          return e.time;
        }),
        y: $scope.info.subList.map(function (e) {
          return e.total;
        }),
      }
      $('#sub_list').highcharts({
        chart: {
          style: {
            fontFamily: 'Open Sans'
          }
        },
        title: {
          text: $translate.instant('thong_ke_thue_bao'),
          x: -20 //center
        },
        //subtitle: {
        //  text: 'Source: WorldClimate.com',
        //  x: -20
        //},
        xAxis: {
          categories: subList.x,
          title: {
            text:"Time"
          }
        },
        yAxis: {
          title: {
            text: $translate.instant('active_users')
          },
          plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
          }]
        },
        tooltip: {
          valueSuffix: ''
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          borderWidth: 0
        },
        series: [{
          name: ' ',
          data: subList.y
        }]
      });

      var requestList = {
        x: $scope.info.requestList.map(function (e) {
          return e.time;
        }),
        y: $scope.info.requestList.map(function (e) {
          return e.total;
        }),
      }
      $('#request_list').highcharts({
        chart: {
          style: {
            fontFamily: 'Open Sans'
          }
        },
        title: {
          text: $translate.instant('thong_ke_yeu_cau'),
          x: -20 //center
        },
        //subtitle: {
        //  text: 'Source: WorldClimate.com',
        //  x: -20
        //},
        xAxis: {
          categories: requestList.x,
          title: {
            text: "Time"
          }
        },
        yAxis: {
          title: {
            text: 'Requests'
          },
          plotLines: [{
            value: 0,
            width: 1,
            color: '#303040'
          }]
        },
        tooltip: {
          valueSuffix: ''
        },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          borderWidth: 0
        },
        series: [{
          name: ' ',
          data: requestList.y
        }]
      });


      var colors = Highcharts.getOptions().colors;
      $('#os_list').highcharts({
        chart: {
          type: 'pie',
          style: {
            fontFamily: 'Open Sans'
          }
        },
        title: {
          text:  $translate.instant('phan_bo_theo_nen_tang_hdh')
        },

        //yAxis: {
        //  title: {
        //    text: 'Total percent market share'
        //  }
        //},
        plotOptions: {
          pie: {
            shadow: true,
            center: ['50%', '50%']
          }
        },
        tooltip: {
          valueSuffix: '%'
        },
        series: [{
          name: 'OS',
          data: $scope.info.osList.map(function (e,index) {
            return {name:e.key, y:e.total,color:colors[index]}
          })
        }]
      });

      $scope.$apply();
    })
  }

});
