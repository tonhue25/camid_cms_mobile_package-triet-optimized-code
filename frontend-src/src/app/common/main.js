/***
Metronic AngularJS App Main Script
***/

/* Metronic App */
var ViettelMyVTG = angular.module("ViettelMyVTG", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize",
    'pascalprecht.translate'
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
ViettelMyVTG.config(['$ocLazyLoadProvider', '$translateProvider', function ($ocLazyLoadProvider, $translateProvider) {
  $ocLazyLoadProvider.config({
    // global configs go here
  });

  $translateProvider.translations('vn', translations);
  $translateProvider.preferredLanguage('vn');

}]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
ViettelMyVTG.config(['$controllerProvider', function ($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/

/* Setup global settings */
ViettelMyVTG.factory('settings', ['$rootScope', '$state', '$http', function ($rootScope, $state, $http) {

  // supported languages

  var folder = configuration.basePath;

  var basePath = window.location.origin + folder;

  var settings = {
    layout: {
      pageSidebarClosed: false, // sidebar menu state
      pageContentWhite: true, // set page content layout
      pageBodySolid: false, // solid body color state
      pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
    },
    assetsPath: folder + 'assets',
    globalPath: folder + 'assets/global',
    layoutPath: folder + 'assets/layouts/layout',
    basePath: basePath
  };

  $rootScope.settings = settings;

  $rootScope.logout = function () {
    $http({
      method: "POST",
      url: configuration.baseAPI + "UserLogout",
      data: {
        sessionId: $rootScope.auth.sessionId ? $rootScope.auth.sessionId : "",
        username: $rootScope.auth.username
      }
    }).then(
      function (res) {
          myApp.logout();
    });
  }

  if (localStorage.getItem("auth") != undefined) {
    $rootScope.auth = JSON.parse(localStorage.getItem("auth"));
  }
  else {
    window.location = basePath + "login.html";
  }
  $rootScope.route = {

    goToList: function (state, resource) {
      state.go(resource + ".list");
    },
    goToAdd: function (state, resource) {
      state.go(resource + ".add");
    },
    goToEdit: function (state, resource) {
      state.go(resource + ".edit");
    }
  }

  $rootScope.isAdmin = function () {
    return myApp.isAdmin();
  }
  return settings;
}]);

/* Setup App Main Controller */
ViettelMyVTG.controller('AppController', ['$scope', '$rootScope', '$templateCache', function ($scope, $rootScope, $templateCache) {
  $scope.$on('$viewContentLoaded', function () {
    App.initComponents(); // init core components
    Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive
    $templateCache.removeAll();

  });
}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/

/* Setup Layout Part - Header */
ViettelMyVTG.controller('HeaderController', ['$scope', function ($scope) {
  $scope.$on('$includeContentLoaded', function () {
    Layout.initHeader(); // init header
  });
}]);

/* Setup Layout Part - Sidebar */
ViettelMyVTG.controller('SidebarController', ['$state', '$scope', function ($state, $scope) {
  $scope.$on('$includeContentLoaded', function () {
    Layout.initSidebar($state); // init sidebar
  });
}]);

/* Setup Layout Part - Quick Sidebar */
ViettelMyVTG.controller('QuickSidebarController', ['$scope', function ($scope) {
  $scope.$on('$includeContentLoaded', function () {
    setTimeout(function () {
      QuickSidebar.init(); // init quick sidebar
    }, 2000)
  });
}]);

/* Setup Layout Part - Theme Panel */
ViettelMyVTG.controller('ThemePanelController', ['$scope', function ($scope) {
  $scope.$on('$includeContentLoaded', function () {
    Demo.init(); // init theme panel
  });
}]);

/* Setup Layout Part - Footer */
ViettelMyVTG.controller('FooterController', ['$scope', function ($scope) {
  $scope.$on('$includeContentLoaded', function () {
    Layout.initFooter(); // init footer
  });
}]);

/* Setup Layout Part - Sidebar */
ViettelMyVTG.controller('PageHeadController', ['$scope', function ($scope) {
  $scope.$on('$includeContentLoaded', function () {
    Demo.init(); // init theme panel
  });
}]);

/* Setup Rounting For All Pages */
ViettelMyVTG.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function ($stateProvider, $urlRouterProvider, $locationProvider) {

  $urlRouterProvider.otherwise("/dashboard");

}]);

/* Init global settings request run the app */
ViettelMyVTG.config(function ($httpProvider) {
  //$httpProvider.defaults.transformResponse = function (data) {
  //  //
  //  //console.log(data);
  //  return data;
  //};
});

/* Init global settings and run the app */
ViettelMyVTG.run(["$rootScope", "settings", "$state", function ($rootScope, settings, $state) {
  $rootScope.$state = $state; // state to be accessed from view
  $rootScope.$settings = settings; // state to be accessed from view

  $rootScope.$on('$stateChangeStart',
  function (event, toState, toParams, fromState, fromParams) {
    window.currentState = toState.name;
    if (fromState.name != toState.name) {
      if (window.tableCaches != undefined) {
        if (window.tableCaches[window.currentState]) {
          window.tableCaches[window.currentState].firstInit = true;
        }
      }
    }
  })

}]);


