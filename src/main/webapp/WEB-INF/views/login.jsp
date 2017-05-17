<%--
  Created by IntelliJ IDEA.
  User: crschmidt
  Date: 01.02.2017
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<style>
  form {
    width: 100%;
  }
  label {
    min-width: 150px;
    float: left;
    display: block;
    padding-bottom: 15px;
  }
  input {
    margin-bottom: 15px;
  }
</style>

  <h1>Das ist mein Login.jsp 554444 f :-)</h1>

  <p style="color: red;">
    ${errorMessage}
  </p>
  <form action="/login" method="post">
    <fieldset>
      ${testService.getTest}
    <label>Name:</label><input type="text" name="name" /><br/>
    <label>Passwort:</label><input name="password" type="password" /><br/>
    <input type="submit" name="senden" />
    </fieldset>
  </form>
</body>
</html>
