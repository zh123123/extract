 <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">
<meta name="applicable-device" content="pc,mobile">
<title>管理员登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css"
	href="static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/css/index.css">
<link rel="icon" href="https://v3.bootcss.com/favicon.ico">
</head>

<body>
	<a id="mybtn" class="a globalLoginBtn">修改密码</a>
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true"
		style="display: none;">
		<div style="display: table; width: 100%; height: 100%;">
			<div style="vertical-align: middle; display: table-cell;">
				<div class="modal-dialog modal-sm" style="width: 540px;">
					<div class="modal-content" style="border: none;">
						<div class="col-left"></div>
						<div class="col-right">
							<div class="modal-header">
								<button type="button" id="login_close" class="close"
									data-dismiss="modal">
									<span aria-hidden="true">×</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="loginModalLabel"
									style="font-size: 18px;">修改密码</h4>
							</div>
							<div class="modal-body">
								<section class="box-login v5-input-txt" id="box-login">
									<form id="login_form" action="infocenter" method="post"
										autocomplete="off">

										<ul>
											<li class="form-group"><input class="form-control"
												id="old_password" name="old_password" placeholder="请输入旧密码"
												type="password" value=""></li>
											<li class="form-group"><input class="form-control"
												id="new_password" name="new_password" placeholder="请输入新密码"
												type="password" value=""></li>
											<li class="form-group"><input class="form-control"
												id="new_password2" name="new_password" placeholder="请再次输入新密码"
												type="password" value=""></li>
										</ul>
									</form>
									<p class="marginB10">
										<span id="msg" style="color:red;"> &nbsp;&nbsp; </span>
									</p>
									<div class="login-box marginB10">
										<button id="login_btn" type="button"
											class="btn btn-micv5 btn-block globalLogin">修改密码</button>
									</div>

								</section>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript" src="static/js/jquery2.2.2.min.js"></script>
				<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
				<script type="text/javascript" src="static/js/common.js"></script>
				<script type="text/javascript" src="static/js/login.js"></script>
				<script type="text/javascript">
					$(document).ready(function() {
						
						$("#mybtn").click();
						
						$("#login_btn").click(function() {
							var old_password = $("#old_password").val()
							var new_password = $("#new_password").val()
							var new_password2 = $("#new_password2").val()
                            if(old_password=="" || new_password == "" ||new_password2==""){
                                $("#msg").html("请完整填写表单。")
                                return;
                            }
							if(new_password !=  new_password2){
								 $("#msg").html("两次输入的密码不一致。")
	                             return;
							}
							$.ajax({
                                url : "updatePassword",
                                method : "POST",
                                data:{
                                	oldPass : old_password,
                                	newPass : new_password,
                                	newPass2: new_password2
                                },
                                success : function(res){
                                    if(res.status == 200){
                     
                                    	$("#msg").html("修改成功！")
                                    	location.href = "/extract/index"
                                    }else{
                                    	
                                    	$("#msg").html(res.msg)
                                    }
                                }
                            })
						})
					})
				</script>
</body>
</html>












