import java.net.UnknownHostException;

import ventanas.Ventana;

public class TreeKillerDogServerMain {
	
	static int puertoSocket;

	
	
  public static void main(String args[]) throws UnknownHostException {
	  DatosServer.launch();
	  DatosServer.load();
	 (new Thread(new HiloMostrarEstadoHilos())).start();
	  System.out.println("Server");
	  SocketSet.launch(100);
	  Datos.crear();
	  Ventana.launch("Tree Killer Dog Server","Server IP","Puerto");
	  Ventana.setText1(Datos.getIP(), false);
	  Ventana.setText2("3493", true);
	  Ventana.setAccion1(new AccionBoton1(), "Correr Server");
	  Ventana.setAccion2(new AccionSalir(), "Salir");
	  
  }

}