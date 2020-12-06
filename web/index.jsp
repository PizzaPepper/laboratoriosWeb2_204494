<%-- 
    Document   : index
    Created on : 16/10/2020, 10:12:41 PM
    Author     : eliu
--%>

<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Bienvenido</title>
        
    </head>
    <body>
        <%-- Incluye el encabezado de la pagina --%>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>

        <%-- Incluye el menu principal --%>
        <%@include file="jspf_FragmentosPag/menuPpal.jspf"%>
        
        <main>
            <div class="titulo">
                <h1>¡Bienvenido!</h1>
                <p>Puedes Ingresar,eliminar,actualizar productos<br>
                Inventariar y desinventariar materiales<br>
                Ademas de consultar los productos y materiales
                </p>

            </div>
        </main>
        
        <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
        
        
    </body>
</html>
