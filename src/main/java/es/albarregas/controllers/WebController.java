package es.albarregas.controllers;

import es.albarregas.beans.Libro;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Dependiendo del botón presionado, realiza diferentes acciones, como agregar libros 
al carrito, ver el carrito o finalizar la compra. Luego, redirige al usuario a la página apropiada. 
*/
@WebServlet(name = "WebController", urlPatterns = {"/WebController"})
public class WebController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String boton = request.getParameter("boton");
        String vista = "";
        Cookie[] galletas = request.getCookies();
        Cookie compra = null;
        String hayCompra = "disabled";
        String infoAgregado = "";
        String[] libroSeleccionado = new String[2];
        int cantidadLibros = 0;
        ArrayList<Libro> listaDeLibros = new ArrayList<>();

        switch (boton) {

            case "Agregar":
                //
                String libroFormulario = request.getParameter("libro");
                if (galletas != null) {
                    for (int i = 0; i < galletas.length; i++) {
                        if (URLDecoder.decode(galletas[i].getName(), "UTF-8").equals("compra")) {
                            compra = galletas[i];
                            hayCompra = " "; //Para quitar el disabled a los botones
                            break;
                        } else {
                            compra = new Cookie(URLEncoder.encode("compra", "UTF-8"), URLEncoder.encode("", "UTF-8"));
                            response.addCookie(compra);
                        }
                    }
                }

                //usamos el split para separar nombre y precio del libro
                libroSeleccionado = libroFormulario.split(",");
                cantidadLibros = Integer.parseInt(request.getParameter("cantidad"));

                infoAgregado = "<h3 style='color: olive;'>Se han añadido " + cantidadLibros + " ejemplar/es del libro " + libroSeleccionado[0] + " al carrito.</h3>";
                request.setAttribute("infoAgregado", infoAgregado);

                //Metemos el libro en el array de los libors
                Libro libroNuevo = new Libro(libroSeleccionado[0],Double.parseDouble(libroSeleccionado[1]),cantidadLibros);
                
                if(request.getSession().getAttribute("listaDeLibros") != null){
                    listaDeLibros = (ArrayList<Libro>)request.getSession().getAttribute("listaDeLibros");
                }
                boolean libroEncontrado = false;
               
                
                if(!listaDeLibros.isEmpty()){
                    for (Libro articulo : listaDeLibros) {
                        String nombreArticulo = articulo.getNombre();
                        
                        if (nombreArticulo.equals(libroSeleccionado[0])) {
                            int cantidadAnterior = articulo.getCantidad();
                            articulo.setCantidad(cantidadAnterior + cantidadLibros);
                            articulo.setPrecioTotal();
                            libroEncontrado = true;
                            break;
                        }
                    }
                }

                if (!libroEncontrado) {
                    listaDeLibros.add(libroNuevo);
                }
                request.getSession().setAttribute("listaDeLibros", listaDeLibros);

               
                vista = "./jsp/tienda.jsp";
                break;

            case "Ver Carrito":
                
                vista = "./jsp/carrito.jsp";
                break;

            case "Finalizar Compra":
                vista = "./jsp/factura.jsp";
                break;

        }

        request.getRequestDispatcher(vista).forward(request, response);

    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
