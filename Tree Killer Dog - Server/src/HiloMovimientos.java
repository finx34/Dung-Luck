import ventanas.Ventana;

public class HiloMovimientos implements Runnable {

	private int etiqueta;

	public HiloMovimientos(int etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public void run() {
		System.out.println("Cree un hilo de movimientos para el jugador"+this.etiqueta);
		String msg;
		while(true){
			Ventana.setEstado("Jugador "+etiqueta+"Esperando siguiente mensaje del juegador ");
			msg = SocketSet.recivir(etiqueta);
			Ventana.setEstado("Jugador "+etiqueta+" envio:"+msg);
			SocketSet.enviar(msg, etiqueta);

		}

	}

}
