package vista;

import controlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class Form10HistorialReservas extends JFrame {
    LocalDate todaysDate = LocalDate.now();  //Fecha actual del sistema
    String fechaActual = todaysDate.toString();
    String[] arrayFecha = fechaActual.split("-");
    String anhoActual = arrayFecha[0];
    int intAnhoActual = Integer.parseInt(anhoActual);
    int intPrimerAnho = intAnhoActual - 5;
    String primerAnho = String.valueOf(intPrimerAnho);
    private final JPanel contentPanel = new JPanel();
    Controlador controlador;

    private DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
    /**
     * @wbp.nonvisual location=-49,244
     */
    private final JTextField textField = new JTextField();
    private String[] mesesAnho = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private String[] numerosMeses = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    //private String[] mesesAnteriores = {};
    //private String[] mesesSiguientes = {};
    private ModeloTablaReservas miModelo;
    private JScrollPane scrollPane;
    private JTable table_1;
    JComboBox comboBoxMeses;
    JComboBox comboBoxAnhos;



    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }



    public static void main(String[] args) {
        try {
            Form10HistorialReservas frame = new Form10HistorialReservas();
            frame.setDefaultCloseOperation(JFrame
                    .DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Form10HistorialReservas() {
        textField.setColumns(10);
        setTitle("Historial Reservas");
        setBounds(100, 100, 845, 496);
        setExtendedState(JFrame.NORMAL);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("HISTORIAL RESERVAS");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(230, 22, 238, 43);
        contentPanel.add(lblNewLabel);

        comboBoxMeses = new JComboBox();
        comboBoxMeses.setBounds(90, 97, 186, 21);
        rellenarComboMesesAnteriores(comboBoxMeses);
        contentPanel.add(comboBoxMeses);
        //comboBoxMeses.setSelectedItem("Noviembre");

        comboBoxAnhos = new JComboBox();
        comboBoxAnhos.setBounds(402, 97, 158, 21);
        rellenarComboAnhos(comboBoxAnhos);
        comboBoxAnhos.setSelectedIndex(0);
        contentPanel.add(comboBoxAnhos);

        JLabel lblNewLabel_1 = new JLabel("Mes");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(153, 74, 45, 13);
        contentPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Año");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(462, 73, 45, 13);
        contentPanel.add(lblNewLabel_2);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 162, 811, 238);
        contentPanel.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setColumnHeaderView(table_1);


        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new OkButtonActionListener());
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton atrasButton = new JButton("Atrás");
                atrasButton.addActionListener(new AtrasButtonActionListener());
                atrasButton.setActionCommand("Atrás");
                buttonPane.add(atrasButton);
            }
        }

        comboBoxAnhos.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (comboBoxAnhos.getSelectedItem().toString().equals(primerAnho)) {
                    rellenarComboMesesSiguientes(comboBoxMeses);
                } else if (comboBoxAnhos.getSelectedItem().toString().equals(anhoActual)) {
                    rellenarComboMesesAnteriores(comboBoxMeses);
                } else {
                    rellenarTodosLosMeses(comboBoxMeses);
                }
            }
        });

    }


    private class OkButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class AtrasButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
            rellenarComboMesesAnteriores(comboBoxMeses);
            comboBoxAnhos.setSelectedIndex(0);
            controlador.mostrarF03ListadoReservas();
        }
    }

    private class ComboMesActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

        }
    }

    /*private void ReservasNoviembre() {
        miModelo = new ModeloTablaReservas();
        table_1 = new JTable(miModelo);
        centrarTextoTabla(table_1);
        miModelo.ListadoReservasNoviembre();
        scrollPane.setViewportView(table_1);
    }*/

    private void centrarTextoTabla(JTable table){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0; i<5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    private void rellenarComboMesesAnteriores(JComboBox comboMeses){
        comboMeses.removeAllItems();
        int indiceMes = 0;
        String fechaActual = todaysDate.toString();
        String[] arrayFecha = fechaActual.split("-");
        String mesActual = arrayFecha[1];
        System.out.println("MES ACTUAL "+mesActual);

        for (int i=0; i<numerosMeses.length; i++){
            if(numerosMeses[i].equals(mesActual)){
                break;
            }
            indiceMes++;
        }

        int tamanhoArray = indiceMes+1;
        String[] mesesAnteriores = new String[tamanhoArray];

        for (int i=0; i<mesesAnteriores.length; i++){
            mesesAnteriores[i] = mesesAnho[i];
        }
        for(int i=0; i<mesesAnteriores.length; i++){
            comboMeses.addItem(mesesAnteriores[i]);
        }
    }


    private void rellenarComboMesesSiguientes(JComboBox comboMeses){
        comboMeses.removeAllItems();
        int indiceMes = 0;
        String fechaActual = todaysDate.toString();
        String[] arrayFecha = fechaActual.split("-");
        String mesActual = arrayFecha[1];
        System.out.println("MES ACTUAL "+mesActual);

        for (int i=0; i<numerosMeses.length; i++){
            if(numerosMeses[i].equals(mesActual)){
                break;
            }
            indiceMes++;
        }

        int tamanhoArray = 12-indiceMes;
        String[] mesesSiguientes = new String[tamanhoArray];

        for (int i=0; i<mesesSiguientes.length; i++){
            mesesSiguientes[i] = mesesAnho[indiceMes+i];
        }
        for(int i=0; i<mesesSiguientes.length; i++){
            comboMeses.addItem(mesesSiguientes[i]);
        }
    }

    private void rellenarTodosLosMeses(JComboBox comboMeses){
        comboMeses.removeAllItems();
        for(int i=0; i<mesesAnho.length; i++){
            comboMeses.addItem(mesesAnho[i]);
        }
    }

    private void rellenarComboAnhos(JComboBox comboAnhos){
        comboAnhos.removeAllItems();
        int[] anhos = new int[6];

        for (int i=0; i<anhos.length; i++){
            anhos[i] = intAnhoActual;
            intAnhoActual --;
        }

        System.out.println(Arrays.toString(anhos));

        for (int i=0; i<anhos.length; i++){
            comboAnhos.addItem(anhos[i]);
        }
    }

}

