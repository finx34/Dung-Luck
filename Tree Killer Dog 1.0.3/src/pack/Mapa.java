package pack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Mapa {
	private char[][] terreno;
	private char[][] objeto;
	private Perro[][] perros;
	private int x;
	private int y;

	public Mapa(int x, int y, String terreno, String objeto) {
		this.terreno = new char[x][y];
		this.objeto = new char[x][y];
		this.perros = new Perro[x][y];
		this.x = x;
		this.y = y;
		int cont = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				this.setTerreno(terreno.charAt(cont), i, j);
				cont++;
			}
		}
		cont = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				this.setObjeto(objeto.charAt(cont), i, j);
				cont++;
			}
		}

	}

	public boolean isAgarrable(int x, int y) {
		switch (this.getObjeto(x, y)) {
		case 't':
			return true;
		default:
			return false;
		}
	}

	public boolean isTerrenoTrancitable(int x, int y) {
		switch (this.getTerreno(x, y)) {
		case 'g':
			return true;
		case 'b':
			return true;
		default:
			return false;
		}
	}

	public boolean isObjetoTrancitable(int x, int y) {
		switch (this.getObjeto(x, y)) {
		case 'x':
			return true;
		case 't':
			return true;
		default:
			return false;
		}
	}

	public boolean isPerroTrancitable(int x, int y) {
		return (this.getPerro(x, y) == null);
	}

	public boolean isTrancitable(int x, int y) {

		return (this.isObjetoTrancitable(x, y)
				&& this.isTerrenoTrancitable(x, y) && this.isPerroTrancitable(
				x, y));

	}

	public void rendTerreno(Graphics g) throws SlickException {
		Image grass = new Image("sprites/grass.png");
		Image water = new Image("sprites/water.png");
		Image bridge = new Image("sprites/bridge.png");

		double inicioX, inicioY, finX, finY;
		inicioX = ((Datos.getCentro().x - 450) / 50);
		inicioY = (Datos.getCentro().y - 350) / 50;
		finX = (Datos.getCentro().x + 450) / 50+1;
		finY = (Datos.getCentro().y + 350) / 50+1;

		for (int i = (int) Math.ceil(inicioX); i < finX; i++) {
			for (int j = (int) Math.ceil(inicioY); j < finY; j++) {
				switch (this.getTerreno(i, j)) {
				case 'g':
					grass.draw(i * 50, j * 50);
					break;
				case 'w':
					water.draw(i * 50, j * 50);
					break;
				case 'b':
					bridge.draw(i * 50, j * 50);
					break;

				}

			}

		}
	}

	public void rendObjeto(Graphics g) throws SlickException {
		Image tree = new Image("sprites/tree.png");
		for (int i = 0; i < this.getX(); i++) {
			for (int j = 0; j < this.getY(); j++) {
				switch (this.getObjeto(i, j)) {
				case 't':
					tree.draw(i * 50, j * 50);
					break;

				}
			}
		}
	}

	public void rendPerros(Graphics g) throws SlickException {
		double inicioX, inicioY, finX, finY;
		inicioX = ((Datos.getCentro().x - 450) / 50);
		inicioY = (Datos.getCentro().y - 350) / 50;
		finX = (Datos.getCentro().x + 450) / 50;
		finY = (Datos.getCentro().y + 350) / 50;

		for (int i = (int) Math.ceil(inicioX); i < finX; i++) {
			for (int j = (int) Math.ceil(inicioY); j < finY; j++) {
				try {
					if (this.perros[i][j] != null) {
						this.perros[i][j].rend(g);
					}
				} catch (Exception e) {
				}

			}

		}

	}

	public char getTerreno(int x, int y) {
		if (x < 0 || x >= this.x || y < 0 || y >= this.y)
			return 'x';
		return terreno[x][y];
	}

	public void setTerreno(char c, int x, int y) {
		if (!(x < 0 || x >= this.x || y < 0 || y >= this.y))
			this.terreno[x][y] = c;

	}

	public char getObjeto(int x, int y) {
		if (x < 0 || x >= this.x || y < 0 || y >= this.y)
			return 'x';
		return objeto[x][y];
	}

	public void setObjeto(char c, int x, int y) {
		if (!(x < 0 || x >= this.x || y < 0 || y >= this.y))
			this.objeto[x][y] = c;

	}

	public Perro getPerro(int x, int y) {
		return perros[x][y];

	}

	public void setPerro(Perro perro, int x, int y) {
		if (!(x < 0 || x >= this.x || y < 0 || y >= this.y))
			this.perros[x][y] = perro;
	}

	public void limpiarPerro(int x, int y) {
		this.setPerro(null, x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
