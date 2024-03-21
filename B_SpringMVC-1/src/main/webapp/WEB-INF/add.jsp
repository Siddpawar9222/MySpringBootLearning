<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
       <meta charset="UTF-8">
       <title>Hello JSP</title>
</head>
<body>
<h1>Hello JSP!</h1>

<form action="/model-view/register1">
       <input type="text" name="name"><br/>
       <input type="text" name="lang"><br/>
       <input type="submit" value="Submit"><br/>
</form>
</body>
</html>
<%--
   action="/model-view/register"  : When you call like this url http://localhost:8080/model-view/ change into http://localhost:8080/model-view/register?name=Siddhesh&lang=java . Which give us correct result

   action="model-view/register" : when you call like this then http://localhost:8080/model-view/ change into http://localhost:8080/model-view/model-view/register?name=Siddhesh&lang=java . which is wrong
--%>

<%--

<%=user1name%> (Scriptlet):
This is an example of scripting language expression.
Allow you to include data into webpage from variable /object.
<%=user1name%> is equivalent to writing <% out.print(user1name); %>


${user1name} (Expression Language):

This is an example of Expression Language (EL) .
This  is used access object which is inserted or send by modelandView(or other method) in controller
--%>

<%--
  Always use class in interative code , id doesnt work
  a - support only get method
  form - suppport get and post method
  to use other methods we have to use jquery
 --%>