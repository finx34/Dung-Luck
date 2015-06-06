package pack;

import ventanas.Ventana;

public class AccionRegistroA implements Runnable{

	@Override
	public void run() {
		Ventana.setTextLabel1("Usuario");
		Ventana.setTextLabel2("Contraseña");
		Ventana.setText1("", true);
		Ventana.setText2("", true);
		Ventana.setAccion1(new AccionRegistroB(), "Enviar Registro");
		Ventana.setAccion2(null, "Volver");
		
	}
	
}
