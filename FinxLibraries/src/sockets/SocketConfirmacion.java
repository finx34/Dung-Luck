package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;

public class SocketConfirmacion {

	Socket socket;
	PrintWriter out;
	BufferedReader in;
	ObjectInputStream oIn;
	ObjectOutputStream oOut;
	int keyEnvio=0;
	int keyRecepcion=11;
	

	public void cambiaKeyEnvio(){
		if(keyEnvio==9){
			keyEnvio=0;
		}else{
			keyEnvio++;
		}
	}
	
	public void enviar(String s) {

		System.out.println("me ordenaron enviar: "+s);
		out.println(keyEnvio + s);
		System.out.println("envie: "+s);
		try {
			this.socket.setSoTimeout(50);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while(true){
			try{
				System.out.println("Espero confirmacion");
				in.readLine();
				System.out.println("Me confirmaron");
				break;
			} catch (IOException e){
				System.out.println("No me confirmaron");
			}

		}
		try {
			this.socket.setSoTimeout(0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cambiaKeyEnvio();
	}

	public Object recivirObjeto() {
		try {
			return oIn.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("(SocketSimple)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("(SocketSimple)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return null;
		}
	}

	public void enviarObjeto(Object object) {
		try {
			oOut.writeObject(object);

		} catch (IOException e) {
			System.out.println("(SocketSimple)ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e.printStackTrace();
		}
	}

	public String recivir(){
		System.out.println("me ordenaron recivir un mensaje");
		String msg="";
		try {
			System.out.println("intento leer un mensaje");
		msg=in.readLine();
		System.out.println("lei un mensaje: "+msg);
		} catch (IOException e) {
			
		}
		out.println("1");
		if(keyRecepcion != Integer.parseInt(msg.charAt(0)+"")){
			System.out.println("La key es distinta)");
			keyRecepcion = Integer.parseInt(msg.charAt(0)+"");
			return msg.substring(1);
			
		}else{
			System.out.println("me enviaron un mensaje repetido");
			return this.recivir();
		}

	}

	public SocketConfirmacion(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
		this.in = new BufferedReader(new InputStreamReader(
				this.socket.getInputStream()));
	}
	
	public void refresh(){
		try {
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("ERROR EN IMPUT AUPUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}

	}

	public String getIP() {
		return socket.getInetAddress().toString();
	}

}
