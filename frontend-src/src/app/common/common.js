
function showToast(mes, title, timeOut) {
  var i = -1,
           toastCount = 0,
           $toastlast,
           getMessage = function () {
             var msgs = ['Hello, some notification sample goes here',
                 '<div><input class="form-control input-small" value="textbox"/>&nbsp;<a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes" target="_blank">Check this out</a></div><div><button type="button" id="okBtn" class="btn blue">Close me</button><button type="button" id="surpriseBtn" class="btn default" style="margin: 0 8px 0 8px">Surprise me</button></div>',
                 'Did you like this one ? :)',
                 'Totally Awesome!!!',
                 'Yeah, this is the Metronic!',
                 'Explore the power of App. Purchase it now!'
             ];
             i++;
             if (i === msgs.length) {
               i = 0;
             }

             return msgs[i];
           };
  var shortCutFunction = "success";
  var msg = "success";
  var title = title || "";
  var toastIndex = toastCount++;

  if (!msg) {
    msg = "Success";
  }
  //$("#toastrOptions").text("Command: toastr[" + shortCutFunction + "](\"" + msg + (title ? "\", \"" + title : '') + "\")\n\ntoastr.options = " + JSON.stringify(toastr.options, null, 2));

  var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
  $toastlast = $toast;
  if ($toast.find('#okBtn').length) {
    $toast.delegate('#okBtn', 'click', function () {
      alert('you clicked me. i was toast #' + toastIndex + '. goodbye!');
      $toast.remove();
    });
  }
  if ($toast.find('#surpriseBtn').length) {
    $toast.delegate('#surpriseBtn', 'click', function () {
      alert('Surprise! you clicked me. i was toast #' + toastIndex + '. You could perform an action here.');
    });
  }
}

toastr.options = {
  "closeButton": true,
  "debug": false,
  "positionClass": "toast-top-full-width",
  "onclick": null,
  "showDuration": "1000",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
};

//Get value from object by it's path
Object.byString = function (o, s) {
  s = s.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
  s = s.replace(/^\./, '');           // strip a leading dot
  var a = s.split('.');
  for (var i = 0, n = a.length; i < n; ++i) {
    var k = a[i];
    if (k in o) {
      o = o[k];
    } else {
      return;
    }
  }
  return o;
}

var myApp = {

  auth:(localStorage.getItem("auth")==null)?{}:JSON.parse(localStorage.getItem("auth")),
  isAdmin: function () {

    if (myApp.auth.role && myApp.auth.role[0].roleCode == "CALL_CMS") {
      return true;
    }
    return false;
  },
  escapeHtml: function escapeHtml(unsafe) {
    return unsafe.replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
  },

  htmlEncode:function(value){
    return $('<div/>').text(value).html();
  },
  strimStringToShort: function (des, maxlength) {
    if (des != null) {
      if (des.length < maxlength)
        return des;
      else
        return des.slice(0, maxlength) + "...";
    } else
      return "";
  },
  renderLink: function (link) {

    return '<a href="' + link + '">'+translations["lien_ket"]+'</a>';
  },
  renderActions: function (full) {
    if (full && full.action == "")
      return "";
    else {
      if (myApp.isAdmin()) {

        if (full) {

          if (full.approved == undefined) {
            return "<button  class='glyphicon btn btn-success btn-sm glyphicon-pencil btn-edit'></button><button class='glyphicon btn btn-danger btn-sm glyphicon-remove-circle btn-remove' ></button>";
          }
          else if (full.approved == 1)
            return "<button  class='glyphicon btn btn-success btn-sm glyphicon-pencil btn-edit'></button><button class='glyphicon btn btn-danger btn-sm glyphicon-remove-circle btn-remove' ></button><button class='glyphicon btn btn-primary btn-sm glyphicon-ok btn-approve ' style='color:white' ></button>";
          else
            return "<button  class='glyphicon btn btn-success btn-sm glyphicon-pencil btn-edit'></button><button class='glyphicon btn btn-danger btn-sm glyphicon-remove-circle btn-remove' ></button><button class='glyphicon btn btn-secondary btn-sm glyphicon-ok btn-approve ' style='color:black' ></button>";
        }
      }
      else {
        return "<button  class='glyphicon btn btn-success btn-sm glyphicon-pencil btn-edit'></button>";
      }
    }
  },
  convertDateFormat: function (dateString, notUTC) {
    if (dateString == "")
      return "";
    var format = config.timeFormat;
    var date = new Date(dateString);
    var day = "";
    var month = "";
    var year = "";
    if (notUTC) {
      day = date.getDate();
      month = date.getMonth() + 1;
      year = date.getFullYear();
      month = ("0" + month).slice(-2);
      day = ("0" + day).slice(-2);
    } else {
      day = date.getUTCDate();
      month = date.getUTCMonth() + 1;
      year = date.getUTCFullYear();
      month = ("0" + month).slice(-2);
      day = ("0" + day).slice(-2);
    }
    var convertedString = format.replace('dd', day);
    convertedString = convertedString.replace('mm', month);
    convertedString = convertedString.replace('yyyy', year);
    return convertedString;
  },
  trimContent: function (content) {
    if (!content || content == "null") return "";

    content = myApp.escapeHtml(content);
    if (content.length > config.maxLengthDisplay) {
      return content.substr(0, config.maxLengthDisplay) + "...";
    }
    else return content;
  },

  decodeHtml:function(content){
    return $('<textarea></textarea>').html(content).text();
  },

  validateForm: function (selector, config) {
    var defaultConfig = {
      errorElement: 'div', //default input error message container
      errorClass: 'help-block help-block-error', // default input error message class
      focusInvalid: true, // do not focus the last invalid input
      ignore: ".ignore", // validate all fields including form hidden input
      errorPlacement: function (error, element) {
        if (element.is(':checkbox')) {
          error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
        } else if (element.is(':radio')) {
          error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
        } else if (element.is('select')) {
          error.insertAfter(element.closest(".form-group").find(".select2-container"));
        }
        else if (element.is('textarea')) {
          error.insertAfter(element.closest(".form-group").find(".note-editor"));
        }
        else if (element.is(':file')) {

          error.insertAfter(element.closest(".fileinput"));
        }
        else {

          error.insertAfter(element); // for other inputs, just perform default behavior
        }

      },
      highlight: function (element, errorClass, validClass) {
        $(element)
            .closest('.form-group').addClass('has-error'); // set error class to the control group

      },
      unhighlight: function (element, errorClass, validClass) { // revert the change done by hightlight
        $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
      },
      submitHandler: function (form) {
        success1.show();
        error1.hide();
      }
    };
    return $(selector).validate($.extend({}, defaultConfig, config));
  },

  getInlineFormData: function (formElement) {
    var data = {};
    var valid = true;
    formElement.find("input, select").each(function (index, e) {
      if (e.value == undefined || e.value == "") {
        valid = false;
      }
      else {
        data[e.name] = e.value;
      }
    });

    if (valid == false) {
      toastr.error("Vui lòng nhập đầy đủ thông tin!");
      return null;
    }

    return data;
  },

  goToLoginPage: function () {
    window.location = configuration.basePath + "login.html";
  },
  goHome: function () {
    window.location = configuration.basePath;
  },
  logout: function () {
    localStorage.removeItem('auth');
    myApp.goToLoginPage();
  },
  renderImage: function (imageUrl) {

    if (imageUrl == null || imageUrl == "") {
      return "";
    }
    else return '<img height="24px" width="32px" src="' + imageUrl + '"/>';
  },
  notiLoginRequire: function () {
    toastr.error(notiMessages.loginRequire);
  }
}

var FormiCheck = function () {
  return {
    //main function to initiate the module
    init: function () {

      $('.icheck-colors li').click(function () {
        var self = $(this);

        if (!self.hasClass('active')) {
          self.siblings().removeClass('active');

          var skin = self.closest('.skin'),
            color = self.attr('class') ? '-' + self.attr('class') : '',
            colorTmp = skin.data('color') ? '-' + skin.data('color') : '-grey',
            colorTmp = (colorTmp === '-black' ? '' : colorTmp);

          checkbox_default = 'icheckbox_minimal',
          radio_default = 'iradio_minimal',
          checkbox = 'icheckbox_minimal' + colorTmp,
          radio = 'iradio_minimal' + colorTmp;

          if (skin.hasClass('skin-square')) {
            checkbox_default = 'icheckbox_square';
            radio_default = 'iradio_square';
            checkbox = 'icheckbox_square' + colorTmp;
            radio = 'iradio_square' + colorTmp;
          };

          if (skin.hasClass('skin-flat')) {
            checkbox_default = 'icheckbox_flat';
            radio_default = 'iradio_flat';
            checkbox = 'icheckbox_flat' + colorTmp;
            radio = 'iradio_flat' + colorTmp;
          };

          if (skin.hasClass('skin-line')) {
            checkbox_default = 'icheckbox_line';
            radio_default = 'iradio_line';
            checkbox = 'icheckbox_line' + colorTmp;
            radio = 'iradio_line' + colorTmp;
          };

          skin.find('.icheck').each(function () {
            var element = $(this).hasClass('state') ? $(this) : $(this).parent();
            var element_class = element.attr('class').replace(checkbox, checkbox_default + color).replace(radio, radio_default + color);
            element.attr('class', element_class);
          });

          skin.data('color', self.attr('class') ? self.attr('class') : 'black');
          self.addClass('active');
        };
      });
    }
  };
}();
jQuery.validator.addMethod("phone", function (value, element) {

  // allow any non-whitespace characters as the host part
  return this.optional(element) || /^\+*\d{1,20}$/.test(value);
}, 'Please enter a valid phone number.');

jQuery.validator.addMethod("intCheck", function (value, element) {

  return this.optional(element) || !isNaN(value) && (parseFloat(value) === parseInt(value, 10));

}, 'Please enter a integer value.');


jQuery.validator.addMethod("urlLink", function (value, element) {
    // allow any non-whitespace characters as the host part
    return this.optional(element) || /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/.test(value);
}, 'Please enter a valid URL.');

jQuery.validator.addMethod("expireDate_larger_effectDate", function (value, element, param) {

    var elemTime = $(element).val().split('/');
    var effectTime = param.split('/');
    return parseInt(elemTime[0]) >= parseInt(effectTime[0]) && parseInt(elemTime[1]) >= parseInt(effectTime[1]) && parseInt(elemTime[2]) >= parseInt(effectTime[2]);


}, 'Expired date should be greater or equal than  Effect date.');
_.debounce(myApp.notiLoginRequire, 10000, true);
jQuery.validator.addMethod("imageLink", function (value, element) {
  // allow any non-whitespace characters as the host part
  return this.optional(element) || /(https?:)?\/?[^'"<>]+?\.(jpg|jpeg|gif|png)/.test(value);
}, 'Please enter a valid image URL.');

//_.debounce(myApp.notiLoginRequire, 10000, true);

