/**
 * 
 */
package Distribuciones;

/**
 *
 * @author isidro
 */
public class DistribucionHipergeometrica extends Distribucion{
    // -Atributos
    int nP,//Tamaño de la poblacion
            n , //Tamaño de la muestra 
            k; // Casos d eexito en la poblacion.

    public DistribucionHipergeometrica(int nP, int n, int k) {
        this.nP = nP;
        this.n = n;
        this.k = k;
    }
    
    
    @Override
    public double probabilidad(double x) {
        int z = (int)x;
        return (double)(Distribucion.combinatoria(k, z)
                * Distribucion.combinatoria(nP - k, n - z)) 
                / Distribucion.combinatoria(nP, n);
    }

    @Override
    public double probabilidadAc(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
