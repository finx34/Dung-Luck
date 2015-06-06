import java.util.Random;

import ventanas.Ventana;


public class IniciarJuego {

	public void launch(){
		Random rand= new Random();
		String msg;
		
		SocketSet.enviarAIniciadores(Datos.getJugadores()+"");
		
		char[] text = new char[10000];
		for (int r = 0; r < 10000; r++) {
			int num = rand.nextInt(3);
			switch (num) {
			case 1:
				text[r] = 'w';
				break;
			default:
				text[r] = 'g';
				break;
			}
			text[0] = 'g';

		}
		msg = new String(text);
		
		SocketSet.enviarAIniciadores(msg);

		text = new char[10000];
		for (int r = 0; r < 10000; r++) {
			int num = rand.nextInt(12);
			switch (num) {
			case 1:
				text[r] = 't';
				break;
			default:
				text[r] = 'x';
				break;
			}


		}
		msg = new String(text);
		Ventana.setEstado(msg);
		SocketSet.enviarAIniciadores(msg);
		int iniX;
		int iniY;
		for(int i=0;i<Datos.getJugadores();i++){
			iniX = rand.nextInt(99);
			iniY = rand.nextInt(99);
			msg = String.format("%03d", iniX)+String.format("%03d", iniY);
			SocketSet.enviarAIniciadores(msg);
		}

		
	}
	
}
