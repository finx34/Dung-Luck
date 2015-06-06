import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ventanas.Ventana;

public class DatosServer implements Serializable {

	private Usuario users[] = new Usuario[100];
	static DatosServer instance;
	private String ruta = "data.bin";
	private int totalDeCuentas = 0; 
	
	
	public static boolean existeUsuario(String id){
		System.out.println("buscando user");
		Usuario user = buscarUsuario(id);
		System.out.println("Resultado: "+user != null);
		return (user != null);
	}
	
	public static Usuario buscarUsuario(String id){
		for (int i=0;i<instance.totalDeCuentas;i++){
			if(instance.users[i].getId().equals(id) ){
				return instance.users[i];

			}
		}
		System.out.println("no lo encontre ni ahi");
		return null;
	}
	
	public static void nuevoUsuario(String id, String pasword){
		instance.users[instance.totalDeCuentas]= new Usuario(id, pasword);
		instance.totalDeCuentas++;
		Ventana.setEstado("Se a creado el usuario :"+id
				+ "\nTotal de usuarios: "+instance.totalDeCuentas);
		
	}

	public static void launch() {
		instance = new DatosServer();
	}

	public static void load() {
		try {
			FileInputStream fin = new FileInputStream(instance.ruta);
			ObjectInputStream in = new ObjectInputStream(fin);
			instance = (DatosServer) in.readObject();
			in.close();

		} catch (FileNotFoundException e) {
			save();
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	public static void save() {

		FileOutputStream fout;
		try {
			fout = new FileOutputStream(instance.ruta);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(instance);
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
