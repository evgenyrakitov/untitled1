<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update 'User</title>
</head>

  <body>
  <form method="POST" >
              <table border="0">
                  <tr>
                     <td>Id</td>
                     <td><input type="text" name="id" value="${user.id}" /></td>
                  </tr>
                 <tr>
                    <td>Login</td>
                    <td><input type="text" name="login" value="${user.login}" /></td>
                 </tr>
                 <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" value="${user.email}" /></td>
                 </tr>
                 <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" value="${user.password}" /></td>
                 </tr>
                 <tr>
                    <td colspan = "2">
                        <input type="submit" value="Submit" />
                    </td>
                 </tr>
              </table>
           </form>
  </body>
</html>
