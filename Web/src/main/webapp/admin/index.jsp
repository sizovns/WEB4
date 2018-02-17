<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Админка</title>
</head>
<body>
<p>Привет админушка!</p>
<p>Что ты хочешь сделать?</p>
<form method="post" action="/admin/index">
<pre>
<label for="name">NAME</label>      <input type="text" name="name" id="name" required="required">
<label for="login">LOGIN</label>     <input type="text" name="login" id="login" required="required">
<label for="password">PASSWORD</label>  <input type="text" name="password" id="password" required="required">
<label for="role">ROLE</label>      <select id="role" required name="role">
<option disabled>Chose role</option>
<option value="user">user</option>
<option value="admin">admin</option>
</select>
<input type="submit" name="add" value="add">
</pre>
</form>
<form method="get" action="/admin/showAll">
    <input type="submit" name="showAll" value="showAll">
</form>
</body>
</html>