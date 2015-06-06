import ventanas.Ventana;




public class AccionBoton2 implements Runnable {

	@Override
	public void run() {
		Ventana.setAccion1(null, "iniciado...");
		IniciarJuego iniciador = new IniciarJuego();
		iniciador.launch();
		Ventana.setAccion1(null, "Servidor en curso...");

		
	}
}
