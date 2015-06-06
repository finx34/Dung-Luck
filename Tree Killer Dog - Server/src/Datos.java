import java.net.Inet4Address;
import java.net.UnknownHostException;

import ventanas.Ventana;

public class Datos {

	private static Datos instance;
	private int puerto;
	private int jugadores = 0;
	private String ip;


	public static void crear() {
		instance = new Datos();
		try {
			instance.ip = Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("(Datos)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void incrementarJugadoresActuales() {
		instance.jugadores++;
		Ventana.setEstado("Total de jugadores: "+instance.jugadores);
	}

	public static String getIP() {
		return instance.ip;
	}

	public static int getPuerto() {
		return instance.puerto;
	}

	public static void setPuerto(int puerto) {
		instance.puerto = puerto;
	}
	
	public static int getJugadores() {
		return instance.jugadores;
	}

	public static void setJugadores(int jugadores) {
		instance.jugadores = jugadores;
	}

}
