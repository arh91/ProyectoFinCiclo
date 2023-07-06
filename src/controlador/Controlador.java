package controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modeloDao.ClienteDao;
import modeloDao.CocheDao;
import modeloDao.InvolucraDao;
import modeloDao.ReservaDao;
import modeloVo.Cliente;
import modeloVo.Coche;
import modeloVo.FilaCliente;
import modeloVo.FilaReserva;
import modeloVo.Involucra;
import modeloVo.Reserva;

import vista.*;

public class Controlador {
	
	Form01Inicial inicio;
	Form02NuevaReserva nuevaReserva;
	Form03ListadoReservas listaReservas;
	Form04Cliente clientes;
	Form05ListadoClientes listaClientes;
	Form06MasOpcionesCliente masOpciones;
	Form10HistorialReservas historialReservas;
	
	ClienteDao clienteDao = new ClienteDao();
	CocheDao cocheDao = new CocheDao();
	ReservaDao reservaDao = new ReservaDao();
	InvolucraDao involucraDao = new InvolucraDao();
	
	
	public void setInicio (Form01Inicial inicio) {
		this.inicio = inicio;
	}
	
	public void setNuevaReserva (Form02NuevaReserva nuevaReserva) {
		this.nuevaReserva = nuevaReserva;
	}
	
	public void setListadoReservas (Form03ListadoReservas listaReservas) {
		this.listaReservas = listaReservas;
	}
	
	public void setCliente (Form04Cliente clientes) {
		this.clientes = clientes;
	}
	
	public void setListadoClientes (Form05ListadoClientes listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public void setMasOpcionesCliente(Form06MasOpcionesCliente masOpciones) {
		this.masOpciones = masOpciones;
	}

	public void setHistorialReservas(Form10HistorialReservas historialReservas) { this.historialReservas = historialReservas;}
	
	public void setClienteDao (ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	public void setCocheDao (CocheDao cocheDao) {
		this.cocheDao = cocheDao;
	}
	
	public void setReservaDao (ReservaDao reservaDao) {
		this.reservaDao = reservaDao;
	}
	
	public void setInvolucraDao(InvolucraDao involucraDao) {
		this.involucraDao = involucraDao;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	public void mostrarF01Inicial() {
		inicio.setVisible(true);
	}
	
	public void mostrarF02NuevaReserva() {
		nuevaReserva.setVisible(true);
	}
	
	public void mostrarF03ListadoReservas() {
		listaReservas.setVisible(true);
	}
	
	public void mostrarF04Clientes() {
		clientes.setVisible(true);
	}
	
	public void mostrarF05ListadoClientes() {
		listaClientes.setVisible(true);
	}
	
	public void mostrarF06MasOpcionesCliente() {
		masOpciones.setVisible(true);
	}

	public void mostrarF10HistorialReservas() { historialReservas.setVisible(true); }


	
	///////////////////////////////////////////////////////////////////////

	public void insertarCliente(Cliente cliente, String codigo) {
		clienteDao.insertarCliente(cliente, codigo);
	}
	
	public void buscarCliente(Cliente cliente, String codigo) {
		clienteDao.buscarCliente(cliente, codigo);
	}
	
	public void eliminarCliente(String codigo) {
		clienteDao.eliminarCliente(codigo);
	}
	
	public void modificarCliente(Cliente cliente, String codigo) {
		clienteDao.modificarCliente(cliente, codigo);
	}
	
	public void obtenerDireccionesClientes(ArrayList<String> list) {
		clienteDao.obtenerDireccionesClientes(list);
	}
	
	public void insertarReserva(Reserva reserva, int codigo) {
		reservaDao.insertarReserva(reserva, codigo);
	}

	public void eliminarReserva(int codigo){
		reservaDao.eliminarReserva(codigo);
	}

	public void eliminarReservasAntiguas() throws SQLException { reservaDao.eliminarReservasAntiguas();}

	public void moverAHistorial() throws SQLException { reservaDao.moverAHistorial();}

	public boolean existeMatriculaCoche(String codigo){
		return cocheDao.existeMatriculaCoche(codigo);
	}

	public void reservarCoche(Date fecInicio, Date fecFin, String cadena) {
		cocheDao.reservarCoche(fecInicio, fecFin, cadena);
	}
	public void busquedaReservas(String matricula){
		reservaDao.busquedaReservas(matricula);
	}

	public void liberarCoche(String cadena){
		cocheDao.liberarCoche(cadena);
	}
	
	/*public void preguntarDisponibilidadCoche(String codigo, Coche coche) {
		cocheDao.preguntarDisponibilidadCoche(codigo, coche);
	}*/

	public boolean existeCliente(String codigo){
		return involucraDao.existeCliente(codigo);
	}

	public boolean existeCoche(String codigo){
		return involucraDao.existeCoche(codigo);
	}

	public void insertarInvolucra(Involucra involucra, int codigoReserva) {
		involucraDao.insertarInvolucra(involucra, codigoReserva);
	}

	public void eliminarInvolucra(int codigo) throws SQLException {
		involucraDao.eliminarInvolucra(codigo);
	}

	public void obtenerCodigoReserva(Involucra involucra, String matricula){
		involucraDao.obtenerCodigoReserva(involucra, matricula);
	}
	///////////////////////////////////////////////////////////////////////
	
	
	public ArrayList<FilaCliente> cargarClientesPorLocalidad(String localidad){
		return clienteDao.cargarClientesPorLocalidad(localidad);
	}
	
	public ArrayList<FilaReserva> ReservasEnero() {
		return reservaDao.ReservasEnero();		
	}
	
	public ArrayList<FilaReserva> ReservasFebrero() {
		return reservaDao.ReservasFebrero();		
	}
	
	public ArrayList<FilaReserva> ReservasMarzo() {
		return reservaDao.ReservasMarzo();		
	}
	
	public ArrayList<FilaReserva> ReservasAbril() {
		return reservaDao.ReservasAbril();		
	}
	
	public ArrayList<FilaReserva> ReservasMayo() {
		return reservaDao.ReservasMayo();		
	}
	
	public ArrayList<FilaReserva> ReservasJunio() {
		return reservaDao.ReservasJunio();		
	}
	
	public ArrayList<FilaReserva> ReservasJulio() {
		return reservaDao.ReservasJulio();		
	}
	
	public ArrayList<FilaReserva> ReservasAgosto() {
		return reservaDao.ReservasAgosto();		
	}
	
	public ArrayList<FilaReserva> ReservasSeptiembre() {
		return reservaDao.ReservasSeptiembre();		
	}
	
	public ArrayList<FilaReserva> ReservasOctubre() {
		return reservaDao.ReservasOctubre();		
	}
	
	public ArrayList<FilaReserva> ReservasNoviembre() {
		return reservaDao.ReservasNoviembre();		
	}
	
	public ArrayList<FilaReserva> ReservasDiciembre() {
		return reservaDao.ReservasDiciembre();		
	}
	
	public ArrayList<Cliente> cargarClientes(){
		return clienteDao.cargarClientes();
	}
	
	public ArrayList<Coche> cargarCoches(){
		return cocheDao.cargarCoches();
	}
	
	
}
