<%-- 
    Document   : SeleccionaProductosEliminar
    Created on : 16/10/2020, 08:25:27 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Productos Eliminar</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>
        
        <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>
        
        <main>
            
            <div class="titulo">
            <h1>Escojer producto a Eliminar</h1>
            <p>Selecciona el producto a eliminar.</p>
        </div>
            
            <form action="eliminarProducto" method="post">
                <%-- Tabla donde se muestran los datos de todas los productos --%>
                <table class="bicolor">


                    <%-- Títulos de las columnas --%>
                    <tr>
                        <th>&nbsp;</th>
                        <th>Clave</th>
                        <th>Nombre</th>
                        <th>Unidad</th>
                       
                    </tr>

                    <c:forEach items="${listaProductos}" var="producto" >

                        <tr>
                            <td><input type="radio" name="clave" value="${producto.clave}" /></td>
                            <td>${producto.clave}</td>
                            <td>${producto.nombre}</td>
                            <td>${producto.unidad}</td>
                        </tr>
                    </c:forEach>
                </table>
                <br />
                <div class="tabla centrada">
                        <div class="fila">
                            <%-- Boton enviar --%>
                            <div class="celda">
                                <input type="submit" name="boton" value="Continuar" />
                            </div>
                            <%-- Botón limpiar --%>
                            <div class="celda">
                                <input type="reset" value="Limpiar" />
                            </div>
                        </div>
                    </div>
                     <br>
            </form>
        </main>
        
        <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
