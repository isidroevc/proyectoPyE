/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

/**
 *
 * @author isidro
 */
public class DistribucionPoisson extends Distribucion{
    
    private double lamda;

    public DistribucionPoisson(double lamda) {
        this.lamda = lamda;
    }
    
    @Override
    public double calcularProbabilidad(double x) {
        return (Math.pow(lamda, x) * Math.pow(Math.E,-lamda)) / Distribucion.factorial((int)x);
    }

    @Override
    public double calcularProbabilidadAc(double x) {
        double probabilidad = 0;
        for(int i = 0; i< x; i++){
            probabilidad += calcularProbabilidad(i);
        }
        return probabilidad;
    }

    @Override
    public double calcularMedia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calcularDesviacionE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
