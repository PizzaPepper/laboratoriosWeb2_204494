    <%-- 
    Document   : DespliegaProducto
    Created on : 16/10/2020, 08:24:54 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Despliega Producto</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>
        
        <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>
        
        <main>
            
            <div class="titulo">
            <h1>Desplegar producto</h1>
            <p>Muestra los datos del producto.</p>
        </div>
            
            <table class="centrada">
                <tr>
                  <td class="derecha">Clave</td>
                  <td class="gris">${producto.clave}</td>
                <tr>
                    <td class="derecha">Nombre</td>
                  <td class="gris">${producto.nombre}</td>
                </tr>
                <tr>
                    <td class="derecha">Unidades</td>
                  <td class="gris">${producto.unidad}</td>
                </tr>
            </table>
            
        </main>
        
        <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
