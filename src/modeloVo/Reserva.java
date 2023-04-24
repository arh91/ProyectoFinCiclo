package modeloVo;

import java.util.Date;

public class Reserva {
	private int codigo;
	private Date fecInicio;
	private Date fecFinal;
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reserva(int codigo, Date fecInicio, Date fecFinal) {
		super();
		this.codigo = codigo;
		this.fecInicio = fecInicio;
		this.fecFinal = fecFinal;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecInicio() {
		return fecInicio;
	}
	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}
	public Date getFecFinal() {
		return fecFinal;
	}
	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}
	
	

}
