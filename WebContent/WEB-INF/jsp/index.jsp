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
	<a class="a globalLoginBtn">登录</a>
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
									style="font-size: 18px;">登录</h4>
							</div>
							<div class="modal-body">
								<section class="box-login v5-input-txt" id="box-login">
									<form id="login_form" action="infocenter" method="post"
										autocomplete="off">

										<ul>
											<li class="form-group"><input class="form-control"
												id="username" maxlength="50" name="username"
												placeholder="请输入管理员账户"  type="text" value=""></li>
											<li class="form-group" style="margin-top:20px"><input class="form-control"
												id="password" name="password" placeholder="请输入密码"
												type="password" value=""></li>
										</ul>
									</form>
									<p class="good-tips marginB10">
										<a id="btnForgetpsw" href="updatePass" class="fr">修改密码</a><a
											href="javascript:;" target="_blank" id="btnRegister"></a>
									</p>
									<p class="marginB10">
										<span id="msg" style="color:red;"> &nbsp;&nbsp; </span>
									</p>
									<div class="login-box marginB10" style="margin: 20px auto;">
										<button id="login_btn" type="button"
											class="btn btn-micv5 btn-block globalLogin">登录</button>
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
						$("#login_btn").click(function() {
							var username = $("#username").val()
							var password = $("#password").val()
                            if(username=="" || password == ""){
                                $("#msg").html("请输入用户名和密码。")
                                return;
                            }
							$.ajax({
                                url : "login",
                                method : "POST",
                                data:{
                                    username : username,
                                    password : password
                                },
                                success : function(res){
                                    if(res.status == 200){
                                    	var username = res.data.username;
                                    	var password = res.data.password;
                                    	$("#msg").html("登录成功！")
                                    	$("#login_form").submit();
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












