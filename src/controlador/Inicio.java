package controlador;

import vista.Form01Inicial;
import vista.Form02NuevaReserva;
import vista.Form03ListadoReservas;
import vista.Form04Cliente;
import vista.Form05ListadoClientes;
import vista.Form06MasOpcionesCliente;

public class Inicio {

	private void iniciar() {
		Form01Inicial inicio = new Form01Inicial();
		Form02NuevaReserva nuevaReserva = new Form02NuevaReserva();
		Form03ListadoReservas listaReservas = new Form03ListadoReservas();
		Form04Cliente cliente = new Form04Cliente();
		Form05ListadoClientes listaClientes = new Form05ListadoClientes();
		Form06MasOpcionesCliente opcionesCliente = new Form06MasOpcionesCliente();
		Controlador controlador = new Controlador();
		
		inicio.setControlador(controlador);
		nuevaReserva.setControlador(controlador);
		listaReservas.setControlador(controlador);
		cliente.setControlador(controlador);
		listaClientes.setControlador(controlador);
		opcionesCliente.setControlador(controlador);
		
		controlador.setInicio(inicio);
		controlador.setNuevaReserva(nuevaReserva);
		controlador.setListadoReservas(listaReservas);
		controlador.setCliente(cliente);
		controlador.setListadoClientes(listaClientes);
		controlador.setMasOpcionesCliente(opcionesCliente);
		
		controlador.mostrarF01Inicial();
	}
	
	
	public static void main(String[] args) {
		Inicio principal = new Inicio();
		principal.iniciar();
	}
	
}
