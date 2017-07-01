<%-- 
    Document   : lista
    Created on : 26/05/2017, 22:19:21
    Author     : ufpr
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Principal de Comentarios</title>
        <script src="https://code.jquery.com/jquery-2.2.3.js" integrity="sha256-laXWtGydpwqJ8JA+X9x2miwmaiKhn8tVmOVEigRNtP4=" crossorigin="anonymous"></script>

    </head>
    <body>
        <table>
  <tr>
    
    <th>Id</th>
    <th>Descrição</th>

  </tr>
  <c:forEach var="comentario" items="${comentarios}">
      <tr id="comentarios_${comentario.id}">
          <td>${comentario.id}</td>
          <td>${comentario.comentario}</td>
 
          <td><a href="removeComentario?id=${comentario.id}&id_topico=${comentario.id_topico}">Remover</a></td>
          <td><a href="mostraComentario?id=${comentario.id}&id_topico=${comentario.id_topico}">Editar</a></td>
        
         
      </tr>
  </c:forEach>
      <br>
   </table>

        <td><a href="novoComentario?id_topico=${topico.id}&id_usuario=${usuario.id}">Comentar</a></td>
        <td><a href="listaTopicos"> Ir para lista de Topicos </a></td>
        
       
    </body>
</html>
