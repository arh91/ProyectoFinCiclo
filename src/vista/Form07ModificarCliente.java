package vista;

import controlador.Controlador;
import modeloVo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form07ModificarCliente extends JFrame{
    private final JPanel contentPane = new JPanel();

    Controlador controlador = new Controlador();

    private static String dniCliente, nombreCliente, primerApellido, calle, numero, localidad, telefono;

    private String nombreClienteIntro, primerApellidoIntro, calleIntro, numeroIntro, localidadIntro, telefonoIntro;

    private JTextField textField_primer_apellido;
    private JTextField textField_nombre;
    private JTextField textField_segundo_apellido;
    private JTextField textField_calle;
    private JTextField textField_numero;
    private JTextField textField_telefono;
    private JTextField textField_nif;
    private JTextField textField_localidad;

    Form06MasOpcionesCliente masOpciones = new Form06MasOpcionesCliente();


    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form07ModificarCliente frame = new Form07ModificarCliente(dniCliente, nombreCliente, primerApellido, calle, numero, localidad, telefono);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    /*public Form07ModificarCliente() {
        initialize();
    }*/
    public Form07ModificarCliente(String dniCliente, String nombreCliente, String primerApellido, String calle, String numero, String localidad, String telefono) throws HeadlessException {
        this.dniCliente = dniCliente;
        this.nombreCliente = nombreCliente;
        this.primerApellido = primerApellido;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.telefono = telefono;
        initialize();
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setTitle("Modificar Cliente");
        setBounds(100, 100, 455, 403);
        setExtendedState(JFrame.NORMAL);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        textField_primer_apellido = new JTextField();
        textField_primer_apellido.setBounds(129, 110, 96, 19);
        textField_primer_apellido.setText(primerApellido);
        getContentPane().add(textField_primer_apellido);
        textField_primer_apellido.setColumns(10);

        JLabel label_nombre = new JLabel("Nombre");
        label_nombre.setBounds(28, 79, 58, 13);
        getContentPane().add(label_nombre);

        JLabel label_primer_apellido = new JLabel("Primer Apellido");
        label_primer_apellido.setBounds(28, 113, 110, 13);
        getContentPane().add(label_primer_apellido);

        JLabel label_nif = new JLabel("NIF");
        label_nif.setBounds(28, 45, 45, 13);
        getContentPane().add(label_nif);

        JLabel label_calle = new JLabel("Calle");
        label_calle.setBounds(28, 147, 45, 13);
        getContentPane().add(label_calle);

        JLabel label_numero = new JLabel("Número");
        label_numero.setBounds(28, 181, 45, 13);
        getContentPane().add(label_numero);

        JLabel label_telefono = new JLabel("Teléfono");
        label_telefono.setBounds(28, 249, 70, 13);
        getContentPane().add(label_telefono);

        textField_nombre = new JTextField();
        textField_nombre.setText(nombreCliente);
        textField_nombre.setColumns(10);
        textField_nombre.setBounds(129, 76, 96, 19);
        getContentPane().add(textField_nombre);

        textField_calle = new JTextField();
        textField_calle.setColumns(10);
        textField_calle.setBounds(129, 144, 96, 19);
        textField_calle.setText(calle);
        getContentPane().add(textField_calle);

        textField_numero = new JTextField();
        textField_numero.setColumns(10);
        textField_numero.setBounds(129, 178, 96, 19);
        textField_numero.setText(numero);
        getContentPane().add(textField_numero);

        textField_telefono = new JTextField();
        textField_telefono.setColumns(10);
        textField_telefono.setBounds(129, 252, 96, 19);
        textField_telefono.setText(telefono);
        getContentPane().add(textField_telefono);

        textField_nif = new JTextField();
        textField_nif.setText(dniCliente);
        textField_nif.setEditable(false);
        textField_nif.setColumns(10);
        textField_nif.setBounds(129, 42, 96, 19);
        getContentPane().add(textField_nif);

        JLabel lblLocalidad = new JLabel("Localidad");
        lblLocalidad.setAutoscrolls(true);
        lblLocalidad.setBounds(28, 215, 60, 13);
        getContentPane().add(lblLocalidad);

        textField_localidad = new JTextField();
        textField_localidad.setColumns(10);
        textField_localidad.setBounds(129, 215, 96, 19);
        textField_localidad.setText(localidad);
        getContentPane().add(textField_localidad);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(new BtnAtrasActionListener());
        btnAtras.setBounds(257, 321, 85, 21);
        getContentPane().add(btnAtras);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new BtnModificarActionListener());
        btnModificar.setBounds(297, 196, 100, 21);
        getContentPane().add(btnModificar);
    }


    private class BtnModificarActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            capturarDatos();
            System.out.println(nombreClienteIntro);
            System.out.println(nombreCliente);
            if(nombreClienteIntro.equals(nombreCliente) && primerApellidoIntro.equals(primerApellido) && calleIntro.equals(calle) && numeroIntro.equals(numero) && localidadIntro.equals(localidad) && telefonoIntro.equals(telefono)){
                JOptionPane.showMessageDialog(null, "No se ha modificado ningún dato","Error",JOptionPane.ERROR_MESSAGE);
            }else if(nombreClienteIntro.isEmpty() || primerApellidoIntro.isEmpty() || calleIntro.isEmpty() || numeroIntro.isEmpty() || localidadIntro.isEmpty() || telefonoIntro.isEmpty()){
                JOptionPane.showMessageDialog(null, "Error: no se han rellenado todos los campos","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                modificarCliente();
            }
        }
    }


    private class BtnAtrasActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
            masOpciones = new Form06MasOpcionesCliente();
            masOpciones.setVisible(true);
        }
    }


    public void capturarDatos(){
        nombreClienteIntro = textField_nombre.getText();
        primerApellidoIntro = textField_primer_apellido.getText();
        calleIntro = textField_calle.getText();
        numeroIntro = textField_numero.getText();
        localidadIntro = textField_localidad.getText();
        telefonoIntro = textField_telefono.getText();
    }


    public void modificarCliente() {
        if(textField_nif.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo NIF está vacío. Por favor, introduzca un dni.");
        }

        Cliente cliente = new Cliente();
        capturarDatos();

        String nombreCompletoCliente = nombreClienteIntro+" "+primerApellidoIntro;
        String direccionCliente = calleIntro+", "+numeroIntro+", "+localidadIntro;
        int telefono = Integer.parseInt(telefonoIntro);
        cliente.setNombre(nombreCompletoCliente);
        cliente.setDireccion(direccionCliente);
        cliente.setTelefono(telefono);

        controlador.modificarCliente(cliente, dniCliente);
    }
}
