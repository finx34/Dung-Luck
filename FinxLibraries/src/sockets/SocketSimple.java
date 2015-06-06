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

public class SocketSimple {

	Socket socket;
	PrintWriter out;
	BufferedReader in;
	ObjectInputStream oIn;
	ObjectOutputStream oOut;

	
	public void enviar(String s) {
		out.println(s);
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
		try {
			return in.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	public SocketSimple(Socket socket) throws IOException {
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
