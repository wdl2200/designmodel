<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>


<script type="text/javascript">
$(document).ready(function() {
    $('input[type=radio][name=resultName]').change(function() {
    	$("#serviceUrl").val("http://119.254.34.202:59220/MemberConcentrationService/"+this.value)
    });
});

function aaa(){
	if($("#serviceUrl").val()=="http://119.254.34.202:59220/MemberConcentrationService/" ||!$("#serviceUrl").val()){
		alert("请选择调用的接口！")
		return;
	}
	var postdata=$("#postdata").val();
	if(!postdata){
		alert("请输入参数！")
		return;	
	}
	$.ajax({
		url :$("#serviceUrl").val(),
		type : "post",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		data : {
			"postdata" : encodeURI(postdata)
		},
		success : function(data){
			var json = JSON.stringify(data)
			$("#postdata2").val(json);
		}
	});
}

function rec(){
	//http://localhost:8080/MemberConcentrationService/
	$("#serviceUrl").val("http://119.254.34.202:59220/MemberConcentrationService/");
}
</script>
</head>
<body>
	<!-- 注意： daybook的traceno 每次插入C0002_MEM_DAYBOOK时手动改一下。不能与数据库重复  ce9cbf83a31aede4-->
	<span>接口地址：</span> <br>
	<input type="text" style="width:50%;height:30px;font-size: larger;" id="serviceUrl" value="http://119.254.34.202:59220/MemberConcentrationService/" style="width: 500px;"/>
	<input type="button"  value="重置" onclick="rec()"/>
	<br/><br/>
	<label><input name="resultName" type="radio"  value="memberregisterinsert" />会员注册接口</label>
	<label><input name="resultName" type="radio"  value="memberregisterupdate" />会员修改接口</label> 
	<label><input name="resultName" type="radio"  value="memberregistersplitgroup" />会员拆集体接口</label>
	<label><input name="resultName" type="radio"  value="changeCard" />会员换卡接口</label>
	<label><input name="resultName" type="radio"  value="buCard" />会员补卡接口</label><br/><br/>
	<label><input name="resultName" type="radio"  value="activateCard" />会员卡激活接口 78</label>
	<label><input name="resultName" type="radio"  value="queryAccont" />获取会员账号信息接口</label>
	<label><input name="resultName" type="radio"  value="consumption" />消费（售与退）</label>
	<label><input name="resultName" type="radio"  value="recharge" />充值（正负充值）</label><br/><br/>
	<label><input name="resultName" type="radio"  value="updatePassword" />修改会员密码接口</label>
	<label><input name="resultName" type="radio"  value="updateLifeDate" />修改会员续期接口</label>
	<label><input name="resultName" type="radio"  value="reportLoss" />会员卡挂失接口</label>
	<label><input name="resultName" type="radio"  value="eliminateAccount" />会员卡销户接口</label>
	<label><input name="resultName" type="radio"  value="freezingAndUnsealing" />会员卡冻结,解封接口</label><br/><br/>
	<label><input name="resultName" type="radio"  value="adjustLevel" />会员调级</label>
	<label><input name="resultName" type="radio"  value="adjustScore" />会员调分接口</label>
	<label><input name="resultName" type="radio"  value="buyGiftPackage" />购买礼包接口</label><br/><br/>
	<br>
   	<div>
    <table>
    <tr>
    <th colspan="2">json传入参数：</th>
    <th>返回结果：</th>
    </tr>
    <tr>
    <td><textarea id="postdata" rows="40px" cols="100px" ></textarea></td>
    <td><input  type="button"  onclick="aaa()"  value="点击发送json"/></td>
    <td><textarea id="postdata2" rows="40px" cols="100px" ></textarea></td>
    </tr>
    </table>
   	</div>
<!--    <span>json传入参数：</span><br/> -->
<!--    <textarea id="postdata" rows="20px" cols="100px" ></textarea> -->
<!--    <input  type="button"  onclick="aaa()"  value="点击发送json"/> -->
<!--    <br/> -->
<!--    <span>返回结果：</span><br/> -->
<!--    <textarea id="postdata2" rows="20px" cols="100px" ></textarea> -->
   
</body>
</html>