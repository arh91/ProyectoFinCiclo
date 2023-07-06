package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modeloVo.Coche;
import modeloVo.Involucra;
import modeloVo.Reserva;
import validaciones.ConvertirFechas;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Form02NuevaReserva extends JFrame {

	private final JPanel contentPanel = new JPanel();

	LocalDate todaysDate = LocalDate.now();  //Fecha actual del sistema

	Controlador controlador = new Controlador();

	private JTextField textFecInicial;
	private JTextField textFecFinal;
	private JTextField textLitros;
	private JTextField textCodReserva;
	private ModeloComboClientes comboBox_Clientes;
	private ModeloComboCoches comboBox_Coches;

	private int codigoReserva;
	private Date fechaInicio;
	private Date fechaFinal;
	private int litros;

	private String fechaInicioModificada;
	private String fechaFinModificada;

	private String dniCliente;
	private String matriculaCoche;
	private Date fecInicioCancelar;
	private Date fecFinalCancelar;
	private String nifInvolucra;
	private String fechaInicioModificadaCanc;
	private String fechaFinModificadaCanc;

	ConvertirFechas convertirFechas = new ConvertirFechas();

	private JTextField textField_nif_cliente;
	private JTextField textField_Fecha_Inicio;
	private JTextField textField_Matricula_Coche;
	private JTextField textField_fecha_final;

	private int codigoReservaCancelar;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public static void main(String[] args) {
		try {
			Form02NuevaReserva frame = new Form02NuevaReserva();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form02NuevaReserva() {
		System.out.println(todaysDate);
		setTitle("Nueva Reserva");
		setBounds(100, 100, 869, 455);
		getContentPane().setLayout(new BorderLayout());
		setExtendedState(JFrame.NORMAL);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(35, 24, 56, 16);
		contentPanel.add(lblCliente);

		JLabel lblCoche = new JLabel("Coche:");
		lblCoche.setBounds(35, 67, 56, 16);
		contentPanel.add(lblCoche);



		JPanel panel_Reserva = new JPanel();
		panel_Reserva.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Reserva.setBounds(12, 105, 827, 139);
		contentPanel.add(panel_Reserva);
		panel_Reserva.setLayout(null);

		JLabel lblFechaInicial = new JLabel("Fecha Inicial (DD/MM/AAAA):");
		lblFechaInicial.setBounds(45, 35, 192, 16);
		panel_Reserva.add(lblFechaInicial);

		JLabel lblFechafinal = new JLabel("FechaFinal (DD/MM/AAAA):");
		lblFechafinal.setBounds(438, 35, 179, 16);
		panel_Reserva.add(lblFechafinal);

		textFecInicial = new JTextField();
		textFecInicial.setHorizontalAlignment(SwingConstants.CENTER);
		textFecInicial.setBounds(249, 32, 116, 22);
		panel_Reserva.add(textFecInicial);
		textFecInicial.setColumns(10);

		textFecFinal = new JTextField();
		textFecFinal.setHorizontalAlignment(SwingConstants.CENTER);
		textFecFinal.setColumns(10);
		textFecFinal.setBounds(629, 32, 116, 22);
		panel_Reserva.add(textFecFinal);

		JLabel lblLitros = new JLabel("Litros consumidos:");
		lblLitros.setBounds(438, 81, 152, 16);
		panel_Reserva.add(lblLitros);

		textLitros = new JTextField();
		textLitros.setHorizontalAlignment(SwingConstants.CENTER);
		textLitros.setBounds(629, 78, 116, 22);
		panel_Reserva.add(textLitros);
		textLitros.setColumns(10);

		JLabel lblCdigoReserva = new JLabel("C\u00F3digo Reserva:");
		lblCdigoReserva.setBounds(45, 81, 167, 16);
		panel_Reserva.add(lblCdigoReserva);

		textCodReserva = new JTextField();
		textCodReserva.setHorizontalAlignment(SwingConstants.CENTER);
		textCodReserva.setColumns(10);
		textCodReserva.setBounds(249, 78, 116, 22);
		panel_Reserva.add(textCodReserva);

		comboBox_Coches = new ModeloComboCoches();
		comboBox_Coches.setBounds(101, 65, 686, 20);
		contentPanel.add(comboBox_Coches);

		comboBox_Clientes = new ModeloComboClientes();
		comboBox_Clientes.setBounds(101, 22, 196, 20);
		contentPanel.add(comboBox_Clientes);

		JPanel panel_Cancelar_Reserva = new JPanel();
		panel_Cancelar_Reserva.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cancelar Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Cancelar_Reserva.setBounds(12, 266, 827, 111);
		contentPanel.add(panel_Cancelar_Reserva);
		panel_Cancelar_Reserva.setLayout(null);

		JLabel labelNifCliente = new JLabel("NIF Cliente:");
		labelNifCliente.setBounds(51, 29, 66, 13);
		panel_Cancelar_Reserva.add(labelNifCliente);

		JLabel labelFechaInicio = new JLabel("Fecha Inicial (DD/MM/AAAA):");
		labelFechaInicio.setBounds(50, 70, 164, 13);
		panel_Cancelar_Reserva.add(labelFechaInicio);

		textField_nif_cliente = new JTextField();
		textField_nif_cliente.setHorizontalAlignment(SwingConstants.CENTER);
		textField_nif_cliente.setColumns(10);
		textField_nif_cliente.setBounds(248, 20, 116, 22);
		panel_Cancelar_Reserva.add(textField_nif_cliente);

		textField_Fecha_Inicio = new JTextField();
		textField_Fecha_Inicio.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Fecha_Inicio.setColumns(10);
		textField_Fecha_Inicio.setBounds(248, 67, 116, 22);
		panel_Cancelar_Reserva.add(textField_Fecha_Inicio);

		textField_Matricula_Coche = new JTextField();
		textField_Matricula_Coche.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Matricula_Coche.setColumns(10);
		textField_Matricula_Coche.setBounds(636, 25, 116, 22);
		panel_Cancelar_Reserva.add(textField_Matricula_Coche);

		textField_fecha_final = new JTextField();
		textField_fecha_final.setHorizontalAlignment(SwingConstants.CENTER);
		textField_fecha_final.setColumns(10);
		textField_fecha_final.setBounds(636, 67, 116, 22);
		panel_Cancelar_Reserva.add(textField_fecha_final);

		JLabel lblNewLabel_2 = new JLabel("Matrícula Vehículo:");
		lblNewLabel_2.setBounds(441, 29, 116, 13);
		panel_Cancelar_Reserva.add(lblNewLabel_2);

		JLabel labelFechaFin = new JLabel("Fecha Final (DD/MM/AAAAA):");
		labelFechaFin.setBounds(441, 71, 174, 13);
		panel_Cancelar_Reserva.add(labelFechaFin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Efectuar Reserva");
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);

				JButton cancelButton = new JButton("Cancelar Reserva");
				cancelButton.addActionListener(new CancelButtonActionListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnAtras = new JButton("Atras");
				btnAtras.addActionListener(new AtrasButtonActionListener());
				btnAtras.setActionCommand("Cancel");
				buttonPane.add(btnAtras);
			}
		}

		controlador.busquedaReservas("JBB-5623");
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			/*String cadena = String.valueOf(comboBox_Coches.getSelectedItem());
			String[] arr = cadena.split("  ");
			String matricula = arr[0];*/
			Coche coche = new Coche();
			Reserva reserva = new Reserva();
			Involucra involucra = new Involucra();

			//controlador.preguntarDisponibilidadCoche(matriculaCoche, coche);

			if(coche.isDisponible()==false) {
				JOptionPane.showMessageDialog(null, "Lo sentimos, el coche seleccionado no se encuentra disponible para las fechas que usted ha seleccionado.");
			}else {
				controlador.reservarCoche(fechaInicio, fechaFinal, matriculaCoche);
				Reserva(reserva);
				Involucra(involucra);
				System.out.println("MATRÍCULA: "+matriculaCoche);
				System.out.println("DNI CLIENTE: "+nifInvolucra);
				System.out.println("CÓDIGO RESERVA: "+codigoReserva);
				System.out.println("LITROS: "+litros);
				codigoReserva = reserva.getCodigo();
				controlador.insertarReserva(reserva, codigoReserva);
				controlador.insertarInvolucra(involucra, codigoReserva);
			}
		}
	}

	private class CancelButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			datosCancelacion();
			if (controlador.existeCliente(dniCliente) == false){
				JOptionPane.showMessageDialog(null, "Ningún cliente con dni "+dniCliente+" ha hecho una reserva.","Información",JOptionPane.INFORMATION_MESSAGE);
			}if (controlador.existeCoche(matriculaCoche) == false){
				JOptionPane.showMessageDialog(null, "No existen reservas del coche con matrícula "+matriculaCoche+".","Información",JOptionPane.INFORMATION_MESSAGE);
			}if (controlador.existeCliente(dniCliente) == true && controlador.existeCoche(matriculaCoche) == true) {
				try {
					controlador.eliminarInvolucra(codigoReservaCancelar);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				controlador.eliminarReserva(codigoReservaCancelar);
				controlador.liberarCoche(matriculaCoche);
			}
			}
		}


	private class AtrasButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			controlador.mostrarF01Inicial();
		}
	}

	private void modificarFechaInicio(){
		fechaInicioModificada = textFecInicial.getText();
		String[] arrFecInicial = fechaInicioModificada.split("/");
		String[] arrFecInicialModificado = new String[3];
		System.out.println(arrFecInicial[0]);
		System.out.println(arrFecInicial[1]);
		System.out.println(arrFecInicial[2]);
		int j=0;
		for(int i= arrFecInicial.length-1; i>=0; i--){

			arrFecInicialModificado[j] = arrFecInicial[i];
			j++;
		}

		String diaInicio = String.valueOf(arrFecInicialModificado[2]);
		String mesInicio = String.valueOf(arrFecInicialModificado[1]);
		String anhoInicio = String.valueOf(arrFecInicialModificado[0]);

		fechaInicioModificada = anhoInicio+"-"+mesInicio+"-"+diaInicio;
	}

	private void modificarFechaFin(){
		fechaFinModificada = textFecFinal.getText();
		String[] arrFecFinal = fechaFinModificada.split("/");
		String[] arrFecFinalModificado = new String[3];

		int k=0;
		for(int i=arrFecFinal.length-1; i>=0; i--){
			arrFecFinalModificado[k] = arrFecFinal[i];
			k++;
		}

		String diaFin = String.valueOf(arrFecFinalModificado[2]);
		String mesFin = String.valueOf(arrFecFinalModificado[1]);
		String anhoFin = String.valueOf(arrFecFinalModificado[0]);

		fechaFinModificada = anhoFin+"-"+mesFin+"-"+diaFin;
	}

	private void modificarFechaInicioCanc(){
		fechaInicioModificadaCanc = textField_Fecha_Inicio.getText();
		String[] arrFecInicialCanc = fechaInicioModificadaCanc.split("/");
		String[] arrFecInicialModificadoCanc = new String[3];

		int j=0;
		for(int i= arrFecInicialCanc.length-1; i>=0; i--){
			arrFecInicialModificadoCanc[j] = arrFecInicialCanc[i];
			j++;
		}

		String diaInicio = String.valueOf(arrFecInicialModificadoCanc[2]);
		String mesInicio = String.valueOf(arrFecInicialModificadoCanc[1]);
		String anhoInicio = String.valueOf(arrFecInicialModificadoCanc[0]);

		fechaInicioModificadaCanc = anhoInicio+"-"+mesInicio+"-"+diaInicio;
	}

	private void modificarFechaFinCanc(){
		fechaFinModificadaCanc = textField_fecha_final.getText();
		String[] arrFecFinalCanc = fechaFinModificadaCanc.split("/");
		String[] arrFecFinalModificadoCanc = new String[3];

		int k=0;
		for(int i=arrFecFinalCanc.length-1; i>=0; i--){
			arrFecFinalModificadoCanc[k] = arrFecFinalCanc[i];
			k++;
		}

		String diaFin = String.valueOf(arrFecFinalModificadoCanc[2]);
		String mesFin = String.valueOf(arrFecFinalModificadoCanc[1]);
		String anhoFin = String.valueOf(arrFecFinalModificadoCanc[0]);

		fechaFinModificadaCanc = anhoFin+"-"+mesFin+"-"+diaFin;
	}
	private void Reserva(Reserva reserva) {
		codigoReserva = Integer.parseInt(textCodReserva.getText());

		modificarFechaInicio();
		modificarFechaFin();

		fechaInicio = convertirFechas.convertirStringDate(fechaInicioModificada);
		fechaFinal = convertirFechas.convertirStringDate(fechaFinModificada);

		reserva.setCodigo(codigoReserva);
		reserva.setFecInicio(fechaInicio);
		reserva.setFecFinal(fechaFinal);
	}

	private void Involucra(Involucra involucra) {
		String infoCoche = String.valueOf(comboBox_Coches.getSelectedItem());
		String[] arrCoche = infoCoche.split("  ");
		matriculaCoche = arrCoche[0];

		String infoCliente = String.valueOf(comboBox_Clientes.getSelectedItem());
		String[] arrCliente = infoCliente.split("  ");
		nifInvolucra = arrCliente[0];

		codigoReserva = Integer.parseInt(textCodReserva.getText());
		litros = Integer.parseInt(textLitros.getText());

		/*System.out.println("MATRÍCULA: "+matriculaCoche);
		System.out.println("DNI CLIENTE: "+nifInvolucra);
		System.out.println("CÓDIGO RESERVA: "+codigoReserva);
		System.out.println("LITROS: "+litros);*/

		involucra.setMatricula(matriculaCoche);
		involucra.setCliente(nifInvolucra);
		involucra.setReserva(codigoReserva);
		involucra.setLitros(litros);
	}

	private void datosCancelacion() {
		Involucra involucra = new Involucra();
		dniCliente = textField_nif_cliente.getText();
		matriculaCoche = textField_Matricula_Coche.getText();

		modificarFechaInicioCanc();
		modificarFechaFinCanc();

		fecInicioCancelar = convertirFechas.convertirStringDate(fechaInicioModificadaCanc);
		fecFinalCancelar = convertirFechas.convertirStringDate(fechaFinModificadaCanc);

		controlador.obtenerCodigoReserva(involucra, matriculaCoche);

		codigoReservaCancelar = involucra.getReserva();
	}

}
