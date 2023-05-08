package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.*;

import conexion.Conexion;
import modeloVo.Cliente;
import modeloVo.FilaReserva;
import modeloVo.Involucra;
import modeloVo.Reserva;
import validaciones.ConvertirFechas;

public class ReservaDao {

	public void insertarReserva (Reserva reserva, int codigo){
		boolean existe = false;
		Date fecInicio = new Date(reserva.getFecInicio().getTime());
		Date fecFinal = new Date(reserva.getFecFinal().getTime());
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
			ps1.setDate(2, fecInicio);
			ps1.setDate(3, fecFinal);
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
}
