<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../inc/metas.inc"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="centrado">
            <div class="inicioPagina">
                <%@include file="../inc/cabecera.inc" %>
                <div class="contenido">
                    <form action="<%=request.getContextPath()%>/WebController" method="post">
                        ${requestScope.infoAgregado}
                        <p>Escoja un libro de la lista:</p>
                        <p><select class="form-select" name="libro" id="libro" size="5">
                        <option value="El libro de la Guerra,32.40">El libro de la Guerra</option>
                        <option value="Hueso y Tinta,19.99" selected>Hueso y Tinta</option>
                        <option value="Fallout,39.90">Fallout</option>
                        <option value="FlashPoint,10.00">FlashPoint</option>
                        <option value="El Joker,109.98">El Joker</option>
                     
                    </select>
                        <div><p>Cantidad: <input id="cantidad" name="cantidad" type="number" value="1" min="1"/></p></div>
                        <div class="acciones">
                            <input type="submit" value="Agregar" name="boton"/>
                            <input type="submit" value="Ver Carrito" name="boton" ${requestScope.hayCompra}/>
                        </div>
                    </form>
                    <form action="<%=request.getContextPath()%>/CarroController" method="post">
                        <input type="submit" value="Finalizar compra" name="boton" ${requestScope.hayCompra}/>
                    </form>
                    
                </div>
                <%@include file="../inc/pie.inc" %>
            </div>
        </div>
    </body>
</html>
