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
    <link href="../static/infocenter_files/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../static/infocenter_files/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../static/infocenter_files/starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../static/infocenter_files/ie-emulation-modes-warning.js"></script>

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
        	<a href="../infocenter" title="返回" class="btn btn-primary" style="margin:6px 0;"> 返回主页 </a>
        	<a href="cancellation" title="注销登录" class="btn btn-danger" style="margin:6px 0;"> 注销 </a>
        </div>
      </div>
    </nav>	
	<div class="container">
		<div class="row">
			<div style="margin-top: 20px"></div>
			<div class="col-md-8"></div>
			<div class="col-md-4" style="text-align: right">
			
					<button id="insertEmgroup" class="btn btn-default">添加小分组</button>
			</div>
		</div>

		<input id="groupId" type="hidden" value="${group.groupId}" />
		<table class="table" style="margin:20px auto">
			<caption>
		    	当前所在分组：${group.gname }
		  	</caption>
			<thead>
				<th style="width:200px"> </th>
				<th>员工分组名</th>
				<th>操作</th>
			</thead>
			<tbody>
				<c:forEach items="${emgroupList }" var="emgroup" varStatus="status">
					<tr>
						<td style="width:200px"> 
						${status.index + 1 }
						</td>
						<td> ${emgroup.egname } </td>
						<td style="width:300px"> 
						<button name="${emgroup.egId }" class="btn btn-info editEmgroup"> 编辑 </button>
						<a href="employee?ids=${emgroup.egId }" class="btn btn-info" target="_blank" title="查看员工"> 员工管理 </a> 
						<a href="deleteEmGroup?egId=${emgroup.egId }&groupId=${group.groupId}" class="btn btn-danger"> 删除 </a>
						 </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../static/infocenter_files/jquery.min.js"></script>
    <script src="../static/infocenter_files/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../static/infocenter_files/ie10-viewport-bug-workaround.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$.ajax({
  				url : "getCompanyName",
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
  						url:"updateCompanyName",
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
            $(".editEmgroup").click(function(){
                var egname = prompt("请输入新的分组名","")
                if(egname==""){
                	alert("分组名不能为空！")
                	return;
                }
                $.ajax({
                    url:"editEmgroup",
                    method:"POST",
                    data:{
                        egname : egname,
                        egId: this.name
                    },
                    success:function(res){
                    	location.reload()
                    }
                })
            })
       
            $("#insertEmgroup").click(function(){
               var egname = prompt("分组名称","");
               if(egname==""){
            	   alert("分组名不能为空！");
            	   return;
               }
               $.ajax({
            	   url:"insertEmgroup",
            	   method:"POST",
            	   data:{
            		   egname : egname,
            		   groupId : $("#groupId").val()
            	   },
            	   success:function(res){
            		   location.reload()
                   }
               })
            })
           
        })
  	</script>

</body></html>