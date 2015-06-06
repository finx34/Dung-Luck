package pack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Perro {

	private int posX;
	private int posY;
	public Cordenada pos = new Cordenada(0,0);
	private Inventario inventario = new Inventario(24);
	private String imagen;
	private int etiqueta;

	public Perro(int etiqueta, int posX, int posY, String imagen) {
		super();
		this.posX = posX;
		this.pos.x = posX * 50;
		this.pos.y = posY * 50;
		this.posY = posY;
		this.imagen = imagen;
		Datos.getMapa().setPerro(this, posX, posY);
		this.getInventario().incrementar("madera", 1);
		Datos.getMapa().setObjeto('x', posX, posY);
		Datos.getMapa().setTerreno('g', posX, posY);
		this.etiqueta = etiqueta;
	}

	public Cordenada getPos(){
		return this.pos;
	}
	
	public int getEtiqueta(){
		return this.etiqueta;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void moverArriba(int cantidad) {
		Datos.getMapa().limpiarPerro(this.getPosX(), this.getPosY());
		this.posY -= cantidad;
		Datos.getMapa().setPerro(this, this.getPosX(), this.getPosY());
		for (int i = 0; i < cantidad * 50; i++) {
			this.pos.y--;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("(Perro)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}
		}
	}

	public void moverAbajo(int cantidad) {
		Datos.getMapa().limpiarPerro(this.getPosX(), this.getPosY());
		this.posY += cantidad;
		Datos.getMapa().setPerro(this, this.getPosX(), this.getPosY());
		for (int i = 0; i < cantidad * 50; i++) {
			this.pos.y++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("(Perro)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}
		}
	}

	public void moverIsquierda(int cantidad) {
		Datos.getMapa().limpiarPerro(this.getPosX(), this.getPosY());
		this.posX -= cantidad;
		Datos.getMapa().setPerro(this, this.getPosX(), this.getPosY());
		for (int i = 0; i < cantidad * 50; i++) {
			this.pos.x--;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void moverDerecha(int cantidad) {
		Datos.getMapa().limpiarPerro(this.getPosX(), this.getPosY());
		this.posX += cantidad;
		Datos.getMapa().setPerro(this, this.getPosX(), this.getPosY());
		for (int i = 0; i < cantidad * 50; i++) {
			this.pos.x++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void accion(String key) {
		switch (key) {
		case "u":
			if (Datos.getMapa().isTrancitable(this.getPosX(), this.getPosY() - 1)) {
				this.moverArriba(1);
				this.agarrar();
			} else if (Datos.getMapa().getTerreno(this.getPosX(), this.getPosY() - 1) == 'w'
					&& this.getInventario().getCantidadObjeto("madera") > 0) {
				Datos.getMapa().setTerreno('b', this.getPosX(), this.getPosY() - 1);
				this.getInventario().disminuir("madera", 1);
			}

			break;

		case "d":
			if (Datos.getMapa().isTrancitable(this.getPosX(), this.getPosY() + 1)) {
				this.moverAbajo(1);
				this.agarrar();
			} else if (Datos.getMapa().getTerreno(this.getPosX(), this.getPosY() + 1) == 'w'
					&& this.getInventario().getCantidadObjeto("madera") > 0) {
				Datos.getMapa().setTerreno('b', this.getPosX(), this.getPosY() + 1);
				this.getInventario().disminuir("madera", 1);
			}
			break;

		case "l":
			if (Datos.getMapa().isTrancitable(this.getPosX() - 1, this.getPosY())) {
				this.moverIsquierda(1);
				this.agarrar();
			} else if (Datos.getMapa().getTerreno(this.getPosX() - 1, this.getPosY()) == 'w'
					&& this.getInventario().getCantidadObjeto("madera") > 0) {
				Datos.getMapa().setTerreno('b', this.getPosX() - 1, this.getPosY());
				this.getInventario().disminuir("madera", 1);
			}
			;
			break;

		case "r":
			if (Datos.getMapa().isTrancitable(this.getPosX() + 1, this.getPosY())) {
				this.moverDerecha(1);
				this.agarrar();
			} else if (Datos.getMapa().getTerreno(this.getPosX() + 1, this.getPosY()) == 'w'
					&& this.getInventario().getCantidadObjeto("madera") > 0) {
				Datos.getMapa().setTerreno('b', this.getPosX() + 1, this.getPosY());
				this.getInventario().disminuir("madera", 1);
			}

		}

	}

	public void agarrar() {
		switch (Datos.getMapa().getObjeto(this.getPosX(), this.getPosY())) {
		case 't':
			this.getInventario().incrementar("madera", 1);
			Datos.getMapa() .setObjeto('x', this.getPosX(), this.getPosY());
		}
	}

	public void rend(Graphics g) throws SlickException {
		Image dog = new Image(this.getImagen());
		dog.draw(this.pos.x, this.pos.y, 50, 50);
		g.drawString("Jugador "+Datos.getCentro().x+etiqueta, 130*etiqueta-110, Datos.getCentro().x+510-450);
		dog.draw(130*etiqueta-25,505,25,25);
		Image wood = new Image("sprites/wood.png");
		wood.draw(Datos.getCentro().x+130-400*etiqueta,Datos.getCentro().y+200,25,25);
		g.drawString(" x"+this.getInventario().getCantidadObjeto("madera"), Datos.getCentro().x-450+130*etiqueta-107,Datos.getCentro().y-450+532);
	}

}
