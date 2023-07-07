package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import conexion.Conexion;
import modeloVo.*;
import validaciones.ConvertirFechas;

public class ReservaDao {

	public void insertarReserva (Reserva reserva, int codigo){
		boolean existe = false;
		Date fecInicio = new Date(reserva.getFecInicio().getTime());
		Date fecFinal = new Date(reserva.getFecFinal().getTime());
		java.sql.Date fecInicioSql = new java.sql.Date(fecInicio.getTime());
		java.sql.Date fecFinalSql = new java.sql.Date(fecFinal.getTime());

		Conexion conex= new Conexion();

		String comprobarCodigosBD = "SELECT * FROM reservas WHERE reCodigo = ?";
		String consulta = "INSERT INTO reservas VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conex.getConnection().prepareStatement(comprobarCodigosBD);
			ps.setInt(1, codigo);
			ResultSet resultSet=ps.executeQuery();
			if(resultSet.next()) {
				existe = true;
			} else {
				existe = false;
			}

			if(existe){
				JOptionPane.showMessageDialog(null, "El código de reserva introducido ya existe en la base de datos.","Información",JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			PreparedStatement ps1 = conex.getConnection().prepareStatement(consulta);
			ps1.setInt(1, codigo);
			ps1.setDate(2, fecInicioSql);
			ps1.setDate(3, fecFinalSql);
			int filas = ps1.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			ps1.close();
			conex.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//JOptionPane.showMessageDialog(null, "Error, no se han podido guardar los datos");
		}
	}


	public void eliminarReserva (int codigo){
		/*int res = JOptionPane.showOptionDialog(new JFrame(), "¿Estás seguro que desea cancelar la reserva?", "Options",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Sí, estoy seguro", "Volver atrás" }, JOptionPane.YES_OPTION);

		if (res == JOptionPane.YES_OPTION) {
			Conexion conex= new Conexion();*/
			try {
				Conexion conex= new Conexion();
				String consulta = "DELETE FROM reservas WHERE reCodigo= ?";
				PreparedStatement ps = null;
				ps = conex.getConnection().prepareStatement(consulta);
				ps.setInt(1, codigo);
				ps.executeUpdate();
				//JOptionPane.showMessageDialog(null, " La reserva se ha cancelado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
				ps.close();
				conex.desconectar();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error, no se pudo cancelar la reserva");
			}
		//}
	}


	public void moverAHistorial() throws SQLException {
		Conexion conex= new Conexion();

		String consulta = "INSERT INTO historialReservas "+
		"SELECT * FROM Reservas "+
		"WHERE reFecFinal < CURDATE()";

		String consulta2 = "INSERT INTO historialInvolucra "+
				"SELECT * FROM Involucra "+
				"WHERE inReserva IN "+
				"(SELECT reCodigo FROM Reservas "+
				"WHERE reFecFinal < CURDATE())";
		String consulta3 = "DELETE FROM Involucra WHERE inReserva IN(SELECT reCodigo FROM Reservas WHERE reFecFinal < CURDATE())";
		String consulta4 = "DELETE FROM Reservas WHERE reFecFinal < CURDATE()";

		PreparedStatement ps = null;
		ps = conex.getConnection().prepareStatement(consulta);
		PreparedStatement ps2 = null;
		ps2 = conex.getConnection().prepareStatement(consulta2);
		PreparedStatement ps3 = null;
		ps3 = conex.getConnection().prepareStatement(consulta3);
		PreparedStatement ps4 = null;
		ps4 = conex.getConnection().prepareStatement(consulta4);

		ps.executeUpdate();
		ps2.executeUpdate();
		ps3.executeUpdate();
		ps4.executeUpdate();

		ps.close();
		ps2.close();
		ps3.close();
		ps4.close();
		
		conex.desconectar();
	}

	public void eliminarReservasAntiguas() throws SQLException {
		Conexion conex= new Conexion();

		String consulta = "DELETE FROM historialInvolucra WHERE inReserva IN(SELECT reCodigo FROM historialReservas WHERE reFecFinal < DATE_SUB(NOW(), INTERVAL 5 YEAR))";
		String consulta2 = "DELETE FROM historialReservas WHERE reFecFinal < DATE_SUB(NOW(), INTERVAL 5 YEAR)";

		PreparedStatement ps = null;
		ps = conex.getConnection().prepareStatement(consulta);
		PreparedStatement ps2 = null;
		ps2 = conex.getConnection().prepareStatement(consulta2);

		ps.executeUpdate();
		ps2.executeUpdate();

		ps.close();
		ps2.close();
		conex.desconectar();
	}


	public boolean comprobarDisponibilidadVehiculo(String matricula, java.sql.Date fechaInicioDate, java.sql.Date fechaFinDate) {
		Conexion conexion = new Conexion();
		int contador=0;

		String consulta = "select reFecInicio, reFecFinal"
				+" from Reservas join Involucra on reCodigo = inReserva"
				+" where inMatricula = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			ps.setString(1, matricula);
			/*ps.setDate(2, fechaInicioDate);
			ps.setDate(3, fechaFinDate);*/

			rs = ps.executeQuery();

			while(rs.next()) {
				Date fecha1Date = rs.getDate("reFecInicio");
				Date fecha2Date = rs.getDate("reFecFinal");

				LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fechaFin = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fecha1 = fecha1Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fecha2 = fecha2Date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				if(fechaInicio.isAfter(fecha1)&&fechaInicio.isBefore(fecha2)){
					contador++;
				}
				if(fechaFin.isAfter(fecha1)&&fechaFin.isBefore(fecha2)){
					contador++;
				}
				if(fechaInicio.equals(fecha1)||fechaInicio.equals(fecha2)){
					contador++;
				}
				if(fechaFin.equals(fecha1)||fechaFin.equals(fecha2)){
					contador++;
				}
			}

			/*if(contador==0){
				return true;
			}else{
				return false;
			}*/
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		if(contador==0){
			return true;
		}else{
			return false;
		}
	}
	
	public ArrayList<FilaReserva> ReservasEnero() {
		Conexion conexion = new Conexion();
				
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=1";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasFebrero() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=2";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasMarzo() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=3";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasAbril() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=4";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasMayo() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=5";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasJunio() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=6";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasJulio() {
		Conexion conexion = new Conexion();
	
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=7";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasAgosto() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=8";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasSeptiembre() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=9";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasOctubre() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=10";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasNoviembre() {
		Conexion conexion = new Conexion();
		
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=11";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}
	
	
	public ArrayList<FilaReserva> ReservasDiciembre() {
		Conexion conexion = new Conexion();
	
		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				 +" from Involucra join Clientes on inCliente = clNif"
				 +" join Reservas on inReserva = reCodigo"
				 +" join Coches on inMatricula = coMatricula"
				 +" where month(reFecInicio)=12";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setInt(1, departamento.getCodDepar());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				FilaReserva fila = new FilaReserva();
				fila.setNombreCliente(rs.getString(1));
				fila.setMatriculaCoche(rs.getString(2));
				fila.setPrecio(rs.getInt(3));
				fila.setDias(rs.getInt(4));
				fila.setImporte(rs.getInt(5));
				
				
				
				reserva.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		
		return reserva;
	}


	/*public Double SumaAlquileres() {
		Conexion conexion = new Conexion();

		String consulta = "select sum(coPrecio*)";

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList <FilaCliente> cliente = new ArrayList<FilaCliente>();
		String str = localidad;

		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			//ps.setString(1, "%" + str);
			ps.setString(1, "%" + localidad);

			rs = ps.executeQuery();

			while(rs.next()) {
				FilaCliente fila = new FilaCliente();
				fila.setNifCliente(rs.getString(1));
				fila.setNombreCliente(rs.getString(2));
				fila.setDireccionCliente(rs.getString(3));
				fila.setTelefonoCliente(rs.getInt(4));

				cliente.add(fila);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();

		return cliente;
	}*/
}
