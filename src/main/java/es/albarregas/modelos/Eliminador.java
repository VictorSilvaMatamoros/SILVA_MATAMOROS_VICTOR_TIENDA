
package es.albarregas.modelos;

import es.albarregas.beans.Libro;
import java.util.ArrayList;


public class Eliminador {
     public ArrayList<Libro> eliminarLibro(ArrayList<Libro> listadoLibros, String nombreLibro) {

        for (Libro articulo : listadoLibros) {
            String nombreArticulo = articulo.getNombre();

            if (nombreArticulo.equals(nombreLibro)) { 
                listadoLibros.remove(articulo);
                break;
            }
        }

        return listadoLibros;
    }
}
