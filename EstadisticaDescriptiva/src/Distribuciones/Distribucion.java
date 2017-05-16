/**
 * Distribucion.java
 * 
 * De esta clase abstracta derivarán las clases de las demás distribuciones
 */
package Distribuciones;

public abstract class Distribucion {
    // -Atributos
    
    // -Métodos abstractos.
    
    public abstract double probabilidad(double x);
    
    public abstract double probabilidadAc(double x);
    
    public abstract double calcularMedia();
    
    public abstract double calcularDesviacionE();
    
    public static int factorial(int x){
        int fact = 1;
        /*if(x == 0)
            fact = 1;
        else
            fact = x*factorial(x-1);
        return fact;*/
        for(int i = 1; i <= x; i++){
            fact *= i;
        }
        return fact;
    }
    
    public static final double factorial(double x){
        return Math.sqrt(2*Math.PI*x) * Math.pow(x,x) * Math.pow(Math.E, -x);
    }
    
    public static int combinatoria(int n, int x){
        return Distribucion.factorial(n)/(Distribucion.factorial(x)*Distribucion.factorial(n-x));
    }
    
}
