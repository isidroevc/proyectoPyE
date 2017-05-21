/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionBinomial;
import Distribuciones.DistribucionHipergeometrica;
import Distribuciones.DistribucionNormal;
import Distribuciones.DistribucionPoisson;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.CurvaDeDistribucion;
import estadisticadescriptiva.graficas.Histograma;
import java.awt.Color;

/**
 *
 * @author isidro
 */
public class TestCurvaD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            DatosEnBruto dE = new DatosEnBruto(new ArchivoDeDatos("hiper.txt", ",").getDatos(true));
            DatosAgrupados dA = new DatosAgrupados(dE,DatosAgrupados.FormulasNC.Sturges);
            Histograma hist = new Histograma(640,480,Color.WHITE,dA);
            Distribucion dist = new DistribucionHipergeometrica(20,5,13);
            System.out.println("media: " +dist.calcularMedia() + " - " + dE.calcularMedia());
            System.out.println("desviacionE: " +dist.calcularDesviacionE() + " - " + dE.calcularDeviacionE());
            CurvaDeDistribucion curva = new CurvaDeDistribucion(640, 480, dist, dE.getDatos()[0], dE.getDatos()[149], Color.WHITE);
            
            curva.dibujar();
            
            hist.dibujar(dist);
            hist.guardarEnDisco("histogramaCurva.png", "PNG");
            curva.guardarEnDisco("Curva.png", "PNG");
        } catch (Exception ex) {

        }
    }

}
