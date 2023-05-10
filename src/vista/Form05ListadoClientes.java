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
import java.util.ArrayList;

public class Form05ListadoClientes extends JFrame {

	private final JPanel contentPanel = new JPanel();


	Controlador controlador = new Controlador();
	private JScrollPane scrollPane;
	

	private DefaultTableCellRenderer alinearCentro, alinearDerecha, alinearIzquierda;
	/**
	 * @wbp.nonvisual location=-49,244
	 */
	private final JTextField textField = new JTextField();
	private JTable tabla_Clientes;
	private JComboBox localidadesClientes;
	private ModeloTablaClientes miModelo;
	private ArrayList<String> direcciones = new ArrayList<String>();
	private ArrayList<String> localidades = new ArrayList<String>();
	private ArrayList<String> localidadesFinal = new ArrayList<String>();
	private String localidad;
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	
	public static void main(String[] args) {
		try {
			Form03ListadoReservas frame = new Form03ListadoReservas();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form05ListadoClientes() {
		textField.setColumns(10);
		setTitle("Listado Clientes Localidad");
		setExtendedState(JFrame.NORMAL);
		setBounds(100, 100, 884, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Localidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 842, 44);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		localidadesClientes = new JComboBox();
		localidadesClientes.setBounds(65, 13, 106, 20);
		panel.add(localidadesClientes);

		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 842, 178);
		contentPanel.add(scrollPane);
		
		tabla_Clientes = new JTable();
		scrollPane.setColumnHeaderView(tabla_Clientes);

	


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
		
		obtenerDirecciones();
		
		for(String lf: localidadesFinal) {
			localidadesClientes.addItem(lf);
		}
				
	}
	
	/*private class ComboMesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}*/
	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			localidad = String.valueOf(localidadesClientes.getSelectedItem());
			ListadoClientes(localidad);
		}
	}
	private class AtrasButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			controlador.mostrarF01Inicial();
		}
	}
	
	private void obtenerDirecciones() {
		controlador.obtenerDireccionesClientes(direcciones);
		int i=0;
		for (String dc: direcciones) {
			String [] arrayLocalidades = dc.split(",");
			String localidad = arrayLocalidades[arrayLocalidades.length-1];
			localidades.add(localidad);
		}

		for(String l:localidades){
			if(localidadesFinal.contains(l)) {
				continue;
			}else {
				localidadesFinal.add(l);
			}
		}
		
		
	}
	
	private void ListadoClientes(String localidad) {
		miModelo = new ModeloTablaClientes();
		tabla_Clientes = new JTable(miModelo);
		miModelo.ListadoClientes(localidad);
		scrollPane.setViewportView(tabla_Clientes);
	}
	

}
