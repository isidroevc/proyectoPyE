/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

/**
 *
 * @author tharduz
 */
public class DistribucionJiCuadrado extends Distribucion {
    
    private double k;
    private double partePrec;
    private static final double DX = 0.00001;
    
    public DistribucionJiCuadrado(double k) {
        this.k = k;
        
    }
    
    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
        
    }
    
    
    @Override
    public double probabilidad(double x) {
        if(x == 0)
            return 0;
        
        double ks2 = k /2;
        return 1 / (Math.pow(2, ks2) * gamma(ks2)) * Math.pow(x, ks2 - 1) * Math.exp(-x/2);
    }

    @Override
    public double probabilidadAc(double z) {
        double x, probabilidad = 0;
        for(x = 0; x <= z; x += DX){
            probabilidad += probabilidad(x) * DX;
        }
        return probabilidad;
    }

    @Override
    public double calcularMedia() {
       return this.k;
    }

    @Override
    public double calcularDesviacionE() {
         return Math.sqrt(this.calcularVarianza());
    }

    @Override
    public double calcularVarianza() {
        return this.k * 2;
    }

    static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173 / (x + 0) - 86.50532033 / (x + 1)
                + 24.01409822 / (x + 2) - 1.231739516 / (x + 3)
                + 0.00120858003 / (x + 4) - 0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }

    public static double gamma(double x) { return Math.exp(logGamma(x)); }

}
