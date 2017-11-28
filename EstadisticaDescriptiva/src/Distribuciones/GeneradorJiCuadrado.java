/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

import estadisticadescriptiva.datos.Clase;
import estadisticadescriptiva.datos.DatosAgrupados;
import estadisticadescriptiva.datos.DatosAgrupados.FormulasNC;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.Histograma;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 *
 * @author tharduz
 */
public class GeneradorJiCuadrado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, gL;
        try {
            System.out.println("Cuantos numeros se van a generar: ");
            n = Integer.parseInt(sc.nextLine());
            System.out.println("Con que grados de libertad? " );
            gL = Integer.parseInt(sc.nextLine());
            double[] gen = GeneradorDistribuciones.generarJICuadrados(gL, n);
            FileOutputStream fos = new FileOutputStream(new File("jiCuadrado/jiCuad.csv"));
            String aux = "";
            DatosEnBruto datos = new DatosEnBruto(gen);
            DatosAgrupados agrupados = new DatosAgrupados(datos,FormulasNC.Sturges );
            Histograma hist = new Histograma(700, 800, Color.WHITE, agrupados);
            
            Clase[] cls = agrupados.getClases();
            double media = datos.calcularMedia();
            System.out.println("media: " + media);
            DistribucionJiCuadrado ji = new DistribucionJiCuadrado(media);
            hist.dibujar(ji);
            hist.guardarEnDisco("jiCuadrado/histograma.png", "png");
            for (int i = 0, c = gen.length; i < c; i++) {
                if(i < cls.length)
                   aux = ", " + cls[i].getMarca();
                else 
                    aux = "";
                fos.write((Double.toString(gen[i])+ aux + "\n").getBytes());
            }
            fos.close();
            PruebaKolmogorovSmirnov pruebaKS = new PruebaKolmogorovSmirnov(gen, ji, 0.05, true);
            System.out.println("Sigen la distribucion: " + pruebaKS.analizar());

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
