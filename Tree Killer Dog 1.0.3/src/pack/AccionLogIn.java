package pack;

import java.net.Socket;

import sockets.SocketSimple;
import ventanas.Ventana;

public class AccionLogIn implements Runnable {

	@Override
	public void run() {
		try {
				Datos.getSocketPrincipal().enviar("l");
				String user=Ventana.getText1();
				String pass=Ventana.getText2();
				Datos.getSocketPrincipal().enviar(user);
				Datos.getSocketPrincipal().enviar(pass);
				String respuesta = Datos.getSocketPrincipal().recivir();
				Ventana.setEstado(respuesta);
				if (respuesta == "1") {
					Ventana.setEstado("Log In Exitoso");
					Ventana.setAccion1(null, "Espere...");
					Thread t = new Thread(new HiloInicio());
					t.start();
					HiloMostrarEstadoHilos.add(t);

				} else {
					Ventana.setEstado("Error al loguear");
				}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
