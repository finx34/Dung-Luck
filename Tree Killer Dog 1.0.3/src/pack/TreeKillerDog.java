package pack;



import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TreeKillerDog extends BasicGame {
	boolean inicio = true;
	static Random rand = new Random();
	static int sizeX = 100;
	static int sizeY = 100;
	static String[] argumentos;

	public TreeKillerDog(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {

		if (inicio) {
			Input input = gc.getInput();

			TablaDeSeñales player1 = new TablaDeSeñales(Input.KEY_UP,
					Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT);
			Thread t = new Thread(new Controlador(input, player1));
			t.start();
			HiloMostrarEstadoHilos.add(t);

			for (int x = 2; x < argumentos.length; x++) {
				String sIniX = argumentos[x].substring(1, 3);
				String sIniY = argumentos[x].substring(4, 6);
				;
				int iniX = Integer.parseInt(sIniX);
				int iniY = Integer.parseInt(sIniY);
				Perro perro = new Perro(x - 1, iniX, iniY,
						"sprites/perro/1.png");
				Datos.getPerros()[perro.getEtiqueta()] = perro;
				Thread t2 = new Thread(new HiloPerro(perro));
				t2.start();
				HiloMostrarEstadoHilos.add(t);

			}
			Datos.setCentro(Datos.getPerros()[Datos.getEtiqueta()].getPos());
			inicio = false;
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		g.translate(-Datos.getCentro().x + 375, -Datos.getCentro().y + 275);
		Datos.getMapa().rendTerreno(g);
		Datos.getMapa().rendObjeto(g);
		Datos.getMapa().rendPerros(g);
		g.translate(0, 0);
		g.drawString("(" + Datos.getCentro().x + "," + Datos.getCentro().y
				+ ")", 600, 500);

	}

	public static void main(String[] args) {
		AppGameContainer game;
		argumentos = args;
		char[] text = new char[192];
		for (int r = 0; r < 192; r++) {
			Random rand = new Random();
			int num = rand.nextInt(3);
			switch (num) {
			case 1:
				text[r] = 'w';
				break;
			default:
				text[r] = 'g';
				break;
			}
			text[0] = 'g';

		}
		String terrenoSeed = new String(text);

		text = new char[192];
		for (int r = 0; r < 192; r++) {

			int num = rand.nextInt(12);
			switch (num) {
			case 1:
				text[r] = 't';
				break;
			default:
				text[r] = 'x';
				break;
			}
			text[0] = 'g';

		}
		String ObjetoSeed = new String(text);
		Datos.setMapa(new Mapa(sizeX, sizeY, argumentos[0], argumentos[1]));

		try {
			game = new AppGameContainer(new TreeKillerDog("Tree Killer Dog"));
			game.setShowFPS(false);
			game.setDisplayMode(800, 600, false);
			game.setAlwaysRender(true);
			game.setTargetFrameRate(50);

			game.start();

		} catch (SlickException e) {
			System.out
					.println("(Main)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}

	}

}
