package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import conexion.Conexion;
import modeloVo.Cliente;
import modeloVo.Involucra;
import modeloVo.Reserva;

public class InvolucraDao {
	
	public void insertarInvolucra (Involucra involucra, int codigoReserva){
		boolean existe = false;
		Conexion conex= new Conexion();

		String comprobarCodigosBD = "SELECT * FROM involucra WHERE inReserva = ?";
		String consulta = "INSERT INTO clientes VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conex.getConnection().prepareStatement(comprobarCodigosBD);
			ps.setInt(1, codigoReserva);
			ResultSet resultSet=ps.executeQuery();
			if(resultSet.next()) {
				existe = true;
			} else {
				existe = false;
			}

			if(existe){
				return;
			}


			PreparedStatement ps1 = conex.getConnection().prepareStatement(consulta);
			ps1.setString(2, involucra.getMatricula());
			ps1.setString(3, involucra.getCliente());
			ps1.setInt(4, involucra.getReserva());
			ps1.setInt(5,  involucra.getLitros());
			int filas = ps1.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			ps1.close();
			conex.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error, no se han podido guardar los datos");
		}
	}


	public void eliminarInvolucra (String codigo) throws SQLException {
		/*int res = JOptionPane.showOptionDialog(new JFrame(), "¿Estás seguro que quieres eliminar éste cliente?", "Options",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Continuar", "Cancelar" }, JOptionPane.YES_OPTION);

		if (res == JOptionPane.YES_OPTION) {
			Conexion conex= new Conexion();
			try {*/
		Conexion conex= new Conexion();
		String consulta = "DELETE FROM involucra WHERE inMatricula= ?";
		PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
		ps.setString(1, codigo);
		ps.executeUpdate();
		ps.close();
		conex.desconectar();
			/*} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error, no se pudo eliminar el cliente");
			}*/
	}


	public void obtenerCodigoReserva(Involucra involucra, String matricula){
		Conexion conex= new Conexion();
		try {
			String consulta = "SELECT inReserva FROM reservas WHERE inMatricula= ?";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				involucra.setReserva(res.getInt("inReserva"));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se han podido obtener los datos");
			System.out.println(e);
		}
	}
}
