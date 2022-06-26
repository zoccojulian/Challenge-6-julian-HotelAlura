package dao;

public class Usuario {

	private String usuario;
	private String password;
	private Integer nivelAcceso;
	
	
	public Usuario(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}


	public Usuario(String usuario, String password, Integer nivelAcceso) {
		this.usuario = usuario;
		this.password = password;
		this.nivelAcceso = nivelAcceso;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getNivelAcceso() {
		return nivelAcceso;
	}


	public void setNivelAcceso(Integer nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}
	
	
}
