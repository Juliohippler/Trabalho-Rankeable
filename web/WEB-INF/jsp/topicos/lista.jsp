<%-- 
    Document   : lista
    Created on : 26/05/2017, 22:19:21
    Author     : ufpr
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page session="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Principal de topicos </title>
        <script src="https://code.jquery.com/jquery-2.2.3.js" integrity="sha256-laXWtGydpwqJ8JA+X9x2miwmaiKhn8tVmOVEigRNtP4=" crossorigin="anonymous"></script>

    </head>
    <body>
        <table>
  <tr>
    
    <th>Autor</th>
    <th>Descrição</th>

  
  </tr>
  <c:forEach var="topico" items="${topicos}">
    
      <tr id="topicos_${topico.id}">
       
          <td></td>
          <td>${topico.descricao}</td>         
          
          <td><a href="mostraTopico?id=${topico.id}">Editar</a></td>
          <td><a href="removeTopico?id=${topico.id}">Remover</a></td>
          
          <td><a href="listaComentarios?id_topico=${topico.id}&id_usuario=${usuario.id}">Ver comentarios</a></td>
         
      </tr>
  </c:forEach>
      <br>
      
   </table>
      
        <td><a href="novoTopico?id_usuario=${usuario.id}"  >Adicionar Novo tópico </a></td>
        
    </body>
</html>
