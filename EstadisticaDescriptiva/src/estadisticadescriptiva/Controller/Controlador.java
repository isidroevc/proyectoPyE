/**
 * Es el controlador de la implementación.
 */
package estadisticadescriptiva.Controller;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import estadisticadescriptiva.View.InterfazGrafica;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.Histograma;
import java.awt.Desktop;
import java.io.File;
import java.math.BigInteger;
import java.net.URI;
import javafx.application.Application;
import java.awt.Color;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author Vásquez Cortés Isidro Emmanuel.
 */
public class Controlador{

    // -Atributos
    private InterfazGrafica interfaz;
    private JFileChooser dialogoArchivo;
    private File archivo;
    private final String[] leyendasSeparadores = {"Coma ','",
        "Punto y coma ';'",
        "Espacio ' '",
        "Diagonal '/'",
        "Diagonal invertida '\\'",
        "Tabulación '\\t'",
        "Salto de línea '\\n'",
        "Barra vertical '|'"};
    private final String[] separadores = {",", ";", " ", "/", "\\", "\t", "\n", "|"};

    // -Constructores.
    public Controlador(InterfazGrafica v) {
        this.interfaz = v;
        dialogoArchivo = new JFileChooser();

        v.conectarControlador(this);

    }
    // -Métodos específicos.

    public void iniciar() {
        interfaz.mostrar();
        interfaz.ponerSeparadoresDefecto(leyendasSeparadores);
    }

    public void manejarExaminarClick() {
        int respuesta = dialogoArchivo.showOpenDialog(interfaz);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            archivo = dialogoArchivo.getSelectedFile();
        } else {
            archivo = null;
        }

        if (archivo != null) {
            interfaz.colocarRuta(archivo.getPath());
        } else {
            interfaz.colocarRuta("");
        }
    }

    public void manejarCorrerClick(boolean separadorPorDefecto, String sep) {
        //Verificar que se haya seleccionado un archivo 
        boolean valido = true;
        int i = 0;
        String separador = null;
        String html = "<html><header><meta charset = 'UTF-8'></header><body><center><h1>Reporte de los datos.</h1><br>";
        double[] datos;
        ArchivoDeDatos archivoDatos;
        DatosEnBruto datosBruto;
        DatosAgrupados datosAgrupados;
        Histograma histograma;
        FileWriter w;
        if (archivo == null) {
            valido = false;
            interfaz.mandarMensaje("No se ha seleccionado un archivo.");
        }
        //varificar separador
        if (separadorPorDefecto) {
            for (String s : leyendasSeparadores) {
                if (s.equals(sep)) {
                    separador = separadores[i];
                }
                i++;
            }
            if (separador == null) {
                valido = false;
                interfaz.mandarMensaje("Seleccione un separador válido");
            }
        } else {
            separador = sep;
        }
        if (separador.length() == 0) {
            valido = false;
            interfaz.mandarMensaje("Seleccione un separador válido");
        }
        if (valido) {
            archivoDatos = new ArchivoDeDatos(archivo.getPath(), separador);
            
            try{
               datos = archivoDatos.getDatos(true); 
            }catch(Exception ex){
                interfaz.mandarMensaje("Error no se pudo encontrar el archivo");
                return;
            }
            if (datos == null || datos.length == 0) {
                interfaz.mandarMensaje("No se encontraron datos en el formato específicado");
                System.out.println("Separador: " + sep);
                System.out.println(archivoDatos.getErrores());
            }
            archivo = null;
            datosBruto = new DatosEnBruto(datos);
            datosAgrupados = new DatosAgrupados(datosBruto, DatosAgrupados.FormulasNC.Sturges);
            histograma = new Histograma(640, 480, Color.WHITE, datosAgrupados);
            histograma.dibujar();
            System.out.println("Reporte de los datos: \n Con datos sin agrupar" 
                    + datosBruto.toString()
                    + "\n===============================\n"
                    + "Con datos agrupados\n" + datosAgrupados.toString());
            
            //\html = "<body><center><h1>Reporte de los datos.</h1><br> ";
            html+= "<h3>Cálculos por datos sin agrupar.</h3>";
            html += "<p>Numero de datos: " + datosBruto.getN() + "<p>"
                    + "<p>Media: " + datosBruto.calcularMedia() + "</p>"
                    + "<p>Mediana: " + datosBruto.calcularMediana() + "</p>"
                    + "<p>Modas: " + datosBruto.calcularModa() +"</p>"
                    + "<p>Varianza: " + datosBruto.calcularVarianza()+ "</p>"
                    + "<p>Rango: " + datosBruto.calcularRango()
                    + "<p>Desviacion E.: " + datosBruto.calcularDeviacionE()+ "</p>"
                    + "<p>Sesgo: " + datosBruto.calcularSesgo()+ "</p>";
                    
            html+= "<h3>Cálculos por datos agruAgrupados.</h3>";
            html += "<p>Numero de datos: " + datosAgrupados.getN() + "<p>"
                    + "<p>Media: " + datosAgrupados.calcularMedia() + "</p>"
                    + "<p>Mediana: " + datosAgrupados.calcularMediana() + "</p>"
                    + "<p>Modas: " + datosAgrupados.calcularModa() +"</p>"
                    + "<p>Varianza: " + datosAgrupados.calcularVarianza()+ "</p>"
                    + "<p>Rango: " + datosAgrupados.calcularRango()
                    + "<p>Desviacion E.: " + datosAgrupados.calcularDeviacionE()+ "</p>"
                    + "<p>Sesgo: " + datosAgrupados.calcularSesgo()+ "</p>"
                    + datosAgrupados.getTablaHtml()
                    + "<img src = 'histograma.png'/></center></body></html>";
            try {
                histograma.guardarEnDisco("histograma.png", "PNG");
                w = new FileWriter(new File("reporte.html"));
                w.write(html);
                w.close();
                Desktop.getDesktop().browse(new File("reporte.html").toURI());
            } catch (Exception ex) {
                interfaz.mandarMensaje("Error escribiendo archivos de resultados");
            }
        }
    }
}
