package pack;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import sockets.SocketSimple;

public class HiloPerro implements Runnable {

	Perro perro;
	SocketSimple socket;

	public HiloPerro(Perro perro) {
		this.perro = perro;
		try {
			socket = new SocketSimple(new Socket(Datos.getDireccion(),
					Datos.getPuerto()));
			socket.enviar("j" + perro.getEtiqueta());
		} catch (UnknownHostException e) {
			System.out.println("(HiloPerro)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("(HiloPerro)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		String key;
		while (true) {
			key = socket.recivir();
			System.out.println("recivi key: "+key);
			perro.accion(key);
			if (perro.getEtiqueta() == Datos.getEtiqueta()) {
				Datos.setPuedeMover(true);
				System.out.println("ya se puede mover");
			}
		}

	}

}
