layui.use('layer', function () {
    var $ = layui.$, layer = layui.layer;
    $('#userInfo').click(function () {
        layer.open({
            type: 2,
            area: ['750px', '400px'],
            fix: false,
            maxmin: false,
            shade: 0.4,
            title: '个人信息',
            content: ctx + '/user/info/',
            end: function () {
            }
        });
    });
    $('#userInfoPassword').click(function () {
        layer.open({
            type: 2,
            area: ['750px', '400px'],
            fix: false,
            maxmin: false,
            shade: 0.4,
            title: '个人信息',
            content: ctx + '/user/info/password',
            end: function () {
            }
        });
    });
});


var isShow = true;  //定义一个标志位
$('.kit-side-fold').click(function () {
    //选择出所有的span，并判断是不是hidden
    $('.layui-nav-item span').each(function () {
        if ($(this).is(':hidden')) {
            $(this).addClass("introhide");
        } else {
            $(this).removeClass("introhide");
        }
    });
    //判断isshow的状态
    if (isShow) {
        $('.layui-side.layui-bg-black').width(60); //设置宽度
        $('.kit-side-fold i').css('margin-right', '70%');  //修改图标的位置
        //将footer和body的宽度修改
        $('.layui-body').css('left', 60 + 'px');
        $('.layui-footer').css('left', 60 + 'px');
        $('#LAY_app_tabs').css('left', 60 + 'px');
        $('.layui-layout-admin .layui-layout-left').css('left', 60 + 'px');
        $('.layui-layout-admin .layui-logo').css('width', 60 + 'px');
        $('.logohis').addClass('introhide');
        $('.logohow').show();
        //alert(1);
        $('.kit-side-fold').addClass("layui-icon-spread-left");
        //将二级导航栏隐藏
        $('dd span').each(function () {
            $(this).addClass("introhide");
            $('.layui-nav-itemed dl').addClass("introhide");
            $('.layui-nav-item cite').addClass("introhide");
        });
        //修改标志位

        isShow = false;
    } else {
        $('.layui-side.layui-bg-black').width(220);
        $('.kit-side-fold i').css('margin-right', '10%');
        $('.layui-body').css('left', 220 + 'px');
        $('.layui-footer').css('left', 220 + 'px');
        $('#LAY_app_tabs').css('left', 220 + 'px');
        $('.layui-layout-admin .layui-layout-left').css('left', 220 + 'px');
        $('.layui-layout-admin .layui-logo').css('width', 220 + 'px');
        $('.logohis').removeClass('introhide');
        $('.logohow').hide();
        //alert(2);
        $('.kit-side-fold').removeClass("layui-icon-spread-left");
        $('dd span').each(function () {
            $(this).removeClass("introhide");
            $('.layui-nav-itemed dl').removeClass("introhide");
            $('.layui-nav-item cite').removeClass("introhide");
        });

        isShow = true;
    }
});
$('.cl-1').click(function () {
    $('.layui-side.layui-bg-black').width(220);
    $('.kit-side-fold i').css('margin-right', '10%');
    $('.layui-body').css('left', 220 + 'px');
    $('.layui-footer').css('left', 220 + 'px');
    $('#LAY_app_tabs').css('left', 220 + 'px');
    $('.layui-layout-admin .layui-layout-left').css('left', 220 + 'px');
    $('.layui-layout-admin .layui-logo').css('width', 220 + 'px');
    $('.logohis').removeClass('introhide');
    $('.logohow').hide();
    //alert(250);
    $('.kit-side-fold').removeClass("layui-icon-spread-left");
    $('dd span').each(function () {
        $(this).removeClass("introhide");
        $('.layui-nav-itemed dl').removeClass("introhide");
        $('.layui-nav-item cite').removeClass("introhide");
    });
    isShow = true;
})

layui.use('element', function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    //监听导航点击
    element.on('nav(demo)', function (elem) {
        //console.log(elem)
        layer.msg(elem.text());
    });
});

/**
 * 菜单处理
 */
$(function () {
    // MetsiMenu
    $('#side-menu').metisMenu();

    //固定菜单栏
    $(function () {
        $('.sidebar-collapse').slimScroll({
            height: '100%',
            railOpacity: 0.9,
            alwaysVisible: false
        });
    });

    // 菜单切换
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    $('#side-menu>li').click(function () {
        if ($('body').hasClass('mini-navbar')) {
            NavToggle();
        }
    });
    $('#side-menu>li li a').click(function () {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });

    $('.nav-close').click(NavToggle);

    //ios浏览器兼容性处理
    if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
        $('#content-main').css('overflow-y', 'auto');
    }

});

$(window).bind("load resize",
    function () {
        if ($(this).width() < 769) {
            $('body').addClass('mini-navbar');
            $('.navbar-static-side').fadeIn();
        }
    });

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')) {
        $('#side-menu').hide();
        setTimeout(function () {
                $('#side-menu').fadeIn(500);
            },
            100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(function () {
                $('#side-menu').fadeIn(500);
            },
            300);
    } else {
        $('#side-menu').removeAttr('style');
    }
}

/**
 * iframe处理
 */
$(function () {
    //计算元素集合的总宽度
    function calSumWidth(elements) {
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }

    //滚动到指定选项卡
    function scrollToTab(element) {
        var marginLeftVal = calSumWidth($(element).prevAll()),
            marginRightVal = calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            },
            "fast");
    }

    //查看左侧隐藏的选项卡
    function scrollTabLeft() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).prev();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
            }
        }
        $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            },
            "fast");
    }

    //查看右侧隐藏的选项卡
    function scrollTabRight() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
            if (scrollVal > 0) {
                $('.page-tabs-content').animate({
                        marginLeft: 0 - scrollVal + 'px'
                    },
                    "fast");
            }
        }
    }

    //通过遍历给菜单项加上data-index属性
    $(".menuItem").each(function (index) {
        if (!$(this).attr('data-index')) {
            $(this).attr('data-index', index + 1);
        }
    });

    function menuItem() {
        // 获取标识数据
        var dataUrl = $(this).attr('href'),
            dataIndex = $(this).data('index'),
            menuName = $.trim($(this).text()),
            flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

        // 选项卡菜单已存在
        $('.menuTab').each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('layui-this')) {
                    $(this).addClass('layui-this').siblings('.menuTab').removeClass('layui-this');
                    scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.mainContent .RuoYi_iframe').each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.RuoYi_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });
        // 选项卡菜单不存在
        if (flag) {
            //var str = '<a href="javascript:;" class="layui-this menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle">1</i></a>';
            var str = '<li data-id="' + dataUrl + '" href="javascript:;" class="layui-this menuTab">' + menuName + '<i class="layui-tab-close custom-font">&times;</i></li>';
            $('.menuTab').removeClass('layui-this');

            // 添加选项卡对应的iframe
            var str1 = '<iframe class="RuoYi_iframe cus-frame" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.mainContent').find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);

            // $.modal.loading("数据加载中，请稍后...");

//             $('.mainContent iframe:visible').load(function () {
//             	$.modal.closeLoading();
//             });

            // 添加选项卡
            $('.menuTabs .page-tabs-content').append(str);
            scrollToTab($('.menuTab.layui-this'));
        }
        return false;
    }

    function IndexMenuItem() {
        // 获取标识数据
        var dataUrl = ctx + "/redirect?page=welcome",
            dataIndex = 0,
            menuName = "主页";
        //var str = '<a href="javascript:;" class="layui-this menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle">1</i></a>';
        var str = '<li data-id="' + dataUrl + '" href="javascript:;" class="layui-this menuTab">' + menuName + '<i class="layui-tab-close custom-font">&times;</i></li>';
        $('.menuTab').removeClass('layui-this');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="RuoYi_iframe cus-frame" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.mainContent').find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);

        // 添加选项卡
        $('.menuTabs .page-tabs-content').append(str);
        scrollToTab($('.menuTab.layui-this'));
        return false;
    }

    IndexMenuItem();
    $('.menuItem').on('click', menuItem);

    // 关闭选项卡菜单
    function closeTab() {
        var closeTabId = $(this).parents('.menuTab').data('id');
        var currentWidth = $(this).parents('.menuTab').width();

        // 当前元素处于活动状态
        if ($(this).parents('.menuTab').hasClass('layui-this')) {

            // 当前元素后面有同辈元素，使后面的一个元素处于活动状态
            if ($(this).parents('.menuTab').next('.menuTab').size()) {

                var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
                $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('layui-this');

                $('.mainContent .RuoYi_iframe').each(function () {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });

                var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                if (marginLeftVal < 0) {
                    $('.page-tabs-content').animate({
                            marginLeft: (marginLeftVal + currentWidth) + 'px'
                        },
                        "fast");
                }

                //  移除当前选项卡
                $(this).parents('.menuTab').remove();

                // 移除tab对应的内容区
                $('.mainContent .RuoYi_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }

            // 当前元素后面没有同辈元素，使当前元素的上一个元素处于活动状态
            if ($(this).parents('.menuTab').prev('.menuTab').size()) {
                var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
                $(this).parents('.menuTab').prev('.menuTab:last').addClass('layui-this');
                $('.mainContent .RuoYi_iframe').each(function () {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });

                //  移除当前选项卡
                $(this).parents('.menuTab').remove();

                // 移除tab对应的内容区
                $('.mainContent .RuoYi_iframe').each(function () {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }
        }
        // 当前元素不处于活动状态
        else {
            //  移除当前选项卡
            $(this).parents('.menuTab').remove();

            // 移除相应tab对应的内容区
            $('.mainContent .RuoYi_iframe').each(function () {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
            scrollToTab($('.menuTab.layui-this'));
        }
        return false;
    }

    $('.menuTabs').on('click', '.menuTab i', closeTab);

    //关闭其他选项卡
    function closeOtherTabs() {
        var welcomeUrl = ctx + "/redirect?page=welcome";
        $('.page-tabs-content').children("[data-id]").not(".layui-this").each(function () {
            if ($(this).data('id') == welcomeUrl) {
                return;
            }
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').css("margin-left", "0");
        $(this).parent().removeClass('layui-this');
    }

    $('.tabCloseOther').on('click', closeOtherTabs);

    //滚动到已激活的选项卡
    function showActiveTab() {
        scrollToTab($('.menuTab.layui-this'));
    }

    $('.tabShowActive').on('click', showActiveTab);

    // 点击选项卡菜单
    function activeTab() {
        if (!$(this).hasClass('layui-this')) {
            var currentId = $(this).data('id');
            // 显示tab对应的内容区
            $('.mainContent .RuoYi_iframe').each(function () {
                if ($(this).data('id') == currentId) {
                    $(this).show().siblings('.RuoYi_iframe').hide();
                    return false;
                }
            });
            $(this).addClass('layui-this').siblings('.menuTab').removeClass('layui-this');
            scrollToTab(this);
        }
    }

    // 点击选项卡菜单
    $('.menuTabs').on('click', '.menuTab', activeTab);

    //刷新iframe
    function refreshTab() {
        var currentId = $('.page-tabs-content').find('.layui-this').attr('data-id');
        var target = $('.RuoYi_iframe[data-id="' + currentId + '"]');
        var url = target.attr('src');
        target.attr('src', url).ready();
    }

    // 全屏显示
    $('#fullScreen').on('click', function () {
        $('#wrapper').fullScreen();
    });

    // 刷新按钮
    $('.tabReload').on('click', refreshTab);

    // 双击选项卡全屏显示
    $('.menuTabs').on('dblclick', '.menuTab', activeTabMax);

    // 左移按扭
    $('.tabLeft').on('click', scrollTabLeft);

    // 右移按扭
    $('.tabRight').on('click', scrollTabRight);

    // 关闭当前
    $('.tabCloseCurrent').on('click', function () {
        $('.page-tabs-content').find('.layui-this i').trigger("click");
        $(this).parent().removeClass('layui-this');
    });

    // 关闭全部
    $('.tabCloseAll').on('click', function () {
        var welcomeUrl = ctx + "/redirect?page=welcome";
        $('.page-tabs-content').children("[data-id]").each(function () {
            if ($(this).data('id') == welcomeUrl) {
                return;
            }
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function () {
            $('.RuoYi_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("layui-this");
        });
        $('.page-tabs-content').css("margin-left", "0");
        // $(".RuoYi_iframe").css('display', 'block');
        $('#home').addClass('layui-this');
        $(this).parent().removeClass('layui-this');
    });

    // tab全屏显示
    $('.tabMaxCurrent').on('click', function () {
        $('.page-tabs-content').find('.layui-this').trigger("dblclick");
    });

    // 关闭全屏
    $('#ax_close_max').click(function () {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').hide();
    })

    // 双击选项卡全屏显示
    function activeTabMax() {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').show();
    }

    $(window).keydown(function (event) {
        if (event.keyCode == 27) {
            $('#content-main').removeClass('max');
            $('#ax_close_max').hide();
        }
    });
});