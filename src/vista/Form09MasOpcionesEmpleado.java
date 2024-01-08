package vista;

import controlador.Controlador;
import modeloVo.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form09MasOpcionesEmpleado extends JFrame{

	private final JPanel contentPane = new JPanel();

	Controlador controlador;


	private JTextField textField_primer_apellido;
	private JTextField textField_nombre;
	private JTextField textField_segundo_apellido;
	private JTextField textField_calle;
	private JTextField textField_numero;
	private JTextField textField_telefono;
	private JTextField textField_nif;
	private JTextField textField_localidad;

	private boolean modificarPulsado = false;
	private boolean buscarPulsado = false;

	private String dniCliente;
	private String nombreCompletoCliente;
	private String nombreCliente;
	private String primerApellidoCliente;
	private String direccionCliente;
	private int telefonoCliente;
	private String calleCliente;
	private String numeroCliente;
	private String localidadCliente;


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
					Form09MasOpcionesEmpleado frame = new Form09MasOpcionesEmpleado();
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
	public Form09MasOpcionesEmpleado() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Opciones Clientes");
		setBounds(100, 100, 455, 403);
		setExtendedState(JFrame.NORMAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textField_primer_apellido = new JTextField();
		textField_primer_apellido.setEditable(false);
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
		textField_nombre.setEditable(false);
		textField_nombre.setColumns(10);
		textField_nombre.setBounds(129, 76, 96, 19);
		getContentPane().add(textField_nombre);
		
		textField_calle = new JTextField();
		textField_calle.setEditable(false);
		textField_calle.setColumns(10);
		textField_calle.setBounds(129, 144, 96, 19);
		getContentPane().add(textField_calle);
		
		textField_numero = new JTextField();
		textField_numero.setEditable(false);
		textField_numero.setColumns(10);
		textField_numero.setBounds(129, 178, 96, 19);
		getContentPane().add(textField_numero);
		
		textField_telefono = new JTextField();
		textField_telefono.setEditable(false);
		textField_telefono.setColumns(10);
		textField_telefono.setBounds(129, 252, 96, 19);
		getContentPane().add(textField_telefono);
		
		textField_nif = new JTextField();
		textField_nif.setColumns(10);
		textField_nif.setBounds(129, 42, 96, 19);
		getContentPane().add(textField_nif);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setAutoscrolls(true);
		lblLocalidad.setBounds(28, 215, 60, 13);
		getContentPane().add(lblLocalidad);
		
		textField_localidad = new JTextField();
		textField_localidad.setEditable(false);
		textField_localidad.setColumns(10);
		textField_localidad.setBounds(129, 215, 96, 19);
		getContentPane().add(textField_localidad);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new BtnBuscarActionListener());
		btnBuscar.setBounds(297, 89, 100, 21);
		getContentPane().add(btnBuscar);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new BtnOkActionListener());
		btnOk.setBounds(76, 321, 85, 21);
		getContentPane().add(btnOk);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new BtnAtrasActionListener());
		btnAtras.setBounds(257, 321, 85, 21);
		getContentPane().add(btnAtras);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new BtnEliminarActionListener());
		btnEliminar.setBounds(297, 143, 100, 21);
		getContentPane().add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new BtnModificarActionListener());
		btnModificar.setBounds(297, 196, 100, 21);
		getContentPane().add(btnModificar);
	}
	
	
	private class BtnBuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(textField_nif.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El campo NIF está vacío. Por favor, introduzca un dni.");
			}else {
				buscarPulsado = true;
				modificarPulsado = false;
				buscarCliente();
				desactivarEdicionTextField();
			}
		}
	}
	
	private class BtnEliminarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//modificarPulsado = false;
			if(buscarPulsado == true) {
				eliminarCliente();
				limpiarCampos();
				activarEdicionCodigo();
				buscarPulsado = false;
			}else {
				JOptionPane.showMessageDialog(null, "Primero haga click en botón buscar, consulte los datos del cliente, y posteriormente si así lo desea podrá eliminar dicho cliente.");
			}
		}
	}
	
	private class BtnModificarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(buscarPulsado == true) {
				activarEdicionTextField();
				buscarPulsado = false;
				modificarPulsado = true;
			}else {
				JOptionPane.showMessageDialog(null, "Primero haga click en botón buscar, consulte los datos del cliente, y posteriormente si así lo desea podrá modificar dichos datos.");
			}
		}
	}
	
	private class BtnOkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(modificarPulsado==true) {
				int res = JOptionPane.showOptionDialog(new JFrame(), "¿Estás seguro que quieres modificar los datos de"
						+ " éste cliente?", "Options",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Continuar", "Cancelar" }, JOptionPane.YES_OPTION);
				
				if (res == JOptionPane.YES_OPTION) {	
					desactivarEdicionTextField();
					modificarCliente();
					modificarPulsado = false;
					limpiarCampos();
					activarEdicionCodigo();
				}
			}
			
		}
	}
	
	private class BtnAtrasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modificarPulsado = false;
			limpiarCampos();
			dispose();
			controlador.mostrarF04Clientes();
		}
	}
	
	
	public void activarEdicionTextField() {
		textField_nif.setEditable(true);
		textField_nombre.setEditable(true);
		textField_primer_apellido.setEditable(true);
		textField_calle.setEditable(true);
		textField_numero.setEditable(true);
		textField_localidad.setEditable(true);
		textField_telefono.setEditable(true);
	}
	
	public void desactivarEdicionTextField() {
		textField_nif.setEditable(false);
		textField_nombre.setEditable(false);
		textField_primer_apellido.setEditable(false);
		textField_calle.setEditable(false);
		textField_numero.setEditable(false);
		textField_localidad.setEditable(false);
		textField_telefono.setEditable(false);
	}
	
	public void activarEdicionCodigo() {
		textField_nif.setEditable(true);
	}
	
	public void capturarCodigo() {
		dniCliente = textField_nif.getText();
	}
	
	public void capturarDatos() {
		dniCliente = textField_nif.getText();
		nombreCliente = textField_nombre.getText();
		primerApellidoCliente = textField_primer_apellido.getText();
		calleCliente = textField_calle.getText();
		numeroCliente = textField_numero.getText();
		localidadCliente = textField_localidad.getText();
		telefonoCliente = Integer.parseInt(textField_telefono.getText());
		
		nombreCompletoCliente = nombreCliente+" "+primerApellidoCliente;
		direccionCliente = calleCliente+","+numeroCliente+","+localidadCliente;
	}
	
	
	public void buscarCliente() {
		if(textField_nif.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo NIF está vacío. Por favor, introduzca un dni.");
		}
		else {
			Cliente cliente = new Cliente();
			capturarCodigo();
			
			controlador.buscarCliente(cliente, dniCliente);
			
			dniCliente = cliente.getNif();
			nombreCompletoCliente = cliente.getNombre();
			direccionCliente = cliente.getDireccion();
			telefonoCliente = cliente.getTelefono();
			
			String[] nombre = nombreCompletoCliente.split(" ");
			
			if(nombre.length == 2) {
				nombreCliente = nombre[0];
				primerApellidoCliente = nombre[1];
			}
			if(nombre.length == 3) {
				nombreCliente = nombre[0]+" "+nombre[1];
				primerApellidoCliente = nombre[2];
				
			}
			nombreCliente = nombre[0];
			String[] direccion = direccionCliente.split(",");
			
			calleCliente = direccion[0];
			numeroCliente = direccion[1];
			localidadCliente = direccion[2];
			
			textField_nif.setText(dniCliente);
			textField_nombre.setText(nombreCliente);
			textField_primer_apellido.setText(primerApellidoCliente);
			textField_calle.setText(calleCliente);
			textField_numero.setText(numeroCliente);
			textField_localidad.setText(localidadCliente);
			textField_telefono.setText(String.valueOf(telefonoCliente));
		}
	}
	
	public void limpiarCampos() {
		textField_nif.setText("");
		textField_nombre.setText("");
		textField_primer_apellido.setText("");
		textField_calle.setText("");
		textField_numero.setText("");
		textField_localidad.setText("");
		textField_telefono.setText("");
	}
	
	public void modificarCliente() {
		if(textField_nif.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El campo NIF está vacío. Por favor, introduzca un dni.");
		}
		
		Cliente cliente = new Cliente();
		capturarDatos();
		
		cliente.setNif(dniCliente);
		cliente.setNombre(nombreCompletoCliente);
		cliente.setDireccion(direccionCliente);
		cliente.setTelefono(telefonoCliente);
		
		controlador.modificarCliente(cliente, dniCliente);
	}
	
	public void eliminarCliente() {
		controlador.eliminarCliente(dniCliente);
	}
}
