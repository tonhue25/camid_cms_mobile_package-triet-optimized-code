'use strict';

angular.module('ViettelMyVTG').factory('AppService', function ($http, $state) {

    var pad = function (n, width, z) {
        z = z || '0';
        n = n + '';
        return n.length >= width ? n : new Array(width - n.length + 1).join(z) + n;
    }

    return {
        pad: pad,

        getUrl: function (routeName, params, options) {
            return $state.href(routeName, params, options);
        },

        convertToLocalDate: function (isoDateString, plusTime) {
            if (isoDateString != undefined) {
                if (!isoDateString.endsWith("Z")) isoDateString = isoDateString + "Z";

                var d = new Date(isoDateString);

                var extraTime = 0;

                if (plusTime != undefined) extraTime = plusTime;

                var localTime = d.getTime() + extraTime;

                var localDate = new Date(localTime);
                //var time = pad(localDate.getHours(), 2) + ":" + pad(localDate.getMinutes(), 2) + ":" + pad(localDate.getSeconds(), 2);
                //var result = localDate.toLocaleDateString() + " " + time;

                var result = pad(localDate.getDate(), 2) + "/" + pad(localDate.getMonth() + 1, 2) + "/" + pad(localDate.getFullYear(), 4) + " " + pad(localDate.getHours(), 2) + ":" + pad(localDate.getMinutes(), 2) + ":" + pad(localDate.getSeconds(), 2);

                return result;
            } else {
                return "";
            }

        },
        convertToISODate: function (localDateString) {

            if (localDateString!= null && localDateString != undefined && localDateString != "") {
                var spaceIndex = localDateString.indexOf(" ");
                var time = "";
                var dateArray;
                if (spaceIndex >= 0) {
                    dateArray = localDateString.substring(0, spaceIndex).split("/")
                }
                else {
                    dateArray = localDateString.split("/");
                }

                var date = dateArray[2] + "/" + dateArray[1] + "/" + dateArray[0];

                if (spaceIndex >= 0) {
                    time = " " + localDateString.substring(spaceIndex + 1, localDateString.length);
                }
                var d = new Date(date + time);
                var localTime = d.getTime() + 420 * 60000;
                var localDate = new Date(localTime);
                var result = localDate.toISOString();
                if (result.indexOf("Z") >= 0) {
                    result = result.substring(0, result.length - 1);
                }
                return result;
            } else {
                return "";
            }
        },

        formatNumber: function (toFormat) {
            if (toFormat == undefined) return "";
            return toFormat.toString().replace(
              /\B(?=(\d{3})+(?!\d))/g, "."
            );
        },

        timeSince: function (isoDateString) {
            if (isoDateString!=null && isoDateString!="" && isoDateString != undefined) {
                if (!isoDateString.endsWith("Z")) isoDateString = isoDateString + "Z";

                var timeStamp = new Date(isoDateString);

                var now = new Date(),
                    secondsPast = (now.getTime() - timeStamp.getTime()) / 1000;
                if (secondsPast < 60) {
                    return parseInt(secondsPast) + 's';
                }
                if (secondsPast < 3600) {
                    return parseInt(secondsPast / 60) + 'm';
                }
                if (secondsPast <= 86400) {
                    return parseInt(secondsPast / 3600) + 'h';
                }
                if (secondsPast > 86400) {
                    var day = timeStamp.getDate();
                    var month = timeStamp.toDateString().match(/ [a-zA-Z]*/)[0].replace(" ", "");
                    var year = timeStamp.getFullYear() == now.getFullYear() ? "" : " " + timeStamp.getFullYear();
                    return day + " " + month + year;
                }
            }
            else {
                return "";
            }
        }
    }
});



