/**
 * DatosEnBruto.java
 * Representa un conjunto de datos sin agrupar pero que se ordenan en el mismo
 * constructor o bien en el setter. Se implementa el algoritmo de ordenamiento
 * burbuja debido a la baja difucultad para su implementaición aunque NO es el
 * más eficiente.Los datos siempre se ordenan de menor a mayor.
 *
 */
package estadisticadescriptiva.datos;

import java.util.ArrayList;

/**
 *
 * @author isidro
 */
public class DatosEnBruto extends Datos {

    // -Atributos
    private double datos[]; // - los datos en sí

    /**
     *
     * @param datos recibe los datos sin ordenar
     */
    public DatosEnBruto(double[] datos) {
        this.datos = datos;
        this.n = datos.length;
        ordenarDatos();
    }
    //-Métodos de acceso.
    public double[] getDatos(){
        return this.datos;
    }
    
    public void setDatos(double[] datos){
        this.datos = datos;
        ordenarDatos();
        this.n = datos.length;
    }
    //-Métodos específicos.
    /**
     * Ordena los datos del arreglo llamado Datos
     */
    public void ordenarDatos() {
        if (datos == null)//si los datos son nulos.
        {
            return;//salir
        }
        double aux;
        for (int i = 0, cuenta = datos.length; i < cuenta; i++) {
            for (int j = 0; j < cuenta - 1; j++) {
                //Si el que sigue es menor que en el que estoy 
                if (datos[j + 1] < datos[j]) {
                    //Los cambio
                    aux = datos[j + 1];
                    datos[j + 1] = datos[j];
                    datos[j] = aux;
                }
            }
        }
    }

    @Override
    public double calcularMedia() {
        return Datos.sumatoria(datos) / datos.length;
    }

    @Override
    public double calcularMediana() {
        double mediana = 0;
        int posicionMedia = 0;
        //El numero de datos es par
        posicionMedia = (n / 2);
        if (n % 2 == 0) {

            mediana = (datos[posicionMedia - 1] + datos[posicionMedia]) / 2;
        } else {
            mediana = datos[posicionMedia];
        }
        return mediana;
    }

    @Override
    public ArrayList<Double> calcularModa() {
        double valorActual = Double.NaN;
        int frecuenciaActual = 0, i, mayor = Integer.MIN_VALUE, c = 0;
        int longitud = datos.length;
        ArrayList<Double> valores = new ArrayList<>();
        ArrayList<Integer> frecuencias = new ArrayList<>();
        ArrayList<Double> modas = new ArrayList<>();

        for (i = 0; i < longitud; i++) {
            if (datos[i] == valorActual) {
                frecuenciaActual++;
            } else {
                if (!Double.isNaN(valorActual)) {
                    valores.add(valorActual);
                    frecuencias.add(frecuenciaActual);
                }
                valorActual = datos[i];
                frecuenciaActual = 1;
            }
        }
        valores.add(valorActual);
        frecuencias.add(frecuenciaActual);

        //Buscar la frecuecia mayor
        for (i = 0, c = frecuencias.size(); i < c; i++) {
            if (frecuencias.get(i) > mayor) {
                mayor = frecuencias.get(i);
            }
        }
        //Anexar a la moda sólo los que tienen la frecuencia mayor
        
        for (i = 0, c = valores.size(); i < c; i++) {
            if (frecuencias.get(i) == mayor) {
                modas.add(valores.get(i));
            }
        }
        
        //No hay moda
        
        if (modas.size() == datos.length) {
            modas = null;
        }
        return modas;
    }

    @Override
    public double calcularVarianza() {
        double media = calcularMedia();
        double suma = 0, varianza = 0;
        
        for(int i = 0; i < n; i++){
            suma += (datos[i] - media)*(datos[i] - media);
        }
        varianza = suma/(n-1);
        return varianza;
    }

    @Override
    public double calcularDeviacionE() {
        return Math.sqrt(calcularVarianza());
    }

    @Override
    public double calcularRango() {
        //Sólo funciona si ya están ordenados
        return datos[n -1] - datos[0];
    }
    
    @Override
    public String calcularSesgo(){
        double media = this.calcularMedia();
        double mediana = this.calcularMediana();
        ArrayList<Double> modas = this.calcularModa();
        double moda;
        String sesgo;
        if(modas.size() > 1){
            sesgo = "Se trata de una muestra multimodal.";
        }else{
            moda = modas.get(0);
            if(media < mediana && mediana < moda){
                sesgo = "Asímétrica a la Izquierda.";
            }else if(media > mediana && mediana > moda){
                sesgo = "Asímétrica a la Derecha.";
            }else{
                sesgo = "Completamente simétrica.";
            }
        }
        return sesgo;
    }
}
