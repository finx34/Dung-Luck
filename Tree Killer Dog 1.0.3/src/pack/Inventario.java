package pack;

public class Inventario {
	Objeto objetos[];
	int tamaño;

	public Inventario(int tamaño) {
		this.tamaño=tamaño;
		this.objetos = new Objeto[tamaño];
		for (int i = 0; i < tamaño; i++) {
			this.objetos[i]=new Objeto("", 0);
		}

	}

	public Objeto getObjeto(String nombre) {
		if (this.isObjeto(nombre)) {
			for (int i = 0; i < tamaño; i++) {
				if (this.objetos[i].getNombre() == nombre) {
					return this.objetos[i];
				}
			}
		} 
		return null;

	}

	public boolean isObjeto(String nombre) {
		for (int i = 0; i < tamaño; i++) {
			if (this.objetos[i].getNombre() == nombre) {
				return true;
			}
		}
		return false;
	}

	public void nuevoObjeto(String nombre, int cantidad) {
		for (int i = 0; i < tamaño; i++) {
			if (this.objetos[i].getNombre() == "") {
				this.objetos[i].setNombre(nombre);
				this.objetos[i].setCantidad(cantidad);

				break;

			}
		}
	}

	public void incrementar(String nombre, int cantidad) {
		if (this.isObjeto(nombre)) {
			this.getObjeto(nombre).incrementar(cantidad);
		} else {
			this.nuevoObjeto(nombre, cantidad);
		}
	}
	
	public void disminuir(String nombre, int cantidad) {
		if (this.isObjeto(nombre)) {
			this.getObjeto(nombre).disminuir(cantidad);
		} else {
			this.nuevoObjeto(nombre, cantidad);
		}
	}
	
	public int getCantidadObjeto(String nombre){
		if(this.isObjeto(nombre)){
			return this.getObjeto(nombre).getCantidad();
		}
		return 0;
	}
}
