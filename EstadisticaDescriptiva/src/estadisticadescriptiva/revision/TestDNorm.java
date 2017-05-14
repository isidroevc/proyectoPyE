/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.DistribucionNormal;

/**
 *
 * @author isidro
 */
public class TestDNorm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(1- (0.5 - DistribucionNormal.calcularProbabilidadEAc(1)));
        DistribucionNormal dnorm = new DistribucionNormal(3, 0.5);
        System.out.println(dnorm.calcularProbabilidad(4.2));
        System.out.println(dnorm.calcularProbabilidadAc(3.5));

 /*dnorm = new DistribucionNormal(3,0.5);
        System.out.println(1-dnorm.calcularProbabilidadAcumulativa(3.5));*/
    }

}
