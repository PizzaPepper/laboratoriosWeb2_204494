<%-- 
    Document   : capturaClave
    Created on : 16/10/2020, 08:21:48 PM
    Author     : eliu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/Estilos.css" />
    <title>Captura Clave</title>
</head>

<body>
    <%@include file="jspf_FragmentosPag/Titulo.jspf"%>

    <%@include file="jspf_FragmentosPag/menuProductos.jspf" %>

    <main>
        <div class="titulo">
            <h1>Capturar Clave</h1>
            <p>Captura la clave de un producto agregar al catálogo de productos.</p>
        </div>

        <form action="obtenProducto" method="post" name="CapturaClave" id="form_CapturaClave">
            <%--Clave del producto--%>
            <div class="tabla centrada">
                <div class="fila">
                    <div class="celda"><label for="claveId">Clave* &ensp;</label></div>
                    <div class="celda">
                        <input type="text" id="claveId" name="clave" value="${param.clave}" size="10" maxlength="10"
                            placeholder="Inserte clave" required />
                    </div>
                </div>
            </div>
            <br>
            <%--Botones--%>
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
                    <div id="errorClave" > <p>${errorKey}</p>  </div>

        </form>
    </main>

    

    <script src="js/script.js"></script>
    <%@include file="jspf_FragmentosPag/PieSeccion.jspf" %>
</body>

</html>