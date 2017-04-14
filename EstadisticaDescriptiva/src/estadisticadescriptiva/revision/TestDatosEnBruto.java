/**
 * TestDatosEnBruto.java
 *
 * En este archivo se pŕueba el funcionamiento de la clase DatosEnBruto.
 *
 */
package estadisticadescriptiva.revision;

import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;
import java.util.ArrayList;

/**
 *
 * @author Vasquez Cortes Isidro Emmanuel
 */
public class TestDatosEnBruto {

    public static void main(String[] args) {
        double[] datos = new ArchivoDeDatos("separadorTab.txt","\t").getDatos(true);
        //double[] datos = {1, 1, 3, 3, 5, 6, 7, 8, 9, 10,11};
        String modass = "";
        DatosEnBruto prueba = new DatosEnBruto(datos);
        ArrayList<Double> modas = prueba.calcularModa();
        if (modas != null) {
            for (double moda : modas) {
                modass += moda + ", ";
            }
        }
        System.out.println(prueba.toString());

    }

}
