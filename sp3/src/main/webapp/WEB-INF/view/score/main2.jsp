<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
    margin:0; padding: 0;
}
body {
    font-size:14px;
	font-family:"Malgun Gothic", "맑은 고딕", NanumGothic, 나눔고딕, 돋움, sans-serif;
}
a{
    color:#000000;
    text-decoration:none;
    cursor:pointer;
}
a:active, a:hover {
    text-decoration: underline;
    color:tomato;
}
.title {
    font-weight:bold;
    font-size:16px;
    font-family:나눔고딕, "맑은 고딕", 돋움, sans-serif;
}
.btn {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:1px solid #cccccc;
    background-color:#ffffff;
    text-align:center;
    cursor:pointer;
    padding:3px 10px 5px;
    border-radius:4px;
}
.btn:active, .btn:focus, .btn:hover {
    background-color:#e6e6e6;
    border-color:#adadad;
    color:#333333;
}
.boxTF {
    border:1px solid #999999;
    padding:3px 5px 5px;
    border-radius:4px;
    background-color:#ffffff;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.selectField {
    border:1px solid #999999;
    padding:2px 5px 4px;
    border-radius:4px;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}

.title {
	width:100%;
	height:45px;
	line-height:45px;
	text-align:left;
	font-weight: bold;
	font-size:15px;
}

tr.over {
	background: #f5fffa;
}

.score-table input {
	border:none;
	padding:3px 5px 5px;
	background-color:#ffffff;
	width:100%;
	box-sizing:border-box;
	font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
}
.score-table input[readonly] {
    pointer-events: none;
    border: none;
    text-align: center;
}

.score-table button {
    color:#333333;
    font-weight:500;
    font-family:"맑은 고딕", 나눔고딕, 돋움, sans-serif;
    border:none;
    width:100%;
    text-align:center;
    cursor:pointer;
    padding:4px 10px 4px;
    background-color: #ffffff;
}
.score-table button:hover {
    font-weight:500;
    color:#4374d9;
}

.spanUpdate, .spanUpdateOk, .spanDelete, .spanUpdateCancel {
	cursor: pointer;
}

.spanUpdate:hover, .spanUpdateOk:hover, .spanDelete:hover, .spanUpdateCancel:hover {
	font-weight:500;
	color: #4374d9;
}
</style>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
function isValidNumber(data){
	var format=/^(\d+)$/;
	return format.test(data);
}

function isValidDateFormat(data){
    var regexp = /[12][0-9]{3}[\.|\-|\/]?[0-9]{2}[\.|\-|\/]?[0-9]{2}/;
    if(! regexp.test(data))
        return false;

    regexp=/(\.)|(\-)|(\/)/g;
    data=data.replace(regexp, "");
    
	var y=parseInt(data.substr(0, 4));
    var m=parseInt(data.substr(4, 2));
    if(m<1||m>12) 
    	return false;
    var d=parseInt(data.substr(6));
    var lastDay = (new Date(y, m, 0)).getDate();
    if(d<1||d>lastDay)
    	return false;
		
	return true;
}

// 엔터 처리
$(function(){
	$(document).on('keypress', '.score-table input:text', function(evt){
		var fields = $(this).closest("tr").find("input:text");
		var index = fields.index(this);
	        
		if (evt.keyCode == 13) {
			if ( index > -1 && index < 5 ) {
				fields.eq( index + 1 ).focus();
			} else {
				if($(this).closest("tr").find("#btnAdd").length>0) {
					$("#btnAdd").trigger("click");
				}
			}
			return false;
		}
	});
	   
	$(document).on('keyup', '.score-table input:text', function(evt){
		var fields = $(this).closest("tr").find("input:text");
		var index = fields.index(this);

		if(index>=3 && index<=5) {
			var kor=fields.eq(3).val().trim();
			var eng=fields.eq(4).val().trim();
			var mat=fields.eq(5).val().trim();

			var k = isValidNumber(kor) ? parseInt(kor) : 0;
			var e = isValidNumber(eng) ? parseInt(eng) : 0;
			var m = isValidNumber(mat) ? parseInt(mat) : 0;
	        	
			var tot=k+e+m;
			var ave=Math.floor(tot/3);
	        	
			if(!kor && !eng && !mat) {
				tot="";
				ave="";
			}
	        	
			fields.eq(6).val(tot);
			fields.eq(7).val(ave);
		}
	});
});

// 등록하기
$(function(){
	$("#btnAdd").click(function(){
		if(! check()) {
			return false;
		}
		
		var hak=$("#hak").val().trim();
		var name=$("#name").val().trim();
		var birth=$("#birth").val();
		var kor=$("#kor").val();
		var eng=$("#eng").val();
		var mat=$("#mat").val();
		
		var tot=parseInt(kor)+parseInt(eng)+parseInt(mat);
		var ave=Math.floor(tot/3);
		
		var str="<tr height='26' class='rows' bgcolor='#ffffff' align='center'></tr>";
		$(str).
		   hover(function(){ // 마우스가 위치할 경우
			   $(this).addClass("over");
		   }, function(){ // 마우스가 벗어나면
			   $(this).removeClass("over");
		   })
		       .append("<td>"+hak+"</td>")
		       .append("<td>"+name+"</td>")
		       .append("<td>"+birth+"</td>")
		       .append("<td>"+kor+"</td>")
		       .append("<td>"+eng+"</td>")
		       .append("<td>"+mat+"</td>")
		       .append("<td>"+tot+"</td>")
		       .append("<td>"+ave+"</td>")
		       .append("<td><span class='spanUpdate' >수정</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanDelete' >삭제</span></td>")
		       .appendTo(".score-list");
		
		$(".score-input input:text").each(function(){
			$(this).val("");
		});
		$("#hak").focus();
	});
	
	function check() {
		if(! $("#hak").val().trim()) {
			alert("학번을 입력 하세요 !!!");
			$("#hak").focus();
			return false;
		}
		
		if(! $("#name").val().trim()) {
			alert("이름을 입력 하세요 !!!");
			$("#name").focus();
			return false;
		}
		
		if(! isValidDateFormat($("#birth").val())) {
			alert("생년월일을 정확히 입력 하세요 !!!");
			$("#birth").focus();
			return false;
		}
		
		if(! isValidNumber($("#kor").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#kor").focus();
			return false;
		}
		
		if(! isValidNumber($("#eng").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#eng").focus();
			return false;
		}
		
		if(! isValidNumber($("#mat").val()) ) {
			alert("점수는 숫자만 가능합니다. !!!");
			$("#mat").focus();
			return false;
		}

		return true;
	}
});

// 수정
$(function(){
	var arr=[];
	$("body").on("click", ".spanUpdate", function(){
		var tds = $(this).closest("tr").children("td");

		var s;
		
		$(tds).each(function(i){
			if(i!=tds.length-1) {
				arr[i]=$(this).text();
				s="";
				if(i==6||i==7)
					s="  readonly='readonly' "
				
				$(this).empty();
				$(this).append("<input type='text' value='"+arr[i]+"' "+s+">");
			} else {
				$(this).empty();
				$(this).append("<span class='spanUpdateOk' >완료</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanUpdateCancel' >취소</span>");
			}
		});
		
		$(tds[0]).find("input").focus();
	});
	
	$("body").on("click", ".spanUpdateOk", function(){
		var tds = $(this).closest("tr").children("td");
		
		$(tds).each(function(i){
			var ob=$(this);
			if(i!=tds.length-1) {
				$(this).find("input:text").each(function(i) {
					ob.empty();
			        ob.text($(this).val());
			    });
			} else {
				$(this).empty();
				$(this).append("<span class='spanUpdate' >수정</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanDelete' >삭제</span>");
			}
		});
		
	});
	
	$("body").on("click", ".spanUpdateCancel", function(){
		var tds = $(this).closest("tr").children("td");
		$(tds).each(function(i){
			if(i!=tds.length-1) {
				$(this).empty();
				$(this).text(arr[i]);
			} else {
				$(this).empty();
				$(this).append("<span class='spanUpdate' >수정</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class='spanDelete' >삭제</span>");
			}
		});
	});
});

// 삭제
$(function(){
	$("body").on("click", ".spanDelete", function(){
		if(! confirm("데이터를 삭제 하시겠습니까 ? "))
			return;
		
		var tr = $(this).closest("tr");
		
		$(tr).fadeOut("show", function(){
			$(tr).remove();
		});
	});
});
</script>

</head>
<body>

<div style="width: 800px; margin: 30px auto 0px;">

	<div style="title">
	   <h3><span>|</span> 성적 처리</h3>
	</div>

	<table class="score-table" style="width: 100%; margin: 20px auto 0px; border-spacing: 1px; background: #cccccc;">
	<thead>
		<tr height="35" bgcolor="#E4E6E4" align="center">
			<td width="80">학번</td>
			<td width="100">이름</td>
			<td width="100">생년월일</td>
			<td width="80">국어</td>
			<td width="80">영어</td>
			<td width="80">수학</td>
			<td width="80">총점</td>
			<td width="80">평균</td>
			<td>변경</td>
		</tr>
	</thead>
	
	<tbody class="score-input">
		<tr bgcolor="#ffffff" align="center">
			<td><input type="text" id="hak"></td>
			<td><input type="text" id="name"></td>
			<td><input type="text" id="birth"></td>
			<td><input type="text" id="kor"></td>
			<td><input type="text" id="eng"></td>
			<td><input type="text" id="mat"></td>
			<td><input type="text" id="tot" readonly="readonly" ></td>
			<td><input type="text" id="ave" readonly="readonly" ></td>
			<td>
				<button type="button" id="btnAdd">등록하기</button>
			</td>
		</tr>
	</tbody>
	
	<tfoot class="score-list">
	</tfoot>
	
	</table>
</div>

</body>
</html>