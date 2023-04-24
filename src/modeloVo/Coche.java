package modeloVo;

public class Coche {
	private String matricula;
	private String marca;
	private String modelo;
	private String color;
	private int precio;
	private boolean disponible;
	private int garaje;
	public Coche() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coche(String matricula, String marca, String modelo, String color, int precio, boolean disponible,
			int garaje) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.disponible = disponible;
		this.garaje = garaje;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public int getGaraje() {
		return garaje;
	}
	public void setGaraje(int garaje) {
		this.garaje = garaje;
	}
	@Override
	public String toString() {
		return matricula+"   "+marca+"   "+modelo+"   "+color+"   "+"precio = "+precio; 
	}
	
	
}
