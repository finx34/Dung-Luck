package pack;

import sockets.SocketSimple;
import ventanas.Ventana;

public class AccionRegistroB implements Runnable{

	@Override
	public void run() {

		Datos.getSocketPrincipal().enviar("r");
		String user=Ventana.getText1();
		String pass=Ventana.getText2();
		Datos.getSocketPrincipal().enviar(user);
		Datos.getSocketPrincipal().enviar(pass);
		String respuesta = Datos.getSocketPrincipal().recivir();
		Ventana.setEstado(respuesta);
		
		
	}
	
}
