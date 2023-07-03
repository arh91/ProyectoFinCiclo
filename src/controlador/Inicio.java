package controlador;

import vista.*;

import java.sql.SQLException;

public class Inicio {

	private void iniciar() throws SQLException {
		Form01Inicial inicio = new Form01Inicial();
		Form02NuevaReserva nuevaReserva = new Form02NuevaReserva();
		Form03ListadoReservas listaReservas = new Form03ListadoReservas();
		Form04Cliente cliente = new Form04Cliente();
		Form05ListadoClientes listaClientes = new Form05ListadoClientes();
		Form06MasOpcionesCliente opcionesCliente = new Form06MasOpcionesCliente();
		Form10HistorialReservas historialReservas = new Form10HistorialReservas();
		Controlador controlador = new Controlador();
		
		inicio.setControlador(controlador);
		nuevaReserva.setControlador(controlador);
		listaReservas.setControlador(controlador);
		cliente.setControlador(controlador);
		listaClientes.setControlador(controlador);
		opcionesCliente.setControlador(controlador);
		historialReservas.setControlador(controlador);
		
		controlador.setInicio(inicio);
		controlador.setNuevaReserva(nuevaReserva);
		controlador.setListadoReservas(listaReservas);
		controlador.setCliente(cliente);
		controlador.setListadoClientes(listaClientes);
		controlador.setMasOpcionesCliente(opcionesCliente);
		controlador.setHistorialReservas(historialReservas);
		
		controlador.mostrarF01Inicial();
	}
	
	
	public static void main(String[] args) throws SQLException {
		Inicio principal = new Inicio();
		principal.iniciar();
	}
	
}
