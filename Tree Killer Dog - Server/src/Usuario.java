
public class Usuario {
	private String id;
	private String pasword;
	
	public Usuario(String id, String pasword){
		this.id = id;
		this.pasword = pasword;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

}
