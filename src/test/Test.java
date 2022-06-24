package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		
		List<Object> resultado = new ArrayList<>();
		
		Connection con = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement;
		statement= con.prepareStatement("SELECT H.ID, H.NOMBRE, "
					+ "H.APELLIDO, R.INGRESO, R.EGRESO, R.ID FROM HUESPED H "
					+ "INNER JOIN RESERVA R ON H.ID_RESERVA = R.ID ");
	
		statement.execute();
		
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			
			System.out.println("id huesped: " + resultSet.getInt("H.ID"));
			System.out.println("Nombre: " + resultSet.getString("H.NOMBRE"));
			System.out.println("Apellido: " + resultSet.getString("H.APELLIDO"));
			System.out.println("Fecha Ingreso: " + resultSet.getDate("R.INGRESO"));
			System.out.println("Fecha Egreso: " + resultSet.getDate("R.EGRESO"));
			System.out.println("id Reserva: " + resultSet.getInt("R.ID"));
		}
		
	}
}
