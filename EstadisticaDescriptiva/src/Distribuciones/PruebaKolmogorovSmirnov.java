/**
 * PruebaKolmogorovSmirnov.java
 * 
 * Representa una prueba de bondad de colomogorov smirnov
 */
package Distribuciones;

import java.util.ArrayList;

/**
 *
 * @author isidro
 */
public class PruebaKolmogorovSmirnov {
    //-Atributos
    double[] datos;     // -El conjunto de datos a analizar.
    double[] frecuenciasRE, frecuenciasRO, desviacionesA;
    double desviacionMaxima;
    Distribucion dist; // -La distribucion a contrastar
    int n;
    
    public PruebaKolmogorovSmirnov(double[] datos, Distribucion dist, boolean ordenados){
        this.datos = datos;
        this.dist = dist;
        n = datos.length;
        if(!ordenados)
            ordenarDatos();
    }
    //-Métodos de funcionamiento.
    //Ordena los datos si es necesario
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
    
    
    private void analizar(){
        ArrayList<Double> valoresDistintos = new ArrayList<>();
        ArrayList<Integer> frecuencias = new ArrayList<>();
        double valorActual;
        int frecuenciaActual;
        //primero hay que agrupar los datos por sus valores iguales
        valorActual =  datos[0];
        frecuenciaActual = 1;
        for(int i = 1, c = datos.length; i < c; i++){
            if(valorActual == datos[i]){
                frecuenciaActual++;
            }else{
                valoresDistintos.add(valorActual);
                frecuencias.add(frecuenciaActual);
                
                valorActual = datos[i];
                frecuenciaActual = 1;
            }
            
            if(i == (c-1)){
                valoresDistintos.add(valorActual);
                frecuencias.add(frecuenciaActual);
            }
        }
        
        //Ahora sigue llenar el arreglo de frecuencias relativas
        
        frecuenciasRO = new double[valoresDistintos.size()];
        frecuenciasRE = new double[valoresDistintos.size()];
        desviacionesA = new double[valoresDistintos.size()];
        
        desviacionMaxima = Double.MIN_VALUE;
        
        for(int i =0,c = frecuenciasRO.length; i < c; i++){
            frecuenciasRO[i] = frecuencias.get(i)/n;
            frecuenciasRE[i] = dist.probabilidad(valoresDistintos.get(i));
            desviacionesA[i] = Math.abs(frecuenciasRO[i] - frecuenciasRE[i]);
            if(desviacionesA[i] > desviacionMaxima){
                desviacionMaxima = desviacionesA[i];
            }
        }
        
    }
    // -Métidodos estáticos.
    public double desviacionCritica(int n, double significancia){
        //Nota esto solo jala de 35 datos para arriba.
        double numerador = 0;
        if(significancia == 0.20){
            numerador = 1.07;
        }else if(significancia == 0.15){
            numerador = 1.14;
        }else if(significancia == 0.1){
            numerador = 1.22;
        }else if(significancia == 0.05){
            numerador = 1.36;
        }else if(significancia == 0.01){
            numerador = 1.63;
        }
        return numerador / Math.sqrt(n);
    }
    
}
