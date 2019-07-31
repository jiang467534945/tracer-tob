/**
 * 组合订单结果页引导遮罩效果
 */
$(function () {
    function bodyScroll(event){
        event.preventDefault();
    }
    //有遮罩不让滚动
    if ($('#mask').css('display') == 'block') {
        //禁止页面滚动
        document.body.addEventListener('touchmove',bodyScroll, { passive: false });
        $('body').css({'position':'fixed',"width":"100%"});

      //  $('body').css('overflow-y','hidden');
        $('.shu').css('pointer-events','none');
        $(this).hide()
        $('.header2').show()
        $('.header2').addClass("disable");
        $('.histoy').addClass('disable');

        var width = $(".layui-unselect").width();
        console.log(width);
    }
    $('.next').click(function () {
        $('.zhidao').hide();
        $('.anniu').css('position','static')
        $('.result_menu').addClass('menu_fixted')
        $('.zhiyin1').css('display','block')
        $('.zhidao1').show()
        $('.know').show()
        $('.zhiyin').hide()
        $(this).hide()
        $('.header2').hide()

    })
    $('.know').click(function () {
        $(this).hide()
        $('.know').hide()
        $('#mask').css('display','none')
        $('.zhidao1').hide()

        $('.result_menu').removeClass('menu_fixted')
        //$('body').css('overflow-y','scroll');
        //允许页面滚动
        document.body.removeEventListener('touchmove',bodyScroll,false);
        $("body").css({"position":"initial","height":"auto"});

        $('.zhiyin1').hide();
        $('.shu').css('pointer-events','auto');
        $('.histoy').removeClass('disable');

    })

    $('.pay_block').click(function () {
        $(".pay_select").removeClass("pay_select")
        $(".pay_block").children().removeClass("red_line");
        $(this).addClass("pay_select")
        $(this).children().addClass("red_line");
    })
    $(".shu").each(function () {
        var $e = $(this)
        var index = $e.index()
        if(4<index){
            $e.addClass("js-hidden-menu")
            $e.css("width","23%")
        }
    })

    $(".js-hidden-menu").hide()

    $(".js-toggle-menu-btn").click(function () {
        $(".js-hidden-menu").toggle()
        $(".zhegai").toggle()
        var hed_height = $('.header').height();
        var menu_height = $('.result_menu').height();
        var zhe_height = parseInt(hed_height + menu_height) +'px'
        $(".zhegai").css('top',zhe_height)
        $('.banner_content p').toggle()
        if ($('.zhegai').css('display') == 'block') {
           // $('body').css('overflow-y','hidden');
            //禁止页面滚动
            document.body.addEventListener('touchmove',bodyScroll, { passive: false });
            $('body').css({'position':'fixed',"width":"100%"});
        }else {
          //  $('body').css('overflow-y','scroll');
            document.body.removeEventListener('touchmove',bodyScroll,false);
            $("body").css({"position":"initial","height":"auto"});
        }
    })
    //点击遮罩
    $(".zhegai").click(function () {
        $(".js-toggle-menu-btn").click();
    })

    //没有产品选择下拉框时，修改高度
    if ($(".histoy").length == 0) {
        $(".page_banner").css('margin-top',0);
    }
})



//监听用户下拉框改变事件
layui.use('form', function(){
    var form = layui.form();
    //注意 select(xx),xx必须与lay-filter相同
    form.on('select(username)', function(data){
        var orderid = data.value;
        location.href='https://imgffcs.smxs.com/order/result?orderid='+orderid;
    })
});





