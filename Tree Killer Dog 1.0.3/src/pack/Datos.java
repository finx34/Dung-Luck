package pack;

import sockets.SocketSimple;


public class Datos {
	
	private SocketSimple socketPrincipal;
	static Datos instance;
	private int puerto;
	private String direccion;
	private int etiqueta;
	private boolean puedeMover=true;
	private Cordenada centro=new Cordenada(400,300);
	private Perro perros[] = new Perro[100];
	private Mapa mapa;
	
	public static SocketSimple getSocketPrincipal(){
		return instance.socketPrincipal;
	}
	
	public static void setSocketPrincipal(SocketSimple socket){
		instance.socketPrincipal = socket;
	}
	
	public static Mapa getMapa(){
		return instance.mapa;
	}
	
	public static void setMapa(Mapa mapa){
		instance.mapa = mapa;
	}
	
	public static Perro[] getPerros(){
		return instance.perros;
	}
	
	public static void setCentro(Cordenada centro){
		instance.centro = centro;
	}
	
	public static void setCentro(int x, int y){
		instance.centro.x = x;
		instance.centro.y = y;
	}
	
	public static Cordenada getCentro(){
		return instance.centro;
	}

	public static boolean isPuedeMover() {
		return instance.puedeMover;
	}

	public static void setPuedeMover(boolean puedeMover) {
		instance.puedeMover = puedeMover;
	}

	public static void crear() {
		instance = new Datos();
		
	}

	public static int getPuerto() {
		return instance.puerto;
	}

	public static void setPuerto(int puerto) {
		instance.puerto = puerto;
	}

	public static String getDireccion() {
		return instance.direccion;
	}

	public static void setDireccion(String direccion) {
		instance.direccion = direccion;
	}

	public static int getEtiqueta() {
		return instance.etiqueta;
	}

	public static void setEtiqueta(int etiqueta) {
		instance.etiqueta = etiqueta;
	}

}
