<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>

       <%-- 
      <h1>I Love Java . I love you so much</h1>
      <h2>Master Java Expert : ${obj.id}---> ${obj.name}---->${obj.lang}</h2>
      --%>
      
      
<p>Name: ${name}</p>
<p>Language: ${lang}</p>

<%--For ModelAndView --%>
<p>Name: ${obj.name}</p>
<p>Language: ${obj.lang}</p>




</body>
</html>