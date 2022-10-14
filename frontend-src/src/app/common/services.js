
ViettelMyVTG.service('APIGateway', ['$rootScope', '$http', '$q',
    function ($rootScope, $http, $q) {
      this.sendRequest = function (requestName, data) {
        App.blockUI({
          css: {
            border: '0',
            padding: '0',
            backgroundColor: 'none'
          },
          //target: ".page-content",
          message: notiMessages.ajaxWaiting
        });
        return new Promise(function (resolve, reject) {

          if (!$rootScope.auth.sessionId) myApp.logout();

          var wrapper = {
            sessionId: $rootScope.auth.sessionId ? $rootScope.auth.sessionId : "",
            username: $rootScope.auth.username,
            token: $rootScope.auth.token,
            wsCode: requestName,
            wsRequest: data
          };

          $http({
            method: 'POST',
            url: configuration.baseAPI + "UserRouting",
            data: wrapper,
            //withCredentials: true,
            headers: {
              'Content-type': 'application/json;charset=utf-8'
            }
          })
          .then(function (response) {
            var errorCode = response.data.errorCode;

            if (errorCode == "S200" || errorCode == "") {
              var code = response.data.result.code;
              if (code == "500") {
                reject(response.data.result);
              }
              else if (code == "404") {
                toastr.error(notiMessages.notFound);
                return;
              }
              else if (code != "" && code != undefined) {
                toastr.error(response.data.result.content);
                reject(response.data.result);
              }
              resolve(response.data.result)
            }
            else if (errorCode == "S401") {
              //toastr.error(notiMessages.loginRequire);
              //myApp.notiLoginRequire();

              myApp.logout();
            }
            else if (errorCode == "S204") {
              toastr.error(response.data.errorMessage);
            }
            else {
              reject(response.data);
            }
            window.setTimeout(function () {
              App.unblockUI();
            }, 200)
          }, function (rejection) {
            reject(rejection.content);
            window.setTimeout(function () {
              App.unblockUI();
            }, 200)
          });
        })
      }
    }
])
