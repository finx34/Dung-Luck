import java.io.IOException;
import java.net.ServerSocket;

import sockets.SocketSimple;
import ventanas.Ventana;

public class HiloAceptar implements Runnable {
	private SocketSet socketSet;
	private ServerSocket server;
	private SocketSimple socket;
	private String mensaje;
	private int puerto;

	public HiloAceptar() throws IOException {
		this.server = new ServerSocket(Datos.getPuerto());
		Ventana.setEstado("Creado");
	}

	private void decodificar(String msg, SocketSimple socket) {
		int etiqueta;
		switch (msg.charAt(0)) {


		case 'c':
			etiqueta = Integer.parseInt(msg.charAt(1) + "");
			SocketSet.setSocketControlador(socket, etiqueta);
			Ventana.setEstado("Se creo un socket controlado para el jugador"
					+ etiqueta);
			Thread t = new Thread(new HiloMovimientos(etiqueta));
			t.start();
			HiloMostrarEstadoHilos.add(t);

			break;
		case 'u':
			(new Thread(new HiloAConectar(socket))).run();
		break;
			
		
		case 'j':
			etiqueta = Integer.parseInt(msg.charAt(1) + "");
			SocketSet.addSocketEnvio(socket, etiqueta);
			break;
		}
	}

	public void run() {
		while (true) {
			mensaje = "";
			try {
				socket = new SocketSimple(server.accept());
				Ventana.setEstado("Socket solicita ingreso...");
				mensaje = socket.recivir();
				Ventana.setEstado(socket.getIP() + " envio: " + mensaje);
				this.decodificar(mensaje, socket);
			} catch (IOException e) {
				System.out
						.println("(HiloAceptar)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}

		}

	}

}
