public class HiloMostrarEstadoHilos implements Runnable {
	static public Thread hilos[] = new Thread[100];
	static int cosasAdentro=0;

	static public void add(Thread t) {
				hilos[cosasAdentro] = t;
				cosasAdentro++;

			}


	public void run() {

	}

}
