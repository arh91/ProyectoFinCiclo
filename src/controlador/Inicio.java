package controlador;

import vista.*;

import java.sql.SQLException;

public class Inicio {

	String dniCliente, nombreCliente, primerApellido, calle, numero, localidad, telefono;

	private void iniciar() throws SQLException {
		Form01Inicial inicio = new Form01Inicial();
		Form02NuevaReserva nuevaReserva = new Form02NuevaReserva();
		Form03ListadoReservas listaReservas = new Form03ListadoReservas();
		Form04Cliente cliente = new Form04Cliente();
		Form05ListadoClientes listaClientes = new Form05ListadoClientes();
		Form06MasOpcionesCliente opcionesCliente = new Form06MasOpcionesCliente();
		Form07ModificarCliente modificarCliente = new Form07ModificarCliente(dniCliente, nombreCliente, primerApellido, calle, numero, localidad, telefono);
		Form10HistorialReservas historialReservas = new Form10HistorialReservas();
		Form11ModificarReserva modificarReserva = new Form11ModificarReserva();
		Controlador controlador = new Controlador();
		
		inicio.setControlador(controlador);
		nuevaReserva.setControlador(controlador);
		listaReservas.setControlador(controlador);
		cliente.setControlador(controlador);
		listaClientes.setControlador(controlador);
		opcionesCliente.setControlador(controlador);
		modificarCliente.setControlador(controlador);
		historialReservas.setControlador(controlador);
		modificarReserva.setControlador(controlador);
		
		controlador.setInicio(inicio);
		controlador.setNuevaReserva(nuevaReserva);
		controlador.setListadoReservas(listaReservas);
		controlador.setCliente(cliente);
		controlador.setListadoClientes(listaClientes);
		controlador.setMasOpcionesCliente(opcionesCliente);
		controlador.setModificarCliente(modificarCliente);
		controlador.setHistorialReservas(historialReservas);
		controlador.setModificarReserva(modificarReserva);

		controlador.mostrarF01Inicial();
	}
	
	
	public static void main(String[] args) throws SQLException {
		Inicio principal = new Inicio();
		principal.iniciar();
	}
	
}
