(function () {
  'use strict';
  angular
    .module('ViettelMyVTG')
    .config(function routerConfig($stateProvider) {
      var resource = "advertbanners";
      var controller = "BannerController";
      var baseTemplateUrl = "/app/modules/" + resource + "/" + resource;

      $stateProvider
      .state(resource, {
        abtract: true,
        url: "/advert-banners",
        template: "<div ui-view id='current-controller' class='fade-in-up'></div>",
      })
      .state(resource + ".list", {
        url: "/list",
        templateUrl: baseTemplateUrl + ".list.html",
        controller: controller,
      })
      .state(resource + ".add", {
        url: "/add",
        templateUrl: baseTemplateUrl + ".add.html",
        controller: controller,
      })
      .state(resource + ".edit", {
        url: "/{id}",
        templateUrl: baseTemplateUrl + ".edit.html",
        controller: controller,
      })
    });
})();
