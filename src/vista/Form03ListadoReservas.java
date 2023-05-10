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

public class
Form03ListadoReservas extends JFrame {

	private final JPanel contentPanel = new JPanel();


	Controlador controlador;
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
	private JTable table_1;
	private JComboBox meses;
	private String[] mesesAnho = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	private ModeloTablaReservas miModelo;
	
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
		panel.setBounds(12, 13, 842, 44);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		meses = new JComboBox();
		meses.setBounds(65, 13, 106, 20);
		for(int i=0; i<mesesAnho.length; i++) {
			meses.addItem(mesesAnho[i]);
		}
		panel.add(meses);

		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 842, 179);
		contentPanel.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setColumnHeaderView(table_1);
		
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
				
	}
	
	private class ComboMesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
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
	
	
	private void cargarTabla() {
		if (meses.getSelectedItem() == "Enero") {
			ReservasEnero();
		}else if(meses.getSelectedItem() == "Febrero") {
			ReservasFebrero();
		}else if(meses.getSelectedItem() == "Marzo") {
			ReservasMarzo();
		}else if(meses.getSelectedItem() == "Abril") {
			ReservasAbril();
		}else if(meses.getSelectedItem() == "Mayo") {
			ReservasMayo();
		}else if(meses.getSelectedItem() == "Junio") {
			ReservasJunio();
		}else if(meses.getSelectedItem() == "Julio") {
			ReservasJulio();
		}else if(meses.getSelectedItem() == "Agosto") {
			ReservasAgosto();
		}else if(meses.getSelectedItem() == "Septiembre") {
			ReservasSeptiembre();
		}else if(meses.getSelectedItem() == "Octubre") {
			ReservasOctubre();
		}else if(meses.getSelectedItem() == "Noviembre") {
			ReservasNoviembre();
		}else {
			ReservasDiciembre();
		}
	}
	
	private void ReservasEnero() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasEnero();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasFebrero() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasFebrero();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasMarzo() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasMarzo();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasAbril() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasAbril();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasMayo() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasMayo();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasJunio() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasJunio();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasJulio() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasJulio();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasAgosto() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasAgosto();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasSeptiembre() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasSeptiembre();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasOctubre() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasOctubre();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasNoviembre() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasNoviembre();	
		scrollPane.setViewportView(table_1);				
	}
	
	private void ReservasDiciembre() {				
		miModelo = new ModeloTablaReservas();				
		table_1 = new JTable(miModelo);					
		miModelo.ListadoReservasDiciembre();	
		scrollPane.setViewportView(table_1);				
	}
}
