package modeloVo;

public class Involucra {
	private int codigo;
	private String matricula;
	private String cliente;
	private int reserva;
	private int litros;
	public Involucra() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Involucra(int codigo, String matricula, String cliente, int reserva, int litros) {
		super();
		this.codigo = codigo;
		this.matricula = matricula;
		this.cliente = cliente;
		this.reserva = reserva;
		this.litros = litros;
	}
	
	
	public Involucra(String matricula, String cliente, int reserva, int litros) {
		super();
		this.matricula = matricula;
		this.cliente = cliente;
		this.reserva = reserva;
		this.litros = litros;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getReserva() {
		return reserva;
	}
	public void setReserva(int reserva) {
		this.reserva = reserva;
	}
	public int getLitros() {
		return litros;
	}
	public void setLitros(int litros) {
		this.litros = litros;
	}
	
	

}
