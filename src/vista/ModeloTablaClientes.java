package vista;

import controlador.Controlador;
import modeloVo.FilaCliente;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ModeloTablaClientes extends AbstractTableModel{
	ArrayList <FilaCliente> filaCliente = null;
	private String[] nombresColumnas = {"NIF", "Nombre", "Direccion", "Teléfono"};
	 Controlador controlador = new Controlador();
	
	

	public ModeloTablaClientes() {
		filaCliente = new ArrayList <FilaCliente>();
	}

	public int getColumnCount() {		
		return nombresColumnas.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return filaCliente.size();
	}

	public String getColumnName(int index){
		return nombresColumnas[index];
	}

	public Object getValueAt(int fila, int columna) {
		FilaCliente fc = filaCliente.get(fila);
		
		//poner formato a los numeros
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
		
		switch (columna) {
		case 0:
			return fc.getNifCliente();
		case 1:
			return fc.getNombreCliente();
		case 2:
			return fc.getDireccionCliente();
		case 3:
			return fc.getTelefonoCliente();
		}
		return fc;
	}

	public void ListadoClientes(String localidad) {
		filaCliente = controlador.cargarClientesPorLocalidad(localidad);
	}

}
