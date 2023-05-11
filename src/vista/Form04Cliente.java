package vista;

import controlador.Controlador;
import modeloVo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form04Cliente extends JFrame{
	
	private final JPanel contentPane = new JPanel();
	
	Controlador controlador;

	private JTextField textField_primer_apellido;
	private JTextField textField_nombre;
	private JTextField textField_calle;
	private JTextField textField_numero;
	private JTextField textField_telefono;
	private JTextField textField_nif;
	private JTextField textField_localidad;

	private String nif;
	private String nombre;
	private String primerApellido;
	private String calle;
	private String numero;
	private String localidad;
	private String nombreCompleto;
	private String direccion;
	private int telefono;
	
	
	Cliente cliente = new Cliente();
	
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
					Form04Cliente frame = new Form04Cliente();
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
	public Form04Cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Nuevo Cliente");
		setBounds(100, 100, 635, 404);
		setExtendedState(JFrame.NORMAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textField_primer_apellido = new JTextField();
		textField_primer_apellido.setBounds(129, 110, 96, 19);
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
		textField_nombre.setColumns(10);
		textField_nombre.setBounds(129, 76, 96, 19);
		getContentPane().add(textField_nombre);
		
		textField_calle = new JTextField();
		textField_calle.setColumns(10);
		textField_calle.setBounds(129, 144, 96, 19);
		getContentPane().add(textField_calle);
		
		textField_numero = new JTextField();
		textField_numero.setColumns(10);
		textField_numero.setBounds(129, 178, 96, 19);
		getContentPane().add(textField_numero);
		
		textField_telefono = new JTextField();
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(129, 252, 96, 19);
		getContentPane().add(textField_telefono);
		
		textField_nif = new JTextField();
		textField_nif.setColumns(10);
		textField_nif.setBounds(129, 42, 96, 19);
		getContentPane().add(textField_nif);
		
		JButton btnMasOpciones = new JButton("Más opciones");
		btnMasOpciones.addActionListener(new BtnMasOpcionesActionListener());
		btnMasOpciones.setBounds(396, 121, 139, 21);
		getContentPane().add(btnMasOpciones);
		
		JButton btnInsertar = new JButton("Insertar Cliente");
		btnInsertar.addActionListener(new BtnInsertarActionListener());
		btnInsertar.setBounds(104, 327, 132, 21);
		getContentPane().add(btnInsertar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new BtnAtrasActionListener());
		btnAtras.setBounds(396, 327, 85, 21);
		getContentPane().add(btnAtras);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setAutoscrolls(true);
		lblLocalidad.setBounds(28, 215, 70, 13);
		getContentPane().add(lblLocalidad);
		
		textField_localidad = new JTextField();
		textField_localidad.setColumns(10);
		textField_localidad.setBounds(129, 215, 96, 19);
		getContentPane().add(textField_localidad);
	}

	
	private class BtnMasOpcionesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			controlador.mostrarF06MasOpcionesCliente();
		}
	}
	
	private class BtnInsertarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			insertarCliente();
		}
	}
	
	private class BtnAtrasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			controlador.mostrarF01Inicial();
		}
	}
	
	
	public void recogerTexto() {
		nif = textField_nif.getText();
		nombre = textField_nombre.getText();
		primerApellido = textField_primer_apellido.getText();
		calle = textField_calle.getText();
		numero = textField_numero.getText();
		localidad = textField_localidad.getText();
		telefono = Integer.parseInt(textField_telefono.getText());
		
		nombreCompleto = nombre+(" ")+primerApellido;
		direccion = calle+(",")+numero+(",")+localidad;
	}
	
	public void insertarCliente() {
		Cliente cliente = new Cliente();
		recogerTexto();
		
		cliente.setNif(nif);
		cliente.setNombre(nombreCompleto);
		cliente.setDireccion(direccion);
		cliente.setTelefono(telefono);

		String dniCliente = cliente.getNif();
		controlador.insertarCliente(cliente, dniCliente);
	}
}
