package estadisticadescriptiva.revision;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionBinomial;
import Distribuciones.DistribucionHipergeometrica;
import Distribuciones.DistribucionNormal;
import Distribuciones.DistribucionPoisson;
import Distribuciones.PruebaKolmogorovSmirnov;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;

public class TestKolmogorov {

    public static void main(String[] args) {

        ArchivoDeDatos datos = new ArchivoDeDatos("hiper.txt", ",");
        try {
            DatosEnBruto datosE = new DatosEnBruto(datos.getDatos(true));
            Distribucion dpois = new DistribucionHipergeometrica(20,5,13);
            System.out.println("prob: " + dpois.probabilidadAc( datosE.getDatos()[149]));
            PruebaKolmogorovSmirnov ks = new PruebaKolmogorovSmirnov(datosE.getDatos(),dpois,0.01,false);
            System.out.println("");
            System.out.println("Resultado: " + ks.analizar());
        } catch (Exception ex) {

        }
    }

}
