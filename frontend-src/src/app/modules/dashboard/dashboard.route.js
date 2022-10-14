(function () {
  'use strict';
  angular
    .module('ViettelMyVTG')
    .config(function routerConfig($stateProvider) {
      $stateProvider
        .state("dashboard", {
          url: "/dashboard",
          templateUrl: "/app/modules/dashboard/dashboard.html",
          controller: "DashboardController",
          resolve: {
            deps: ['$ocLazyLoad', function ($ocLazyLoad) {
              return $ocLazyLoad.load({
                name: 'ViettelMyVTG',
                insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                files: [
                    //'../assets/global/plugins/morris/morris.css',
                    //'../assets/global/plugins/morris/morris.min.js',
                    //'../assets/global/plugins/morris/raphael-min.js',
                    //'../assets/global/plugins/jquery.sparkline.min.js',

                    'scripts/pages/dashboard.min.js',
                    //'js/controllers/DashboardController.js',
                ]
              });
            }]
          }
        })

    });
})();
