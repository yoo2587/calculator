<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
Object oErr=request.getAttribute("errmsg");
String errMsg="NULL";
if(oErr!=null) errMsg=oErr.toString();

%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시스템 오류!</title>
</head>
<body>
<p>요청을 처리하는 중에 문제가 발생하였습니다. 잠시 후에 다시 요청하시기 바랍니다.<br><br>
Error : <%=errMsg%>
</p>
</body>
</html>