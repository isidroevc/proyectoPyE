/**
 * Datos.java
 * 
 * Esta clase representa un conjunto de datos absstracto del cual además se debe
 * poder obtener las medidas etendencia central.
 * 
 * 
 */
package estadisticadescriptiva.datos;

/**
 *
 * @author Vásquez Cortés Isidro Emmanuel.  
 */
public abstract class Datos {
    public abstract double calcularMedia();
    public abstract double calcularMediana();
    public abstract double[] calcularModa();
    
    //-Métodos estáticos.
    
    public static double sumatoria(double[] datos){
       
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
