package pack;
import java.net.UnknownHostException;

import ventanas.Ventana;

public class TreeKillerDogOnlineMain {
	  public static void main(String args[]) throws UnknownHostException {
		  System.out.println("Peeeneeees");
		  Ventana.launch("Tree Killer Dog","Direccion IP","Puerto");
		  Ventana.setAccion1(new AccionConectar(), "Conectar");
		  Ventana.setAccion2(new AccionSalir(), "Salir");
		  Ventana.setText1("localhost", true);
		  Ventana.setText2("3493", true);
	  }
}
