package modeloVo;

public class FilaReserva {

	String nombreCliente;
	String matriculaCoche;
	int precio;
	int dias;
	int importe;
	
	
	public FilaReserva(String nombreCliente, String matriculaCoche, int precio, int dias, int importe) {
		super();
		this.nombreCliente = nombreCliente;
		this.matriculaCoche = matriculaCoche;
		this.precio = precio;
		this.dias = dias;
		this.importe = importe;
	}


	public FilaReserva() {
		super();
	}


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}


	public String getMatriculaCoche() {
		return matriculaCoche;
	}


	public void setMatriculaCoche(String matriculaCoche) {
		this.matriculaCoche = matriculaCoche;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public int getDias() {
		return dias;
	}


	public void setDias(int dias) {
		this.dias = dias;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(int importe) {
		this.importe = importe;
	}
	
	
	
	
}
