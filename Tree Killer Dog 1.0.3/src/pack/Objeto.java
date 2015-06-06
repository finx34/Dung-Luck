package pack;

public class Objeto {
	private String nombre;
	private int cantidad;
	
	public Objeto(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void incrementar(int cantidad){
		this.cantidad+=cantidad;
	}
	
	public void disminuir(int cantidad){
		this.cantidad-=cantidad;
	}
	
	
}
