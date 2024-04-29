package es.albarregas.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.albarregas.beans.Libro;
import es.albarregas.modelos.Eliminador;
import es.albarregas.modelos.Precios;
import es.albarregas.modelos.Restador;
import es.albarregas.modelos.Sumador;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.http.Cookie;


@WebServlet(name = "CarroController", urlPatterns = {"/CarroController"})
public class CarroController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String boton = request.getParameter("boton");
        String vista = "";
        Eliminador elimina = new Eliminador();
         Sumador suma = new Sumador();
          Restador resta = new Restador();
           Precios precio = new Precios();
        ArrayList<Libro> listaDeLibros = new ArrayList<>();
        String nombreLibro = request.getParameter("libroTitulo");
        Cookie[] cookies = request.getCookies();
        
        switch (boton) {

            case "Volver":
                vista = "./jsp/tienda.jsp";
                break;

            case "Finalizar compra":
                listaDeLibros = (ArrayList<Libro>)request.getSession().getAttribute("listaDeLibros");
                request.setAttribute("precioLibros", precio.precioLibros(listaDeLibros));
                
                //Manejamos el IVA
                double iva = precio.precioLibros(listaDeLibros) * 0.04;
                DecimalFormat df = new DecimalFormat("#.00");
                String ivaFormateado = df.format(iva);
                request.setAttribute("iva", ivaFormateado);
                
                double totalConIva = precio.precioLibros(listaDeLibros) + iva;
                DecimalFormat df2 = new DecimalFormat("#.00");
                String totalConIvaFormateado = df2.format(totalConIva);
                request.setAttribute("totalConIva", totalConIvaFormateado);
                vista = "./jsp/factura.jsp";
                
                break;

            case "+":
                vista = "./jsp/carrito.jsp";
                listaDeLibros = (ArrayList<Libro>)request.getSession().getAttribute("listaDeLibros");
                nombreLibro = request.getParameter("libroTitulo");
                listaDeLibros = suma.sumarLibro(listaDeLibros, nombreLibro);
                
                request.getSession().setAttribute("listaDeLibros", listaDeLibros);
                break;

            case "-":
                vista = "./jsp/carrito.jsp";
                listaDeLibros = (ArrayList<Libro>)request.getSession().getAttribute("listaDeLibros");
                nombreLibro = request.getParameter("libroTitulo");
                listaDeLibros = resta.restarLibro(listaDeLibros, nombreLibro);
                
                request.getSession().setAttribute("listaDeLibros", listaDeLibros);
                break;

            case "Eliminar":
                listaDeLibros = (ArrayList<Libro>)request.getSession().getAttribute("listaDeLibros");
                nombreLibro = request.getParameter("libroTitulo");
                listaDeLibros = elimina.eliminarLibro(listaDeLibros, nombreLibro);
                
                request.getSession().setAttribute("listaDeLibros", listaDeLibros);
                
                if(!listaDeLibros.isEmpty()){
                    vista = "./jsp/carrito.jsp";
                } else { 
                    vista = "./index.jsp";
                    
                    
                    request.getSession().removeAttribute("listaDeLibros");
                    
                    for (Cookie cookie : cookies) {
                        if ("compra".equals(cookie.getName())) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            
                            break; 
                        }
                    }
                }
                break;
                
            case "Vaciar carrito":
            case "Gracias":
                vista = "./index.jsp";

                for (Cookie cookie : cookies) {
                    if ("compra".equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
                
                request.getSession().removeAttribute("listaDeLibros");
                break;
        }
        request.getRequestDispatcher(vista).forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
