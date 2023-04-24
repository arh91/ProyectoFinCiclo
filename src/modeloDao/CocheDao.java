package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modeloVo.Cliente;
import modeloVo.Coche;


public class CocheDao {

	public ArrayList<Coche> cargarCoches() {
		Conexion conexion = new Conexion();
		
		String consulta = "SELECT * FROM Coches Order BY coMarca, coMatricula";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList <Coche> coches = new ArrayList<Coche>();
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Coche coch = new Coche();
				coch.setMatricula(rs.getString(1));
				coch.setMarca(rs.getString(2));
				coch.setModelo(rs.getString(3));
				coch.setColor(rs.getString(4));
				coch.setPrecio(rs.getInt(5));
				coch.setDisponible(rs.getBoolean(6));
				coch.setGaraje(rs.getInt(7));
				
				coches.add(coch);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		return coches;
	}
	
	
	public void preguntarDisponibilidadCoche(String codigo, Coche coche) {
		String valor;
		Conexion conex = new Conexion();
		boolean existe = false;
		try {
			String consulta = "SELECT coDisponible FROM coches WHERE coMatricula = ?";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				existe=true;
				coche.setDisponible(res.getBoolean(1));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public void reservarCoche(Date fecInicio, Date fecFin, String cadena) {
		LocalDate fechaActual = LocalDate.now();
		String fecActual = String.valueOf(fechaActual);
		String[] arrFechaActual = fecActual.split("-");
		ArrayList<String> fechaOrdenada = new ArrayList<String>();
		String elemento;
		for(int i=arrFechaActual.length-1;i>=0;i--) {
			elemento = arrFechaActual[i];
			fechaOrdenada.add(elemento);
		}
		
		Conexion conexion = new Conexion();
		
		String consulta = "UPDATE coches SET coDisponible= "
				+ "(CASE "
				+ "WHEN "+fechaActual+" BETWEEN "+fecInicio+" AND "+fecFin+" THEN 0"
				+ " ELSE 1"
				+ " END)"
				+ " WHERE coMatricula = "+cadena+"";
		PreparedStatement ps = null;
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
