package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;

public class UsuarioDAO {
	
	final private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	
	
	public List<Usuario> buscarUsuario(Usuario usuario) {
		
		
		try {
			
			List<Usuario> resultado = new ArrayList<>();
			
			final PreparedStatement statement = con.prepareStatement("SELECT USUARIO, PASSWORD, NIVEL_ACCESO "
					+ "FROM USUARIOS WHERE USUARIO = ?");
			
			statement.setString(1, usuario.getUsuario());
			
			try(statement){
				statement.execute();
				
				ResultSet resultSet = statement.getResultSet(); 
				
				while (resultSet.next()) {
					Usuario fila = new Usuario(resultSet.getString("USUARIO"), resultSet.getString("PASSWORD"),
							Integer.parseInt(resultSet.getString("NIVEL_ACCESO")));

					resultado.add(fila);
				}
				
				return resultado;
			}
			

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
		
	
	}

		
	
}
