/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

/**
 *
 * @author Isidro Emmanuel Vasquez Cortés
 */
public class DistribucionUniforme extends Distribucion{
    
    private int t; // Número de elementos del espacio muestral
    private double a;
    private double b;
    private double preCalculada; // - La probabilidad precalculada
    
    // -Constructores.
    public DistribucionUniforme(int t, double a, double b) {
        this.t = t;
        this.preCalculada = (double)1 / t;
        this.a = a;
        this.b = b;
        this.nombre = "Uniforme";
    }
    
    public DistribucionUniforme( double a, double b) {
        this.t = t;
        this.preCalculada = (double)1 / (b - a);
        this.a = a;
        this.b = b;
        this.nombre = "Uniforme";
    }
    
    // -Métodos de acceso
    public boolean setT(int t) {
        boolean result;
        if(t >= 1){
            this.t = t;
            preCalculada = 1 / t;
            result = true;
        }
        else 
            result = false;
        return result;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }
    
    public double probabilidad() {
        return this.preCalculada;
    }
    @Override
    public double probabilidad(double x) {
        return this.preCalculada;
    }

    @Override
    public double probabilidadAc(double x) {
       return (x - a)/(b-a);
    }

    @Override
    public double calcularMedia() {
        return (b + a)/2;
    }

    @Override
    public double calcularDesviacionE() {
        return Math.sqrt(calcularVarianza());
    }

    @Override
    public double calcularVarianza() {
        return Math.pow((b - a),2) / 2;
    }

    /**
     * @param args the command line arguments
     */
    
    
}
