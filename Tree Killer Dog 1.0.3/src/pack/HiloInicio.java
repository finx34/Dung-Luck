package pack;

import ventanas.Ventana;

public class HiloInicio implements Runnable {

	@Override
	public void run() {
		Ventana.setEstado("Socket Creado y conectado...");
		String msg = ("e");
		Datos.getSocketPrincipal().enviar(msg);
		Ventana.setEstado("Enviando mensaje...");
		int etiqueta = Integer.parseInt(Datos.getSocketPrincipal().recivir());
		Datos.setEtiqueta(etiqueta);
		Ventana.setEstado("Conexion establecida. Ustes es el juegador: "+Datos.getEtiqueta());
		int numeroDeJugadores = Integer.parseInt( Datos.getSocketPrincipal().recivir() );
		String arg[] = new String[2+numeroDeJugadores];
		for(int i=0;i<arg.length;i++){
			arg[i] = Datos.getSocketPrincipal().recivir();
			Ventana.setEstado(arg[i]);
		}
		Ventana.delete();
		TreeKillerDog.main(arg);

	}
}
