package pack;

import ventanas.Ventana;

public class AccionVolver implements Runnable {

	@Override
	public void run() {
		Ventana.setAccion1(new AccionLogIn(), "Log in");
		Ventana.setAccion2(new AccionRegistroA(), "Registrarse");

	}

}
