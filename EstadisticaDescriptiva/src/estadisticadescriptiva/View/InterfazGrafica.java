/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.View;

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
    private JTextField cConfiabilidad;
    private JCheckBox normal;
    private JCheckBox binomial;
    private JCheckBox poisson;
    private JCheckBox hipergeometrica;
    

    private Controlador controlador;
    private FileChooser dialogoArchivo;

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
        normal = new JCheckBox("Normal");
        poisson = new JCheckBox("Poisson");
        binomial = new JCheckBox("Binomial:");
        n = new JLabel("n");
        p = new JLabel("p");
        N = new JLabel("N");
        k = new JLabel("k");
        nH = new JLabel("n");
        confiabilidad = new JLabel("Porcentaje de Confiabilidad:");
        confiabilidad1 = new JLabel("%");
        cTamaño = new JTextField();
        cProbabilidad = new JTextField();
        cTamañoP = new JTextField();
        cTamañoH = new JTextField();
        cCasos = new JTextField();
        cConfiabilidad = new JTextField();
        hipergeometrica = new JCheckBox("Hipergeométrica:");
        tamaño = new JLabel("Numero de eventos o");        
        tamaño2 = new JLabel("tamaño de la muestra");        
        tamañoH = new JLabel("Tamaño de la muesta");
        probabilidad = new JLabel("Probabilidad de éxito");
        casos = new JLabel("Casos de éxito en la poblacion");
        tamañoP = new JLabel("Tamaño de la poblacion");
        
        
    }

    private void ensamblar() {
        this.setTitle("Estadistica Descriptiva");
        this.setSize(800, 700);
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/estadisticadescriptiva/View/Escudo-ITL.jpg").getImage());

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

        fondo.setIcon(new ImageIcon("src/estadisticadescriptiva/View/banner2016.png"));
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
        
        
<<<<<<< HEAD
        correr.setBounds(600, 600, 100, 30);
=======
        correr.setBounds(430, 100, 100, 30);
>>>>>>> 2acffd65160fa97326381908275665cf6bfb4f8c
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

            if (separador.isSelected()) {
              controlador.manejarCorrerClick(true,
                        (String) separadores.getSelectedItem());
                
            }else{
                controlador.manejarCorrerClick(false, cSeparador.getText());
            }
            cArchivo.setText("");
        }

    }
}
