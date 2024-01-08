package vista;

import controlador.Controlador;
import modeloVo.FilaReserva;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ModeloTablaReservas extends AbstractTableModel{

	ArrayList <FilaReserva> filaReserva = new ArrayList <FilaReserva>();
	private String[] nombresColumnas = {"Cliente", "Matrícula", "Precio", "Días", "Importe"};
	 Controlador controlador = new Controlador();

	public ModeloTablaReservas() {
		filaReserva = new ArrayList <FilaReserva>();
	}

	public int getColumnCount() {
		return nombresColumnas.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return filaReserva.size();
	}

	public String getColumnName(int index){
		return nombresColumnas[index];
	}
	
	

	public Object getValueAt(int fila, int columna) {
		FilaReserva reserva = filaReserva.get(fila);
		
		//poner formato a los numeros
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
		
		switch (columna) {
		case 0:
			return reserva.getNombreCliente();
		case 1:
			return reserva.getMatriculaCoche();
		case 2:
			return nf.format(reserva.getPrecio());
		case 3:
			return reserva.getDias();
		case 4:
			return nf.format(reserva.getImporte());
		}
		return reserva;
	}

	public void ListadoReservasMes(int mes) {
		filaReserva = controlador.ReservasMes(mes);
	}
	
	public void HistorialReservasMes(int mes, int anho) {
		filaReserva = controlador.HistorialReservasMes(mes, anho);
	}

}
