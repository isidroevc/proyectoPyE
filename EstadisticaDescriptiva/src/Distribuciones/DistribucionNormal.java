/**
 * DistribucionNormal.java
 * 
 * Esta clase representa una funcion de distribucion de probabilidad normal
 * 
 * Binomial
Hipergeomética
Poisson
Normal----
 */
package Distribuciones;

/**
 *
 * @author isidro
 */
public class DistribucionNormal extends Distribucion{
    // -Atributos
    private double media; 
    private double desviacionE;
    private static final double DX = 0.0001;
    public DistribucionNormal(double media, double desviacionE) {
        this.media = media;
        this.desviacionE = desviacionE;
    }
    
    
    
    @Override
    public double probabilidad(double x) {
       double z = (x - media)/desviacionE;
       return calcularProbabilidadE(z);
    }

    @Override
    public double probabilidadAc(double x) {
        double z = (x - media)/desviacionE;
        return 0.5 - calcularProbabilidadEAc(z);
    }

    @Override
    public double calcularMedia() {
        return media;
    }

    @Override
    public double calcularDesviacionE() {
        return desviacionE;
    }
    
    @Override
    public String toString(){
        return "Media:  " + media 
                + "DesviacionE: " + desviacionE;
    }
    
    public static double calcularProbabilidadEAc(double z){
        z = Math.abs(z);
        //retorna la intragral de 0 a z
        double x = 0, probabilidad = 0;
        for(x = 0; x <= z; x += DX){
            probabilidad += calcularProbabilidadE(x) * DX;
        }
        return probabilidad;
    }
    
    public static double calcularProbabilidadE(double x){
        return (1/Math.sqrt(2*Math.PI)) * 
                Math.pow(Math.E,-(x*x)/2);
    }

    @Override
    public double calcularVarianza() {
        return desviacionE * desviacionE;
    }
}
