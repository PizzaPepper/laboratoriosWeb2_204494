<%-- 
    Document   : DespliegaInventarioMateriales
    Created on : 16/10/2020, 08:26:29 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>
        
        <%@include file="jspf_FragmentosPag/menuInventarios.jspf" %>
        
        <main>
            
            <div class="titulo">
                <h1>Consulta de inventario materiales</h1>
                <p>Se muestra una tabla con los materiales capturados</p>
            </div>

            <table class="bicolor">
                
                <%-- Título de la tabla --%>
                <caption>
                    Materiales
                </caption>

                <tr>
                    <%-- Títulos de las columnas --%>
                    <th>Producto</th>
                    <th>Cantidad</th>
                </tr>

                <c:forEach items="${listaMateriales}" var="material">
                    <tr>
                        <td>${material.clave}, ${material.nombre}, ${material.unidad}</td>
                        <td>${material.cantidad}</td>
                    </tr>
                </c:forEach>
            </table>
            
        </main>
        
        
         <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
