<%-- 
    Document   : novo-usuario
    Created on : 01/07/2017, 16:55:26
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <body>
    <h2>Cadastro de Usuario </h2>
    <form action="efetuaCadastro" method="post">
      Digite seu Login: <input type="text" name="nome" /> <br /> 
      Digite sua Senha: <input type="password" name="senha" /> <br />
      
      <input type="submit" value="Efetuar Cadastro" /> 
    </form>
   
  </body>
</html>
