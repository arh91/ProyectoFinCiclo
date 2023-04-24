package modeloVo;

public class FilaCliente {
	String nifCliente;
	String nombreCliente;
	String direccionCliente;
	int telefonoCliente;
	
	
	public FilaCliente(String nifCliente, String nombreCliente, String direccionCliente, int telefonoCliente) {
		super();
		this.nifCliente = nifCliente;
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.telefonoCliente = telefonoCliente;
	}
	
	public FilaCliente() {
		super();
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public int getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(int telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}
	
	
	
	
	
	

}
