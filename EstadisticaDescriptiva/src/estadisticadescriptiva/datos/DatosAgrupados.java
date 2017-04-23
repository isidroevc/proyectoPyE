/**
 * DatosAgrupados.java
 *
 * Este archivo contiene la definicion de la clase DatosAgrupados que representa
 * la agrupación de datos en bruto por medio de clases o categorias, las
 * instancias de esta clase contienen un arreglo con instancias de la clase Clase.
 *
 */
package estadisticadescriptiva.datos;

import java.util.ArrayList;

/**
 *
 * @author Vasquez Cortes Isidro Emmanuel.
 */
public class DatosAgrupados extends Datos {

    // -Atributos.
    private DatosEnBruto origen;
    private double[] datos;
    private Clase[] clases;
    private double limiteUI;
    private double limiteUS;
    private double tolerancia;
    FormulasNC formulaNC;

    public DatosAgrupados(DatosEnBruto datos, FormulasNC formulaNC) {
        this.origen = datos;
        this.datos = datos.getDatos();
        this.n = this.datos.length;
        this.formulaNC = formulaNC;
        agruparDatos();
    }

    //-Métodos de acceso.
    public Clase[] getClases() {
        return this.clases;
    }

    // -Métodos especificos de funcionamiento.
    /**
     * Agrupa los datos contenidos en el arreglo datos creando de nuevo el
     * arreglo de clases llamado "Clases"
     */
    public void agruparDatos() {
        //Nota: se supone que los datos están ordenados.
        //Primero hay que calcular la cantidad de tolerancia.
        double amplitud, noClasesSR = 0, semiAmplitud = 0, aux = 0;
        int i, j, noClases = 0, acum = 0;
        tolerancia = DatosAgrupados.calcularTolerancia(datos[0]);
        if (DatosAgrupados.calcularTolerancia(datos[datos.length - 1]) < tolerancia) {
            tolerancia = DatosAgrupados.calcularTolerancia(datos[datos.length - 1]);
        }
        limiteUI = datos[0] - tolerancia;
        limiteUS = datos[n - 1] + tolerancia;

        switch (formulaNC) {
            case Sturges:
                noClases = DatosAgrupados.clasesSturges(n);
                break;
            case SQRT:
                noClasesSR = Math.sqrt(n);
                noClases = (int) (noClasesSR);
                break;
        }
        amplitud = (limiteUS - limiteUI) / noClases;
        semiAmplitud = amplitud / 2;
        clases = new Clase[noClases];
        clases[0] = new Clase(limiteUI, limiteUI + amplitud, limiteUI + semiAmplitud);
        aux = limiteUI + amplitud;
        for (i = 1; i < noClases; i++) {
            clases[i] = new Clase(aux, aux + amplitud, aux + semiAmplitud);
            aux = aux + amplitud;
        }
        //Ahora sigue determinar la frecuencia de cada clase.
        //El siguiente for solo funciona con los datos ORDENADOS DE MENOR A MAYOR.
        for (Clase c : clases) {

            for (double valor : datos) {
                if (c.pertenece(valor)) {
                    c.aumentarFrecuenciaA();
                }
            }
            acum += c.getFrecuenciaA();
            c.setFrecuenciaAAc(acum);
            c.setFrecuenciaR((double) c.getFrecuenciaA() / (double) n);
            c.setFrecuenciaRAc((double) c.getFrecuenciaAAc() / (double) n);
        }

    }

    @Override
    public double calcularMedia() {
        double media;
        double sumatoria = 0;
        for (Clase c : clases) {

            sumatoria += c.getMarca() * c.getFrecuenciaA();

        }

        media = sumatoria / this.getN();
        return media;
    }

    @Override
    public double calcularMediana() {
        /*t = amplitu, Fi - 1 = frecuencia absoluta anterior,
        fi = frecuencia absoluta*/
        double mediana;

        int saberDondeMeQuede = 0;
        double limite = 0, t = 0, fi = 0, F = 0;
        for (Clase c : clases) {

            if (c.getFrecuenciaAAc() >= (this.getN() / 2)) {

                break;
            }
            saberDondeMeQuede++;
        }
        limite = clases[saberDondeMeQuede].getLimiteInferior();
        t = clases[saberDondeMeQuede].getAmplitud();
        fi = clases[saberDondeMeQuede].getFrecuenciaA();
        if (saberDondeMeQuede == 0) {
            F = 0;
        }

        mediana = limite + ((this.getN() / 2) - F / fi) * t;

        return mediana;
    }

    @Override
    public ArrayList<Double> calcularModa() {

        ArrayList< Double> modas = new ArrayList<Double>();
        double Li = 0, fi = 0, Fmy = 0, Fmn = 0, ti = 0;
        double frecuenciaMayor = Double.MIN_VALUE;
        double mo = 0;

        for (int i = 0, c = clases.length; i < c; i++) {
            if (clases[i].getFrecuenciaA() > frecuenciaMayor) {

                frecuenciaMayor = clases[i].getFrecuenciaA();
            }
        }
        for (int i = 0, c = clases.length; i < c; i++) {

            if (clases[i].getFrecuenciaA() == frecuenciaMayor) {

                Li = clases[i].getLimiteInferior();
                fi = clases[i].getFrecuenciaA();
                ti = clases[i].getAmplitud();
                if (i == c - 1) {
                    Fmy = 0;
                } else {
                    Fmy = clases[i + 1].getFrecuenciaA();
                }
                if (i == 0) {
                    Fmn = 0;
                } else {
                    Fmn = clases[i - 1].getFrecuenciaA();
                }
                mo = Li + ((fi - Fmn) / (fi - Fmn) + (fi - Fmy)) * ti;
                modas.add(mo);
            }

        }

        return modas;
    }

    @Override
    public double calcularVarianza() {
        double varianza = 0;
        double media = calcularMedia();
        double sumatoria = 0;

        for (Clase c : clases) {

            sumatoria += (c.getMarca() * c.getMarca()) * c.getFrecuenciaA();
        }

        varianza = (sumatoria / this.getN()) - (media * media);

        return varianza;
    }

    @Override
    public double calcularDeviacionE() {
        return Math.sqrt(calcularVarianza());
    }

    @Override
    public double calcularRango() {
        return this.limiteUS - this.limiteUI;
    }

    @Override
    public String calcularSesgo() {
        double media = this.calcularMedia();
        double mediana = this.calcularMediana();
        ArrayList<Double> modas = this.calcularModa();
        double moda;
        String sesgo;
        if (modas.size() > 1) {
            sesgo = "Se trata de una muestra multimodal.";
        } else {
            moda = modas.get(0);
            if (media < mediana) {
                sesgo = "Asímétrica a la Izquierda.";
            } else if (media > mediana) {
                sesgo = "Asímétrica a la Derecha.";
            } else {
                sesgo = "Completamente simétrica.";
            }
        }
        return sesgo;
    }

    public String getTablaHtml() {
        String tabla = "<table border = \"1px\"><tr>"
                + "<td>Inter</td>"
                + "<td>Marca</td>"
                + "<td>FA</td>"
                + "<td>FAAc</td>"
                + "<td>FR</td>"
                + "<td>FRAc</td></tr>";
        for (int i = 0, c = clases.length; i < c; i++) {
            tabla += "<tr><td>" + clases[i].getLimiteInferior() + " - "
                    + clases[i].getLimiteSuperior() + "</td>"
                    + "<td>" + clases[i].getMarca() + "</td>"
                    + "<td>" + clases[i].getFrecuenciaA() + "</td>"
                    + "<td>" + clases[i].getFrecuenciaAAc() + "</td>"
                    + "<td>" + clases[i].getFrecuenciaR() + "</td>"
                    + "<td>" + clases[i].getFrecuenciaRAc() + "</td></tr>";
        }
        tabla += "</table>";
        return tabla;
    }

    //-Métodos estáticos.
    /**
     *
     * @param n numero de datos en bruto
     * @return el numero de clases ya redondeado
     */
    public static int clasesSturges(int n) {
        return (int) (sturges(n)) + 1;
    }

    /**
     *
     * @param n numero de datos en bruto
     * @return numero de clases sin redondear, sirve para obtener la amplitud de
     * clase.
     */
    public static double sturges(int n) {
        return 1 + 3.322 * Math.log10(n);
    }

    public static double calcularTolerancia(double valor) {
        double tolerancia;
        String[] partes;
        String decimal = Double.toString(valor), decimales = "";
        int exponente = 0;
        if (decimal.contains("E")) {
            exponente = Integer.parseInt(decimal.split("E")[1]) - 1;

        } else {
            decimales = decimal.split("\\.")[1];

            exponente = -(decimales.length());
        }
        tolerancia = 5 * Math.pow(10, exponente);
        return tolerancia;
    }

    public enum FormulasNC {
        Sturges,
        SQRT
    }
}
