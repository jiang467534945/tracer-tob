var uploadList=$(".zhl_admin_box");

uploadList.each(function(){
	var zhl_admin_file=$(this).find(".zhl_admin_file");//获取上传按钮
	var zhl_admin_imgs=$(this).find(".zhl_admin_imgs");//获取
	var updateInput=$(this).find(".updateInput");
	var thiss=$(this);
	//alert(lenList)
	var up_number=$(this).attr("title");//根据 title控制上传图片的数量
	// 上传数量控制，给出默认值
	if(uploadList){
		var zhl_update_num = 3;  //这里控制上传图片的数量
	}
	
	// 上传数量控制，判断是否定义并赋值
	zhl_update_num = typeof zhl_update_num != "undefined" && zhl_update_num ? zhl_update_num : 1; 
	// 上传大小控制，当前为1M
	var zhl_update_size = 1*10240*10240; 
	/**
	 * 文件选择触发
	 */
	zhl_admin_file.change(function (){
		zhl_update_num=up_number;
		var fileAll = this.files.length;
		for(var i=0;i<fileAll;i++){
			var file = this.files[i];
			if( file.size >  zhl_update_size){  
				var d = parseInt(i+1);
				alert("你选择的文件第"+d+"个太大了！");
				return false;
			}
			if(!/image\/\w+/.test(file.type)){
				alert("文件必须为图片！"); 
				return false; 
			} 
			var reader = new FileReader();
			//读取文件过程方法
			reader.onerror = function (e) {
				console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
			}
			reader.onabort = function(e) {
				console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
			};
			reader.onload = function (e) {
					/*从这里 --------压缩图片*/
					var base64Img= e.target.result;
					//--执行resize。  
					var _ir=ImageResizer({  
							resizeMode:"auto"  
							,dataSource:base64Img  
							,dataSourceType:"base64"  
							,maxWidth:1200 //允许的最大宽度  
							,maxHeight:600 //允许的最大高度。  
							,onTmpImgGenerate:function(img){  
	  
							}  
							,success:function(resizeImgBase64,canvas){
								//压缩后
								var imgstr='<div class="zhl_admin_update_imgtmp"><i></i><img src="'+resizeImgBase64+'" /></div>';
								//限制上传的图片数
								var len = thiss.find(".zhl_admin_update_imgtmp").length;
								//alert(len)
								if(len < zhl_update_num){
									if(len == zhl_update_num-1){
										updateInput.show();
									}else{
										updateInput.show();
									}
									Progressbar();//上传进du条
									zhl_admin_imgs.append(imgstr);
								}else{
									alert('最多'+zhl_update_num+'张图片')
									//tip();
									//$("#tishiyu").text('最多'+zhl_update_num+'张图片');
									//$('.updateInput').hide();
									return false;
								}
							}  
							,debug:true  
					});  
					/*到这里 -----压缩图片 */
			}
			reader.readAsDataURL(file);
		}
	});
	
	/**
	 * 点击删除预选的图片
	 */
	zhl_admin_imgs.delegate("div","click",function(){
		if(confirm('删除该张图片？')){
			$(this).remove();
			//$('#zhl_admin_img').show();
		}
	});
	
	/**
	 * 判断文件是否为空
	 */
	function judgeEmpty(data) {
	  return (Array.isArray(data) && data.length === 0) || (Object.prototype.isPrototypeOf(data) && Object.keys(data).length === 0);
	}

	
})
