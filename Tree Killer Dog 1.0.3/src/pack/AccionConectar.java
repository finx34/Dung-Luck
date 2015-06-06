package pack;

import java.io.IOException;
import java.net.Socket;

import org.lwjgl.Sys;

import sockets.SocketSimple;
import ventanas.Ventana;


public class AccionConectar implements Runnable {

	@Override
	public void run() {
		String direccion = Ventana.getText1();
		int puerto = Integer.parseInt(Ventana.getText2());
		Datos.crear();
		Datos.setDireccion(direccion);
		Datos.setPuerto(puerto);
		try {
			SocketSimple socket = new SocketSimple(new Socket(direccion,puerto));
			Datos.setSocketPrincipal(socket);
		} catch (IOException e) {
			System.exit(0);
		}
		Datos.getSocketPrincipal().enviar("u");
		Ventana.setTextLabel1("Usuario");
		Ventana.setTextLabel2("Contraseña");
		Ventana.setText1("", true);
		Ventana.setText2("", true);
		Ventana.setAccion1(new AccionLogIn(), "Log in");
		Ventana.setAccion2(new AccionRegistroA(), "Registrarse");


	}

}
