/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosAgrupados.FormulasNC;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.Histograma;
import java.awt.Color;

/**
 *
 * @author tharduz
 */
public class PruebaGeneradores {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*double media = 8, dE = 1.66; 
        double[] datos = GeneradorDistribuciones.generarNormales(media, dE, 100);
        
        DatosEnBruto dat = new DatosEnBruto(datos);
        DatosAgrupados dAg = new DatosAgrupados(dat, FormulasNC.Sturges);
        Histograma his = new Histograma(640, 480,Color.WHITE, dAg);
        DistribucionNormal normal = new DistribucionNormal(media, dE);
        PruebaKolmogorovSmirnov ks = new PruebaKolmogorovSmirnov(datos, normal, 0.05, true);
        System.out.println("ks: " + ks.analizar());
        //for(int i = 0; i < 1000; i++) 
            //System.out.println(datos[i]);
        try {
            his.dibujar(normal);
            his.guardarEnDisco("histogramaNormal.png", "png");
            his.dibujar();
            his.guardarEnDisco("histogramaSC.png", "png");
            System.out.println("ks: " + ks.analizar());
        } catch(Exception ex) {
            System.out.println(ex);
        }
        */
        
        double[] datos = GeneradorDistribuciones.generarJICuadrados(1, 100);
        
        DatosEnBruto dat = new DatosEnBruto(datos);
        DatosAgrupados dAg = new DatosAgrupados(dat, FormulasNC.Sturges);
        Histograma his = new Histograma(640, 480,Color.WHITE, dAg);
        DistribucionJiCuadrado ji = new DistribucionJiCuadrado(dat.calcularMedia());
        System.out.println("media: " + dat.calcularMedia());
        PruebaKolmogorovSmirnov ks = new PruebaKolmogorovSmirnov(datos, ji, 0.05, true);
        //for(int i = 0; i < 100; i++) 
            //System.out.println(datos[i]);
        try {
            his.dibujar(ji);
            his.guardarEnDisco("histogramaJi.png", "png");
            his.dibujar();
            his.guardarEnDisco("histogramaJiSC.png", "png");
            System.out.println("ks: " + ks.analizar());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
}
