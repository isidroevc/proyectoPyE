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
    public double probabilidad(double x) {
        //System.out.println("factorial("+ x +") = " +  Distribucion.factorial((int)x));
        return (Math.pow(lamda, x) * Math.pow(Math.E,-lamda)) / Distribucion.factorial((int)x);
    }

    @Override
    public double probabilidadAc(double x) {
        double probabilidad = 0;
        for(int i = 0; i <= x; i++){
            probabilidad += probabilidad(i);
        }
        return probabilidad;
    }

    @Override
    public double calcularMedia() {
        return lamda;
    }

    @Override
    public double calcularDesviacionE() {
         return Math.sqrt(lamda);
    }

    @Override
    public double calcularVarianza() {
        return lamda;
    }
    
}
