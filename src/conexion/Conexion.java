package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	
	static String bd = "UD02BDReservaCoches";
	static String login = "root";
	static String password = "castelao";
	static String url = "jdbc:mysql://localhost/"+bd+
			"?noAccessToProcedureBodies=true&useServerPrepStmts=true&serverTimezone=Europe/Madrid&useSSL=false&allowPublicKeyRetrieval=true";

	Connection conn = null;

	/** Constructor de DbConnection */
	
		public Conexion() {
			try{
				//obtenemos el driver de para mysql
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//obtenemos la conexi�n
				conn = DriverManager.getConnection(url,login,password);
			//	System.out.println(url);
			/*
			 * if (conn==null){
			 * System.out.println("Conexi�n a base de datos "+bd+" no se ha podido realizar"
			 * ); }
			 */
		}
		catch(SQLException e){
			System.out.println(e);
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	/**Permite retornar la conexi�n*/
	public Connection getConnection(){
		return conn;
	}

	public void desconectar(){
		conn = null;
	}
}
