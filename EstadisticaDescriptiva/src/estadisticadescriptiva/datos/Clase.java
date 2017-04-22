/**
 * Clase.java
 * Se trata de un encapsulado simple que contiene propiedades propias de una 
 * clase en el contexto de agurpamiento de datos, es decir, una categoria
 * dichas propiedades o atributos son: 
 * -Límite inferior
 * -Límite superior
 * -Marca
 * -Amplitud
 * -Frecuencia Absoulta
 * -Frecuencia Absoluta Acumulada
 * -Frecuencia Relativa
 * -Frecuencia Relativa Acumulada.
 * 
 */
package estadisticadescriptiva.datos;

/**
 *
 * @author Vásquez Cortés Isidro Emmanuel
 */
public class Clase {
    // -Atributos
    double limiteInferior, 
           limiteSuperior,
           marca,
           frecuenciaR, // -Frecuencia Relativa
           frecuenciaRAc,//-Frecuencia Relativa acumulada.
           amplitud; 
    
    int frecuenciaA, // - Frecuencia absoluta
        frecuenciaAAc; //-Frecuencia Absoluta acumulada
    public Clase(double li, double lf, double marca){
        this.limiteInferior = li;
        this.limiteSuperior = lf;
        this.marca = marca;
    }

    public double getAmplitud() {
        return amplitud;
    }

    public void setAmplitud(double amplitud) {
        this.amplitud = amplitud;
    }
    public double getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(double limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public double getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(double limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public double getMarca() {
        return marca;
    }

    public void setMarca(double marca) {
        this.marca = marca;
    }

    public double getFrecuenciaR() {
        return frecuenciaR;
    }

    public void setFrecuenciaR(double frecuenciaR) {
        this.frecuenciaR = frecuenciaR;
    }

    public double getFrecuenciaRAc() {
        return frecuenciaRAc;
    }

    public void setFrecuenciaRAc(double frecuenciaRAc) {
        this.frecuenciaRAc = frecuenciaRAc;
    }

    public int getFrecuenciaA() {
        return frecuenciaA;
    }

    public void setFrecuenciaA(int frecuenciaA) {
        this.frecuenciaA = frecuenciaA;
    }

    public int getFrecuenciaAAc() {
        return frecuenciaAAc;
    }

    public void setFrecuenciaAAc(int frecuenciaAAc) {
        this.frecuenciaAAc = frecuenciaAAc;
    }
    
    // -Métodos especificos.
    public boolean pertenece(double valor){
        return valor >= this.limiteInferior && valor< this.limiteSuperior;
    }
    
    public void aumentarFrecuenciaA(){
        this.frecuenciaA++;
    }
}
