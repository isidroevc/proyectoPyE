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
        System.out.println(Distribucion.factorial(240000));
        DistribucionHipergeometrica dhyper = new DistribucionHipergeometrica(40,3,5);
        System.out.println(dhyper.probabilidad(6));
        System.out.println(dhyper.probabilidadAc(6));
        System.out.println(dhyper.calcularDesviacionE());
        System.out.println(dhyper.calcularMedia());
    }

}
