package es.albarregas.controllers;

import java.io.IOException;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TiendaController", urlPatterns = {"/TiendaController"})
public class TiendaController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] galletas = request.getCookies();
        Cookie compra = null;
        String hayCompra = "disabled";
        
        if (galletas != null) {
            for (int i = 0; i < galletas.length; i++) {
                if (URLDecoder.decode(galletas[i].getName(), "UTF-8").equals("compra")) {
                    compra = galletas[i];
                    hayCompra = " ";
                    break;
                }
            }
        }
        
        request.setAttribute("hayCompra", hayCompra);
        
        request.getRequestDispatcher("./jsp/tienda.jsp").forward(request, response);

    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
