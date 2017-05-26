package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionBinomial;
import Distribuciones.DistribucionHipergeometrica;
import Distribuciones.DistribucionNormal;
import Distribuciones.DistribucionPoisson;
import Distribuciones.PruebaKolmogorovSmirnov;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;
import java.io.File;
import java.io.FileWriter;

public class TestKolmogorov {

    public static void main(String[] args) {

        ArchivoDeDatos datos = new ArchivoDeDatos("hiper.txt", ",");
        try {
            FileWriter w = new FileWriter(new File("tablakolmogorofv.html"));
            DatosEnBruto datosE = new DatosEnBruto(datos.getDatos(true));
            //Distribucion dpois = new DistribucionNormal(datosE.calcularMedia(), datosE.calcularDeviacionE());
            //Distribucion dpois = new DistribucionNormal(datosE.calcularMedia(),datosE.calcularDeviacionE());
            Distribucion dpois = new DistribucionPoisson(datosE.calcularMedia());
            System.out.println("prob: " + dpois.probabilidadAc( datosE.getDatos()[149]));
            PruebaKolmogorovSmirnov ks = new PruebaKolmogorovSmirnov(datosE.getDatos(),dpois,0.10,false);
            System.out.println("");
            System.out.println("Resultado: " + ks.analizar());
            w.write(ks.getDetalles());
            w.close();
        } catch (Exception ex) {

        }
    }

}
