$('.skin-minimal input').iCheck({
    checkboxClass: 'icheckbox-blue',
    radioClass: 'iradio-blue',
    increaseArea: '20%'
});

function setTagMap(TAG_MAP) {
    localStorage.setItem("TAG_MAP", JSON.stringify(TAG_MAP))
}

function getTagMap() {
    return localStorage.getItem("TAG_MAP") ? JSON.parse(localStorage.getItem("TAG_MAP")) : {}
}

var selectListParams = {},
    pageInitTag = true,
    append_html,
    DATA_LIST = [],
    DATA_ITEM,
    DATA_ITEM_ID,
    DATA_URL_selectListByPageHelper,
    DATA_URL_delete,
    DATA_URL_update,
    DATA_URL_insert,
    DATA_URL_selectById,
    VIEW_PATH_ADD,
    VIEW_PATH_EDIT;

selectListParams.pageNum = 1;
selectListParams.pageSize = 10;


function initPath(
    init_DATA_URL_selectListByPageHelper,
    init_DATA_URL_delete,
    init_DATA_URL_update,
    init_DATA_URL_insert,
    init_DATA_URL_selectById,
    init_VIEW_PATH_ADD,
    init_VIEW_PATH_EDIT,
) {
    DATA_URL_selectListByPageHelper = init_DATA_URL_selectListByPageHelper;
    DATA_URL_delete = init_DATA_URL_delete;
    DATA_URL_update = init_DATA_URL_update;
    DATA_URL_insert = init_DATA_URL_insert;
    DATA_URL_selectById = init_DATA_URL_selectById;
    VIEW_PATH_ADD = init_VIEW_PATH_ADD;
    VIEW_PATH_EDIT = init_VIEW_PATH_EDIT;
}


function resetPageBox() {
    pageInitTag = true;
    $('.M-box3').html("");
}

function doSelectList(requestParams, initAppendHtml, expandBtn, successFun) {
    $('#mytbody').html("加载中");
    ajaxPost(
        DATA_URL_selectListByPageHelper,
        requestParams,
        function (response) {
            var $showTotal = $("#show_total");
            $showTotal.text("0");
            if (response.status === 1) {
                $showTotal.text(response.pageInfo.total);
                layer.msg("共获取：" + response.pageInfo.total + "条数据", {time: 2000});
                var html = "";
                var item = null;
                for (var i = 0; i < response.data.length; i++) {
                    item = response.data[i];
                    DATA_LIST[item.id] = item;
                    html += initAppendHtml(item);
                }
                $('#mytbody').html(html);

                $('#mytbody tr').click(function (e) {
                    if (e.target.type === 'checkbox') {
                        return;
                    }
                    var $objCheckBox = $(this).find("td").eq(0).find(".checkItemTag");
                    $objCheckBox.prop("checked", !$objCheckBox.prop("checked"));
                    fun_check_checked();
                });
                $(".checkItemTag").click(function () {
                    fun_check_checked();
                });
                $("#checkBoxGroup").on("click", function () {
                    $(this).closest("table").find("tr > td:first-child input:checkbox").prop("checked", $("table thead th input:checkbox").prop("checked"));
                    fun_check_checked();
                });
                if (pageInitTag) {
                    pageInitTag = false;
                    $('.M-box3').pagination({
                        pageCount: response.pageInfo.pages, jump: true, coping: true,
                        callback: function (api) {
                            requestParams.pageNum = api.getCurrent();
                            doSelectList(requestParams, initAppendHtml, expandBtn, successFun)
                        }
                    });
                }
                successFun && successFun(response)
            } else {
                $('#mytbody').html('');
                layer.msg(response.message);
            }
            successFun&&successFun(response)
        });

    $("#btn_search").on("click", function () {
        var $inSearchText = $("#in_search_text");
        if ($inSearchText.val()) {
            resetPageBox()
            selectListParams["pageNum"] = 1;
            selectListParams["pageSize"] = 10;
            selectListParams["keyWord"] = $inSearchText.val();
            doSelectList(selectListParams, initAppendHtml, expandBtn, successFun)
            // doSelectList(selectListParams, initAppendHtml);
        } else {
            layer.tips('关键字不能为空', $inSearchText, {
                tips: [1, '#cc3139'],
                time: 3000
            });
        }
    });

    $("#btn_group a").click(function () {
        var code = $(this).attr("click-info"),
            itemId,// for edit only
            count = 0,
            waitDisposeArr = [],//待处理
            currObj = {};
        $(".checkItemTag").each(function () {
            if ($(this).prop("checked")) {
                currObj = $(this);
                itemId = $(this).parents("tr").attr('id')
                count++;
                waitDisposeArr.push(
                    {
                        id: itemId,
                        obj: currObj
                    }
                );
            }
        });
        switch (code) {
            case "add":
                layer_show_full('添加', VIEW_PATH_ADD, '', '510');
                break;
            case "importDate":
                layer_show('批量导入', VIEW_PATH_IMPORT, '', '300');
                break;
            case "downloadImportModel":
                var $form = $('<form method="GET"></form>');
                $form.attr('action', DATA_URL_downloadTemplate);
                $form.appendTo($('body'));
                $form.submit();
                break;
            case 'delete':
                layer.confirm('确认要删除吗？', function (index) {
                    for (var i = 0; i < waitDisposeArr.length; i++) {
                        var item = waitDisposeArr[i];
                        ajaxPost(DATA_URL_delete, {id: item.id}, function (obj) {
                            if (obj.status === 1) {
                                $(item.obj).parents("tr").remove();
                                layer.msg('已删除!', {icon: 1, time: 1000});
                            }
                        });
                    }
                    waitDisposeArr = []
                });
                break;
            case "edit":
                if (count === 1 && itemId) {
                    DATA_ITEM_ID = itemId;
                    layer_show_full('编辑', VIEW_PATH_EDIT, '', '510');
                } else {
                    layer.msg('请勿多选!', {icon: 5, time: 1000});
                }
                break;
            default :
        }

        if (expandBtn) {
            expandBtn(code, count, itemId, currObj, waitDisposeArr)
        }
    });
}


function fun_check_checked() {
    var $single = $(".single-choice"),
        $multiple = $(".multiple-choice"),
        itemId,
        count = 0;

    $(".checkItemTag").each(function () {
        if ($(this).prop("checked")) {
            itemId = $(this).parents("tr").attr('id');
            count++;
        }
    });
    if (itemId && count === 1) {
        $single.addClass("btn-primary");
        $single.removeClass("disabled");
        $multiple.addClass("btn-primary");
        $multiple.removeClass("disabled");
    } else if (itemId && count > 1) {
        $single.removeClass("btn-primary");
        $single.addClass("disabled");
        $multiple.addClass("btn-primary");
        $multiple.removeClass("disabled");
    } else {
        $single.addClass("disabled");
        $single.removeClass("btn-primary");
        $multiple.addClass("disabled");
        $multiple.removeClass("btn-primary");
    }
}


/**
 *  添加一个对象
 * @param validateObject
 * @param Rules 校验规则
 */
function doInsertChild(validateObject, Rules) {
    $(validateObject).validate({
        rules: Rules,
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var jsonArray = $(form).serializeArray();
            var mParameters = {};
            for (var i = 0; i < jsonArray.length; i++) {
                mParameters[jsonArray[i].name] = jsonArray[i].value;
            }
            ajaxPost(parent.DATA_URL_insert, mParameters, function (obj) {
                if (obj.status === 1) {
                    window.parent.location.reload();
                } else {
                    layer.alert(obj.message)
                }
            });
        }
    });
}

function doUpdateChild(validateObject, Rules, url) {
    if (!url) {
        url = parent.DATA_URL_update
    }
    $(validateObject).validate({
        rules: Rules,
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function (form) {
            var jsonArray = $(form).serializeArray();
            var mParameters = {};
            for (var i = 0; i < jsonArray.length; i++) {
                mParameters[jsonArray[i].name] = jsonArray[i].value;
            }
            mParameters['id'] = parent.DATA_ITEM_ID;
            ajaxPost(url, mParameters, function (obj) {
                if (obj.status === 1) {
                    window.parent.location.reload();
                } else {
                    layer.alert(obj.message)
                }
            });
        }
    });
}

function doSelectOneByIdChild(responseSuccess) {
    ajaxPost(parent.DATA_URL_selectById, {id: parent.DATA_ITEM_ID}, function (obj) {
        var data = obj.data;
        if (obj.status === 1) {
            responseSuccess(data)
        } else {
            layer.alert(obj.message);
        }
    });
}


function importByExcel() {
    let $list = $('#thelist');
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '/lib/my0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: parent.DATA_URL_insertByExcel,
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#container_file',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 开起分片上传。
        chunked: true
    });

    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {
        $list.append(
            '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '</div>');
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }
        var show_percentage = percentage * 100;
        show_percentage = show_percentage.toFixed(2) + '%'
        $li.find('p.state').text('上传中 ' + show_percentage);
        $percent.css('width', percentage * 100 + '%');
    });
    uploader.on('uploadSuccess', function (file) {
        $('#' + file.id).find('p.state').text('已上传');
        layer_close()
    });
    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });
    uploader.on('uploadComplete', function (file) {
        // $('#' + file.id).find('.progress').fadeOut();
    });
    $("#btn_submit").click(function () {
        uploader.upload();
    });
};

////////////////////////////////////////////////////////////////////////////////////

var num = 0, oUl = $("#min_title_list"), hide_nav = $("#Hui-tabNav");

/*获取顶部选项卡总长度*/
function tabNavallwidth() {
    var taballwidth = 0,
        $tabNav = hide_nav.find(".acrossTab"),
        $tabNavWp = hide_nav.find(".Hui-tabNav-wp"),
        $tabNavitem = hide_nav.find(".acrossTab li"),
        $tabNavmore = hide_nav.find(".Hui-tabNav-more");
    if (!$tabNav[0]) {
        return
    }
    $tabNavitem.each(function (index, element) {
        taballwidth += Number(parseFloat($(this).width() + 60))
    });
    $tabNav.width(taballwidth + 25);
    var w = $tabNavWp.width();
    if (taballwidth + 25 > w) {
        $tabNavmore.show()
    } else {
        $tabNavmore.hide();
        $tabNav.css({left: 0});
    }
}

/*左侧菜单响应式*/
function Huiasidedisplay() {
    if ($(window).width() >= 768) {
        $(".Hui-aside").show();
    }
}

/*获取皮肤cookie*/
function getskincookie() {
    var v = $.cookie("Huiskin");
    var hrefStr = $("#skin").attr("href");
    if (v == null || v === "") {
        v = "default";
    }
    if (hrefStr !== undefined) {
        var hrefRes = hrefStr.substring(0, hrefStr.lastIndexOf('skin/')) + 'skin/' + v + '/skin.css';
        $("#skin").attr("href", hrefRes);
    }
}

function Hui_admin_tab_cas(href, title) {
    var bStop = false,
        bStopIndex = 0,
        topWindow = $(window.parent.document),
        show_navLi = topWindow.find("#min_title_list li"),
        iframe_box = topWindow.find("#iframe_box");
    if (!href || href === "") {
        alert("未配置data-href属性");
        return false;
    }
    if (!title) {
        alert("v2.5版本之后使用data-title属性");
        return false;
    }
    if (title === "") {
        alert("data-title属性不能为空");
        return false;
    }
    show_navLi.each(function () {
        if ($(this).find('span').attr("data-href") === href) {
            bStop = true;
            bStopIndex = show_navLi.index($(this));
            return false;
        }
    });
    if (!bStop) {
        creatIframe(href, title);
        min_titleList();
    } else {
        show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
        iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", href);
    }
}

/**
 * 菜单导航
 * @return {boolean}
 */
function Hui_admin_tab(obj) {
    var bStop = false,
        bStopIndex = 0,
        href = $(obj).attr('data-href'),
        title = $(obj).attr("data-title"),
        topWindow = $(window.parent.document),
        show_navLi = topWindow.find("#min_title_list li"),
        iframe_box = topWindow.find("#iframe_box");
    if (!href || href === "") {
        alert("未配置data-href属性");
        return false;
    }
    if (!title) {
        alert("v2.5版本之后使用data-title属性");
        return false;
    }
    if (title === "") {
        alert("data-title属性不能为空");
        return false;
    }
    show_navLi.each(function () {
        if ($(this).find('span').attr("data-href") === href) {
            bStop = true;
            bStopIndex = show_navLi.index($(this));
            return false;
        }
    });
    if (!bStop) {
        creatIframe(href, title);
        min_titleList();
    } else {
        show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
        iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src", href);
    }
}

/*最新tab标题栏列表*/
function min_titleList() {
    var topWindow = $(window.parent.document),
        show_nav = topWindow.find("#min_title_list"),
        aLi = show_nav.find("li");
}

/*创建iframe*/
function creatIframe(href, titleName) {
    var topWindow = $(window.parent.document),
        show_nav = topWindow.find('#min_title_list'),
        iframe_box = topWindow.find('#iframe_box'),
        iframeBox = iframe_box.find('.show_iframe'),
        $tabNav = topWindow.find(".acrossTab"),
        $tabNavWp = topWindow.find(".Hui-tabNav-wp"),
        $tabNavmore = topWindow.find(".Hui-tabNav-more");
    var taballwidth = 0;

    show_nav.find('li').removeClass("active");
    show_nav.append('<li class="active"><span data-href="' + href + '">' + titleName + '</span><i></i><em></em></li>');
    if ('function' == typeof $('#min_title_list li').contextMenu) {
        $("#min_title_list li").contextMenu('Huiadminmenu', {
            bindings: {
                'closethis': function (t) {
                    var $t = $(t);
                    if ($t.find("i")) {
                        $t.find("i").trigger("click");
                    }
                },
                'closeall': function (t) {
                    $("#min_title_list li i").trigger("click");
                },
            }
        });
    } else {

    }
    var $tabNavitem = topWindow.find(".acrossTab li");
    if (!$tabNav[0]) {
        return
    }
    $tabNavitem.each(function (index, element) {
        taballwidth += Number(parseFloat($(this).width() + 60))
    });
    $tabNav.width(taballwidth + 25);
    var w = $tabNavWp.width();
    if (taballwidth + 25 > w) {
        $tabNavmore.show()
    } else {
        $tabNavmore.hide();
        $tabNav.css({left: 0})
    }
    iframeBox.hide();
    iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src=' + href + '></iframe></div>');
    var showBox = iframe_box.find('.show_iframe:visible');
    showBox.find('iframe').load(function () {
        showBox.find('.loading').hide();
    });
}


/*关闭iframe*/
function removeIframe() {
    var topWindow = $(window.parent.document),
        iframe = topWindow.find('#iframe_box .show_iframe'),
        tab = topWindow.find(".acrossTab li"),
        showTab = topWindow.find(".acrossTab li.active"),
        showBox = topWindow.find('.show_iframe:visible'),
        i = showTab.index();
    tab.eq(i - 1).addClass("active");
    tab.eq(i).remove();
    iframe.eq(i - 1).show();
    iframe.eq(i).remove();
}

/*关闭所有iframe*/
function removeIframeAll() {
    var topWindow = $(window.parent.document),
        iframe = topWindow.find('#iframe_box .show_iframe'),
        tab = topWindow.find(".acrossTab li");
    for (var i = 0; i < tab.length; i++) {
        if (tab.eq(i).find("i").length > 0) {
            tab.eq(i).remove();
            iframe.eq(i).remove();
        }
    }
}

/*弹出层*/

/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title, url, w, h) {
    if (title == null || title === '') {
        title = false;
    }
    if (url == null || url === '') {
        url = "404.html";
    }
    if (w == null || w === '') {
        w = 800;
    }
    if (h == null || h === '') {
        h = ($(window).height() - 50);
    }
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
}

function layer_show_full(title, url, w, h) {
    if (title == null || title === '') {
        title = false;
    }
    if (url == null || url === '') {
        url = "404.html";
    }
    if (w == null || w === '') {
        w = 800;
    }
    if (h == null || h === '') {
        h = ($(window).height() - 50);
    }
    var index = layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
    //我修改了 gs
    layer.full(
        index
    );
}

/*关闭弹出框口*/
function layer_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

/*时间*/
function getHTMLDate(obj) {
    var d = new Date();
    var weekday = new Array(7);
    var _mm = "";
    var _dd = "";
    var _ww = "";
    weekday[0] = "星期日";
    weekday[1] = "星期一";
    weekday[2] = "星期二";
    weekday[3] = "星期三";
    weekday[4] = "星期四";
    weekday[5] = "星期五";
    weekday[6] = "星期六";
    _yy = d.getFullYear();
    _mm = d.getMonth() + 1;
    _dd = d.getDate();
    _ww = weekday[d.getDay()];
    obj.html(_yy + "年" + _mm + "月" + _dd + "日 " + _ww);
};

var isOrNotArr = {
    "0": {
        "class": "label label-default radius",
        "val": "否",
        "html": '<span class="label label-default radius pd-5">&nbsp;&nbsp;否&nbsp;&nbsp;</span>'
    },
    "1": {
        "class": "label label-primary radius",
        "val": "是",
        "html": '<span class="label label-primary radius pd-5">&nbsp;&nbsp;是&nbsp;&nbsp;</span>'
    },
}

$(function () {
    getHTMLDate($("#top_time"));
    getskincookie();
    //layer.config({extend: 'extend/layer.ext.js'});
    Huiasidedisplay();
    var resizeID;
    $(window).resize(function () {
        clearTimeout(resizeID);
        resizeID = setTimeout(function () {
            Huiasidedisplay();
        }, 500);
    });

    $(".nav-toggle").click(function () {
        $(".Hui-aside").slideToggle();
    });
    $(".Hui-aside").on("click", ".menu_dropdown dd li a", function () {
        if ($(window).width() < 768) {
            $(".Hui-aside").slideToggle();
        }
    });
    /*左侧菜单*/
    $(".Hui-aside").Huifold({
        titCell: '.menu_dropdown dl dt',
        mainCell: '.menu_dropdown dl dd',
    });

    /*选项卡导航*/
    $(".Hui-aside").on("click", ".menu_dropdown a", function () {

        Hui_admin_tab(this);
    });

    $(document).on("click", "#min_title_list li", function () {
        var bStopIndex = $(this).index();
        var iframe_box = $("#iframe_box");
        $("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
        iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
    });
    $(document).on("click", "#min_title_list li i", function () {
        var aCloseIndex = $(this).parents("li").index();
        $(this).parent().remove();
        $('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
        num == 0 ? num = 0 : num--;
        tabNavallwidth();
    });
    $(document).on("dblclick", "#min_title_list li", function () {
        var aCloseIndex = $(this).index();
        var iframe_box = $("#iframe_box");
        if (aCloseIndex > 0) {
            $(this).remove();
            $('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
            num == 0 ? num = 0 : num--;
            $("#min_title_list li").removeClass("active").eq(aCloseIndex - 1).addClass("active");
            iframe_box.find(".show_iframe").hide().eq(aCloseIndex - 1).show();
            tabNavallwidth();
        } else {
            return false;
        }
    });
    tabNavallwidth();

    $('#js-tabNav-next').click(function () {
        num == oUl.find('li').length - 1 ? num = oUl.find('li').length - 1 : num++;
        toNavPos();
    });
    $('#js-tabNav-prev').click(function () {
        num == 0 ? num = 0 : num--;
        toNavPos();
    });

    function toNavPos() {
        oUl.stop().animate({'left': -num * 100}, 100);
    }

    /*换肤*/
    $("#Hui-skin .dropDown-menu a").click(function () {
        var v = $(this).attr("data-val");
        $.cookie("Huiskin", v);
        var hrefStr = $("#skin").attr("href");
        var hrefRes = hrefStr.substring(0, hrefStr.lastIndexOf('skin/')) + 'skin/' + v + '/skin.css';
        $(window.frames.document).contents().find("#skin").attr("href", hrefRes);
    });


});
