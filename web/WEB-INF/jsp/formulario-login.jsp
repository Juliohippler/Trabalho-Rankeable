<%-- 
    Document   : formulario-login
    Created on : 09/06/2017, 21:49:22
    Author     : julio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
  <body>
    <h2>Página de Login </h2>
    <form action="efetuaLogin" method="post">
      Login: <input type="text" name="nome" /> <br /> 
      Senha: <input type="password" name="senha" /> <br />
      
      <input type="submit" value="Efetuar Login" /> 
    </form>
    <a href="cadastrarForm"> Clique aqui para cadastrar um novo usuario</a>
  </body>
</html>