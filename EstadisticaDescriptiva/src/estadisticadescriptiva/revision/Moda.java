
import java.util.Scanner;


/*
Tomando en cuenta que son datos no agrupados 
*/

public class Moda {
 
    public static void main(String[] args) {
        int[] frecuencia = null;
        int v[];
        int moda =0,frec=0;
        int numero = 0;

      
        Scanner sc = new Scanner(System.in);
        System.out.println("calculo de moda");
        System.out.println("ingresar el numaro de datos que se vallan a calcular");
        int num = sc.nextInt();
        v = new int[num];
        System.out.println("ingresa los numeros al vector");
        for (int i = 0,j=0; i < v.length; i++) {
            System.out.print("pos[" + i + "]=");
            v[i] = sc.nextInt();
            for(int k=0;k<=j;k++){
                if(v[i]!=v[k]){
                    int med[] = null;
                    med[j]=v[i];
                    j++;
                    }
            }
        }
        for(int j=0;j<numero;j++)
        {
            if(frecuencia[j]>frec){
                frec=frecuencia[j];
                moda= v[j];
                
                  
            }
          
        }
    System.out.println("Moda:" + moda + "\nFrecuencia:" + frec); 
    }    
    
}         
