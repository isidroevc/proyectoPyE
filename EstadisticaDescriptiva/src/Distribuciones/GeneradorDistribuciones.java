/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuciones;

import metodocongruencias.MotorCongruente;
import metodocongruencias.MotorCongruenteBinario;

/**
 *
 * @author tharduz
 */
public abstract class GeneradorDistribuciones {
    
    private static final int K = 12;
    public static  double[] generarNormalesStd(int n) {
        MotorCongruenteBinario rnd = new MotorCongruenteBinario(n * K, MotorCongruente.Tipos.MIXTO);
        double[] resultado = new double[n];
        double sumatoria = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < K; j++) 
                sumatoria += rnd.siguienteNormal();
            //resultado[i] = desviacionE * Math.sqrt(12 / K) * (sumatoria - K/2) + media; 
             resultado[i] = sumatoria - 6;
             sumatoria = 0;
        }
        
        return resultado;
    }
    
    public static  double[] generarNormales(double media, double desviacionE, int n) {
        MotorCongruenteBinario rnd = new MotorCongruenteBinario(n * K, MotorCongruente.Tipos.MIXTO);
        double[] resultado = new double[n];
        double sumatoria = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < K; j++) 
                sumatoria += rnd.siguienteNormal();
             resultado[i] = desviacionE * Math.sqrt(12 / K) * (sumatoria - K/2) + media; 
             //resultado[i] = sumatoria - 6;
             sumatoria = 0;
        }
        
        return resultado;
    }
    
    
    public static double[] generarJICuadrados(int gL, int n) {
        double[] Z = generarNormalesStd(n);
        double[] resultado = new double[n];
        
        for(int i = 0; i < n; i++) 
            resultado[i] = Math.pow((Z[i] + Math.sqrt(2 * gL - 1)), 2) / 2;
        
        return resultado;
        
    }
    
}
