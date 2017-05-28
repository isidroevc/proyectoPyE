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
    int nP;//Tamaño de la poblacion
    int n ; //Tamaño de la muestra 
    int k; // Casos de exito en la poblacion.

    public DistribucionHipergeometrica(int nP, int n, int k) {
        this.nP = nP;
        this.n = n;
        this.k = k;
        this.tipo = Tipos.Discreta;
        this.nombre = "Hipergeométrica";
    }
    public DistribucionHipergeometrica(){
        this.nombre = "Hipergeométrica";
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
        double probabilidad = 0;
        for(int i = 0, c = (int)x; i <= c; i++){
            probabilidad += probabilidad(i);
        }
        return probabilidad;
    }

    @Override
    public double calcularMedia() {
        double nP = this.nP,
                k = this.k,
                n = this.n;
        return n*k/nP;
    }

    @Override
    public double calcularDesviacionE() {
        return Math.sqrt(calcularVarianza());
    }

    @Override
    public double calcularVarianza() {
        double nP = this.nP,
                k = this.k,
                n = this.n;
        return ((nP -  n)/(nP - 1)) * n * (k/nP) *(1 - k/nP);
    }
    @Override
    public DistribucionHipergeometrica clone(){
        return new DistribucionHipergeometrica(nP,n,k);
    }
}
