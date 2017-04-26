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
        ArchivoDeDatos a = new ArchivoDeDatos("separadorSalto.txt","\n");
        DatosEnBruto origen = null;
        try{
            origen = new DatosEnBruto(a.getDatos(true));
        }catch(Exception ex){
            System.err.print("Error leyendo archivo: \n" + ex.getMessage());
        }
        double sd = origen.calcularDeviacionE();
        File f = new File("tabla.html");
        DatosAgrupados da = new DatosAgrupados(origen,
                DatosAgrupados.FormulasNC.Sturges);
        Histograma h = new Histograma(640,480,Color.WHITE,da);
        h.dibujar();
        System.out.println("Datos Sin agrupar: \n"
                +  origen.toString() + "\n "
                +"============================================="
                + "\ncon DatosAgrupados: \n" + da.toString());
        try {
            h.guardarEnDisco("prueba.PNG", "PNG");
        } catch (Exception ex) {
            System.err.print("Error escribiendo imagen: \n" + ex.getMessage());
        }
    }
    
}
