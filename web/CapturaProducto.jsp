<%-- 
    Document   : CapturaProducto
    Created on : 16/10/2020, 08:23:35 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
        <title>Captura Producto</title>
    </head>
    <body>
        <%@include file="jspf_FragmentosPag/Titulo.jspf"%>

        <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>

        <main>
            <div class="titulo">
                <h1>Capturar Producto</h1>
                <p>Captura los datos de un producto agregar al catálogo de productos.</p>
            </div>
                
        <form action="agregarProducto" method="post" name="CapturaProducto" id="form_CapturaProducto" >
                <div class="tabla">
                    <%-- ID --%>
                    <div class="fila">
                        <div class="celda label">
                            <label for="claveId" >Clave* &ensp;</label>
                        </div>
                        <div class="celda ">
                            <input type="text" id="claveId" name="clave"
                                   value="${param.clave}" size="7" readonly />
                        </div>
                    </div>
                    <br>
                    <%-- Nombre Producto --%>
                    <div class="fila">
                        <div class="celda label"><label for="nombreProd">Nombre* &ensp;</label></div>
                        <div class="celda">
                            <input type="text" id="nombreProd" name="nombre" value="${param.nombre}" size="15" maxlength="35"
                            onkeypress="return checkInputProd(event)" pattern="[@#$%^!&*()_+*]" required />
                        </div>
                    </div>
                    <br>
                    <%-- Unidades Producto --%>
                    <div class="fila label">
                        <div class="celda label"><label for="unidadProd">Unidad &ensp;</label></div>
                        <div class="celda">
                            <input type="text" id="unidadProd" name="unidad" value="${param.unidad}" 
                                   size="3" maxlength="3"
                                   />
                        </div>
                    </div>
                </div>
                <br>
                <%-- Botones --%>
                <div class="tabla input">
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
                <div class="error" > </div>

        </form>
        </main>
        <script src="js/script.js"></script>
        <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
    </body>
</html>
