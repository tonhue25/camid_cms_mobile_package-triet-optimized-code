(function () {
  'use strict';
  angular
    .module('ViettelMyVTG')
    .config(function routerConfig($stateProvider) {
      var resource = "notifications";
      var controller = "NotificationsController";
      var baseTemplateUrl = "/app/modules/" + resource + "/" + resource;

      $stateProvider
      .state(resource, {
        abtract:true,
        url: "/notifications",
        template: "<div ui-view id='current-controller' class='fade-in-up'></div>",
      })
      .state(resource+".list", {
        url: "/list",
        templateUrl: baseTemplateUrl+".list.html",
        controller: controller,
      })
      .state(resource+".add", {
        url: "/add",
        templateUrl: baseTemplateUrl+".add.html",
        controller: controller,
      })
      .state(resource+".reportCamp", {
        url: "/reportCampaign",
        templateUrl: "/app/modules/reportNotifyCamp/reportNotifyCamp.list.html",
        controller: "reportNotiCampController",
      })
      .state(resource+".reportCustomer", {
        url: "/reportCustomer",
        templateUrl: "/app/modules/reportNotifyCustomer/reportNotifyCustomer.list.html",
        controller: "reportNotifyCustomerController",
      })
      .state(resource+".edit", {
          url: "/{id}",
        templateUrl: baseTemplateUrl + ".edit.html",
        controller: controller,
      })
    });
})();
