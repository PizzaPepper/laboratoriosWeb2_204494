<%-- 
    Document   : Productos
    Created on : 16/10/2020, 08:20:49 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Productos</title>
    </head>
    <body>
       
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>
        
        <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>
        
        <main>
            <div class="titulo">
                <h1>Productos</h1>
                <p>Puedes Ingresar,eliminar,actualizar productos</p>

            </div>
        </main>
        
        
         <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
