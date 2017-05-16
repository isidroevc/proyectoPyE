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

    public DistribucionBinomial(int n, double p) {
        this.p = p;
        this.n = n;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calcularDesviacionE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
