/**
 * ArchivoDeDatos.java
 *
 * Esta clase permite la lectura de datos numéricos desde un archivo de texto
 * en el cual los datos se encuentran delimitados por un caracter en específico
 * como puede ser espacios, comas, etc
 */
package estadisticadescriptiva.datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Isidro Emmanuel Vásquez Cortés
 */
public class ArchivoDeDatos {

    // -Atributos.
    private String ruta; // -Ruta del archivo de donde provendrán los datos
    private String separador; // -Separador de los datos
    private String codificacion; //-Codifiacion de texto del archivo
    private String errores; // -Guarda los errore sque hubo al parsear datos.
    double[] datos; // -Datos.
    private int cuentaErrores;
    boolean leidos; // -Indica si ya se ha hecho lectura.
    // -Constructores

    /**
     *
     * @param ruta define la ruta en la que se encuentra el archivo en disco
     * @param separador define que caracter separa los datos dentro del archivo.
     */
    public ArchivoDeDatos(String ruta, String separador) {
        this.ruta = ruta;
        this.separador = separador;
        this.codificacion = "ASCII";
    }

    /**
     *
     * @param ruta
     * @param separador
     * @param codificacion define bajo que estándar esta codificado el texto del
     * archivo.
     */
    public ArchivoDeDatos(String ruta, String separador, String codificacion) {
        this.ruta = ruta;
        this.separador = separador;
        this.codificacion = codificacion;
    }

    /**
     *
     * @throws FileNotFoundException
     */
    public void leerDatos() throws FileNotFoundException, IOException {
        errores = "";
        cuentaErrores = 0;
        File archivo; // -Representa el archivo que se tendrá en disco
        FileInputStream flujoEntrada; // -Asiste en la lectura de la totalidad
        //del texto del archivo

        String textoArchivo; // -Variable que contendrá el texto del archivo                    
        String[] datosString; // -Arreglo de datos pero aún de tipo String
        double[] respaldo;
        byte[] bytesArchivo; // -Destinado a guardar los bytes que contenga 
        // archivo
        int i, j, longitud;
        archivo = new File(ruta);

        flujoEntrada = new FileInputStream(archivo); // -Crear flujo de entrada
        bytesArchivo = new byte[(int) archivo.length()];
        //leer los bytes del archivo
        flujoEntrada.read(bytesArchivo);

        textoArchivo = new String(bytesArchivo, codificacion);
        if(!separador.equals("\n")){
            textoArchivo.replaceFirst("[\n\r]", separador);
        }
        bytesArchivo = null; //-Limpiar la memoria
        datosString = textoArchivo.split(separador);
        textoArchivo = null; //-Limpiar la memoria
        this.datos = new double[datosString.length];
        // -El siguiente for llena los datos 
        for (i = 0, longitud = datos.length; i < longitud; i++) {
            try {
                datos[i] = Double.parseDouble(datosString[i].trim());
            } catch (Exception ex) {
                datos[i] = Double.NaN;
                errores += "\nError parseando: " + datosString[i]
                        + " posición " + (i + 1);
                cuentaErrores++;
            }
        }
        //Si hubo errores hay que "resanar" el arreglo y dejar sólo los datos
        //que si sirven.
        if (cuentaErrores > 0) {
            longitud = datos.length;
            respaldo = datos.clone();
            datos = new double[datos.length - cuentaErrores];
            i = 0;
            j = 0;
            while (i < longitud) {
                if (!Double.isNaN(respaldo[i])) {
                    datos[j] = respaldo[i];
                    j++;
                }
                i++;
            }
        }
        leidos = true;
    }
    // -Métodos de acceso

    /**
     *
     * @param releer
     * @return datos, los datos que contiene el archivo en un arreglo de double
     */
    public double[] getDatos(boolean releer) throws IOException {
        if (releer || !leidos) {
            leerDatos();
        }
        return this.datos;
    }

    /**
     *
     * @return la ruta del archivo en disco
     */
    public String getRuta() {
        return ruta;
    }

    /**
     *
     * @param ruta nueva ruta del archivo en disco
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     *
     * @return el caracter o caracteres que separan los datos en el archivo
     */
    public String getSeparador() {
        return separador;
    }

    /**
     *
     * @param separador el nuevo caracter que se separ alos datos en el archivo
     */
    public void setSeparador(String separador) {
        this.separador = separador;
    }

    /**
     *
     * @return verifica que haya datos en el arreglo datos.
     */
    public boolean hasDatos() {
        return datos.length > 0;
    }

    /**
     *
     * @param codificacion la nueva codificacion de texto para el archivo
     */
    public void setCodificacion(String codificacion) {
        this.codificacion = codificacion;
    }

    /**
     *
     * @return retorna la codificacion de texto actual para el archivo.
     */
    public String getCodificacion() {
        return this.codificacion;
    }

    /**
     *
     * @return muestra el detalle de los errores que hubo parseando
     */
    public String getErrores() {
        return errores;
    }

    /**
     *
     * @return los errores que hubo parseando los datos del archivo
     */
    public int getCuentaErrores() {
        return cuentaErrores;
    }

}
