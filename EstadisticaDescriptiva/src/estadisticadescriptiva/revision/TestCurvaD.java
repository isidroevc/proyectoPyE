/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionNormal;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;
import estadisticadescriptiva.graficas.CurvaDeDistribucion;
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
            DatosEnBruto dE = new DatosEnBruto(new ArchivoDeDatos("normal.txt", ",").getDatos(true));
            System.out.println(dE.toString());
            Distribucion dist = new DistribucionNormal(dE.calcularMedia(), dE.calcularDeviacionE());
            CurvaDeDistribucion curva = new CurvaDeDistribucion(640, 480, dist, dE.getDatos()[0], dE.getDatos()[149], Color.WHITE);
            curva.dibujar();
            curva.guardarEnDisco("Curva.png", "PNG");
        } catch (Exception ex) {

        }
    }

}
