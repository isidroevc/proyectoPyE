/**
 * TestArchivoDatos.java
 * 
 * En esta clase con método main se prueba el funcionamiento de la clase
 * ArchivoDeDatos
 */
package estadisticadescriptiva.revision;

import estadisticadescriptiva.datos.ArchivoDeDatos;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author tharduz
 */
public class TestArchivoDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArchivoDeDatos a = new ArchivoDeDatos("src/estadisticadescriptiva/archivosdeprueba/jeje.txt",",");
        double[] datos = a.getDatos(false);
        double aux = 0;
        System.out.println(datos.length);
        //ordenarlos
        int c = datos.length;
        for(int i = 0; i < c -1; i++){
            for(int j = 0;j < c -1; j++){
                if(datos[j+1] < datos[j]){
                    aux = datos[j+1];
                    datos[j+1] = datos[j];
                    datos[j] = aux;
                } 
            }
        }
        for(int i = 0; i < c; i++){
            
            System.out.println(datos[i]);
        }
        /*Random r = new Random();
        try{
            
             FileWriter w = new FileWriter(new File("C:/users/tharduz/Desktop/jeje.txt"));
             w.write(Double.toString(Math.abs(r.nextDouble())));
             for(int i =0; i < 9999; i++){
                 w.write("," + Math.abs(r.nextDouble()));
             }
             w.close();
        }catch(Exception ex){
            
        }*/
       
    }
    
}
