<%-- 
    Document   : Inventarios
    Created on : 16/10/2020, 08:21:26 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Inventario</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>
        
        <%@include file="jspf_FragmentosPag/menuInventarios.jspf" %>
        
        <main>
            <div class="titulo">
                <h1>Inventariar</h1>
                <p>Puedes consultar y inventariar o desinventariar un producto</p>
            </div>
        </main>
        
        
         <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
