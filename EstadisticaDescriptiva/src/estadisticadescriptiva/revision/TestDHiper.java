/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionHipergeometrica;

/**
 *
 * @author isidro
 */
public class TestDHiper {

    public static void main(String[] args) {
        System.out.println(Distribucion.factorial(24.0));
        DistribucionHipergeometrica dhyper = new DistribucionHipergeometrica(5, 3, 3);
        System.out.println(dhyper.calcularProbabilidad(2));
    }

}
