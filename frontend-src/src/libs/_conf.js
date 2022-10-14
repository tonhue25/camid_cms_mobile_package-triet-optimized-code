var getBasePath = function () {
    if (window.location.origin.indexOf("localhost:50411") > 0) {
        return "/WebContent/";
    }
    else if (window.location.origin.indexOf("localhost:3000") < 0) {
        return "/myvtg-frontend/";
    }
    else {
        return "/";
    }
}

//Nhận 1 trong các giá trị sau: local | production
var environment = "production"

var configuration = {
  basePath: getBasePath(),
	prefix: "856",
	appCode: "MyVTG_CMS"
}

if (environment == "local") {
  configuration.baseAPI = "http://183.182.100.231:8123/ApigwGateway/CoreService/";
}
else if (environment == "production") {
  configuration.baseAPI = "http://10.120.47.19:8123/ApigwGateway/CoreService/";
}

//else if (environment == "test") {
//  configuration.hostAPI = "http://125.212.204.244:8080/ApigwGateway/CoreService/UserRouting/";
//  configuration.loginAPI = "http://125.212.204.244:8080/ApigwGateway/CoreService/UserLogin";
//}

var languageConfig = {
  //firstLang: "vi",
  //firstLangText: "Tiếng việt",

  firstLang: "lo",
  firstLangText: "Lao",

  secondLang: "en",
  secondLangText: "English",
}

var config = {
    headers: {
        'Content-Type': "application/json;charset=UTF-8"
    },
    //langVnCode: "vi",
    //langEnCode: "en",
    timeFormat: "dd/mm/yyyy",
    maxLengthDisplay:50,
    tableLangDropdown : {
      vtgDataTypes: [{ text: "vi", value: "vi" },
      { text: "en", value: "en" }]
    },
    viLanguage: "vi",
    enLanguage: "en",

    langFilterHtml: '<label class="control-label" id="language-text">' + translations["ngon_ngu"] + '</label><select class="form-control input-sm search-input" filter="language"><option value="">' + translations["tat_ca"] + '</option><option value="'+languageConfig.firstLang+'">' + languageConfig.firstLangText + '</option><option value="en">' + languageConfig.secondLangText + '</option></select>',
    langTableInlineForm: "<select class='form-control input-sm' name='language-table'><option value='" + languageConfig.firstLang + "'>" + languageConfig.firstLang + "</option><option value='" + languageConfig.secondLang + "'>" + languageConfig.secondLang + "</option> </select>",
    radioLanguageTemplate: '<div class="form-group form-md-radios"><label class="col-md-4 control-label text-right" for="form_control_1">Language</label><div class="col-md-8"><div class="md-radio-inline"><div class="md-radio"><input type="radio" name="{{name}}" id="{{name}}1" value="' + languageConfig.firstLang + '" ng-model="ngModel" class="md-radiobtn"><label for="{{name}}1"><span></span><span class="check"></span><span class="box"></span>' + languageConfig.firstLang + '</label></div>'
    + '<div class="md-radio"><input type="radio" id="{{name}}2" name="language" value="' + languageConfig.secondLang + '" ng-model="ngModel" class="md-radiobtn"><label for="{{name}}2"><span></span><span class="check"></span><span class="box"></span> ' + languageConfig.secondLang + '</label></div></div></div></div>',

    validate: {
      length2:2,
      length5: 5,
      length6:6,
      length10: 10,
      length20: 20,
      length50: 50,
      length100: 100,
      length200: 200,
      length1000: 1000,
      length2000: 2000,
      length4000:4000
    }
};
