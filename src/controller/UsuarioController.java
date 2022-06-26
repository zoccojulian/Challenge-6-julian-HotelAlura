package controller;

import java.util.List;

import dao.Usuario;
import dao.UsuarioDAO;
import factory.ConnectionFactory;

public class UsuarioController {
	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Usuario> buscarUsuario(Usuario usuario) {
		return usuarioDAO.buscarUsuario(usuario);
		
	}

}
