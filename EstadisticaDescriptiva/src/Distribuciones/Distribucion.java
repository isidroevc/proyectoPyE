/**
 * Distribucion.java
 * 
 * De esta clase abstracta derivarán las clases de las demás distribuciones
 */
package Distribuciones;

public abstract class Distribucion {
    // -Atributos
    
    // -Métodos abstractos.
    
    public abstract double calcularProbabilidad(double x);
    
    public abstract double calcularProbabilidadAc(double x);
    
    public abstract double calcularMedia();
    
    public abstract double calcularDesviacionE();
}
