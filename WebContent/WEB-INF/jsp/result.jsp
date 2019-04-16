<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>extract</title>
     <link rel="icon" href="https://v3.bootcss.com/favicon.ico">
    <link href="../static/infocenter_files/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
  
  	<table class="table table-bordered" style="margin:20px auto;width:60%">
	<thead>
		<th></th>
		<th>姓名</th>
		<th>联系方式</th>
		<th>信息</th>
	</thead>
	<tbody>
		<c:forEach items="${employeeList }" var="employee" varStatus="status">
	    	<tr>
	    		<td> ${status.index + 1}</td>
	    		<td> ${employee.ename } </td>
	    		<td> ${employee.phone } </td>
	    		<td> ${employee.info } </td>
	    		
	    	</tr>
	    </c:forEach>
	</tbody>
  	
	</table>
    

    <script src="../static/infocenter_files/jquery.min.js"></script>
    <script src="../static/infocenter_files/bootstrap.min.js"></script>
  </body>
  <script type="text/javascript">
  $(document).ready(function(){
	  
  })
  </script>
</html>