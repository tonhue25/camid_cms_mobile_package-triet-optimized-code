(function () {
  'use strict';
  angular
    .module('ViettelMyVTG')
    .config(function routerConfig($stateProvider) {
      var serviceRoute = "servicePage.services";
      var serviceGroupRoute = "servicePage.serviceGroups";
      var subServiceRoute = "servicePage.subServices";
      var baseTemplateUrl = "/app/modules/services/";

      $stateProvider
      .state("servicePage", {
        abtract: true,
        templateUrl: '/app/modules/services/servicepage.html',
        controller: "ServicePageController",
      })

      .state(serviceGroupRoute, {
        url: "/service-groups",
        views: {
          'service-groups': {
            template: '<div ui-view class="fade-in-up" id="angular-controller"></div>',
          }
        }
      })
      .state(serviceGroupRoute + ".list", {
        url: "/list",
        templateUrl: baseTemplateUrl + "servicegroups.list.html",
        controller: "ServiceGroupController",
      })
      .state(serviceGroupRoute + ".add", {
        url: "/add",
        templateUrl: baseTemplateUrl + "servicegroups.add.html",
        controller: "ServiceGroupController",
      })
      .state(serviceGroupRoute + ".edit", {
        url: "/{id}",
        templateUrl: baseTemplateUrl + "servicegroups.edit.html",
        controller: "ServiceGroupController",
      })

        .state(serviceRoute, {
          url: "/services",
          views: {
            'services': {
              template: '<div ui-view class="fade-in-up" id="angular-controller"></div>',
            }
          }
        })
      .state(serviceRoute + ".list", {
        url: "/list",
        templateUrl: baseTemplateUrl + "services.list.html",
        controller: "ServiceController",
      })
      .state(serviceRoute + ".add", {
        url: "/add",
        templateUrl: baseTemplateUrl + "services.add.html",
        controller: "ServiceController",
      })
      .state(serviceRoute + ".edit", {
        url: "/{id}",
        templateUrl: baseTemplateUrl + "services.edit.html",
        controller: "ServiceController",
      })

      .state(subServiceRoute, {
        url: "/sub-services",
        views: {
          'sub-services': {
            template: '<div ui-view class="fade-in-up" id="angular-controller"></div>',
          }
        }
      })
      .state(subServiceRoute + ".list", {
        url: "/list",
        templateUrl: baseTemplateUrl + "subservices.list.html",
        controller: "SubServiceController",
      })
      .state(subServiceRoute + ".add", {
        url: "/add",
        templateUrl: baseTemplateUrl + "subservices.add.html",
        controller: "SubServiceController",
      })
      .state(subServiceRoute + ".edit", {
        url: "/{id}",
        templateUrl: baseTemplateUrl + "subservices.edit.html",
        controller: "SubServiceController",
      })

    });
})();
