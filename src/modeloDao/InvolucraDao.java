package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modeloVo.Cliente;
import modeloVo.Involucra;

public class InvolucraDao {
	
	public void insertarInvolucra (Involucra involucra){
		Conexion conex= new Conexion();
		try {
			String consulta = "INSERT INTO clientes VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setString(2, involucra.getMatricula());
			ps.setString(3, involucra.getCliente());
			ps.setInt(4, involucra.getReserva());
			ps.setInt(5,  involucra.getLitros());
			int filas = ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			ps.close();
			conex.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error, no se han podido guardar los datos");
		}
	}
}
