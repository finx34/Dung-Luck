
public class AccionSalir implements Runnable  {

	@Override
	public void run() {
		DatosServer.save();
		System.exit(0);
		
	}
	
	
}
