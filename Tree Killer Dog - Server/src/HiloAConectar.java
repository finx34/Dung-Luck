import sockets.SocketSimple;
import ventanas.Ventana;

public class HiloAConectar implements Runnable {

	private boolean continuar = true;

	SocketSimple socket;

	public HiloAConectar(SocketSimple socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while (continuar) {

			String key = socket.recivir();
			String id;
			String pasword;
			Ventana.setEstado("recibi: "+key);
			switch (key.charAt(0)) {

			case 'r':
				id = socket.recivir();
				pasword = socket.recivir();
				if (!DatosServer.existeUsuario(id)) {
					DatosServer.nuevoUsuario(id, pasword);
					socket.enviar("Usuario creado");
				} else {
					socket.enviar("No se ha podido crear el usuario");
				}
				

				break;
				
			case 'e':
				String respuesta = Integer.toString(SocketSet
						.addSocketInicio(socket));
				Ventana.setEstado("Se conecto un nuevo jugador");
				socket.enviar(respuesta);
				break;
			case 'l':
				id = socket.recivir();
				pasword = socket.recivir();
				Usuario usuario = DatosServer.buscarUsuario(id);
				if (usuario != null) {
					if(usuario.getPasword().equals(pasword)){
						Ventana.setEstado("envie: 1");
						socket.enviar("1");
						continuar = false;

					}else{
						Ventana.setEstado("envie: 2");
						socket.enviar("2");
					}
					break;
				}
			}

		}

	}
}
