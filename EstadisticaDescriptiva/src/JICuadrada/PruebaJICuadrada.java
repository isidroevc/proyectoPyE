/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JICuadrada;

import Distribuciones.DistribucionJiCuadrado;

/**
 *
 * @author tharduz
 */
public class PruebaJICuadrada {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DistribucionJiCuadrado dist = new DistribucionJiCuadrado(10);
        
        System.out.println("Prob 7: " + dist.probabilidad(7));
        System.out.println("ProbAc 7: " + dist.probabilidadAc(7));
        
    }
    
}
