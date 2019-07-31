// 使用模块
layui.use(['form', 'element', 'laydate'], function(){
    var jQuery = layui.jquery
    ,layer = layui.layer
    ,form = layui.form()
    ,element = layui.element();

    //结婚吉日折叠版监听
    element.on('collapse(zedie)', function(data){
        jQuery(".layui-colla-title").each(function () {
            jQuery(this).removeClass("layui-colla-titled");
        });
        jQuery(".date_arrow").each(function () {
            jQuery(this).removeClass("date_arrow90");
        });
        if(data.show){
            jQuery(this).addClass("layui-colla-titled");
            jQuery(this).children(".date_arrow").addClass("date_arrow90");
        }
        $("html,body").animate({scrollTop:800}, 200);
    });
    // 表单验证规则
    form.verify({
        username: function(value){
            var value = jQuery.trim(value);
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            if (value == ''){
                return '姓名不能为空';
            }
            if(!new RegExp("^[\u4e00-\u9fa5s]+$").test(value)){
                return '姓名不是汉字';
            }
            if(value.length > 5 || value.length < 2){
                return '姓名需2-5个汉字';
            }
            if(value.length > 5 || value.length < 2){
                return '姓名需2-5个汉字';
            }
        }
        ,birthday: function(value){
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            if (jQuery.trim(value) == ''){
                return '生日不能为空';
            }
        }
        ,xing : function(value){
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            if (value == ''){
                return '姓氏不能为空';
            }
            if(!new RegExp("^[\u4e00-\u9fa5s]+$").test(value)){
                return '姓氏必须为汉字';
            }
            if(value.length > 2 || value.length < 1){
                return '姓氏为1-2个汉字';
            }
        }
        ,ming : function(value){
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            if (value == ''){
                return '名不能为空';
            }
            if(!new RegExp("^[\u4e00-\u9fa5s]+$").test(value)){
                return '名必须为汉字';
            }
        }
        ,fuxing: function(value){
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            if (jQuery.trim(value) == ''){
                return '请填写正确的姓名';
            }
        }
    });

    // 监听测算表单提交
    form.on('submit(cesuan)', function(data){

        jQuery('.J_ajax_submit_btn').attr('disabled', true);
        if (data.field.agreement != 'on') {
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            layer.msg('您未接受"个人隐私协议"');
            return false;
        };
        layer.msg("正在计算结果", {icon:6,time:2000});

        $.ajax({
            url:"/wap/consulta/add",
            type:"post",
            dataType: 'json',
            contentType: 'application/json',
            data:JSON.stringify({
                "memberId":"0",
                "lat":lat,
                "lng":lng,
                "birthDay":$('#b_input').val(),
                "name":$('#xingming').val(),
                "type":1
            }),
            success:function (e) {

                var time = jQuery.now();
                setTimeout(function(){location.href = "/wap/getConsulta"}, 200);


            }
        })

        return false;
    });

    // 监听订单查询表单提交
    form.on('submit(orderSearch)', function(data){
        var keyword = jQuery('input[name="keyword"]').val();
        var postData = {keyword: keyword};
        jQuery.post('/order/query/', postData, function(result){
            if (result.status == '0') {
                layer.msg(result.data.msg, {icon:5});
            }
            else if (result.status == '1') {
                layer.msg(result.data.msg, {icon:6});
                var url = "";
                if (result.data.type == 'orderid') {
                    var time = jQuery.now();
                    url = "/order/result/?orderid=" + result.data.orderid + "&t=" + time;
                }
                else if (result.data.type == 'mobile') {
                    url = "/order/query/?keyword=" + result.data.keyword;
                }
                setTimeout(function(){location.href = url}, 3000);
            }
        }, 'json');
        return false;
    });

    // 监听订单查询表单提交
    form.on('submit(bindMobile)', function(data){
        jQuery('.J_ajax_submit_btn').attr('disabled', true);
        var mobile = jQuery('#bind_mobile_pop input[name="mobile"]').val()
        ,orderid = jQuery('#bind_mobile_pop input[name="orderid"]').val()
        ,mail = jQuery('input[name="mail"]').val();
        if(!mobile && !mail){
            jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            layer.msg("请输入正确的联系方式", {icon:6});
            return false;
        }
        // var ismobile = jQuery("#bind_mobile_pop input[name='mail']").is(":hidden");
        var postData = {mobile: mobile, orderid: orderid, mail: mail};
        jQuery.post('/user/bindmobile/', postData, function(result){
            if (result.status == '0') {
                layer.msg(result.data.msg, {icon:5});
                jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            }
            else if (result.status == '1') {
                layer.msg(result.data.msg, {icon:6});
                var url     = window.location.href;
                var time    = jQuery.now();
                url = url + '&t=' + time;
                setTimeout(function(){window.location.href = url;}, 1000);
            }
        }, 'json');
        return false;
    });
    // 监听订单查询表单提交
    jQuery('.uindMobile').click( function(){
        layer.closeAll("page");
        var url = "/user/donotbind/";
        var orderid = jQuery('input[name="orderid"]').val();
        var pData = {"orderid":orderid};
        jQuery.post(url, pData, function(){

        }, "json");
    });
    jQuery('.nophone').click( function(){
            jQuery("#bind_mobile_pop input[name='mail']").show();
            jQuery(this).text("我要绑定手机");

    });

    // 监听qm、zyqm问题反馈表单提交
    form.on('submit(fankuiq)', function(data){
        jQuery('.tijiao_fankui').attr('disabled', true);
        var question = data.field.question
            ,qtype = data.field.interest
            ,telmodel = data.field.telmodel
            ,browser = data.field.browser
            ,waytype = data.field.waytype
            ,contactway = data.field.contactway;
        if(qtype == 1 || qtype == 2 ){
            var strs = jQuery("select[name='interest'] option[value='"+qtype+"']").text();
            question = strs;
        }
        question = "问题:"+question+",机型:"+telmodel+",浏览器:"+browser;
        var postData = {question: question, qtype: qtype, username: '起名', paytime: '1980-1-1 00:00:00', waytype: waytype, contactway: contactway};
        jQuery.post('/order/question/', postData, function(result){
            if (result.status == '0') {
                layui.layer.msg(result.data.msg, {icon:5});
                jQuery('.tijiao_fankui').removeAttr('disabled');
            }
            else if (result.status == '1') {
                layui.layer.msg(result.data.msg, {icon:6});
                setTimeout(function(){location.reload();}, 3000);
            }
        }, 'json');
        return false;
    });

    // 监听问题反馈表单提交
    form.on('submit(faq)', function(data){
        jQuery('.J_ajax_submit_btn').attr('disabled', true);
        var question = data.field.question
        ,qtype = data.field.interest
        ,telmodel = data.field.telmodel
        ,browser = data.field.browser
        ,waytype = data.field.waytype
        ,contactway = data.field.contactway;
        if(qtype == 1 || qtype == 2 || qtype == 4){
            var strs = jQuery("select[name='interest'] option[value='"+qtype+"']").text();
            question = strs;
        }
        question = "问题:"+question+",机型:"+telmodel+",浏览器:"+browser;
        var postData = {question: question, qtype: qtype, username: "测算产品", paytime:'1980-1-1 00:00:00', waytype: waytype, contactway: contactway};
        jQuery.post('/user/faq/', postData, function(result){
            if (result.status == '0') {
                layer.msg(result.data.msg, {icon:5});
                jQuery('.J_ajax_submit_btn').removeAttr('disabled');
            }
            else if (result.status == '1') {
                layer.msg(result.data.msg, {icon:6});
                setTimeout(function(){location.reload();}, 3000);
            }
        }, 'json');
        return false;
    });
    form.on('submit(qiming_comment)', function (data) {
        var manyi = jQuery("#qiming_comment input[name='manyi']").val();
        var orderid = jQuery("#qiming_comment input[name='orderid']").val();
        var qtitle = new Array();
        var q_anser = new Array();
        jQuery(".quest-title").each(function(){
            var tit = jQuery(this);
            qtitle.push(tit.text());
        });
        // var anser = new Array();
        jQuery(".quest-content").find("input[type='checkbox']:checked").each(function () {
            var anser_value = jQuery(this).val();
            q_anser.push(anser_value);
        });
        // q_anser['不满意的原因'] = anser;
        var comment = jQuery("#qiming_comment textarea[name='tucao']").val();
        // var isok = true;
        // jQuery(".quest-title").each(function(){
        //     var tit = jQuery(this);
        //     qtitle.push(tit.text());
        //     var anser = new Array();
        //     tit.parent(".comment-quest-item").children(".quest-content").find("input[type='checkbox']:checked").each(function () {
        //         var anser_value = jQuery(this).val();
        //         anser.push(anser_value);
        //     });
        //     if(manyi < 4 && anser.length == 0){
        //         layer.msg("请选择:"+tit.text(),  {icon:6});
        //         isok = false;
        //         return false;
        //     }
        //     q_anser.push(anser);
        // });
        // if(!isok){
        //     return false;
        // }
        jQuery('.comment-sub-btn').attr('disabled', true);
        var postData = {orderid: orderid, manyi: manyi, qtitle: qtitle, q_anser: q_anser,comment:comment};
        jQuery.post('/user/QimingComment/', postData, function(result){
            if (result.status == '0') {
                layer.msg(result.msg, {icon:5});
                jQuery('.comment-sub-btn').removeAttr('disabled');
            }
            else if (result.status == '1') {
                jQuery("#qiming-comment-view").addClass("hidden");
                jQuery(".result-reselect").addClass("hidden");
                // layer.msg(result.msg, {icon:6});
                if(manyi <= 3){
                    $("#cover").fadeIn(300);
                    $(".go-wx").fadeIn(300);
                    // $("#teacher-give").text("查看老师为宝宝准备的助运小秘籍");
                }else {
                    $("#teacher-give").text("领取宝宝助运秘籍");
                    $("#cover").fadeIn(300);
                    $(".go-wx").fadeIn(300);
                }
                // if (result.data.show){
                //     jQuery(".songming-view").removeClass("hidden");
                //     location.href = "#gift_btn";
                // }
                // jQuery("#qiming_comment_fankui").removeClass("hidden");
                // setTimeout(function(){location.reload();}, 3000);
            }
        }, 'json');
        return false;
    });
    form.on('submit(last_comment)',function(){
        jQuery('.comment-sub-btn').attr('disabled', true);
        var orderid = jQuery("#qiming_comment_fankui input[name='orderid']").val();
        var comment = jQuery("#qiming_comment_fankui textarea").val();
        var postData = {orderid: orderid, comment:comment};
        if (comment.length <2){
            jQuery('.comment-sub-btn').attr('disabled', false);
            layer.msg("您还没输入内容，说两句吧", {icon:6});
            return false;
        }
        jQuery.post('/user/QimingComment/', postData, function(result){
            jQuery('.comment-sub-btn').attr('disabled', false);
            if (result.status == '0') {
                layer.msg(result.msg, {icon:5});
                jQuery('.comment-sub-btn').removeAttr('disabled');
            }
            else if (result.status == '1') {
                jQuery(".last-comment").addClass("hidden");
                layer.msg(result.msg, {icon:6});
                setTimeout(function(){location.reload();}, 3000);
            }
        }, 'json');
        return false;
    });
    form.on('select(qtype)', function(data){
        var qtype = data.value;
        if(qtype == 1 || qtype == 2 || qtype == 4){
            jQuery(".qtquestion").hide();
        }else {
            if(qtype == 3){
                var str = "请写上正确时间（新历），我们会在第一时间帮您修改，例：2018年1月1号，早上6点";
                jQuery(".qtquestion textarea[name='question']").attr('placeholder', str);
            }else if(qtype == 88){
                var str = "您的详细描述能让我们更快帮您解决问题，比如您支付完了跳转到什么页面？";
                jQuery(".qtquestion textarea[name='question']").attr('placeholder', str);
            }else {
                var str = "请输入您遇到的问题";
                jQuery(".qtquestion textarea[name='question']").attr('placeholder', str);
            }
            jQuery(".qtquestion").show();
        }
    });
    form.on("radio(sex2)", function (data) {
        var sex = data.value;
        var o_sex = 3 - sex;
        jQuery('input:radio[name="sex"][value='+ o_sex +']').click();
        form.render('radio');
    });
    form.on("radio(sex)", function (data) {
        var sex = data.value;
        var o_sex = 3 - sex;
        jQuery('input:radio[name="sex2"][value='+ o_sex +']').click();
        form.render('radio');
        if (sex == 1) {
            $("#qwboy").show();
            $("#qwgirl").hide();
        }else{
            $("#qwgirl").show();
            $("#qwboy").hide();
        }
        $("input[name='qiwang']").val("");
    });
    form.on("radio(love)", function (data) {
        var who = data.value;
        if (who=="you"){
            $(".you").removeClass("hidden");
            $(".wu").addClass("hidden");
            $(".outside-btn").removeClass("hidden");
            $("#common_tijiao").text("祈福");
        }else {
            $(".wu").removeClass("hidden");
            $(".you").addClass("hidden");
            $(".outside-btn").removeClass("hidden");
            $("#common_tijiao").text("确定");
        }
    });
    form.on('radio(dzi)', function(data){
        if(data.value ==1){
            jQuery(".dz1").show();
            jQuery(".dz2").hide();
            jQuery(".k1").focus();
            jQuery(".k1").addClass("red-flicker");
            setTimeout(function(){
                jQuery(".k1").removeClass("red-flicker");
            },1500);
        }else {
            jQuery(".dz2").show();
            jQuery(".dz1").hide();
            jQuery(".k2").focus();
            jQuery(".k2").addClass("red-flicker");
            setTimeout(function(){
                jQuery(".k2").removeClass("red-flicker");
            },1500);
        }
    });
    form.on('radio(lenght)', function(data){
        jQuery("input:radio[name='lenght']").each(function () {
            var value = jQuery(this).val();
            if (value == data.value && !jQuery(this).is(':checked')){
                jQuery(this).next(".layui-form-radio").trigger("click");
                form.render('radio');
            }
        });
    });
    form.on('radio(sex)', function(data){
        jQuery("input:radio[name='sex']").each(function () {
            var value = jQuery(this).val();
            if (value == data.value && !jQuery(this).is(':checked')){
                jQuery(this).next(".layui-form-radio").trigger("click");
                form.render('radio');
            }
        });
    });
    form.on('submit(submit_comment)', function (data) {
        window.location.href = "/wap/order";

    });

    form.on('submit(zyqm_comment)', function (data) {
        jQuery('#zyqm_comment #common_tijiao').attr('disabled', true);
        var love = jQuery("#zyqm_comment input[type='radio'][name='love']:checked").val();
        var mingzi = jQuery.trim(jQuery("#zyqm_comment input[type='text'][name='mingzi']").val());
        var orderid = jQuery("#zyqm_comment input[name='orderid']").val();
        var qtitle = [];
        var q_anser = [];
        var dingziBei = jQuery("#dingziBei").text();
        isok = true;
        jQuery(".quest-title").each(function(){
            var tit = jQuery(this);
            qtitle.push(tit.text());
            var anser = new Array();
            tit.parent(".comment-quest-item").children(".quest-content").find("input[type='checkbox']:checked").each(function () {
                var anser_value = jQuery(this).attr("title");
                anser.push(anser_value);
            });
            tit.parent(".comment-quest-item").children(".quest-content").find("input[type='radio']:checked").each(function () {
                var anser_value = jQuery(this).attr("title");
                anser.push(anser_value);
            });
            if(love == "wu" && anser.length == 0 && tit.text()!=dingziBei){
                jQuery('#zyqm_comment #common_tijiao').removeAttr('disabled');
                layer.msg("请选择:"+tit.text(),  {icon:6});
                isok = false;
                return false;
            }
            q_anser.push(anser);
        });
        if(!isok){
            return false;
        }
        if(love == "you" && mingzi == ""){
            jQuery('#zyqm_comment #common_tijiao').removeAttr('disabled');
            layer.msg("请输入喜欢的名字",  {icon:6});
            isok = false;
            return false;
        }
        var hanzi =''; //jQuery("#zyqm_comment input[type='radio'][name='hanzi']:checked").val();
        var hanzi_str = (jQuery("#zyqm_comment input[type='text'][name='hanzi_str']").val()=='') ? jQuery("#zyqm_comment input[type='text'][name='hanzi_str2']").val():jQuery("#zyqm_comment input[type='text'][name='hanzi_str']").val();
        // var hanzi_str2 = jQuery("#zyqm_comment input[type='text'][name='hanzi_str2']").val();
        var leng = jQuery("#zyqm_comment input[type='checkbox'][name='leng']:checked").val();
        var remark = jQuery("#zyqm_comment .remark").val();
        leng = leng ? 1 : 2;
        if(jQuery("#zyqm_comment input[type='text'][name='hanzi_str']").val()!=''){
            hanzi = 0;
        }
        if (jQuery("#zyqm_comment input[type='text'][name='hanzi_str2']").val()!=''){
            hanzi = 1;
        }
        var postData = {love:love,mingzi:mingzi, qtitle : qtitle, q_anser: q_anser, hanzi: hanzi, hanzi_str : hanzi_str, remark : remark,leng: leng,number_list:"", orderid: orderid};
        jQuery.post('/zyqm/postComment/', postData, function(result){
            jQuery('#zyqm_comment #common_tijiao').removeAttr('disabled');
            if (result.status == '0') {
                layer.msg(result.msg, {icon:5});
            }
            else if (result.status == '1') {
                // layer.msg(result.msg, {icon:6});
                if(love =='wu'){
                    jQuery('#cover').fadeIn(500);
                    jQuery('.go-wx').fadeIn(500);
                } else {
                    layer.msg("已收到您的祈福请求！", {icon:6});

                }
                jQuery('.questionnaire').hide();
            }else{
                layer.msg(result.msg, {icon:5});
            }
            jQuery('#common_tijiao').attr('disabled', false);
            var isFunction = false;
            try{
                isFunction = typeof(eval('postComment_back'))=="function";
            }catch(e){}
            if(isFunction) {
                postComment_back(love, result);
            }
        }, 'json');
        return false;
    });
    //返回祈福的评论提交
    form.on('submit(qifu_comment)', function (data) {
        jQuery('#back_comment #qifu').attr('disabled', true);
        var mingzi = jQuery.trim(jQuery("#back_comment input[type='text'][name='mingzi']").val());
        var orderid = jQuery("#back_comment input[name='orderid']").val();
        var mingzi2 = jQuery(".f-named").text();
        if(mingzi == "" && mingzi2==""){
            jQuery('#back_comment #qifu').removeAttr('disabled');
            layer.msg("请输入喜欢的名字",  {icon:6});
            return false;
        }
        var mingzistr = '';
        if(mingzi==''){
            mingzistr = mingzi2;
        }else {
            mingzistr = mingzi2==''?mingzi:(mingzi2+','+mingzi);
        }
        var love = "you";
        var postData = {love:love,mingzi:mingzistr, qtitle : [], q_anser: [], hanzi: "", hanzi_str : "", remark : "",leng: "",number_list:"", orderid: orderid};
        jQuery.post('/zyqm/postComment/', postData, function(result){
            jQuery('#back_comment #qifu').removeAttr('disabled');
            if (result.status == '0') {
                layer.msg(result.msg, {icon:5});
            }
            else if (result.status == '1') {
                layer.msg("已收到您的祈福请求！", {icon:6});
                jQuery('.back-tips').hide();
                jQuery('#bcover').hide();
                if (typeof(isGift) !="undefined"){
                    $(".meal-cover").fadeIn();
                    $(".pay-meal").fadeIn();
                }
            }else{
                layer.msg(result.msg, {icon:5});
            }
            jQuery('#qifu').attr('disabled', false);
            if (typeof(isGift) =="undefined"){
                var isFunction = false;
                try{
                    isFunction = typeof(eval('postComment_back'))=="function";
                }catch(e){}
                if(isFunction) {
                    postComment_back(love, result);
                }
            }
        }, 'json');
        return false;
    });
    //返回名字要求的评论提交
    form.on('submit(tijiao_comment)', function (data) {
        jQuery('#back_comment #tijiao-btn').attr('disabled', true);
        var orderid = jQuery("#back_comment input[name='orderid']").val();
        var qtitle = [];
        var q_anser = [];
        var anser = new Array();
        jQuery(".back-title").each(function(){
            qtitle.push(jQuery(this).text());
        });
        jQuery(".back-kuai").each(function(){
            if(jQuery(this).hasClass("kuai-select")){
                anser.push(jQuery(this).text());
            }
        });
        q_anser.push(anser);
        anser=[];
        jQuery(".back-kuai2").each(function(){
            if(jQuery(this).hasClass("kuai-select2")){
                anser.push(jQuery(this).text());
            }
        });
        q_anser.push(anser);
        anser=[];
        var backnum = 0;
        jQuery(".back-require").each(function(){
            jQuery(".back-require").each(function () {
                if(jQuery(this).hasClass("require-select")){
                    backnum++;
                }
            })
            if(jQuery(this).hasClass("require-select")){
                anser.push(jQuery(this).text());
            }
        });
        if( backnum == 0 ){
            layer.msg("您有选项未填写", {icon:5});
            jQuery('#back_comment #tijiao-btn').removeAttr('disabled');
            return false;
        }
        q_anser.push(anser);
        var hanzi ='';
        var hanzi_str = (jQuery("#back_comment input[type='text'][name='hanzi_str3']").val()=='') ? jQuery("#back_comment input[type='text'][name='hanzi_str4']").val():jQuery("#back_comment input[type='text'][name='hanzi_str3']").val();
        var leng = jQuery("#back-danzi").hasClass("kuai-select")?1:2;
        var remark = '';
        if(jQuery("#back_comment input[type='text'][name='hanzi_str3']").val()!=''){
            hanzi = 0;
        }
        if (jQuery("#back_comment input[type='text'][name='hanzi_str4']").val()!=''){
            hanzi = 1;
        }
        var wuxing = [];
        jQuery(".wuxing-selected").each(function(){
            wuxing.push(jQuery(this).attr("data-id"));
        })
        var remark = jQuery("#back_comment .remark").val();
        var love = "wu";
        var postData = {love:"wu",mingzi:"", qtitle : qtitle, q_anser: q_anser, hanzi: hanzi, hanzi_str :hanzi_str, remark : remark,leng:leng,number_list:"", orderid: orderid,wuxing:wuxing};
        jQuery.post('/zyqm/postComment/', postData, function(result){
            jQuery('#back_comment #tijiao-btn').removeAttr('disabled');
            if (result.status == '0') {
                layer.msg(result.msg, {icon:5});
            }
            else if (result.status == '1') {
                // layer.msg("提交成功！", {icon:6});
                jQuery('.back-tips').hide();
                jQuery('#bcover').hide();
                jQuery('#cover').fadeIn(500);
                jQuery('.go-wx').fadeIn(500);
                if (typeof(isGift) !="undefined"){
                    jQuery(".meal-cover").fadeIn();
                    jQuery(".pay-meal").fadeIn();
                }else {
                    jQuery("#shenji-tc").text("已重新为您挑选好名");
                }
            }else{
                layer.msg(result.msg, {icon:5});
            }
            jQuery('#tijiao-btn').attr('disabled', false);
            if (typeof(isGift) =="undefined") {
                var isFunction = false;
                try{
                    isFunction = typeof(eval('postComment_back'))=="function";
                }catch(e){}
                if(isFunction) {
                    postComment_back(love, result);
                }
            }
        }, 'json');
        return false;
    });
    // 触发事件
    var active = {
        paypop: function(){
            // 支付弹出层
            layer.open({
                type: 1
                ,title: '' // 不显示标题栏
                ,closeBtn: false
                ,shadeClose: true
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'PAY_popup' //设定一个id，防止重复弹出
                ,moveType: 1 //拖拽模式，0或者1
                ,content: jQuery('#PAY_pop')
            });
        }
        ,bind_mobile_pop: function(){
            // 支付弹出层
            layer.open({
                type: 1
                ,title: '' // 不显示标题栏
                ,closeBtn: false
                ,shadeClose: false
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'BIND_MOBILE_POPUP' //设定一个id，防止重复弹出
                ,moveType: 1 //拖拽模式，0或者1
                ,content: jQuery('#bind_mobile_pop')
            });
        },
        ispay_pop: function(){
        // 支付弹出层
            layer.open({
                type: 1
                ,title: '' // 不显示标题栏
                ,closeBtn: false
                ,shadeClose: false
                ,area: '300px;'
                ,shade: 0.4
                ,id: 'ISPAY_POPUP' //设定一个id，防止重复弹出
                ,moveType: 1 //拖拽模式，0或者1
                ,content: jQuery('#ispay_pop')
            });
        },
        reset_sex: function(){
            // 支付弹出层
            layer.open({
                type: 1
                ,title: '' // 不显示标题栏
                ,closeBtn: false
                ,shadeClose: true
                ,area: '3.8rem;border-radius: 0.20rem;overflow: hidden;'
                ,shade: 0.8
                ,id: 'RESET_SEX_POPUP' //设定一个id，防止重复弹出
                ,moveType: 1 //拖拽模式，0或者1
                ,content: jQuery('#reset_sex')
            });
    }
    };

    // 支付弹出层
    jQuery('.lock_area').on('click', function(){
        var othis = jQuery(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    jQuery('.payway a').on('click', function(){
        jQuery("#check_order_num").val(60 * 5);
        jQuery(".payed").attr("payresult", "");
        setTimeout(function(){
            var othis = jQuery(this), method = "ispay_pop";
            active[method] ? active[method].call(this, othis) : '';
        }, 1500);
    });
    var ispay = jQuery("#ispay_pop");
    if (ispay && ispay.attr("data-tag") == "pay_back"){
        jQuery("#check_order_num").val(60 * 5);
        var othis = jQuery(this), method = "ispay_pop";
        active[method] ? active[method].call(this, othis) : '';
    }
    // 绑定手机号弹出层
    //jQuery('.layui-colla-title').on('click', function(){
    //    var othis = jQuery(this), method = othis.data('method');
    //    active[method] ? active[method].call(this, othis) : '';
    //});
    jQuery('.bind_mobile_btn').on('click', function(){
        var othis = jQuery(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    // 绑定手机号弹出层
    if (jQuery('#bind_mobile_pop').length) {
        var orderid = jQuery('input[name="orderid"]').val();
        var postData = {orderid: orderid};
        jQuery.post('/user/ajaxbindmobilestatus/', postData, function(result){
            if (result.status == '0') {
                setTimeout(function () {
                    jQuery(".bind_mobile_btn").trigger("click");
                }, 1000);
            }
        }, 'json');
    }
    jQuery('.reselect_sex').on('click', function(){
        var othis = jQuery(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
    // 用户反馈轮播
    var scrollTop   =   0;
    var scrollUl    =   jQuery('#feedbackScroll').children('ul');
    function scrollTip(){
        var top     =   scrollUl.children('li').eq(0).outerHeight();
        if(Math.abs(scrollTop) == Math.abs(top)){
            scrollUl.children('li').eq(0).appendTo(scrollUl);
            scrollUl.css("top",0);
            scrollTop   =   0;
        }else{
            scrollTop--;
            scrollUl.css("top",scrollTop);
        }
    }
    setInterval(scrollTip,50);

    // 底部测算按钮悬浮
    var topShow     =   jQuery(".J_testFixedShow");
    if(topShow.length > 0 && jQuery("#testFixedBtn").length > 0){
        var topShow     =   topShow.offset().top;
        var topNum      =   jQuery(".J_testFixedTop").length>0?(jQuery(".J_testFixedTop").offset().top-20):200;
        var testBtn     =   jQuery("#testFixedBtn");
        jQuery(window).scroll(function(){
            var wt  =   jQuery(window).scrollTop();
            wt>topShow?(testBtn.fadeIn(),jQuery('.page_footer2').css('padding-bottom','50px')):(testBtn.fadeOut(),jQuery('.page_footer2').css('padding-bottom','20px'));
        });
        testBtn.on('click',function(){jQuery('html,body').scrollTop(topNum)})
    }

    // 支付页底部测算按钮悬浮
    var topShow2     =   jQuery(".J_jiesuoFixedShow");
    if(topShow2.length > 0 && jQuery("#jiesuoFixedBtn").length > 0){
        var topShow2    =   topShow2.offset().top;
        var topNum      =   jQuery(".J_testFixedTop").length>0?(jQuery(".J_testFixedTop").offset().top-20):200;
        var jiesuoBtn   =   jQuery("#jiesuoFixedBtn");
        jQuery(window).scroll(function(){
            var wt  =   jQuery(window).scrollTop();
            wt>topShow2?(jiesuoBtn.fadeIn(),jQuery('.page_footer2').css('padding-bottom','50px')):(jiesuoBtn.fadeOut(),jQuery('.page_footer2').css('padding-bottom','20px'));
        });
        //jiesuoBtn.on('click',function(){jQuery('html,body').scrollTop(topNum)})
    }

    // 日期控件
    if (jQuery(".Js_date").length>0) {
        for (var a = 0, e = jQuery(".Js_date").length; a < e; a++) {
            (new lCalendar).init("#" + jQuery(".Js_date").eq(a).attr("id"))
        }
    }

    // 折叠面板标题定位
    if (jQuery(".layui-collapse").length>0) {
        jQuery(".layui-colla-title").click(function() {
            var aa = jQuery('.layui-colla-item').offset().top;
            var par_item = jQuery(this).parent(".layui-colla-item");
            var index = jQuery(this).index('.layui-colla-title');
            var id = jQuery(this).attr("id");
            if(par_item.attr("id")){
               var top = par_item.offset().top;
                jQuery(window).scrollTop(top);
            }else if (id){
                var top = jQuery(this).offset().top;
                jQuery(window).scrollTop(top);
            }else{
                var toppos = parseInt(aa) + 50 * index;
                jQuery('body').scrollTop(toppos);
            }
        });
    }

    // 公众号原生js支付
    if (jQuery(".weixinJsPay").length>0) {
        jQuery('.weixinJsPay').on('click', function(){
            var othis = jQuery(this)
                ,orderid = othis.data('orderid')
                ,subAppid = othis.data('mpappid')
                ,subOpenid = othis.data('mpopenid')
                ,url = '';
            var payUrl = othis.data("url");
            if(payUrl){
                url = payUrl + '?orderid=' + orderid + '&payway=weixin&sub_appid=' + subAppid + '&sub_openid=' + subOpenid;
            }else{
                url = 'https://ffcs.smxs.com/pay/swiftpass/?orderid=' + orderid + '&payway=weixin&sub_appid=' + subAppid + '&sub_openid=' + subOpenid;
            }

            jQuery.get(url, function(res) {
                if(typeof(res) === 'string'){
                    res = JSON.parse(res);
                    var payInfo = JSON.parse(res.pay_info);
                    //alert(payInfo.appId);
                }
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest',{
                        "appId" : payInfo.appId, //公众号名称，由商户传入
                        "timeStamp": payInfo.timeStamp, //戳，自1970 年以来的秒数
                        "nonceStr" : payInfo.nonceStr, //随机串
                        "package" : payInfo.package,
                        "signType" : payInfo.signType, //微信签名方式:
                        "paySign" : payInfo.paySign  //微信签名,
                    },function(res){
                        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                            // 此处可以使用此方式判断前端返回,微信团队郑重提示：res.err_msg 将在用户支付成功后返回ok，但并不保证它绝对可靠。
                            location.href = payInfo.callback_url;
                        }
                    }
                );
            });
        });
    }
    
    jQuery('.scroll_top').click(function(){
        jQuery('html,body').animate({scrollTop: '0px'}, 800);
    });
});
