/**
 * Es el controlador de la implementación.
 */
package estadisticadescriptiva.Controller;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionNormal;
import Distribuciones.DistribucionPoisson;
import Distribuciones.PruebaKolmogorovSmirnov;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import estadisticadescriptiva.View.InterfazGrafica;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.CurvaDeDistribucion;
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

    public void manejarCorrerClick(boolean separadorPorDefecto, String sep, double significancia ,Distribucion dist) {
        //Verificar que se haya seleccionado un archivo 
        boolean valido = true;
        int i = 0;
        String separador = null;
        String nombre = dist.getNombre();
        String html = "<html><header><meta charset = 'UTF-8'></header><body><center><h1>Reporte de los datos.</h1><br>";
        double[] datos;
        boolean cumple;
        ArchivoDeDatos archivoDatos;
        DatosEnBruto datosBruto;
        PruebaKolmogorovSmirnov pruebaKS;
        DatosAgrupados datosAgrupados;
        Histograma histograma;
        CurvaDeDistribucion curva;
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
                interfaz.colocarRuta("");
                System.out.println("Separador: " + sep);
                System.out.println(archivoDatos.getErrores());
            }
            archivo = null;
            
            datosBruto = new DatosEnBruto(datos);
            if(dist.getClass() == DistribucionNormal.class){
                dist = new DistribucionNormal(datosBruto.calcularMedia(), datosBruto.calcularDeviacionE());
            }else if(dist.getClass() == DistribucionPoisson.class){
                dist = new DistribucionPoisson(datosBruto.calcularMedia());
            }
            datos = datosBruto.getDatos();
            datosAgrupados = new DatosAgrupados(datosBruto, DatosAgrupados.FormulasNC.Sturges);
           
            //Hacer prueba de kolmogorov
            pruebaKS = new PruebaKolmogorovSmirnov(datosBruto.getDatos(), dist, significancia, true);
            cumple = pruebaKS.analizar();
            //---
            
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
                    
            html+= "<h3>Cálculos por datos Agrupados.</h3>";
            html += "<p>Numero de datos: " + datosAgrupados.getN() + "<p>"
                    + "<p>Media: " + datosAgrupados.calcularMedia() + "</p>"
                    + "<p>Mediana: " + datosAgrupados.calcularMediana() + "</p>"
                    + "<p>Modas: " + datosAgrupados.calcularModa() +"</p>"
                    + "<p>Varianza: " + datosAgrupados.calcularVarianza()+ "</p>"
                    + "<p>Rango: " + datosAgrupados.calcularRango()
                    + "<p>Desviacion E.: " + datosAgrupados.calcularDeviacionE()+ "</p>"
                    + "<p>Sesgo: " + datosAgrupados.calcularSesgo()+ "</p>"
                    + datosAgrupados.getTablaHtml()
                    + "<img src = 'histograma.png'/>"
                    + "<br><img src = 'curva.png'/>"
                    + "<h1>Prueba de Komogorov-Smirnov con distribución " + nombre + "</h1>"
                    +  pruebaKS.getDetalles() 
                    + "</center></body></html>";
            curva = new CurvaDeDistribucion(640, 480, dist, datos[0], datos[datos.length - 1],Color.WHITE);
            histograma = new Histograma(640, 480, Color.WHITE, datosAgrupados);
            histograma.dibujar(dist);
            curva.dibujar();
            try {
                histograma.guardarEnDisco("histograma.png", "PNG");
                curva.guardarEnDisco("curva.png", "PNG");
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
