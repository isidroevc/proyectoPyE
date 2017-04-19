/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.Histograma;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author isidro
 */
public class TestHistograma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //double[] datos = {1,2,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10};
        ArchivoDeDatos a = new ArchivoDeDatos("separadorTab.txt","\t");
        DatosEnBruto origen = new DatosEnBruto(a.getDatos(true));
        double sd = origen.calcularDeviacionE();
        File f = new File("tabla.html");
        DatosAgrupados da = new DatosAgrupados(origen,DatosAgrupados.FormulasNC.Sturges);
        //Histograma h = new Histograma(640,480,Color.WHITE,da,false);
        Histograma h = new Histograma(1280,960,Color.WHITE,da,false);
        h.dibujar();
        
        try {
            FileWriter w = new FileWriter(f);
            w.write(da.getTablaHtml());
            w.close();
            h.guardarEnDisco("prueba.PNG", "PNG");
        } catch (Exception ex) {

        }
    }
    
}
