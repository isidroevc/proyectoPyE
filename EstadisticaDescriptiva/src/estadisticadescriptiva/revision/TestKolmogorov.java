package estadisticadescriptiva.revision;

import Distribuciones.DistribucionPoisson;
import Distribuciones.PruebaKolmogorovSmirnov;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;

public class TestKolmogorov {

    public static void main(String[] args) {

        ArchivoDeDatos datos = new ArchivoDeDatos("poisson.txt", ",");
        try {
            DatosEnBruto datosE = new DatosEnBruto(datos.getDatos(true));
            DistribucionPoisson dpois = new DistribucionPoisson(datosE.calcularMedia());
            PruebaKolmogorovSmirnov ks = new PruebaKolmogorovSmirnov(datosE.getDatos(),dpois,0.01,false);
            System.out.println("Resultado: " + ks.analizar());
        } catch (Exception ex) {

        }
    }

}
