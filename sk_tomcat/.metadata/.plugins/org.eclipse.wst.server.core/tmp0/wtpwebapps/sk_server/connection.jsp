<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Date nowDate=new Date();//현재날짜와 시간을 얻어옴
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//날짜형식을 yyyy년MM월dd일 형태로 사용하기 위해서 SimpleDateFormat객체 생성
String formatDate=dateFormat.format(nowDate);
//현재의 날짜와 시간에 yyyy년 MM월dd일 형식을 format()메소드를 사용해서 적용
Date date = new Date(System.currentTimeMillis());
 %>
<h1>로그인 </h1>
<form action="receiveSilverData" method="post">
id. : <input type="text" name="silverID"/><br/>
심박. : <input type="text" name="heartRate"/><br/>
걸음. : <input type="text" name="walkCount"><br/>
아이디 : <input type="text" name="identifyNumber"/><br/>
날짜. : <input type="text" name="currentTime" ><br/>
미밴드. : <input type="text" name="checkMiBand"/><br/>
<input type="submit" value="로그인"/>
</form>

</body>
</html>