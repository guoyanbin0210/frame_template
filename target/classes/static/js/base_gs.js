var basePath = ""
var base_share_img_url = ' '

function setTempGolKeyWord(tempGolKeyWord) {
    localStorage.setItem("tempGolKeyWord", tempGolKeyWord);
}

function getTempGolKeyWord() {
    return localStorage.getItem("tempGolKeyWord");
}

function removeTempGolKeyWord() {
    localStorage.removeItem("tempGolKeyWord");
}

// 存储
function setLoginUserInfo(LoginUserInfo) {
    localStorage.setItem("LoginUserInfo", JSON.stringify(LoginUserInfo));
}

// 取回
function getLoginUserInfo() {
    return JSON.parse(localStorage.getItem("LoginUserInfo"));
}

// 清空用户信息缓存
function removeLoginUserInfo() {
    localStorage.removeItem("LoginUserInfo");
}

function setLoginToken(token) {
    localStorage.setItem("loginToken", token)
}

function getLoginToken() {
    return localStorage.getItem("loginToken");
}

function removeLoginToken() {
    localStorage.removeItem("loginToken");

}

function ajaxGetAsync(url, data, success) {
    $.ajax(getAjaxOption("get", true, url, data, success));
}


function ajaxGet(url, data, success) {
    $.ajax(getAjaxOption("get", false, url, data, success));

}

function ajaxPostAsync(url, data, success, error) {
    $.ajax(getAjaxOption("post", true, url, data, success));

}

function ajaxPost(url, data, success, error) {
    $.ajax(getAjaxOption("post", false, url, data, success));
}


function getAjaxOption(type, async, url, data, success) {
    return {
        type: type,
        async: async,
        url: basePath + url,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: data,
        success: success,
        timeout: 10000,
        beforeSend: function (XHR) {

            // 发送请求前可修改 XMLHttpRequest 对象的函数，如添加自定义 HTTP 头。
            // XMLHttpRequest 对象是唯一的参数。
            // 这是一个 Ajax 事件。如果返回 false 可以取消本次 ajax 请求。
            if (getLoginToken()) {
                XHR.setRequestHeader("Authentication", getLoginToken());
            }
        },
        error: function (xhr, errorText, errorType) {
            console.log("网络请求失败:", JSON.parse(xhr.responseText));
            return false;
        },
        // complete: function (XHR, TS) {
        //     // 请求完成后回调函数 (请求成功或失败之后均调用)。
        //     // 参数： XMLHttpRequest 对象和一个描述请求类型的字符串。
        //     // 这是一个 Ajax 事件。
        //
        // }
    }
}

/**
 *
 * @param name
 * @returns {string}
 */
function getUrlParamByMatch(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    } else {
        return "";
    }
}

function myFormat(time, format) {
    if (!format) {
        format = "yyyy-MM-dd"
    } else if (format === "ss") {
        format = "yyyy-MM-dd HH:mm:ss"
    } else if (format === "SSS") {
        format = "yyyy-MM-dd HH:mm:ss:SSS"
    }
    if (time) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss|SSS/g,
            function (a) {
                switch (a) {
                    case 'yyyy':
                        return tf(t.getFullYear());
                    case 'MM':
                        return tf(t.getMonth() + 1);
                    case 'mm':
                        return tf(t.getMinutes());
                    case 'dd':
                        return tf(t.getDate());
                    case 'HH':
                        return tf(t.getHours());
                    case 'ss':
                        return tf(t.getSeconds());
                    case 'SSS':
                        return tf(t.getMilliseconds());
                }
            });
    } else {
        return "";
    }
};

function getWeekDay(date) {
    var week;
    if (date.getDay() === 0) week = "星期日";
    if (date.getDay() === 1) week = "星期一";
    if (date.getDay() === 2) week = "星期二";
    if (date.getDay() === 3) week = "星期三";
    if (date.getDay() === 4) week = "星期四";
    if (date.getDay() === 5) week = "星期五";
    if (date.getDay() === 6) week = "星期六";
    return week;
}

function doLogout() {

    ajaxGet("/base/doLogout.do", {}, function (obj) {

        location.href = "../html/login.html";
        // location.reload();
        removeLoginToken();
        removeLoginUserInfo();

    });

}

/**
 * 检查有否存在登陆信息
 * @returns {*}
 */
function checkLoginUserInfo() {
    var loginUserInfo = getLoginUserInfo();
    if (loginUserInfo) {
        return loginUserInfo;
    } else {
        alert("未检测到登录信息");
        location.href = "../html/login.html";
        location.reload();

    }
}

function myParseDate(dateStr) {
    dateStr = dateStr.substring(0, 19);
    dateStr = dateStr.replace(/-/g, '/');
    var timestamp = new Date(dateStr).getTime();
    return timestamp;
}

/**
 *  审核状态
 * @param code
 * @returns {string}
 */
function doCheckAudit(code) {
    let result = "";
    if (code == 0) {
        result = '<span class="label label-default radius">待审核</span>';
    } else if (code == 1) {
        result = '<span class="label label-success radius">通过</span>';
    } else if (code == 10) {
        result = '<span class="label label-warning radius">拒绝</span>';
    } else if (code == 20) {
        result = '<span class="label label-default radius">二次 待审核</span>';
    }
    return result;
}



/**
 *  打开指定菜单的 fragment
 * @param _layer
 * @param id
 */
function openLayerFragment(id, param) {
    if (param.keyWord) {
        setTempGolKeyWord(param.keyWord)
    }

    window.parent.$(".Hui-aside .menu_dropdown a").each(function () {
        if ($(this).context.id == id) {//打开 评论 选项卡
            Hui_admin_tab($(this).context);
            if (param._layer) {
                layer.close(param._layer);
            }
            return false;
        }


    });
}

