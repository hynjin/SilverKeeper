<%@ page language="java" contentType="text/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="connection.sendPushAlarm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%sendPushAlarm pushAlarm = new sendPushAlarm();
String tokenId="c3ZAOK5sfb4:APA91bErcaX68sZjmH4Ad_wcqeocd9H4UYYV01dpQ2M9TCtZnNZUtgARJkl7HY6Yqy7psZ-1JLudoqbgELyZPi0Ojc1S0ytni-lgMGNg8izoi5sDMUcoAx8DlrBsQvUj99zpQovAVS47";//"cK10vzn7dLE:APA91bFa-IzfXEYS7AIgTjEjcvRPRbDq_fl74l4KN2vnCS9bAByticmEYr5WxGxrrkN5Kn0LXt0kV3YCHKVg6Rz37TuJkI_AHbMqNee64MgGIrmbSah3xHceJUQw8tBInJjYdf_-GHYJ";
String server_key= "AAAAB7TI-uE:APA91bFKkm7OJcvHl8dJv8cswAzz7Ulg42odqafOF-9FayoYWvzAIf5VunKRgFBPLDzPSyCjy_BCfURzNU-ojWcHb7ULO23JjsaqdG-a42YCXbmGJ8n-Wmo_qE7Q61TwzQJHpDjSKeOc";
String message="이제그만자고싶다";
pushAlarm.send_FCM_Notification(tokenId, server_key, message);
%>
dlwpehlfusk
</body>
</html>



