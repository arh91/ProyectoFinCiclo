package vista;

import controlador.Controlador;
import validaciones.ConvertirFechas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class Form11ModificarReserva extends JFrame {
    private final JPanel contentPanel = new JPanel();

    LocalDate todaysDate = LocalDate.now();  //Fecha actual del sistema
    String today = String.valueOf(todaysDate);

    Controlador controlador = new Controlador();

    private JTextField textFecInicio;
    private JTextField textFecFinal;
    private JTextField textLitros;
    private JTextField textCodReserva;
    private ModeloComboClientes comboBox_Clientes;
    private ModeloComboCoches comboBox_Coches;


    private int codigoReserva;
    private Date fechaInicio;
    private Date fechaFinal;
    private int litros;

    private String dniCliente;
    private String matricula;
    private Date fecInicio;
    private Date fecFinal;
    private String nifInvolucra;

    ConvertirFechas convertirFechas = new ConvertirFechas();
    private JTextField textField_Fecha_Inicio;
    private JTextField textField_fecha_final;

    //combos fecha inicio reserva
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private String[] fecActual = today.split("-");
    private String a = fecActual[0];
    private String m = fecActual[1];
    private String d = fecActual[2];
    int anhoActual = Integer.parseInt(a);
    int mesActual = Integer.parseInt(m);
    int diaActual = Integer.parseInt(d);
    private boolean existeProxAnho = false;


    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public static void main(String[] args) {
        try {
            prueba frame = new prueba();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Form11ModificarReserva() {
        setTitle("Modificar Reserva");
        setBounds(100, 100, 760, 390);
        getContentPane().setLayout(new BorderLayout());
        setExtendedState(JFrame.NORMAL);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(50, 107, 56, 16);
        contentPanel.add(lblCliente);

        JLabel lblCoche = new JLabel("Coche:");
        lblCoche.setBounds(50, 147, 56, 16);
        contentPanel.add(lblCoche);

        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
        lblFechaInicial.setBounds(50, 187, 100, 16);
        contentPanel.add(lblFechaInicial);

        JLabel lblFechafinal = new JLabel("Fecha Final:");
        lblFechafinal.setBounds(50, 227, 100, 16);
        contentPanel.add(lblFechafinal);

        JLabel lblLitros = new JLabel("Litros consumidos:");
        lblLitros.setBounds(50, 267, 152, 16);
        contentPanel.add(lblLitros);

        textLitros = new JTextField();
        textLitros.setHorizontalAlignment(SwingConstants.CENTER);
        textLitros.setBounds(174, 265, 116, 22);
        contentPanel.add(textLitros);
        textLitros.setColumns(10);

        JLabel lblCdigoReserva = new JLabel("C\u00F3digo Reserva:");
        lblCdigoReserva.setBounds(50, 67, 167, 16);
        contentPanel.add(lblCdigoReserva);

        textCodReserva = new JTextField();
        textCodReserva.setHorizontalAlignment(SwingConstants.CENTER);
        textCodReserva.setColumns(10);
        textCodReserva.setBounds(174, 65, 116, 22);
        contentPanel.add(textCodReserva);

        textFecInicio = new JTextField();
        textFecInicio.setHorizontalAlignment(SwingConstants.CENTER);
        textFecInicio.setColumns(10);
        textFecInicio.setBounds(174, 185, 116, 22);
        contentPanel.add(textFecInicio);

        comboBox_Coches = new ModeloComboCoches();
        comboBox_Coches.setBounds(174, 143, 450, 20);
        contentPanel.add(comboBox_Coches);

        comboBox_Clientes = new ModeloComboClientes();
        comboBox_Clientes.setBounds(174, 105, 196, 20);
        contentPanel.add(comboBox_Clientes);

        textFecFinal = new JTextField();
        textFecFinal.setHorizontalAlignment(SwingConstants.CENTER);
        textFecFinal.setColumns(10);
        textFecFinal.setBounds(174, 225, 116, 22);
        contentPanel.add(textFecFinal);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBounds(0, 321, 736, 31);
            contentPanel.add(buttonPane);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            {
                JButton btnBuscar = new JButton("Buscar Reserva");
                btnBuscar.addActionListener(new BuscarButtonActionListener());
                btnBuscar.setActionCommand("OK");
                buttonPane.add(btnBuscar);

                JButton btnModificar = new JButton("Modificar Reserva");
                btnModificar.addActionListener(new ModificarButtonActionListener());
                btnModificar.setActionCommand("Cancel");
                buttonPane.add(btnModificar);
                getRootPane().setDefaultButton(btnBuscar);
            }
            {
                JButton btnAtras = new JButton("Atras");
                btnAtras.addActionListener(new AtrasButtonActionListener());
                btnAtras.setActionCommand("Cancel");
                buttonPane.add(btnAtras);
            }
        }

        JLabel lblTitulo = new JLabel("BUSCAR/MODIFICAR RESERVAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(292, 10, 279, 22);
        contentPanel.add(lblTitulo);

    }

    private class BuscarButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

        }
    }

    private class ModificarButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

        }
    }

    private class AtrasButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
            controlador.mostrarF02NuevaReserva();
        }
    }
}
