package vista;

import controlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;

public class
Form03ListadoReservas extends JFrame {

	private final JPanel contentPanel = new JPanel();
	Controlador controlador = new Controlador();
	private JScrollPane scrollPane;
	
	


	private DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
	private JTextField textFieldPrecioMedio;
	private JTextField textDiasMedia;
	/**
	 * @wbp.nonvisual location=-49,244
	 */
	private final JTextField textField = new JTextField();
	private JTextField textTotalMes;
	private JTextField textNumAlquileres;
	private JTable tabla_Reservas;
	private JComboBox meses;
	private String[] mesesAnho = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private ModeloTablaReservas miModelo;
	private ModeloTablaReservas reservas = new ModeloTablaReservas();
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	
	public static void main(String[] args) {
		try {
			Form03ListadoReservas frame = new Form03ListadoReservas();
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
	public Form03ListadoReservas() {
		textField.setColumns(10);
		setTitle("Listado Reservas Mes");
		setBounds(100, 100, 884, 345);
		setExtendedState(JFrame.NORMAL);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Seleccionar Mes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 672, 44);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		meses = new JComboBox();
		meses.setBounds(65, 13, 106, 20);
		for(int i=0; i<mesesAnho.length; i++) {
			meses.addItem(mesesAnho[i]);
		}
		panel.add(meses);

		JButton historialReservas = new JButton("Historial Reservas");
		historialReservas.addActionListener(new HistorialReservasActionListener());
		historialReservas.setBounds(704, 22, 150 ,30);
		contentPanel.add(historialReservas);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 842, 179);
		contentPanel.add(scrollPane);
		
		tabla_Reservas = new JTable();
		//scrollPane.setColumnHeaderView(table_1);
		tabla_Reservas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matr\u00EDcula Coche", "Cliente", "Precio Reserva", "N\u00FAmero D\u00EDas", "Precio DiarioN2Dias Reserva"
				}
		));
		tabla_Reservas.getColumnModel().getColumn(0).setPreferredWidth(86);
		scrollPane.setColumnHeaderView(tabla_Reservas);

		/*{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Resumen Mes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(12, 200, 842, 67);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);

			textFieldPrecioMedio = new JTextField();
			textFieldPrecioMedio.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPrecioMedio.setBounds(348, 30, 116, 22);
			panel_1.add(textFieldPrecioMedio);
			textFieldPrecioMedio.setColumns(10);

			textDiasMedia = new JTextField();
			textDiasMedia.setHorizontalAlignment(SwingConstants.CENTER);
			textDiasMedia.setBounds(537, 30, 116, 22);
			panel_1.add(textDiasMedia);
			textDiasMedia.setColumns(10);

			textTotalMes = new JTextField();
			textTotalMes.setHorizontalAlignment(SwingConstants.CENTER);
			textTotalMes.setBounds(714, 30, 116, 22);
			panel_1.add(textTotalMes);
			textTotalMes.setColumns(10);

			textNumAlquileres = new JTextField();
			textNumAlquileres.setHorizontalAlignment(SwingConstants.CENTER);
			textNumAlquileres.setBounds(175, 30, 116, 22);
			panel_1.add(textNumAlquileres);
			textNumAlquileres.setColumns(10);

			JLabel lblNumAlquileres = new JLabel("Número Alquileres");
			lblNumAlquileres.setBounds(182, 10, 120, 13);
			panel_1.add(lblNumAlquileres);

			JLabel lblPrecioMedio = new JLabel("Precio Medio");
			lblPrecioMedio.setBounds(369, 10, 100, 13);
			panel_1.add(lblPrecioMedio);

			JLabel lblMediaDias = new JLabel("Media Días Reserva");
			lblMediaDias.setBounds(529, 10, 124, 13);
			panel_1.add(lblMediaDias);

			JLabel lblTotalMes = new JLabel("Total Mes");
			lblTotalMes.setBounds(731, 10, 100, 13);
			panel_1.add(lblTotalMes);
		}*/

	


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

		ReservasMes(1);
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cargarTabla();
		}
	}
	private class AtrasButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF01Inicial();
		}
	}

	private class HistorialReservasActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF10HistorialReservas();
		}
	}
	
	
	private void cargarTabla() {
		for(int i=0; i<mesesAnho.length; i++){
			if(meses.getSelectedItem() == mesesAnho[i]){
				ReservasMes(i+1);
			}
		}
	}

	private void centrarTextoTabla(JTable table){
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0; i<5; i++) {
			tabla_Reservas.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
	}

	private void ReservasMes(int mes) {
		miModelo = new ModeloTablaReservas();
		tabla_Reservas = new JTable(miModelo);
		centrarTextoTabla(tabla_Reservas);
		miModelo.ListadoReservasMes(mes);
		scrollPane.setViewportView(tabla_Reservas);
	}

}
