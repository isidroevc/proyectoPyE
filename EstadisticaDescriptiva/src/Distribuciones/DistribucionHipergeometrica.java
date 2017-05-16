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
    int nP, // Numero de elementos en la poblacion.
        t, // casos de exito en la poblacion
        n; //tamaño de la muestr

    public DistribucionHipergeometrica(int nP,int t, int n) {
        this.t = t;
        this.n = n;
        this.nP = nP;
    }
    
    
    @Override
    public double calcularProbabilidad(double x) {
        return (Distribucion.combinatoria(nP - t, n - (int)x) * Distribucion.combinatoria(t, (int)x)) 
                 / Distribucion.combinatoria(nP, n);
    }

    @Override
    public double calcularProbabilidadAc(double x) {
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
