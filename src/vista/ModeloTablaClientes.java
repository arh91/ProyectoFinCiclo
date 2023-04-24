package vista;

import controlador.Controlador;
import modeloVo.FilaCliente;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ModeloTablaClientes extends AbstractTableModel{
	ArrayList <FilaCliente> filaCliente = null;
	ArrayList <String> nombresColumnas = null;
	 Controlador controlador = new Controlador();
	
	

	public ModeloTablaClientes() {
		nombresColumnas = new ArrayList <String>();
		
		nombresColumnas.add("NIF");
		nombresColumnas.add("Nombre");
		nombresColumnas.add("Direccion");
		nombresColumnas.add("Telefono");
		
		filaCliente = new ArrayList <FilaCliente>();
	}

	public int getColumnCount() {		
		return nombresColumnas.size();
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return filaCliente.size();
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
			return nf.format(fc.getTelefonoCliente());
		}
		return fc;
	}

	public void ListadoClientes(String localidad) {
		controlador.cargarClientesPorLocalidad(localidad);
	}
	
	/*public void ListadoReservasEnero() {
		cliente = controlador.ReservasEnero();		
	}
	
	public void ListadoReservasFebrero() {
		cliente = controlador.ReservasFebrero();		
	}
	
	public void ListadoReservasMarzo() {
		cliente = controlador.ReservasMarzo();		
	}
	
	public void ListadoReservasAbril() {
		cliente = controlador.ReservasAbril();		
	}
	
	public void ListadoReservasMayo() {
		cliente = controlador.ReservasMayo();		
	}
	
	public void ListadoReservasJunio() {
		cliente = controlador.ReservasJunio();		
	}
	
	public void ListadoReservasJulio() {
		cliente = controlador.ReservasJulio();		
	}
	
	public void ListadoReservasAgosto() {
		cliente = controlador.ReservasAgosto();		
	}
	
	public void ListadoReservasSeptiembre() {
		cliente = controlador.ReservasSeptiembre();		
	}
	
	public void ListadoReservasOctubre() {
		cliente = controlador.ReservasOctubre();		
	}
	
	public void ListadoReservasNoviembre() {
		cliente = controlador.ReservasNoviembre();		
	}
	
	public void ListadoReservasDiciembre() {
		cliente = controlador.ReservasDiciembre();		
	}*/
}
