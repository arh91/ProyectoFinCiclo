package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import conexion.Conexion;
import modeloVo.Cliente;
import modeloVo.FilaCliente;
import modeloVo.FilaReserva;


public class ClienteDao {

	public void insertarCliente (Cliente cliente, String codigo){
		boolean existe = false;
		/*comprobarCodigosDatabase(codigo, existe);
		System.out.println(existe);
		if (existe){
			JOptionPane.showMessageDialog(null, "El dni introducido ya existe en la base de datos.","Información",JOptionPane.INFORMATION_MESSAGE);
			return;
		}*/
		Conexion conex= new Conexion();
		String comprobarCodigosBD = "SELECT * FROM clientes WHERE clNif = ?";

		String consulta = "INSERT INTO clientes VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement ps = conex.getConnection().prepareStatement(comprobarCodigosBD);
			ps.setString(1, codigo);
			ResultSet resultSet=ps.executeQuery();
			if(resultSet.next()) {
				existe = true;
			} else {
				existe = false;
			}

			if(existe){
				JOptionPane.showMessageDialog(null, "El dni introducido ya existe en la base de datos.","Información",JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			PreparedStatement ps1 = conex.getConnection().prepareStatement(consulta);
			ps1.setString(1,cliente.getNif());
			ps1.setString(2, cliente.getNombre());
			ps1.setString(3, cliente.getDireccion());
			ps1.setInt(4, cliente.getTelefono());
			int filas = ps1.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han guardado los datos correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
			ps1.close();
			conex.desconectar();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error, no se han podido guardar los datos");
		}
	}


	public void comprobarCodigosDatabase(String codigo, boolean existe){
		//String email = request.getParameter("email"); // Get email from a form
		Conexion conex= new Conexion();

		String query = "SELECT * FROM clientes WHERE clNif = ?"; // Select all users belongs to the provided email

		try {
			PreparedStatement statement; // Using a preparedStatement to prevent SQL-Injection
			ResultSet resultSet;
			statement = conex.getConnection().prepareStatement(query);
			statement.setString(1, codigo);
			resultSet = statement.executeQuery();

			if(resultSet.next()) {
				existe = true;
			} else {
				existe = false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/*public Cliente buscarPersona(int codigo) {
		Conexion conex= new Conexion();
		Cliente cliente= new Cliente();
		boolean existe=false;
		try {
			String consulta = "SELECT * FROM personas where id = ? ";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setInt(1, codigo);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				existe=true;
				cliente.setNif(res.getString("clNif"));
				cliente.setNombre(res.getString("clNombre"));
				cliente.setDireccion(res.getString("clDireccion"));
				cliente.setTelefono(Integer.parseInt(res.getString("clTelefono")));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se han podido obtener los datos");
			System.out.println(e);
		}
		if (existe) {
			return cliente;
		}
		else return null;
	}*/
	
	
	public void buscarCliente (Cliente cliente, String codigo) {
		Conexion conex= new Conexion();
		//Cliente cliente= new Cliente();
		boolean existe=false;
		try {
			String consulta = "SELECT * FROM clientes where clNif = ? ";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setString(1, codigo);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				existe=true;
				cliente.setNif(res.getString("clNif"));
				cliente.setNombre(res.getString("clNombre"));
				cliente.setDireccion(res.getString("clDireccion"));
				cliente.setTelefono(Integer.parseInt(res.getString("clTelefono")));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se han podido obtener los datos");
			System.out.println(e);
		}
		if (!existe) {
			JOptionPane.showMessageDialog(null, "No existe ningún cliente con éste código en nuestra base de datos.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void modificarCliente (Cliente cliente, String codigo) {
		Conexion conex= new Conexion();
		try{
			String consulta="UPDATE clientes SET clNif = ? ,clNombre = ? , clDireccion = ?, clTelefono = ? WHERE clNif = ? ";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ps.setString(1, cliente.getNif());
			ps.setString(2, cliente.getNombre());
			ps.setString(3, cliente.getDireccion());
			ps.setInt(4, cliente.getTelefono());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, " Los datos del cliente se han modificado correctamente ","Confirmación",JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException e){
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Error al modificar los datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public void eliminarCliente (String codigo){
		int res = JOptionPane.showOptionDialog(new JFrame(), "¿Estás seguro que quieres eliminar éste cliente?", "Options",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Continuar", "Cancelar" }, JOptionPane.YES_OPTION);
		
		if (res == JOptionPane.YES_OPTION) {			
			Conexion conex= new Conexion();
			try {
				String consulta = "DELETE FROM clientes WHERE clNif= ?";
				PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
				ps.setString(1, codigo);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, " El cliente se ha eliminado Correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
				ps.close();
				conex.desconectar();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Error, no se pudo eliminar el cliente");
			}
		}
	}
	
	
	public void obtenerDireccionesClientes (ArrayList<String> list) {
		Conexion conex= new Conexion();
		try {
			String consulta = "SELECT clDireccion FROM clientes";
			PreparedStatement ps = conex.getConnection().prepareStatement(consulta);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				list.add(res.getString("clDireccion"));
			}
			res.close();
			conex.desconectar();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se han podido obtener los datos");
			System.out.println(e);
		}
	}
	
	
	public ArrayList<Cliente> cargarClientes() {
		Conexion conexion = new Conexion();
		
		String consulta = "SELECT * FROM Clientes Order BY clNombre";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList <Cliente> clientes = new ArrayList<Cliente>();
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Cliente cli = new Cliente();
				cli.setNif(rs.getString(1));
				cli.setNombre(rs.getString(2));
				cli.setDireccion(rs.getString(3));
				cli.setTelefono(rs.getInt(4));				
				
				clientes.add(cli);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		conexion.desconectar();
		return clientes;
	}
	
	
	public ArrayList<FilaCliente> cargarClientesPorLocalidad(String localidad) {
		Conexion conexion = new Conexion();
		
		String consulta = "select * from clientes where clDireccion like ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList <FilaCliente> cliente = new ArrayList<FilaCliente>();
		String str = localidad;
		
		try {
			ps = conexion.getConnection().prepareStatement(consulta);
			ps.setString(1, "%" + str);
			
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
	}
	


}
