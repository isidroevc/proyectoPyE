/**
 * PruebaKolmogorovSmirnov.java
 * 
 * Representa una prueba de bondad de colomogorov smirnov
 */
package Distribuciones;

import java.util.ArrayList;
public class PruebaKolmogorovSmirnov {
    //-Atributos
    double[] datos;     // -El conjunto de datos a analizar.
    double[] frecuenciasRE,
            frecuenciasRO,
            desviacionesA;
    boolean error, 
            cumple;
    double desviacionMaxima, //  -La desviacion máxima obtenida
            desviacionCritica, // -La desviacion máxima esperada
            significancia;  // - El porcentaje de significancia.
            
    Distribucion dist; // -La distribucion a contrastar
    ArrayList<Double> valoresDistintos;
    ArrayList<Integer> frecuencias;
    int n;
    
    public PruebaKolmogorovSmirnov(double[] datos, Distribucion dist,double significancia, boolean ordenados){
        this.datos = datos;
        this.dist = dist;
        n = datos.length;
        this.significancia = significancia;
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
    
    
    public boolean analizar(){
        valoresDistintos = new ArrayList<>();
        frecuencias = new ArrayList<>();
        double valorActual;
        int frecuenciaActual, frecuenciaAc;
       //Calcular la desviacion maxima esperada 
        desviacionCritica = desviacionCritica(n,significancia);
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
        
        frecuenciaAc = 0;
        for(int i =0,c = frecuenciasRO.length; i < c; i++){
            frecuenciaAc += frecuencias.get(i);
            frecuenciasRO[i] = (double)frecuenciaAc/n;
            frecuenciasRE[i] = dist.probabilidadAc(valoresDistintos.get(i));
            desviacionesA[i] = Math.abs(frecuenciasRO[i] - frecuenciasRE[i]);
            //System.out.println(desviacionesA[i]);
            if(Double.isNaN(desviacionesA[i])){
                error = true;
                return false;
            }
            if(desviacionesA[i] > desviacionMaxima){
                desviacionMaxima = desviacionesA[i];
            }
            
        }
        return cumple = desviacionMaxima < desviacionCritica;
    }
    
    public boolean cumple() {
        return this.cumple;
    }
    public double[] getDatos() {
        return datos;
    }

    public void setDatos(double[] datos) {
        this.datos = datos;
    }

    public double[] getFrecuenciasRE() {
        return frecuenciasRE;
    }

    public void setFrecuenciasRE(double[] frecuenciasRE) {
        this.frecuenciasRE = frecuenciasRE;
    }

    public double[] getFrecuenciasRO() {
        return frecuenciasRO;
    }

    public void setFrecuenciasRO(double[] frecuenciasRO) {
        this.frecuenciasRO = frecuenciasRO;
    }

    public double[] getDesviacionesA() {
        return desviacionesA;
    }

    public void setDesviacionesA(double[] desviacionesA) {
        this.desviacionesA = desviacionesA;
    }

    public double getDesviacionMaxima() {
        return desviacionMaxima;
    }

    public void setDesviacionMaxima(double desviacionMaxima) {
        this.desviacionMaxima = desviacionMaxima;
    }

    public double getDesviacionCritica() {
        return desviacionCritica;
    }

    public void setDesviacionCritica(double desviacionCritica) {
        this.desviacionCritica = desviacionCritica;
    }

    public double getSignificancia() {
        return significancia;
    }

    public void setSignificancia(double significancia) {
        this.significancia = significancia;
    }

    public Distribucion getDist() {
        return dist;
    }

    public void setDist(Distribucion dist) {
        this.dist = dist;
    }

    public ArrayList<Double> getValoresDistintos() {
        return valoresDistintos;
    }

    public void setValoresDistintos(ArrayList<Double> valoresDistintos) {
        this.valoresDistintos = valoresDistintos;
    }

    public ArrayList<Integer> getFrecuencias() {
        return frecuencias;
    }

    public void setFrecuencias(ArrayList<Integer> frecuencias) {
        this.frecuencias = frecuencias;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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
        return (double)(numerador / (double)Math.sqrt(n));
    }
    
    
    public String getDetalles(){
        if(error){
            return "<b>No se puede afirmar o negar si los datos siguen la distribución propuesta" 
                    + "<br>parece que se ha rebasado el límite valor máximo de alguna variable </b>";
        }
        boolean cumple = desviacionMaxima < desviacionCritica;
        String detalles= "<b>Los datos " + ((!cumple)? "NO " : " ") + " siguen una distribución " + dist.getNombre()
                + " con una desviación máxima de " + desviacionMaxima + ((cumple)? " menor " : " mayor ")
                + " a la crítica " + desviacionCritica + ", usando un porcentaje de significancia de " + (significancia*100) + "</b>";
        detalles  += "<table border = '1px'><tr><td>Dato</td>"
                + "<td>Frecuencia</td>"
                + "<td>FRA Observada</td>"
                + "<td>FRA Esperada</td>"
                + "<td>Desviacion Absouluta</td></tr>";
        for(int i = 0, c = valoresDistintos.size(); i < c; i++){
            detalles += "<tr" + ((desviacionesA[i] == desviacionMaxima)? " bgcolor = '#FF0000'":"") + ">";
            detalles+= "<td>" + valoresDistintos.get(i) + "</td>"
                    +  "<td>" + frecuencias.get(i) + "</td>"
                    +  "<td>" + frecuenciasRO[i] + "</td>"
                    +  "<td>" + frecuenciasRE[i] + "</td>"
                    +  "<td>" + desviacionesA[i] + "</td></tr>";
        }
        detalles+= "</table><br>";
        
        return detalles;
    }
}
