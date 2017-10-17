/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementacionKolmogorov;

import Distribuciones.DistribucionUniforme;
import Distribuciones.PruebaKolmogorovSmirnov;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;

/**
 *
 * @author Alison
 */
public class ImplementacionKT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PruebaKolmogorovSmirnov  prueba;
        ArchivoDeDatos archivo = new ArchivoDeDatos("datosUnif.txt", "\n");
        DistribucionUniforme dist;
        DatosEnBruto datos = null;
        try {
            datos  = new DatosEnBruto(archivo.getDatos(true));
        } catch(Exception e){
            System.out.println("Ha ocurrido un error");
            System.exit(0);
        }
        
        dist = new DistribucionUniforme(datos.getN(),datos.getMayor(), 
                datos.getMenor()); 
        prueba = new PruebaKolmogorovSmirnov(datos.getDatos(), dist, 0.15, true);
        prueba.analizar();
        System.out.println("desert " + dist.probabilidadAc( datos.getMayor()));
        System.out.println("probabilidad ind: " + dist.probabilidad());
        //System.out.println(prueba.getDesviacionMaxima());
    }
    
}
