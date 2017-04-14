/**
 * DatosEnBruto.java
 * Representa un conjunto de datos sin agrupar pero que se ordenan en el mismo
 * constructor o bien en el setter. Se implementa el algoritmo de ordenamiento
 * burbuja debido a la baja difucultad para su implementaición aunque NO es el 
 * más eficiente.Los datos siempre se ordenan de menor a mayor.
 * 
 */
package estadisticadescriptiva.datos;

/**
 *
 * @author isidro
 */
public class DatosEnBruto extends Datos{
    // -Atributos
    private double datos[]; // - los datos en sí
    /**
     * 
     * @param datos recibe los datos sin ordenar
     */
    public DatosEnBruto(double[] datos){
         this.datos = datos;
         
    }
    //-Métodos específicos.
    /**
     * Ordena los datos del arreglo llamado Datos
     */
    public void ordenarDatos(){
        if(datos == null)//si los datos son nulos.
            return;//salir
        double aux;
        for(int i =0, cuenta = datos.length; i < cuenta; i++){
            for(int j = 0; j < cuenta -1; j++){
                if(datos[j+1] < datos[j]){
                    aux = datos[j+1];
                    datos[j+1] = datos[j];
                    datos[j] = aux;
                }
            }
        }
    }

    @Override
    public double calcularMedia() {
        return Datos.sumatoria(datos) / datos.length;
    }

    @Override
    public double calcularMediana() {
        double mediana = 0;
        int posicionMedia = 0, n = datos.length;
        //El numero de datos es par
        posicionMedia = n/2;
        if(n % 2 == 0){
            
            mediana = (datos[n] + datos[n+1])/2;
        }else{
            mediana = datos[posicionMedia];
        }
        return mediana;
    }

    @Override
    public double[] calcularModa() {
        
    }
    
    
}
