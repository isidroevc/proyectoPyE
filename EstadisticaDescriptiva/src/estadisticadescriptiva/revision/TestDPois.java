/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionPoisson;

/**
 *
 * @author isidro
 */
public class TestDPois {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DistribucionPoisson dpois = new DistribucionPoisson(2.88);
        System.out.println(dpois.calcularProbabilidad(4));
        System.out.println(dpois.calcularProbabilidadAc(10));
    }
    
}
