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
public class Controlador extends Application {

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
        try {

        } catch (Exception ex) {

        }
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
        String html = "<html> <body> <h1>Reporte de los datos.</h1><br> <center>";
        String modasS = "";
        double[] datos;
        ArrayList<Double> modas;
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
            datos = archivoDatos.getDatos(true);
            if (datos == null || datos.length == 0) {
                System.out.println("Separador: " + sep);
                System.out.println(archivoDatos.getErrores());
            }
            archivo = null;
            datosBruto = new DatosEnBruto(archivoDatos.getDatos(true));
            datosAgrupados = new DatosAgrupados(datosBruto, DatosAgrupados.FormulasNC.Sturges);
            histograma = new Histograma(640, 480, Color.WHITE, datosAgrupados, false);
            histograma.dibujar();
            modas = datosBruto.calcularModa();
            for(int j = 0, c = modas.size(); j < c; j++){
                modasS += modas.get(j);
                if(j != c -1){
                    modasS += ", ";
                }
            }
            html = "<html> <body> <h1>Reporte de los datos.</h1><br> <center>";
            html += "<p>Numero de datos: " + datosBruto.getN() + "<p>"
                    + "<p>Media: " + datosBruto.calcularMedia() + "</p>"
                    + "<p>Mediana: " + datosBruto.calcularMediana() + "</p>"
                    + "<p>Modas: " + modas +"</p>"
                    + "<p>Varianza: " + datosBruto.calcularVarianza()+ "</p>"
                    + "<p>Rango: " + datosBruto.calcularRango()
                    + "<p>Desviacion E.: " + datosBruto.calcularDeviacionE()+ "</p>"
                    + datosAgrupados.getTablaHtml()
                    + "<img src = 'histograma.png'/></center></body></html>";
            
            try {
                histograma.guardarEnDisco("histograma.png", "PNG");
                //Desktop.getDesktop().browse(new File("histograma.png").toURI());
                //Desktop.getDesktop().browse(new File("tabla.html").toURI());
                w = new FileWriter(new File("reporte.html"));
                w.write(html);
                w.close();
                Desktop.getDesktop().browse(new File("reporte.html").toURI());
            } catch (Exception ex) {

            }
        }
    }

    private void crearReporte(double media, double mediana, String modas, double desviacionE, double varianza) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
