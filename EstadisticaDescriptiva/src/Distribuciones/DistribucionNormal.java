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
    private static final double DX = 0.00001;
    
    public DistribucionNormal(){
        this.nombre = "Normal";
    }
    
    public DistribucionNormal(double media, double desviacionE) {
        this.media = media;
        this.desviacionE = desviacionE;
        this.nombre = "Normal";
    }
    
    public void setMedia(double media){
        this.media = media;
    }
    
    @Override
    public double probabilidad(double x) {
       double z = (x - media)/desviacionE;
       return calcularProbabilidadE(z);
    }

    @Override
    public double probabilidadAc(double x) {
        double z = (x - media)/desviacionE;
        return calcularProbabilidadEAc(z);
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
        //retorna la intragral de 0 a z
        
        double x, probabilidad = 0;
        for(x = -3.6; x <= z; x += DX){
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
    
    @Override
    public DistribucionNormal clone(){
        return new DistribucionNormal(media, desviacionE);
    }
}
