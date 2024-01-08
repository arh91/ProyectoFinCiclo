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
		boolean existeHistorial = false;
		LocalDate fechaActual = LocalDate.now();
		Date fecInicio = new Date(reserva.getFecInicio().getTime());
		Date fecFinal = new Date(reserva.getFecFinal().getTime());
		java.sql.Date fecInicioSql = new java.sql.Date(fecInicio.getTime());
		java.sql.Date fecFinalSql = new java.sql.Date(fecFinal.getTime());

		Conexion conex= new Conexion();

		String comprobarCodigosBD = "SELECT * FROM reservas WHERE reCodigo = ?";
		String comprobarCodigosHistorial = "SELECT * FROM historialReservas WHERE reCodigo = ?";
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

			PreparedStatement ps2 = conex.getConnection().prepareStatement(comprobarCodigosHistorial);
			ps2.setInt(1, codigo);
			ResultSet rs2=ps2.executeQuery();
			if(rs2.next()) {
				existeHistorial = true;
			} else {
				existeHistorial = false;
			}

			if(existe || existeHistorial){
				JOptionPane.showMessageDialog(null, "El código de reserva introducido ya existe en la base de datos.","Información",JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			PreparedStatement ps1 = conex.getConnection().prepareStatement(consulta);
			ps1.setInt(1, codigo);
			ps1.setDate(2, fecInicioSql);
			ps1.setDate(3, fecFinalSql);
			int filas = ps1.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			ps1.close();
			conex.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error, no se han podido guardar los datos");
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
	}

	public void buscarReserva (Reserva reserva, int codigo) {
		Conexion conex= new Conexion();
		boolean existe=false;
		try {
			String consulta = "SELECT * FROM reservas where reCodigo = ? ";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setInt(1, codigo);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				existe=true;
				reserva.setCodigo(res.getInt("reCodigo"));
				reserva.setFecInicio(res.getDate("reFecInicio"));
				reserva.setFecFinal(res.getDate("reFecFinal"));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se han podido obtener los datos");
			System.out.println(e);
		}
		if (!existe) {
			JOptionPane.showMessageDialog(null, "No existe ninguna reserva con éste código en nuestra base de datos.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}


	public void modificarReserva (Reserva reserva, int codigo) {
		Conexion conex= new Conexion();
		Date fecInicio = new Date(reserva.getFecInicio().getTime());
		Date fecFinal = new Date(reserva.getFecFinal().getTime());
		java.sql.Date fecInicioSql = new java.sql.Date(fecInicio.getTime());
		java.sql.Date fecFinalSql = new java.sql.Date(fecFinal.getTime());

		try{
			String consulta="UPDATE reservas SET reCodigo = ? ,reFecInicio = ? , reFecFinal = ? WHERE reCodigo = ? ";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setInt(1, reserva.getCodigo());
			ps.setDate(2, fecInicioSql);
			ps.setDate(3, fecFinalSql);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, " Los datos de la reserva se han modificado correctamente ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Error al modificar los datos","Error",JOptionPane.ERROR_MESSAGE);
		}
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

			rs = ps.executeQuery();

			while(rs.next()) {
				Date fecha1Date = rs.getDate("reFecInicio");
				Date fecha2Date = rs.getDate("reFecFinal");
				java.sql.Date fecha1SqlDate = new java.sql.Date(fecha1Date.getTime());
				java.sql.Date fecha2SqlDate = new java.sql.Date(fecha2Date.getTime());

				LocalDate fechaInicio = fechaInicioDate.toLocalDate();
				LocalDate fechaFin = fechaFinDate.toLocalDate();
				LocalDate fecha1 = fecha1SqlDate.toLocalDate();
				LocalDate fecha2 = fecha2SqlDate.toLocalDate();

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


	public ArrayList<FilaReserva> ReservasMes(int mes) {
		Conexion conexion = new Conexion();

		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				+" from Involucra join Clientes on inCliente = clNif"
				+" join Reservas on inReserva = reCodigo"
				+" join Coches on inMatricula = coMatricula"
				+" where month(reFecInicio)=?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();

		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			ps.setInt(1, mes);
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
	
	
	public ArrayList<FilaReserva> HistorialReservasMes(int mes, int anho) {
		Conexion conexion = new Conexion();

		String consulta = "select clNombre, inMatricula, coPrecio, DateDiff(reFecFinal, reFecInicio), coPrecio*DateDiff(reFecFinal, reFecInicio)"
				+" from historialInvolucra join Clientes on inCliente = clNif"
				+" join historialReservas on inReserva = reCodigo"
				+" join Coches on inMatricula = coMatricula"
				+" where month(reFecInicio)=? and year(reFecInicio)=?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList <FilaReserva> reserva = new ArrayList<FilaReserva>();

		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			ps.setInt(1, mes);
			ps.setInt(2,  anho);
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
