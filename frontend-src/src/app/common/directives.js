/***
GLobal Directives
***/

// Route State Load Spinner(used on page or content load)
ViettelMyVTG.directive('ngSpinnerBar', ['$rootScope', '$state',
  function ($rootScope, $state) {
    return {
      link: function (scope, element, attrs) {
        // by defult hide the spinner bar
        element.addClass('hide'); // hide spinner bar by default

        // display the spinner bar whenever the route changes(the content part started loading)
        $rootScope.$on('$stateChangeStart', function () {
          element.removeClass('hide'); // show spinner bar
        });

        // hide the spinner bar on rounte change success(after the content loaded)
        $rootScope.$on('$stateChangeSuccess', function (event) {
          element.addClass('hide'); // hide spinner bar
          $('body').removeClass('page-on-load'); // remove page loading indicator
          Layout.setAngularJsSidebarMenuActiveLink('match', null, event.currentScope.$state); // activate selected link in the sidebar menu

          // auto scorll to page top
          setTimeout(function () {
            App.scrollTop(); // scroll to the top on content load
          }, $rootScope.settings.layout.pageAutoScrollOnLoad);
        });

        // handle errors
        $rootScope.$on('$stateNotFound', function () {
          element.addClass('hide'); // hide spinner bar
        });

        // handle errors
        $rootScope.$on('$stateChangeError', function () {
          element.addClass('hide'); // hide spinner bar
        });
      }
    };
  }
])

// Handle Dropdown Hover Plugin Integration
ViettelMyVTG.directive('dropdownMenuHover', function () {
  return {
    link: function (scope, elem) {
      elem.dropdownHover();
    }
  };
});

// Config table directive
ViettelMyVTG.directive('vtgTable', function ($compile, $q) {

  return {
    scope: {
      dataTableConfig: "=vtgTable",
      paramQuery: "=",
      listOfDate: "=",
      ngModel: "=?",
      fnExport: "=?"
    },
    controller: function ($scope, $element, $state, APIGateway, $rootScope) {
      var defaultConfig = {
        ajax: function (data, callback, settings) {

          var cache = window.tableCaches[window.currentState];
          window.tableCaches[window.currentState].tableSettings = $.extend({}, cache.tableSettings, settings);
          cache = window.tableCaches[window.currentState];

          var pageSize = settings._iDisplayLength;
          var pageNumber = settings._iDisplayStart / pageSize;

          if (settings.aaSorting.length > 0) {
            var sortCol = settings.aaSorting[0][0];
            var sortType = (settings.aaSorting[0][1] == "asc") ? 0 : 1;
            var sortBy = settings.aoColumns[sortCol].data;
          }

          var params = {
            page: pageNumber,
            size: pageSize,
            sortBy: sortBy,
            sortType: sortType
          };
          cache.filters = {};
          $element.find(".filter-region [filter]").each(function (index, e) {
            var name = $(e).attr("filter");
            var value = $(".filter-region [filter='" + name + "']").val();
            if ($(e).is(":checkbox")) {
              if (e.checked) {
                params[name] = 1;
              } else
                params[name] = 0;
            }
            else
              if (value != "" && value != null) {
                params[name] = value.trim();
                cache.filters[name] = value.trim();
              }
          });

          var resource = "";
          if ($scope.dataTableConfig.noPgnresource)
          {
            resource = $scope.dataTableConfig.noPgnresource;
          }
            // used in case api returns just a list and not paginating
          else {
            resource = $scope.dataTableConfig.resource;
          }

          if ($scope.dataTableConfig.getParams) {
            params = Object.assign(params, $scope.dataTableConfig.getParams)
          }
          APIGateway.sendRequest(resource + ".GET", params).then(function (resp) {
            if ($scope.dataTableConfig.isInlineEdit) {
              settings.isInlineEdit = true;
              if ($scope.dataTableConfig.noPgnresource)
                resp.data = $scope.dataTableConfig.inlineForm.concat(resp);
              else
                resp.data = $scope.dataTableConfig.inlineForm.concat(resp.content);
            }
            else {
              resp.data = resp.content;
            }
            resp.recordsFiltered = resp.totalElements;
            resp.recordsTotal = resp.totalElements;
            callback(resp);
          }, function (resp) {
            //toastr.error(tableTitles.errorMessage);
          });


          //window.tableCaches[window.currentState] = cache;
          //debugger;
        },
        lengthMenu: [20, 50, 100, 200],
        pageLength: 20,
        language: {
          "lengthMenu": "_MENU_",
          "info": tableTitles.total + " _TOTAL_ " + tableTitles.record,
          "infoEmpty": tableTitles.empty,
          "emptyTable": tableTitles.empty,
          "infoFiltered": tableTitles.infoFilter + " _MAX_ " + tableTitles.record,
          "zeroRecords": tableTitles.zeroRecords,
        },
        nameElementSuff: "-table",
        //stateSave: true,
        processing: true,
        serverSide: true,
        dom: "<'row'<'col-md-1 col-sm-12'l><'col-md-5 col-sm-12 text-right'i><'col-md-6 col-sm-12'B<'table-group-actions pull-right'>>r>t<'row'<'col-md-12 col-sm-12 text-center'p><'col-md-4 col-sm-12'>>",
        bSort: true,
        sScrollY: '300px',
        unEditedColumns: [0],
        addFunc: function (data, config) {
          var deferred = $q.defer();
          APIGateway.sendRequest($scope.dataTableConfig.resource + ".POST", data)
            .then(function (response) {
              toastr.success(tableTitles.successAddMessage);
              deferred.resolve(response.content);
            }, function (rejection) {
              deferred.reject(rejection.content);
            });
          return deferred.promise;
        },
        updateFunc: function (oldValue, data, config) {
          var deferred = $q.defer();
          data.id = oldValue.id;
          APIGateway.sendRequest($scope.dataTableConfig.resource + ".PUT", data)
            .then(function (response) {
              deferred.resolve(response.content);
            }, function (rejection) {
              deferred.reject(rejection.content);
            });
          return deferred.promise;
        },
        deleteFunc: function (params) {
          var deferred = $q.defer();
          APIGateway.sendRequest($scope.dataTableConfig.resource + ".DELETE", params)
            .then(function (response) {
              deferred.resolve(response);
            }, function (rejection) {
              deferred.reject(rejection);
            });
          return deferred.promise;
        },
        approveFunc: function (id, isApproved, data) {
          var deferred = $q.defer();
          APIGateway.sendRequest($scope.dataTableConfig.resource + "/approve.PUT", { active: isApproved, ids: [id] })
            .then(function (response) {
              deferred.resolve(response.content);
            }, function (rejection) {
              deferred.reject(rejection.content);
            });
          return deferred.promise;
        },
        editHandler: function (id) {

          var route = $scope.dataTableConfig.resource + ".edit";
          if ($scope.dataTableConfig.prefixRoute){
            route = $scope.dataTableConfig.prefixRoute + route;
          }

          $state.go(route, { id: id });
        },
      };

      var buttonsConfig = $scope.dataTableConfig.buttonsConfig;
      var buttons = [];
      var addButton = {
        text: '<i class="fa fa-plus"></i> ' + tableTitles.addNew,
        action: function (e, dt, node, config) {
          var isValidated = true;
          var validationMsg = "";
          var config = {};
          var data = {};
          var columns = $scope.dataTableConfig.columns;
          if (!$scope.dataTableConfig.isInlineEdit)
            $state.go(buttonsConfig.add);
          else {
            var id = "";
            $scope.dataTableConfig.columns.forEach(function loop(item, index) {
              validationMsg = "";
              if (item.data && $scope.dataTableConfig.unEditedColumns.indexOf(index) < 0) {
                data[item.data] = $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "']").val();
                id = "[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "']";
                if (columns[index].validation) {

                  if (columns[index].validation.maxlength) {
                    if (data[columns[index].data].length > columns[index].validation.maxlength) {
                      $(id).css('border-color', 'red');
                      validationMsg = validateMessages.ErrMaxLength +
                          '(' + validateMessages.ErrMaxLengthSpecific + ' ' + columns[index].validation.maxlength + ')' + '\n';
                      isValidated = false;
                      $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "'] + span").remove();
                      $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "']").
                      after("<span style='color:red'>" + validationMsg + "</span>")
                    }
                  }
                  if (columns[index].validation.required) {
                    if (!data[columns[index].data]) {
                      $(id).css('border-color', 'red');
                      validationMsg = validateMessages.ErrRequired + '\n';
                      //+'(' + validateMessages.ErrRequiredSpecific + ')' + '\n';

                      isValidated = false;
                      $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "'] + span").remove();
                      $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "']").
                      after("<span style='color:red'>" + validationMsg + "</span>")
                    }
                  }
                }
              }
            });
            if (isValidated) {
              $scope.dataTableConfig.addFunc(data, config).then(function (resp) {
                $element.find("input").val("");
                dt.draw();
              }, function (err) {

              });

              $scope.dataTableConfig.columns.forEach(function loop(item, index) {
                if (item.data && $scope.dataTableConfig.unEditedColumns.indexOf(index) < 0) {
                  $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "']").css('border-color', '#e7ecf1');
                  $("[name='" + item.data + $scope.dataTableConfig.nameElementSuff + "'] + span").remove();
                }
              });
            }
          }
        },
        className: 'btn green'
      };
      var refreshButton = {
        text: '<i class="fa fa-refresh"></i> ' + tableTitles.refresh,
        action: function (e, dt, node, config) {
          //if ($scope.dataTableConfig.exWthRefresh)
          //    $scope.dataTableConfig.exWthRefresh();
          dt.draw();
        },
        className: 'btn info'
      };

      var exportButton = {
        text: '<i class="fa fa-file-excel-o" aria-hidden="true"></i> ' + "Report",
        action: function (e, dt, node, config) {
          $scope.fnExport();
        },
        className: 'btn green'
      };

      var approveAllButton = {
        text: '<i class="fa fa-check-circle"></i> ' + tableTitles.approveAll,
        action: function (e, dt, node, config) {
          APIGateway.sendRequest($scope.dataTableConfig.resource + "/approve.PUT", { forAll: true, active: true })
            .then(function (response) {
              toastr.success(tableTitles.approvedMessage);
              dt.draw();
            }, function (rejection) {
              //toastr.error(tableTitles.errorMessage);
            });
        },
        className: 'btn info'
      };

      if (buttonsConfig) {
        if (buttonsConfig.exportButton){
          buttons.push(exportButton);
        }

        if (buttonsConfig.add != false) {
          buttons.push(addButton);
        }

        if (buttonsConfig.refresh != false) {
          buttons.push(refreshButton);
        }

        if (buttonsConfig.approveAll != false && $rootScope.isAdmin()) {
          buttons.push(approveAllButton);
        }
      }
      else {
        if (!$scope.dataTableConfig.buttons) {
          buttons = [addButton, refreshButton, approveAllButton];
        }
      }

      defaultConfig.buttons = buttons;

      if ($scope.dataTableConfig.searchPlaceholder) {
        $('input.search-input').attr('placeholder', $scope.dataTableConfig.searchPlaceholder);
      }

      //debugger;
      if (window.tableCaches == undefined) window.tableCaches = [];
      var oldCache = window.tableCaches[window.currentState];

      if (oldCache != undefined) {
        $scope.dataTableConfig = oldCache.tableSettings;
        for (var filter in oldCache.filters) {

          $element.find(".filter-region [filter='" + filter + "']").val(oldCache.filters[filter]);
        }
      }
      else {
        $scope.dataTableConfig = $.extend({}, defaultConfig, $scope.dataTableConfig);
        window.tableCaches[window.currentState] = { tableSettings: $scope.dataTableConfig, filters: {} };
      }

      if ($(".btn-approve") != null)
        $scope.dataTableConfig["fnRowCallback"] = function (row, aData, iDisplayIndex, iDisplayIndexFull) {
          this.find(".btn-approve").removeClass('btn-primary');
          this.find(".btn-approve").removeClass('btn-secondary');
          if (aData.approved == 1) {
            this.find(".btn-approve").addClass('btn-primary');
            this.find(".btn-approve").css('color', 'white')
          } else {
            this.find(".btn-approve").addClass('btn-secondary');
            this.find(".btn-approve").css('color', 'black');
          }
        }
      function restoreRow(oTable, nRow) {
        var aData = oTable.dataTable().fnGetData(nRow);
        var jqTds = $('>td', nRow);

        for (var i = 0, iLen = jqTds.length; i < iLen - 1; i++) {
          if (!$scope.dataTableConfig.unEditedColumns.includes(i))
            oTable.dataTable().fnUpdate(Object.byString(aData, $scope.dataTableConfig.columns[i].data), nRow, i, false);
        }
        if (typeof ($scope.dataTableConfig.columns[jqTds.length - 1].render) === 'function')
          oTable.dataTable().fnUpdate($scope.dataTableConfig.columns[jqTds.length - 1].render(), nRow, jqTds.length - 1, false);
        else
          oTable.dataTable().fnUpdate($scope.dataTableConfig.columns[jqTds.length - 1].defaultContent, nRow, jqTds.length - 1, false);

      }

      function sleep(milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
          if ((new Date().getTime() - start) > milliseconds) {
            break;
          }
        }
      }

      function editRow(oTable, nRow) {
        var aData = oTable.dataTable().fnGetData(nRow);
        var jqTds = $('>td', nRow);
        var length = nRow.cells.length;
        for (var i = 0; i < length - 1; i++) {
          if (!$scope.dataTableConfig.unEditedColumns.includes(i)) {
            if ($scope.dataTableConfig.columns[i].vtgType != null) {
              if ($scope.dataTableConfig.columns[i].vtgType == 'dropdown') {
                var legth = $scope.dataTableConfig.columns[i].vtgDataTypes.length;
                var options = "";
                for (var j = 0; j < legth; j++) {
                  options += '<option ';
                  if ($scope.dataTableConfig.columns[i].vtgDataTypes[j].value == Object.byString(aData, $scope.dataTableConfig.columns[i].data))
                    options += ' selected ';
                  options += ' value="' + $scope.dataTableConfig.columns[i].vtgDataTypes[j].value + '">'
                    + $scope.dataTableConfig.columns[i].vtgDataTypes[j].text + '</option>';
                }
                if ($scope.dataTableConfig.columns[i].plusClass) {
                  jqTds[i].innerHTML = '<select class="form-control ' + $scope.dataTableConfig.columns[i].plusClass + '" id = "dropdown-' + $scope.dataTableConfig.columns[i].data + '">' + options;
                  if ($scope.dataTableConfig.columns[i].plusClass == 'select2-table')
                    $(document).ready(function () {
                      $(".select2-table").select2();
                    });

                } else
                  jqTds[i].innerHTML = '<select class="form-control" id = "dropdown-' + $scope.dataTableConfig.columns[i].data + '">' + options;
              }
              else if ($scope.dataTableConfig.columns[i].vtgType == 'password') {
                jqTds[i].innerHTML = '<input type="password" class="form-control input-small" id = "input-' + $scope.dataTableConfig.columns[i].data + '">';
              }
            } else

              sleep(100);
            var fields = $scope.dataTableConfig.columns[i].data.split('.');

            var value = aData[fields[0]];

            if (fields.length > 1) {
              for (var j = 1; j < fields.length; j++) {
                value = value[fields[j]];
              }
            }
            jqTds[i].innerHTML = '<input type="text" class="form-control input-small" id="input-' + $scope.dataTableConfig.columns[i].data + '" value="' + value + '">';
          }
        }
        if (nNew == true) {
          jqTds[length - 1].innerHTML = '<a class="btn-submit btn-success" href="">' + tableTitles.submit + '</a>';
        } else {
          jqTds[length - 1].innerHTML = '<a class="btn btn-sm btn-edit btn-success" href="">' + tableTitles.save + '</a>';
          jqTds[length - 1].innerHTML += '<a class="btn btn-sm cancel btn-danger" href="">' + tableTitles.cancel + '</a>';
        }
      }

      function saveRow(oTable, nRow) {
        var columns = $scope.dataTableConfig.columns;
        var jqInputs = [];
        var data = oTable.dataTable().fnGetData(nRow);
        var newValue = {};
        var isValidated = true;
        var validationMsg = "";

        for (var i = 0; i < columns.length - 1; i++) {
          var id = "";
          validationMsg = "";
          if (document.getElementById('input-' + columns[i].data) != null) {
            id = '#input-' + columns[i].data;
            id = id.replace(/\./g, '\\.');
            newValue[columns[i].data] = $(id).val();
          } else if (document.getElementById('dropdown-' + columns[i].data) != null) {
            id = '#dropdown-' + columns[i].data;
            id = id.replace(/\./g, '\\.');
            newValue[columns[i].data] = $(id).val();
          }
          /**
           * Validate data
           */
          if (columns[i].validation) {

            if (columns[i].validation.maxlength) {
              if (newValue[columns[i].data].length > columns[i].validation.maxlength) {
                $(id).css('border-color', 'red');
                validationMsg = validateMessages.ErrMaxLength +
                  '(' + validateMessages.ErrMaxLengthSpecific + ' ' + columns[i].validation.maxlength + ')' + '\n';
                isValidated = false;
                $(id + "+span").remove();
                $(id).after("<span style='color:red'>" + validationMsg + "</span>")
              }
            }
            if (columns[i].validation.required) {
              if (!newValue[columns[i].data]) {
                $(id).css('border-color', 'red');
                validationMsg = validateMessages.ErrRequired + '\n';
                //'(' + validateMessages.ErrRequiredSpecific + ')' + '\n';
                isValidated = false;
              }
              $(id + "+span").remove();
              $(id).after("<span style='color:red'>" + validationMsg + "</span>")
            }
          }
        }

        var updateNewValue = function (oTable, nRow) {
          for (var i = 0; i < columns.length - 1; i++) {
            if (document.getElementById('input-' + columns[i].data) != null) {
              var id = '#input-' + columns[i].data;
              id = id.replace(/\./g, '\\.');
              oTable.dataTable().fnUpdate($(id).val(), nRow, i, false);
            } else
              if (document.getElementById('dropdown-' + columns[i].data) != null) {
                var id = '#dropdown-' + columns[i].data;
                id = id.replace(/\./g, '\\.');
                newValue[columns[i].data] = $(id).val();

                oTable.dataTable().fnUpdate($(id).val(), nRow, i, false);
              }
          }
          if (typeof ($scope.dataTableConfig.columns[columns.length - 1].render) === 'function')
            oTable.dataTable().fnUpdate($scope.dataTableConfig.columns[columns.length - 1].render(), nRow, columns.length - 1, false);
          else
            oTable.dataTable().fnUpdate($scope.dataTableConfig.columns[columns.length - 1].defaultContent, nRow, columns.length - 1, false);
        }
        var deferred = $q.defer();
        if (isValidated) {
          $scope.dataTableConfig.updateFunc(data, newValue, null).then(
            function (content) {
              oTable.fnDraw(false);
              deferred.resolve(content);
            },
            function (content) {
              restoreRow(table, nRow);
              deferred.reject(content);
            });
        };
        return deferred.promise;
      }

      function deleteRow(oTable, nRow) {
        var deferred = $q.defer();
        var data = oTable.dataTable().fnGetData(nRow);
        $scope.dataTableConfig.deleteFunc({ id: data.id }, data).then(
          function (content) {
            oTable.fnDraw(false);
            deferred.resolve(content);
          },
          function (content) {
            deferred.reject(content);
          });

        return deferred.promise;
      }

      var _that = this;
      var table = $element.find("table").first();
      this.dConfig = $scope.dataTableConfig;
      var nEditing = null;
      var nNew = false;
      if ($('.btn-approve') != null) {
        $('.btn-approve').unbind('click');
        table.on('click', '.btn-approve', function (e) {
          var that = this;
          var thatt = $(e.target);
          var nRow = $(this).parents('tr')[0];
          var data = table.dataTable().fnGetData(nRow);
          var isApproved = true;
          if (that.style.color == "white")
            isApproved = false;
          $scope.dataTableConfig.approveFunc(data.id, isApproved, data).then(function (success) {

            thatt.removeClass('btn-primary');
            thatt.removeClass('btn-secondary');
            if (isApproved) {
              that.style.color = "white";
              thatt.addClass('btn-primary');
              toastr.success(tableTitles.approvedMessage)
            }
            else {
              that.style.color = "black";
              thatt.addClass('btn-secondary');
              toastr.success(tableTitles.unApprovedMessage)
            }
          }, function (error) {
            //toastr.error(tableTitles.errorMessage);
          });
        });
      }

      $('.btnActive').unbind('click');
      table.on('click', '.btnActive', function (e) {
        var nRow = $(this).parents('tr')[0];
        var data = table.dataTable().fnGetData(nRow);
        if (data.test_status!= 3){
          toastr.error("Notification testing is not completed. You can't approve");
        }else {
          APIGateway.sendRequest($scope.dataTableConfig.resource + "/approve.PUT", {
            active: !data.status,
            ids: [data.id]
          }).then(function (success) {
            if (data.status){
              toastr.success("Testing");
            }else {
              toastr.success("Approved");
            }
            table.fnDraw();
          }, function (error) {
            table.fnDraw();
          });
        }
      })

      $('.btn-edit').unbind('click');
      if ($scope.dataTableConfig.isInlineEdit == false) {
        table.on('click', '.btn-edit', function (e) {
          var nRow = $(this).parents('tr')[0];
          var data = table.dataTable().fnGetData(nRow);


          $scope.dataTableConfig.editHandler(data.id, data);

        });
      } else
        table.on('click', '.btn-edit', function (e) {
          e.preventDefault();
          nNew = false;
          /* Get the row as a parent of the link that was clicked on */
          var nRow = $(this).parents('tr')[0];
          if (nEditing !== null && nEditing != nRow) {
            /* Currently editing - but not this row - restore the old before continuing to edit mode */
            restoreRow(table, nEditing);
            editRow(table, nRow);
            nEditing = nRow;
          } else if (nEditing == nRow && this.innerHTML == tableTitles.save) {
            /* Editing this row and want to save it */
            saveRow(table, nEditing).then(
              function (content) {
                toastr.success(tableTitles.successUpdateMessage);
                nEditing = null;
              },
              function (content) {
                if (content.validationMsg) {
                  swal(validateMessages.ErrNotValidInput, content.validationMsg, 'warning');
                } else {
                  toastr.error(tableTitles.errorMessage);
                  nEditing = null;
                }
              });
          } else {
            /* No edit in progress - let's start one */
            editRow(table, nRow);
            nEditing = nRow;
          }
        });

      //This function handles the event of clicking 'remove' button
      table.on('click', '.btn-remove', function (e) {
        e.preventDefault();

        var nRow = $(this).parents('tr')[0];
        swal({
          title: tableTitles.deleteMessage,
          text: "",
          type: "warning",
          allowOutsideClick: false,
          showConfirmButton: true,
          showCancelButton: true,
          confirmButtonClass: "btn-danger",
          cancelButtonClass: "btn-default",
          closeOnConfirm: false,
          closeOnCancel: true,
          confirmButtonText: tableTitles.delete,
          cancelButtonText: tableTitles.cancel,
        },
          function (isConfirm) {
            if (isConfirm) {
              deleteRow(table, nRow).then(function (response) {
                toastr.success(tableTitles.successDeleteMessage);
                table.fnDraw();
              }, function (reject) {
                //toastr.error(tableTitles.errorMessage);
              });
              swal.close();
            } else {
              swal.close();
            }
          });
      });
      table.on('click', '.cancel', function (e) {
        e.preventDefault();
        if (nNew) {
          table.fnDeleteRow(nEditing);
          nEditing = null;
          nNew = false;
        } else {
          table.fnDraw(false);
          //restoreRow(table, nEditing);
          nEditing = null;
        }
      });
      table.on('click', '.btn-submit', function (e) {
        addRow(table, nEditing).then(
          function (content) {
            toastr.success(tableTitles.successAddMessage);
          },
          function (content) {
            table.fnDeleteRow(this);
          });
        nEditing = null;
      });
    },
    link: function ($scope, $element, $attrs, controller) {
      //$scope.dataTableInstance = $element.find("table").dataTable(controller.dConfig);
      $scope.dataTableConfig.displayStart = $scope.dataTableConfig._iDisplayStart;
      $scope.dataTableConfig.pageLength = $scope.dataTableConfig._iDisplayLength;
      $scope.dataTableInstance = $element.find("table").dataTable($scope.dataTableConfig);

      controller.dataTableInstance = $scope.dataTableInstance;

      if ($scope.dataTableConfig.watchFunc && typeof ($scope.dataTableConfig.watchFunc) === 'function')
        $scope.dataTableConfig.watchFunc($scope.dataTableInstance);

      $element.find(".search-button").click(function (e) {
        $scope.dataTableInstance.fnDraw();
      });

      $element.find(".search-input").keypress(function (e) {
        if (e.keyCode == 13) {
          $scope.dataTableInstance.fnDraw();
        }
      });

      $element.find(".filter-region select").change(function (e) {
        $scope.dataTableInstance.fnDraw();
      });

      $element.find(".filter-region input:checkbox").change(function (e) {
        $scope.dataTableInstance.fnDraw();
      });
    }
  };
})

ViettelMyVTG.directive('summernote', function ($compile, $q) {

  return {
    restrict: "EA",
    scope: {
      ngModel: "=",
      name: "@",
      mode: "@"
    },
    template: '',
    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {
      var textarea = $element;
      $(textarea).summernote({
        height: 200,
        callbacks: {
          onChange: function (contents, $editable) {

            if ($scope.firstInit == undefined) {
              $scope.firstInit = false;
            }
            else {
              if (contents) contents = contents.trim();
              if (contents.startsWith("<p>") && contents.endsWith("</p>")) {
                contents = contents.substr(3, contents.length - 7);
              }
              this.value = contents;
              validator.element(this);

              $scope.ngModel = contents;
              _.defer(function () {
                $scope.$apply();
              });
            }
          },
          onPaste: function (e) {
            validator.element($(textarea)[0]);
          },
          onKeyup: function (e) {
            validator.element($(textarea)[0]);
          }
        }
      });


      if ($scope.mode == "code") {
        $(textarea).summernote('codeview.toggle');
      }

      $element.closest(".form-group").find(".note-codable").on('keypress', function (e) {
        var contents = ($(textarea).summernote('code') + String.fromCharCode(e.keyCode)).trim();
        $scope.ngModel = contents;
        $element.val(contents);
        validator.element($(textarea));
        $scope.$apply();
      });

      if ($scope.ngModel) {
        $(textarea).summernote('code', $scope.ngModel);
      }
      else {
        $(textarea).summernote('code', "");
      }


      $scope.$watch('ngModel', function (newValue, oldValue) {
        if (oldValue == undefined) {
          $(textarea).summernote('code', newValue);
        }
      });
    }
  };
})

ViettelMyVTG.directive('datepicker', function ($compile, $q) {
  return {
    restrict: "A",
    scope: {
      //ngModel: "=",
      name: "@",
      ngModel: "="
    },
    template: '<input style="cursor:pointer" name={{name}} value={{outputValue}} type="text" size="16"  class="form-control"><span class="input-group-addon"><button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button></span><div class="form-control-focus"></div>',

    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {

      if ($scope.ngModel != undefined && $scope.ngModel != "") {
        var d = new Date($scope.ngModel);
        $scope.outputValue = d.getDate() + "/" + (d.getMonth() + 1) + "/" + d.getFullYear();
      }

      var datepicker = $element.datepicker({
        format: 'dd/mm/yyyy',
        rtl: App.isRTL(),
        orientation: "bottom left",
        autoclose: true,
        pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
        minView: 2,
        startDate: '01/01/2000',
        endDate: '31/12/3000',
        //initialDate: "20/10/2016"
      }).on("changeDate", function (e) {

        var $this = this;
        var item = $($this.element).find('input');

        setTimeout(function(){
          validator.element(item);
        },500);

        if (e.date != undefined && e.date != "") {
          $scope.ngModel = e.date.valueOf();
        }

        _.defer(function () {
          $scope.$apply();
        });
      }.bind(object = { element: $element, scope: $scope }));

      $scope.$watch('ngModel', function (newValue, oldValue) {

        if (newValue != undefined && oldValue == undefined) {
          datepicker.datepicker('setDate', new Date(newValue));
        }
      });
    }
  };
})

ViettelMyVTG.directive('datepickerfilter', function ($compile, $q) {
  return {
    restrict: "A",
    scope: {
      name: "@",
      ngModel: "="
    },
    template: '<input  style="cursor:pointer" filter={{name}} name={{name}} value={{outputValue}} type="text" size="16"  class="form-control inputDateTime"><span class="input-group-addon"><button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button></span><div class="form-control-focus"></div>',
    controller: function ($scope, $element, $state) {},
    link: function ($scope, $element, $attrs, controller) {
      if ($scope.ngModel != undefined) {
        if ($scope.ngModel == "") {
          $element.find('input').attr('value', "");
        } else {
          var d = new Date($scope.ngModel);
          $scope.outputValue = d.getDate() + "/" + (d.getMonth() + 1) + "/" + d.getFullYear();
        }
      }

      var datepicker = $element.datepicker({
        format: 'dd/mm/yyyy',
        rtl: App.isRTL(),
        orientation: "bottom left",
        autoclose: true,
        pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left"),
        minView: 2,
        startDate: '01/01/2000',
        endDate: '31/12/3000',
      }).on("changeDate", function (e) {
        var $this = this;
        var item = $($this.element).find('input');
        var date = new Date(e.date);
        var dateVal = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();

        $(item).attr("value", dateVal);
        $scope.ngModel = dateVal;

        _.defer(function () {
          $scope.$apply();
        });
      }.bind(object = {
        element: $element,
        scope: $scope
      }));

      datepicker.on("clearDate", function () {
        $element.find('input').attr("value", "");
        $scope.ngModel = "";
      })

      $scope.$watch('ngModel', function (newValue, oldValue) {
        if (newValue) {
          datepicker.datepicker('setDate', new Date(newValue));
        }
      });
    }
  };
})

ViettelMyVTG.directive('input', function ($compile) {
  return {
    restrict: "E",
    scope: {
      name: "@",
      ngModel: "="
    },

    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {
      if ($element.attr("type") != "file") {
        $element.on('change', function (e) {
          var content = $(e.target).val().trim();
          //content = content.replace(/(<([^>]+)>)/ig, "");
          $(e.target).val(content);
          $scope.$apply();
        });
      }
    }
  };
})

ViettelMyVTG.directive('select2Filter', function ($compile, $q) {
  return {
    restrict: "A",
    scope: {
      name: "@",
      ngModel: "=",
      getData: "&",
      placeholder: "@",
      includeAll: "@"
    },
    //template: '<option ng-if="includeAll==undefined"></option><option ng-select="x.id==ngModel" value="{{x.id}}" ng-repeat="x in data">{{x.name}}</option>',
    template: '<option ng-if="includeAll==undefined"></option><option ng-select="x.id==ngModel" value="{{x.id}}" ng-repeat="x in data">{{x.name}}</option>',

    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {
      $scope.getData({}).then(function (resp) {

        var config = {};

        if ($scope.includeAll != undefined) {
          var all = [{ id: "", name: translations["tat_ca"] }];
          $scope.data = all.concat(resp);
        }
        else {
          $scope.data = resp;
          config.placeholder = $scope.placeholder;
        }

        //config.data = $scope.data.map(function (item) {
        //  item.text = item.name;
        //  return item;
        //});
        $scope.$apply();
        $element.select2(config);
        $element.siblings(".select2-container").addClass("input-sm");
        $element.select2('val', $scope.ngModel);
      });
    }
  };
})

ViettelMyVTG.directive('select2', function ($compile, $q) {

  return {
    restrict: "A",
    scope: {
      name: "@",
      ngModel: "=",
      getData: "&",
      placeholder: "@",
      includeAll: "@",
      refreshCounter: "="
    },
    // template: '<option ng-if="includeAll==undefined"></option><option ng-select="x.id==ngModel" value="{{x.id}}" ng-repeat="x in data">{{x.name}}</option>',
    template: '<option></option>',

    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {
      $element.select2().on("change", function (e) {
        validator.element(this);
      });


      function init() {
        $scope.getData({}).then(function (resp) {

          var config = {};

          if ($scope.includeAll != undefined) {
            var all = [{ id: "", name: "Tất cả" }];
            $scope.data = all.concat(resp);
          }
          else {
            $scope.data = resp;
            config.placeholder = $scope.placeholder;
          }
          config.data = $scope.data.map(function (item) {
            item.text = item.name;

            return item;
          });

          $scope.select2 = $element.prepend('<option></option>').select2(config);

          if ($scope.ngModel) {
            var filter = $scope.data.filter(function (item) {
              return item.id == $scope.ngModel;
            });
            if (filter.length > 0) {
              $element.val($scope.ngModel).trigger('change');
            }
            else {
              $element.val("").trigger('change');
            }
          }

          $element.siblings(".select2-container").addClass("input-sm");
        });
      }

      $scope.$watch('refreshCounter', function (newValue, oldValue) {
        if (oldValue != undefined) {
          $element.select2().empty();
          init();
        }
      });

      init();

    }
  };
})

ViettelMyVTG.directive('fileinput', function ($compile, $q, $translate, APIGateway) {
  function base64(file, callback) {
    var coolFile = {};
    function readerOnload(e) {
      var base64 = btoa(e.target.result);
      coolFile.base64 = base64;
      callback(coolFile)
    };

    var reader = new FileReader();
    reader.onload = readerOnload;

    var file = file[0].files[0];
    coolFile.filetype = file.type;
    coolFile.size = file.size;
    coolFile.filename = file.name;
    reader.readAsBinaryString(file);
  }

  return {
    restrict: "A",
    scope: {
      name: "@",
      ngModel: "=",
      folder: "@",
      mode: "@",
    },
    template: '<div class="fileinput-preview thumbnail"  style="width: 100px; height: 100px; line-height: 150px;"><img ng-show="ngModel" src="{{ngModel}}" /></div>'
    + '<div style="width:200px"><span class="btn red btn-outline btn-file"><span class="fileinput-new"> {{"chon_anh"|translate}} </span><span class="fileinput-exists"> {{"doi_anh"|translate}} </span>'
    + '<input type="file" accept="image/*" class="{{class}}" /></span><a href="javascript:;" class="btn red fileinput-exists" data-dismiss="fileinput"> {{"xoa_anh"|translate}} </a></div><input name="{{name}}" type="text" ng-model="ngModel" placeholder="' + translations["duong_dan_anh"] + '" />',
    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {
      if ($scope.mode == "sua") $scope.class = "ignore";
      else $scope.class = "";


      $element.find("input[type='file']").change(function (e) {

        $scope.class = "";


        var fileName = $element.find("input[type='file']").val();

        var ext = fileName.substr(fileName.lastIndexOf('.') + 1);
        ext = ext.toLowerCase();
        var folderName = $scope.folder;
        base64($element.find("input[type='file']"), function (data) {
          APIGateway.sendRequest('uploadImage.POST', { content: data.base64, folder: folderName, extension: ext }).then(function (resp) {
            $scope.ngModel = resp.data;
            $scope.$apply();
            $element.find("input[name='" + $scope.name + "']").focus();
            $element.find("input[name='" + $scope.name + "']").blur();
          },
            function (err) {
              toastr.error("Image upload error");
            })
        })
      });

      //window.setTimeout(function () {
      //  //debugger
      //  this.$element.find("input[type='file']").val(this.$scope.ngModel);
      //}.bind({$element:$element,$scope:$scope}), 500);

      //$element.bind("change", function (changeEvent) {
      //    var reader = new FileReader();
      //    reader.onload = function (loadEvent) {
      //        scope.$apply(function () {
      //            scope.fileread = loadEvent.target.result;
      //        });
      //    }
      //    reader.readAsDataURL(changeEvent.target.files[0]);
      //  });

    }
  };
})

ViettelMyVTG.directive('filterLanguage', function ($compile, $q) {
  return {
    restrict: "A",
    scope: {
      name: "@",
    },
    template: config.langFilterHtml,
    controller: function ($scope, $element, $state) {

    },
    link: function ($scope, $element, $attrs, controller) {

    }
  };
})

ViettelMyVTG.directive('radioLanguage', function ($compile, $q) {
  return {
    restrict: "A",
    scope: {
      name: "@",
      ngModel: "="
    },
    template: config.radioLanguageTemplate,

    controller: function ($scope, $element, $state) {
    },
    link: function ($scope, $element, $attrs, controller) {

    }
  };
})

ViettelMyVTG.directive('convertNumber', function () {
  return {
    require: 'ngModel',
    link: function (scope, el, attr, ctrl) {
      ctrl.$parsers.push(function (value) {
        return parseInt(value, 10);
      });

      ctrl.$formatters.push(function (value) {
        if (!value) return;
        return value.toString();
      });
    }
  }
});
