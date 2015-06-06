package pack;

import java.io.IOException;
import java.net.Socket;

import org.newdawn.slick.Input;

import sockets.SocketSimple;

public class Controlador implements Runnable {

	Input imput;
	char key;
	TablaDeSeñales controles;
	SocketSimple socket;

	public Controlador(Input imput, TablaDeSeñales controles) {
		super();
		this.imput = imput;

		this.controles = controles;
		try {
			socket = new SocketSimple(new Socket(Datos.getDireccion(),
					Datos.getPuerto()));
		} catch (IOException e) {
			System.exit(2);
			e.printStackTrace();
		}
		String msg = ("c" + Datos.getEtiqueta());
		socket.enviar(msg);

	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (Datos.isPuedeMover()) {
				
				if (imput.isKeyDown(controles.u)) {
					Datos.setPuedeMover(false);
					socket.enviar("u");
					System.out.println("no se puede mover");
				} else if (imput.isKeyDown(controles.d)) {
					Datos.setPuedeMover(false);
					socket.enviar("d");
					System.out.println("no se puede mover");
				} else if (imput.isKeyDown(controles.l)) {
					Datos.setPuedeMover(false);
					socket.enviar("l");
					System.out.println("no se puede mover");
				} else if (imput.isKeyDown(controles.r)) {
					Datos.setPuedeMover(false);
					socket.enviar("r");
					System.out.println("no se puede mover");
				}

				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
