<%@page import="java.util.List"%>
<%@page import="es.albarregas.beans.Libro"%>
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
            <table border="1">
                <tr>
                    <th>Título</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Sumar o Restar</th>
                    <th>Eliminar</th>
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
                    <td>
                        <form action="CarroController" method="post">
                            <input type="hidden" name="libroTitulo" value="<%= libro.getNombre() %>">
                            <input type="submit" value="+" name="boton">
                            <input type="submit" value="-" name="boton" <%= libro.getCantidad() == 0 ? "disabled" : "" %>>
                        </form>
                    </td>
                    <td>
                        <form action="CarroController" method="post">
                            <input type="hidden" name="libroTitulo" value="<%= libro.getNombre() %>">
                            <input type="submit" value="Eliminar" name="boton">
                        </form>
                    </td>
                </tr>
                <% 
                    }
                }
                %>
            </table>
        </div>
        <form action="CarroController" method="post" style="margin-top:20px;">
            <input type="submit" value="Volver" name="boton"/>
            <input type="submit" value="Finalizar compra" name="boton"/>
            <input type="submit" value="Vaciar carrito" name="boton"/>
        </form>
        <%@include file="../inc/pie.inc" %>
    </div>
</div>
</body>
</html>
