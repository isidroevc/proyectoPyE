/**
 * Datos.java
 * 
 * Esta clase representa un conjunto de datos absstracto del cual además se debe
 * poder obtener las medidas etendencia central.
 * 
 * 
 */
package estadisticadescriptiva.datos;

import java.util.ArrayList;

/**
 *
 * @author Vásquez Cortés Isidro Emmanuel.  
 */
public abstract class Datos {
    
    //-Atributos
    protected int n;
    // -Métodos
    public abstract double calcularMedia();
    public abstract double calcularMediana();
    public abstract ArrayList<Double> calcularModa();
    public abstract double calcularVarianza();
    public abstract double calcularDeviacionE();
    public abstract double calcularRango();
    public abstract String calcularSesgo();
    //-Métodos de acceso.
    
    public int getN(){
       
        return n;
    }
    
    @Override
    public String toString(){
        String modasS = "";
        ArrayList<Double> modas = this.calcularModa();
        if(modas == null){
            modasS = "Todos los datos son diferentes entre sí.";
        }else{
            for(double moda : modas){
                modasS +=  moda + "  ";
            }
        }
        return "Rango: " + this.calcularRango()
               + "\nMedia: " + this.calcularMedia()
               + "\nMediana: " + this.calcularMediana()
               + "\nModas: " + modasS
               + "\nVarianza: " + this.calcularVarianza()
               + "\nDesviacion E.:" + this.calcularDeviacionE()
               + "\nSesgo:" + this.calcularSesgo();
    }
    
    //-Métodos estáticos.
    public static double sumatoria(double[] datos) {
        double sumatoria = Double.NaN;
        if(datos != null){
            sumatoria = 0;
            for(int i =0, cuenta = datos.length; i < cuenta; i++){
                sumatoria += datos[i];
            }
        }
        return sumatoria;
    }
    
}
