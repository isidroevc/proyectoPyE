/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosEnBruto;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;

/**
 *
 * @author isidro
 */
public class TestDatosAgrupados {

    public static void main(String[] args) {
        //double[] datos = {1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10
        ArchivoDeDatos a = new ArchivoDeDatos("separadorTab.txt","\t");
        DatosEnBruto origen = new DatosEnBruto(a.getDatos(true));
        double sd = origen.calcularDeviacionE();
        File f = new File("tabla.html");
        DatosAgrupados da = new DatosAgrupados(origen,DatosAgrupados.FormulasNC.Sturges);
        try {
            FileWriter w = new FileWriter(f);
            w.write(da.getTablaHtml());
            w.close();
        } catch (Exception ex) {

        }
        //System.out.println("N: " + origen.getN() + "Sd: " + sd + "Clases: " + DatosAgrupados.clasesScott(origen.getN(), sd));

    }
}
