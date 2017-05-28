/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.View;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionBinomial;
import Distribuciones.DistribucionHipergeometrica;
import Distribuciones.DistribucionNormal;
import Distribuciones.DistribucionPoisson;
import estadisticadescriptiva.Controller.Controlador;
import estadisticadescriptiva.EstadisticaDescriptiva;
import static java.awt.Color.BLACK;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.stage.FileChooser;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Zaira Cortés Jiménez
 */
public class InterfazGrafica extends JFrame {

    private JPanel panel;
    private JTextField cArchivo;
    private JTextField cSeparador;
    private JButton examinar;
    private JButton correr;
    private JComboBox separadores;
    private JRadioButton separador;
    private JRadioButton otrossep;
    private JLabel fondo;
    private JLabel archivo;
    private ButtonGroup separadorGroup;
    private JLabel tamaño;
    private JLabel tamaño2;
    private JLabel tamañoH;
    private JLabel probabilidad;
    private JLabel n;
    private JLabel nH;
    private JLabel p;
    private JLabel N;
    private JLabel k;
    private JLabel confiabilidad;
    private JLabel confiabilidad1;
    private JLabel comparar;
    private JLabel casos;
    private JLabel tamañoP;
    private JTextField cTamaño;
    private JTextField cProbabilidad;
    private JTextField cTamañoP;
    private JTextField cCasos;
    private JTextField cTamañoH;
    private JComboBox cConfiabilidad;
    private JRadioButton normal;
    private JRadioButton binomial;
    private JRadioButton poisson;
    private JRadioButton hipergeometrica;
    private ButtonGroup grupo;
    
    private Controlador controlador;
    private FileChooser dialogoArchivo;
    
    private boolean norm, pois, binom, hiper;
    private int distribucion;
    public InterfazGrafica() {

    }

    public void conectarControlador(Controlador c) {
        this.controlador = c;
    }

    public void mostrar() {
        crear();
        ensamblar();
        this.setVisible(true);
    }

    private void crear() {
        panel = new JPanel();
        cArchivo = new JTextField();
        cSeparador = new JTextField();
        examinar = new JButton("Examinar");
        correr = new JButton("Correr");
        separador = new JRadioButton("Separador");
        otrossep = new JRadioButton("Otro Seprador:");
        separadores = new JComboBox();
        fondo = new JLabel();
        archivo = new JLabel("Archivo:");
        separadorGroup = new ButtonGroup();
        dialogoArchivo = new FileChooser();
        comparar = new JLabel ("Comparar datos de la muesta con:");
        normal = new JRadioButton("Normal");
        poisson = new JRadioButton("Poisson");
        binomial = new JRadioButton("Binomial:");
        n = new JLabel("n");
        p = new JLabel("p");
        N = new JLabel("N");
        k = new JLabel("k");
        nH = new JLabel("n");
        confiabilidad = new JLabel("Porcentaje de Significancia:");
        confiabilidad1 = new JLabel("%");
        cTamaño = new JTextField();
        cProbabilidad = new JTextField();
        cTamañoP = new JTextField();
        cTamañoH = new JTextField();
        cCasos = new JTextField();
        cConfiabilidad = new JComboBox(new String[]{"20","15","10", "5","1"});
        hipergeometrica = new JRadioButton("Hipergeométrica:");
        tamaño = new JLabel("Numero de eventos o");        
        tamaño2 = new JLabel("Tamaño de la muestra");        
        tamañoH = new JLabel("Tamaño de la muesta");
        probabilidad = new JLabel("Probabilidad de éxito");
        casos = new JLabel("Casos de éxito en la poblacion");
        tamañoP = new JLabel("Tamaño de la poblacion");
        grupo = new ButtonGroup();
        
    }

    private void ensamblar() {
        this.setTitle("Estadistica Descriptiva");
        this.setSize(800, 700);
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("Escudo-ITL.jpg").getImage());
        
        cConfiabilidad.setSelectedIndex(0);
        
        panel.setLayout(null);
        otrossep.addActionListener(new OtroSeparadorClick());

        separador.setSelected(true);
        separador.addActionListener(new SeparadorClick());

        examinar.addActionListener(new ExaminarClick());
        cArchivo.setEditable(false);
        panel.add(archivo);
        panel.add(examinar);
        panel.add(cArchivo);
        panel.add(separador);
        panel.add(separadores);
        panel.add(otrossep);
        panel.add(cSeparador);
        panel.add(comparar);
        panel.add(normal);
        panel.add(binomial);
        panel.add(poisson);
        panel.add(n);
        panel.add(cTamaño);
        panel.add(tamaño);
        panel.add(tamaño2);
        panel.add(p);
        panel.add(cProbabilidad);
        panel.add(probabilidad);
        panel.add(hipergeometrica);
        panel.add(N);
        panel.add(cTamañoP);
        panel.add(tamañoP);
        panel.add(k);
        panel.add(cCasos);
        panel.add(casos);
        panel.add(nH);
        panel.add(cTamañoH);
        panel.add(tamañoH);
        panel.add(confiabilidad);
        panel.add(confiabilidad1);
        panel.add(cConfiabilidad);
        
        panel.add(correr);
        panel.add(fondo);

        separadorGroup.add(separador);

        separadorGroup.add(otrossep);

        fondo.setIcon(new ImageIcon("banner2016.png"));
        fondo.setBounds(0, 0, 800, 97);
        fondo.setFocusable(false);

        archivo.setBounds(90, 137, 90, 30);
        archivo.setFont(new Font("Arial", Font.BOLD, 16));

        examinar.setBounds(180, 137, 100, 30);

        cArchivo.setBounds(290, 137, 400, 30);

        separador.setBounds(100, 197, 100, 30);
        separador.setFont(new Font("Arial", Font.BOLD, 14));

        separadores.setBounds(100, 227, 200, 30);

        otrossep.setBounds(400, 197, 200, 30);
        otrossep.setFont(new Font("Arial", Font.BOLD, 14));
        
        cSeparador.setBounds(400, 227, 300, 30);
        cSeparador.setEnabled(false);
        
        comparar.setBounds(20, 293, 400, 30);
        comparar.setFont(new Font("Arial", Font.BOLD, 16));
        
        normal.setBounds(20, 333, 200, 30);
        normal.setFont(new Font("Arial", Font.BOLD, 14));
        
        poisson.setBounds(400, 333, 200, 30);
        poisson.setFont(new Font("Arial", Font.BOLD, 14));
        
        binomial.setBounds(20, 383, 200, 30);
        binomial.setFont(new Font("Arial", Font.BOLD, 14));
        
        n.setBounds(40, 423, 20, 30);
        n.setFont(new Font("Arial", Font.BOLD, 16));
        
        cTamaño.setBounds(70, 423, 150, 30);
        
        tamaño.setBounds(230, 423, 300, 15);
        tamaño2.setBounds(230, 438, 300, 15);
        
        p.setBounds(40, 463, 20, 30);
        p.setFont(new Font("Arial", Font.BOLD, 16));
        
        cProbabilidad.setBounds(70, 463, 150, 30);
        
        probabilidad.setBounds(230, 463, 300, 30);
        
        hipergeometrica.setBounds(400, 383, 200, 30);
        hipergeometrica.setFont(new Font("Arial", Font.BOLD, 14));
        
        N.setBounds(420, 423, 20, 30);
        N.setFont(new Font("Arial", Font.BOLD, 16));
        
        cTamañoP.setBounds(450, 423, 150, 30);
        
        tamañoP.setBounds(610, 423, 300, 30);
        
        k.setBounds(420, 463, 20, 30);
        k.setFont(new Font("Arial", Font.BOLD, 16));
        
        cCasos.setBounds(450, 463, 150, 30);
        
        casos.setBounds(610, 463, 300, 30);
        
        nH.setBounds(420, 503, 20, 30);
        nH.setFont(new Font("Arial", Font.BOLD, 16));
        
        cTamañoH.setBounds(450, 503, 150, 30);
        
        tamañoH.setBounds(610, 503, 300, 30);
        
        confiabilidad.setBounds(20, 566, 300, 30);
        confiabilidad1.setBounds(410, 566, 300, 30);
        confiabilidad.setFont(new Font("Arial", Font.BOLD, 16));
        confiabilidad1.setFont(new Font("Arial", Font.BOLD, 16));
        
        cConfiabilidad.setBounds(250, 566, 150, 30);
        grupo.add(normal);
        grupo.add(poisson);
        grupo.add(binomial);
        grupo.add(hipergeometrica);
        
        normal.addActionListener(new NormalClick());
        binomial.addActionListener(new BinomialClick());
        poisson.addActionListener(new PoissonClick());
        hipergeometrica.addActionListener(new HiperClick());
        correr.setBounds(600, 600, 100, 30);
        correr.addActionListener(new CorrerClick());
    }

    public void ponerSeparadoresDefecto(String[] listaSeparadores) {
        separadores.removeAllItems();
        for (String s : listaSeparadores) {
            separadores.addItem(s);
        }
    }

    public void colocarRuta(String s) {
        cArchivo.setText(s);
    }

    public void mandarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    // -Manejo de eventos.
    private class SeparadorClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            //Implica que esta activada la primera opción.
            if (radio.isSelected()) {
                //Hay que desactivar el textField de la segunda opcion
                cSeparador.setEnabled(false);
                //Y activar el combo para esta opcion
                separadores.setEnabled(true);
            } else {
                //No hacemos nada...
            }
        }

    }

    private class OtroSeparadorClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            //Implica que esta activada la primera opción.
            if (radio.isSelected()) {
                //Hay que desactivar el combobox de la primera opcion
                separadores.setEnabled(false);
                //Y activar el JTextfield para este caso
                cSeparador.setEnabled(true);
            } else {
                //No hacemos nada...
            }
        }

    }

    private class ExaminarClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.manejarExaminarClick();
        }

    }

    private class CorrerClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Verthissi si se haya seleccionado un archivo.
            Distribucion dist = null;
            int _n = 0, _k, _nP;
            double probabilidad;
            if (separador.isSelected()) {
              switch(distribucion){
                  case 0: 
                      dist = new DistribucionNormal();
                  break;
                  case 1:
                      dist = new DistribucionPoisson();
                  break;
                  case 2:
                      //validar datos 
                      try{
                          _n = Integer.parseInt(cTamaño.getText());
                          probabilidad = Double.parseDouble(cProbabilidad.getText());
                          
                      }catch(Exception ex){
                          mandarMensaje("El tamaño de la muestra debe de ser un entero." 
                                  + "\n La probabilidad deve ser un unmero decimal entre 0 y 1");
                          return;
                      }
                      if(_n < 0){
                          mandarMensaje("El tamaño de la muestra debe de ser un entero." 
                                  + "\n La probabilidad deve ser un unmero decimal entre 0 y 1");
                          return;
                      }
                      if(probabilidad < 0 || probabilidad > 1){
                          mandarMensaje("El tamaño de la muestra debe de ser un entero." 
                                  + "\n La probabilidad deve ser un unmero decimal entre 0 y 1");
                          return;
                      }
                      dist = new DistribucionBinomial(_n, probabilidad);
                      System.out.println(dist.getNombre());
                  break;
                  
                  
                  case 3: 
                      try{
                          _nP = Integer.parseInt(cTamañoP.getText());
                          _k = Integer.parseInt(cCasos.getText());
                          _n = Integer.parseInt(cTamañoH.getText());
                          
                          System.out.println(_nP + " " + _k + " " + _n );
                      }catch(Exception ex){
                          mandarMensaje("Todos los datos deben ser enteros" 
                                  + "\n k debe ser menor o igual que N");
                          return;
                      }
                      if(_k > _nP){
                          mandarMensaje("Todos los datos deben ser enteros" 
                                  + "\n k debe ser menor o igual que N");
                          return;
                      }
                      dist = new DistribucionHipergeometrica(_nP, _n, _k);
                  break;
              }
              controlador.manejarCorrerClick(true,
                        (String) separadores.getSelectedItem(),Double.parseDouble((String)cConfiabilidad.getSelectedItem())/100,dist);
                
            }else{
                controlador.manejarCorrerClick(false, cSeparador.getText(),Double.parseDouble((String)cConfiabilidad.getSelectedItem())/100,dist);
            }
            cArchivo.setText("");
        }

    }
    
     private class NormalClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton rb = (JRadioButton)e.getSource();
            norm = rb.isSelected();
            
            binom = !norm;
            hiper = !norm;
            pois = !norm;
            
            probabilidad.setEnabled(binom);
            n.setEnabled(binom);
            cTamaño.setEnabled(binom);
            cProbabilidad.setEnabled(binom);
            k.setEnabled(hiper);
            N.setEnabled(hiper);
            tamañoP.setEnabled(hiper);
            tamaño.setEnabled(binom);
            tamañoH.setEnabled(hiper);
            nH.setEnabled(hiper);
            casos.setEnabled(hiper);
            cCasos.setEnabled(hiper);
            cTamañoH.setEnabled(hiper);
            cTamañoP.setEnabled(hiper);
            p.setEnabled(binom);
            tamaño2.setEnabled(binom);
            distribucion = 0;
        }
         
     }
     
     private class PoissonClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton rb = (JRadioButton)e.getSource();
            pois = rb.isSelected();
            binom = !pois;
            hiper = !pois;
            norm = !pois;
            
            probabilidad.setEnabled(binom);
            n.setEnabled(binom);
            cTamaño.setEnabled(binom);
            cProbabilidad.setEnabled(binom);
            k.setEnabled(hiper);
            N.setEnabled(hiper);
            tamañoP.setEnabled(hiper);
            tamaño.setEnabled(binom);
            tamañoH.setEnabled(hiper);
            nH.setEnabled(hiper);
            casos.setEnabled(hiper);
            cCasos.setEnabled(hiper);
            cTamañoH.setEnabled(hiper);
            cTamañoP.setEnabled(hiper);
            p.setEnabled(binom);
            tamaño2.setEnabled(binom);
            distribucion = 1;
        }
         
     }
     
     private class BinomialClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton rb = (JRadioButton)e.getSource();
            binom = rb.isSelected();
            
            pois = !binom;
            hiper = !binom;
            norm = !binom;
            
           probabilidad.setEnabled(binom);
            n.setEnabled(binom);
            cTamaño.setEnabled(binom);
            cProbabilidad.setEnabled(binom);
            k.setEnabled(hiper);
            N.setEnabled(hiper);
            tamañoP.setEnabled(hiper);
            tamaño.setEnabled(binom);
            tamañoH.setEnabled(hiper);
            nH.setEnabled(hiper);
            casos.setEnabled(hiper);
            cCasos.setEnabled(hiper);
            cTamañoH.setEnabled(hiper);
            cTamañoP.setEnabled(hiper);
            p.setEnabled(binom);
            tamaño2.setEnabled(binom);
            distribucion = 2;
        }
         
     }
     
     private class HiperClick implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            JRadioButton rb = (JRadioButton)e.getSource();
            hiper = rb.isSelected();
            pois = !hiper;
            binom = !hiper;
            norm = !hiper;
            
            probabilidad.setEnabled(binom);
            n.setEnabled(binom);
            cTamaño.setEnabled(binom);
            cProbabilidad.setEnabled(binom);
            k.setEnabled(hiper);
            N.setEnabled(hiper);
            tamañoP.setEnabled(hiper);
            tamaño.setEnabled(binom);
            tamañoH.setEnabled(hiper);
            nH.setEnabled(hiper);
            casos.setEnabled(hiper);
            cCasos.setEnabled(hiper);
            cTamañoH.setEnabled(hiper);
            cTamañoP.setEnabled(hiper);
            p.setEnabled(binom);
            tamaño2.setEnabled(binom);
            distribucion = 3;
        }

       
         
     }
}
