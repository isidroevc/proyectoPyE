/**
 * Clase.java
 * Se trata de un encapsulado simple que contiene propiedades propias de una 
 * clase en el contexto de agurpamiento de datos, es decir, una categoria
 * dichas propiedades o atributos son: 
 * -Límite inferior
 * -Límite superior
 * -Marca
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
           frecuenciaRAc; //-Frecuencia Relativa acumulada.
    
    int frecuenciaA, // - Frecuencia absoluta
        frecuenciaAAc; //-Frecuencia Absoluta acumulada
    
}
