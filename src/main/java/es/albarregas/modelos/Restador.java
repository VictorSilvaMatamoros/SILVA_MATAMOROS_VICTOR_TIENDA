
package es.albarregas.modelos;

import es.albarregas.beans.Libro;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class Restador {
     public ArrayList<Libro> restarLibro(ArrayList<Libro> listadoLibros, String nombreLibro) {

        for (Libro articulo : listadoLibros) {
            String nombreArticulo = articulo.getNombre();

            if (nombreArticulo.equals(nombreLibro)) { 
                int cantidadAnterior = articulo.getCantidad();
                articulo.setCantidad(cantidadAnterior - 1);
                articulo.setPrecioTotal();
                break;
            }
        }

        return listadoLibros;
    }
}
