package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Utilitario.UtilitarioFecha;
import modelo.Huesped;

public class HuespedDAO {

	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public List<Huesped> listar() {

		List<Huesped> listaHuespedes = new ArrayList<Huesped>();

		try {

			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, " + "TELEFONO, ID_RESERVA FROM HUESPED");

			try (statement) {
				statement.execute();

				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"), resultSet.getDate("NACIMIENTO").toLocalDate(),
							resultSet.getString("NACIONALIDAD"), resultSet.getString("TELEFONO"),
							resultSet.getInt("ID_RESERVA"));

					listaHuespedes.add(fila);

				}

				return listaHuespedes;

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void ingresarHuesped(Huesped huesped) {

		try {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPED (NOMBRE, APELLIDO, NACIMIENTO, "
							+ "NACIONALIDAD, TELEFONO, ID_RESERVA) VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, UtilitarioFecha.tranformarADateSQL(huesped.getNacimiento()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId_reserva());
				statement.execute();
			}

		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}

	}

	public void eliminar(int idHuesped) {
		
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPED WHERE ID = ?");

			try (statement) {
				statement.setInt(1, idHuesped);
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void modificar(Huesped huesped) {
		try {

			final PreparedStatement statement = con.prepareStatement(
					"UPDATE HUESPED SET "
					+ "NOMBRE = ?, "
					+ "APELLIDO = ?, "
					+ "NACIMIENTO = ?, "
					+ "NACIONALIDAD = ?, "
					+ "TELEFONO = ? "
					+ "WHERE ID = ?");

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, UtilitarioFecha.tranformarADateSQL(huesped.getNacimiento()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getId());
				statement.execute();
			}

		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}

		
	}

}
