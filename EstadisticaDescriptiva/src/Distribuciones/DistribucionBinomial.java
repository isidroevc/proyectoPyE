/**
 * DistribucionBinomial.java
 * 
 * Representa una funcion de distribucion Binomial 
 */
package Distribuciones;

/**
 *
 * @author isidro
 */
public class DistribucionBinomial extends Distribucion{
    // -Atributos
    
    private double p;
    private int    n;

    public DistribucionBinomial(){
        this.nombre = "Binomial";
    }
    
    public DistribucionBinomial(int n, double p) {
        this.p = p;
        this.n = n;
        this.nombre = "Binomial";
    }
    
    
    
    @Override
    public double probabilidad(double x) {
       return Distribucion.combinatoria(n,(int)x)
               * Math.pow(p, x) 
               * Math.pow(1-p, n-x);    
    }

    @Override
    public double probabilidadAc(double x) {
        double prob = 0;
        for(int i = 0, c = (int)x; i <= c; i++){
            prob += probabilidad(i);
        }
        return prob;
    }

    @Override
    public double calcularMedia() {
        return (double)n * p;
    }

    @Override
    public double calcularDesviacionE() {
         return Math.sqrt(calcularVarianza());
    }

    @Override
    public double calcularVarianza() {
        return (double)n * p * (1 - p);
    }
    
    @Override
    public DistribucionBinomial clone(){
        return new DistribucionBinomial(n, p);
    }
}
