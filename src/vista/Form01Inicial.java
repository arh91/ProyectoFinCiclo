package vista;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Form01Inicial extends JFrame{

	private final JPanel contentPane = new JPanel();
	
	//private JDialog dialogg;
	
	Controlador controlador = new Controlador();
	
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
					Form01Inicial frame = new Form01Inicial();
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
	public Form01Inicial() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		setTitle("Inicio");
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.NORMAL);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnNuevaReserva = new JButton("Nueva Reserva");
		btnNuevaReserva.addActionListener(new BtnNuevaReservaActionListener());
		btnNuevaReserva.setBounds(57, 29, 140, 25);
		getContentPane().add(btnNuevaReserva);
		
		JButton btnListadoReservas = new JButton("Listado Reserva");
		btnListadoReservas.addActionListener(new BtnListadoReservasActionListener());
		btnListadoReservas.setBounds(251, 29, 140, 25);
		getContentPane().add(btnListadoReservas);
		
		JButton btnListadoClientes = new JButton("Listado Clientes");
		btnListadoClientes.addActionListener(new BtnListadoClientesActionListener());
		btnListadoClientes.setBounds(57, 120, 140, 25);
		getContentPane().add(btnListadoClientes);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new BtnClientesActionListener());
		btnClientes.setBounds(251, 120, 140, 25);
		getContentPane().add(btnClientes);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new BtnSalirActionListener());
		btnSalir.setBounds(155, 201, 140, 25);
		getContentPane().add(btnSalir);


		controlador.eliminarReservasAntiguas();
	}
	
	private class BtnNuevaReservaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			controlador.mostrarF02NuevaReserva();
		}
	}
	
	private class BtnListadoReservasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF03ListadoReservas();
		}
	}
	
	private class BtnListadoClientesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF05ListadoClientes();
		}
	}
	private class BtnClientesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF04Clientes();
		}
	}
	private class BtnSalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
}
