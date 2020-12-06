<%-- 
    Document   : DespliegaProductos
    Created on : 21/10/2020, 05:45:20 PM
    Author     : eliu
--%>

<%@page import="java.util.List"%>
<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Despliega Productos</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>

        <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>
        
        
        
        <main>

            <div class="titulo">
                <h1>Desplegar productos</h1>
                <p>Muestra los datos en una lista de productos.</p>
            </div>


            <table class="bicolor" >

                <%-- Título de la tabla --%>
                <caption>
                    Productos
                </caption>

                <tr>
                    <%-- Títulos de las columnas --%>
                    <th>Clave</th>
                    <th>Nombre</th>
                    <th>Unidad</th>
                </tr>
                <%--${listaProductos.lista} --%>



                <c:forEach  items="${listaProductos}" var="producto"> 
                    <tr>
                        <td> ${producto.clave}</td>
                        <td> ${producto.nombre}</td>
                        <td> ${producto.unidad}</td>
                    </tr>
                </c:forEach>

            </table>
        </main>

        <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
