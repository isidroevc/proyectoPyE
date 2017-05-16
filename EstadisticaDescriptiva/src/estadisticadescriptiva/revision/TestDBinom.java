/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionBinomial;

/**
 *
 * @author isidro
 */
public class TestDBinom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DistribucionBinomial dbinom = new DistribucionBinomial(12,0.2);
        
        System.out.println(dbinom.probabilidad(1));
        System.out.println(dbinom.probabilidadAc(12));
    }
    
}
