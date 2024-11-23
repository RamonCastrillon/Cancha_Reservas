<%-- 
    Document   : mostrarUsuarios
    Created on : 11/11/2024, 4:05:02 p. m.
    Author     : ramon
--%>

<%@page import="java.util.List"%>
<%@page import="CRUDI.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar usuarios</title>
    </head>
    <body>
        <h1>Lista de usuarios registrados</h1>
        <%
            List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
            
            int cont = 1;
            for(Usuario usu: listaUsuarios){
            
            //El b es pa la negrita
        %>
        <p><b>Cliente N° <%=cont%></b></p>
        <p>Dni: <%=usu.getIdUsuario()%></p>
        <p>Apellido: <%=usu.getContrasena()%></p>
        <p>---------------------------------------------------</p>
        
        <% cont++;
            }%>
    </body>
</html>
