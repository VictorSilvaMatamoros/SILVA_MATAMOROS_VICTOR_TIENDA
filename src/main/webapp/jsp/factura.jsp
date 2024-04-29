<%@page import="java.util.List"%>
<%@page import="es.albarregas.beans.Libro"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="../inc/metas.inc" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="centrado">
    <div class="inicioPagina">
        <%@ include file="../inc/cabecera.inc" %>
        <div class="contenido">
            <table border="1">
                <tr>
                    <th>Título</th>
                    <th>Cantidad</th>
                    <th>Precio Unitario</th>
                    <th>Precio Total</th>
                </tr>
                <% 
                List<Libro> listaDeLibros = (List<Libro>) session.getAttribute("listaDeLibros");
                if (listaDeLibros != null) {
                    for (Libro libro : listaDeLibros) {
                %>
                <tr>
                    <td><%= libro.getNombre() %></td>
                    <td><%= libro.getCantidad() %></td>
                    <td><%= libro.getPrecio() %>€</td>
                    <td><%= libro.getPrecioTotal() %>€</td>
                </tr>
                <% 
                    }
                }
                %>
            </table>
            <p>Suma total: <%= request.getAttribute("precioLibros") %>€</p>
            <p>IVA 4%: <%= request.getAttribute("iva") %>€</p>
            <p>Total con IVA: <%= request.getAttribute("totalConIva") %>€</p>
        </div>
        <form action="CarroController" method="post" style="margin-top:20px;">
            <input type="submit" value="Gracias" name="boton"/>
        </form>
        <%@ include file="../inc/pie.inc" %>
    </div>
</div>
</body>
</html>
