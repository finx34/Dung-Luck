import sockets.SocketSimple;
import ventanas.Ventana;

public class SocketSet {

	private SocketSimple sockets[][] = new SocketSimple[100][100];
	private SocketSimple socketsInicio[] = new SocketSimple[100];
	int jugadores;
	int jugadoresActuales = 0;
	private static SocketSet instance;

	public static void launch(int jugadores) {
		instance = new SocketSet();

	}

	public static void enviarAIniciadores(String msg) {
		for (int i = 0; i < Datos.getJugadores(); i++) {
			instance.socketsInicio[i].enviar(msg);
		}
	}

	public static int addSocketInicio(SocketSimple socket) {

		instance.socketsInicio[Datos.getJugadores()] = socket;
		Datos.incrementarJugadoresActuales();
		return Datos.getJugadores();
	}

	public static void setSocketControlador(SocketSimple socket, int etiqueta) {

		instance.sockets[etiqueta][0] = socket;

	}

	public static void addSocketEnvio(SocketSimple socket, int jugador) {
		for (int i = 1; i <100; i++) {
			if (instance.sockets[jugador][i] == null) {
				Ventana.setEstado("Se agrego un scoket de envio para el jugador"+jugador+" , en el slot"+i);
				instance.sockets[jugador][i] = socket;
				break;
			}
		}
	}

	public static String recivir(int jugador) {
		return instance.sockets[jugador][0].recivir();
	}

	public static void enviar(String s, int jugador) {
		for (int i = 1; i < 100; i++) {
			if (instance.sockets[jugador][i] != null) {
				Ventana.setEstado("De "+jugador+"A a "+i+": "+s);
				instance.sockets[jugador][i].enviar(s);
			}
		}

	}

}
