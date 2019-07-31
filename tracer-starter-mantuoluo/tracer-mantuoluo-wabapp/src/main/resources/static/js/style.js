 //关闭弹出框
 $(".closes").click(function() {
 	$(".eject").hide();
 	$(".bg100").fadeOut();
 })
 //提示
 function tip() {
 	$(".tip-tip-box").fadeIn();
 	$(".tip-tip-box-nei").animate({
 		opacity: "1"
 	}, function() {
 		$(".tip-tip-box-nei").animate({
 			opacity: "1"
 		}, 1500, function() {
 			$(".tip-tip-box-nei").animate({
 				opacity: "0"
 			}, 1000)
 			$(".tip-success-bg").hide()
 		})
 	})
 }
 //服务须知
 $(".fuwuxuzhi span").click(function(){
	$(".eject-xuzhi").fadeIn();
	$(".bg100").show();	
 })
 //登录校验
 $(".login-name").focus(function(){
	 var name=$(this).val();
	if(name=="姓名"){
		$(this).val("");
	}
 })
 $(".login-name").blur(function(){
	var name=$(this).val();
	if(name==""){
		$(this).val("姓名");
	}
 })
 $(".login-tel").focus(function(){
	 var tel=$(this).val();
	if(tel=="手机号"){
		$(this).val("");
	}
 })
 $(".login-tel").blur(function(){
	var tel=$(this).val();
	if(tel==""){
		$(this).val("手机号");
	}
 })
 $(".login-code").focus(function(){
	 var code=$(this).val();
	if(code=="验证码"){
		$(this).val("");
	}
 })
 $(".login-code").blur(function(){
	var code=$(this).val();
	if(code==""){
		$(this).val("验证码");
	}
 })