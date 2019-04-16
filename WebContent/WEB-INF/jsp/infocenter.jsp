<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- saved from url=(0049)https://v3.bootcss.com/examples/starter-template/ -->
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://v3.bootcss.com/favicon.ico">

    <title>extract</title>

    <!-- Bootstrap core CSS -->
    <link href="static/infocenter_files/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="static/infocenter_files/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/infocenter_files/starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="static/infocenter_files/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header" >
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a id="conpanyName" class="navbar-brand" href="javascript:;" title="点击编辑" >  </a>
          <input id="updateCompany" class="form-control" value="" style="display: none;margin:8px 0;"/>
          <input id="companyId" type="hidden" value="" />
        </div>
        <div style="float:right">
        	<a href="infocenter/manage" title="分组管理" class="btn btn-primary" style="margin:6px 0;"> 分组管理 </a>
        	<a href="infocenter/cancellation" title="注销登录" class="btn btn-danger" style="margin:6px 0;"> 注销 </a>
        </div>
      </div>
    </nav>

	



    <div class="container">
    
    <!-- 库里人数：    抽取人数     添加分组    添加员工分组 -->
	<div class="row">
		<div style="margin-top:20px"></div>
		<div class="col-md-8">
			库里人数：${count }人
		</div>
		<div class="col-md-4" style="text-align:right">
				<form class="form-inline">
					<div class="form-group">
						<label class="sr-only" for="num">
							抽取人数
						</label> <input type="text" class="form-control"
							id="num" placeholder="抽取人数">
					</div>
					<button id="_submit" type="button" class="btn btn-default">抽取</button>
				</form>
		</div>
	</div>
    
    <form action="infocenter/selectGroup" id="myform" method="POST">
    <input type="hidden" name="number" id="number" value=""/> 
    <c:forEach items="${groupList }" var="group">
    	<div style="margin-top:10px;">
			<button type="button" class="btn btn-default group" name="${group.groupId }" style="text-align:left;width:100%;">
			<span class="${group.groupId }">+</span>
			${group.gname }
			</button>
			<ul class="list-group" id="${group.groupId }" style="display: none;margin:0px auto;">
				 <c:forEach items="${group.emGroupList }" var="emGroup">
				 	<li class="list-group-item"><input type="checkbox" name="selectGroup" value="${emGroup.egId }">
				 		<a href="infocenter/employee?ids=${emGroup.egId }" target="_blank" title="查看员工">${emGroup.egname }</a>
				 	</li>
				 </c:forEach>
			</ul>
			
		</div>
    </c:forEach>
	</form>
		
		
		
		
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="static/infocenter_files/jquery.min.js"></script>
    <script src="static/infocenter_files/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="static/infocenter_files/ie10-viewport-bug-workaround.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$.ajax({
  				url : "infocenter/getCompanyName",
  				method:"POST",
                success:function(res){
                	var companyName = res.data.companyName;
                	var companyId = res.data.companyId;
                	$("#conpanyName").html( companyName )
                	$("#updateCompany").val(companyName)
                	$("#companyId").val(companyId)
                }
  			})
  			
  			$("#conpanyName").click(function(){
  	                $("#conpanyName").hide()
                    $("#updateCompany").show()
                    $("#updateCompany").focus()
  			})
  			
  			$("#updateCompany").blur(function(){
  				var update =  $("#updateCompany").val();
  				if(update == ""){
  					update = "未命名"
  				}
  				if( update != $("#conpanyName").html()){
  					if(update.length > 50 ){
  						alert("公司名过长！")
  						return;
  					}
  					$("#conpanyName").html(update)
  					$.ajax({
  						url:"infocenter/updateCompanyName",
  						method:"POST",
  						data:{
  							id : $("#companyId").val(),
  							name : update
  						},
  						success:function(res){
                            if(res.status != 200){
                            	alert(res.msg)
                            }
                        }
  					})
  				}
                $("#conpanyName").show()            
                $("#updateCompany").hide()
            })
  			
            $(".group").click(function(){
            	
            	$("#" + this.name).toggle()
            	var icon = $("." + this.name).html()
            	icon = icon=="+"? "-":"+";
            	$("." + this.name).html(icon)
            })
            
            $("#_submit").click(function(){
            	var num = $("#num").val()
            	var strgetSelectValue="";
				var getSelectValueMenbers = $("input[name='selectGroup']:checked").each(function(j) {
				    if (j >= 0) {
				        strgetSelectValue += $(this).val() + "," 
				    }
				})
				if(strgetSelectValue == ""){
					alert("请选择分组。")
					return;
				}
            	if(num != "" && !isNaN(num)){
            		$("#number").val(num)
            		$("#myform").submit();
            	}else{
            		alert("请正确输入抽取人数。")
            	}
            })
            
        })
  	</script>

</body></html>