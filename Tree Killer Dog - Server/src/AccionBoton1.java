import java.io.IOException;

import ventanas.Ventana;


public class AccionBoton1 implements Runnable {

	@Override
	public void run() {
		int puerto = Integer.parseInt(Ventana.getText2());
		Datos.setPuerto(puerto);
		try {
			Thread t = new Thread(new HiloAceptar());
			t.start();
			HiloMostrarEstadoHilos.add(t);
		} catch (IOException e1) {
			System.out.println("(AccionBoton1)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			// TODO Auto-generated catch block
			System.exit(2);
		}
		Ventana.setAccion1(new AccionBoton2(), "¡Empezar juego!");

	}

}
