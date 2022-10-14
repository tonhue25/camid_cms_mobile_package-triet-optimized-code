(function () {
  'use strict';
  angular
    .module('ViettelMyVTG')
    .config(function routerConfig($stateProvider) {
      $stateProvider
      .state("another", {
        abtract: true,
        templateUrl: "/app/modules/another/another.html",
        controller: "AnotherController",
      })
      .state("another.hobbys", {
        url: "/hobbys",
        views: {
          'Hobby': {
            template:'<div ui-view class="fade-in-up" id="angular-controller"></div>'
          }
        }
      })
      .state("another.hobbys.list", {
        url: "/list",
        templateUrl: "/app/modules/another/hobbys.list.html",
        controller: "HobbyController",
      })

      .state("another.jobs", {
        url: "/jobs",
        views: {
          'Job': {
            template: '<div ui-view class="fade-in-up" id="angular-controller"></div>'
          }
        }
      })
      .state("another.jobs.list", {
        url: "/list",
        templateUrl: "/app/modules/another/jobs.list.html",
        controller: "JobController",
      })
    });
})();
