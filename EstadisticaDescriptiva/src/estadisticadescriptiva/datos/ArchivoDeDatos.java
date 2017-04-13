/**
 * ArchivoDeDatos.java
 * 
 * Esta clase permite la lectura de datos numéricos desde un archivo de texto
 * en el cual los datos se encuentran delimitados por un caracter en específico
 * como puede ser espacios, comas, etc
 */
package estadisticadescriptiva.datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Isidro Emmanuel Vásquez Cortés
 */
public class ArchivoDeDatos {
    // -Atributos.
    private File archivo; // -El archivo de donde provendrán los datos
    private String ruta; // -Ruta del archivo de donde provendrán los datos
    private String separador; // -Separador de los datos
    double[] datos; // -Datos.
    boolean leidos; // -Indica si ya se ha hecho lectura.
    
    // -Constructores
    
    /**
     * 
     * @param ruta define la ruta en la que se encuentra el archivo en disco
     * @param separador define que caracter separa los datos dentro del archivo.
     */
    public ArchivoDeDatos(String ruta,String separador){
        this.ruta = ruta;
        this.separador = separador;
    }
    
    /**
     * 
     * @throws FileNotFoundException
     */
    public void leerDatos() throws FileNotFoundException{
        Scanner lector;
        String temporal = ""; // -Funge como buffer.
        String[] datosString;
        archivo = new File(ruta);
        if(archivo == null){
            throw new FileNotFoundException();
        }
        lector = new Scanner(archivo);
        while(lector.hasNext()){
            temporal += lector.next();
        }
        datosString = temporal.split(this.separador);
        this.datos = new double[datosString.length];
        for(int i = 0, longitud = datos.length; i < longitud; i++){
            try{
            this.datos[i] = Double.parseDouble(datosString[i].trim());
            }catch(Exception ex){
                
            }
        }
        leidos = false;
    }
    // -Métodos de acceso
    
    /**
     * 
     * @param releer
     * @return datos, los datos que contiene el archivo en un arreglo de double
     */
    public double[] getDatos(boolean releer){
        if(releer || !leidos){
            try{
                leerDatos();
            }catch(Exception ex){
                
            }
        }
        return this.datos;
    }
}
