/**
 * Distribucion.java
 * 
 * De esta clase abstracta derivarán las clases de las demás distribuciones
 */
package Distribuciones;

public abstract class Distribucion {
    // -Atributos
    protected Tipos tipo;

    public Tipos getTipo() {
        return tipo;
    }
    // -Métodos abstractos.
    
    public abstract double probabilidad(double x);
    
    public abstract double probabilidadAc(double x);
    
    public abstract double calcularMedia();
    
    public abstract double calcularDesviacionE();
    
    public abstract double calcularVarianza();
    
    public static double factorial(double x){
        double fact = 1;
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
    
    public static final double factorialSt(double x){
        return Math.sqrt(2*Math.PI*x) * Math.pow(x,x) * Math.pow(Math.E, -x);
    }
    
    public static double combinatoria(int n, int x){
        return (double)(Distribucion.factorial(n)/(Distribucion.factorial(x)*Distribucion.factorial(n-x)));
    }
    
    public enum Tipos{
        Discreta,
        Continua
    }
    
}
