
package es.albarregas.modelos;

import es.albarregas.beans.Libro;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class Precios {
     public double precioLibros(ArrayList<Libro> listadoLibros) {

        double sumaPrecioTotal = 0.0f;

        for (Libro libro : listadoLibros) {
            sumaPrecioTotal += libro.getPrecioTotal();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String sumaPrecioFormateada = decimalFormat.format(sumaPrecioTotal);
        
        double sumaPrecioTruncada = Double.parseDouble(sumaPrecioFormateada.replace(",","."));
        

        return sumaPrecioTruncada;
    }
}
