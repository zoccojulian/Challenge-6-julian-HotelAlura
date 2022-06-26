package controller;

import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Reserva> listar() {
		
		return this.reservaDAO.listar();
	}

	public int abrirConexion(Reserva reserva) {
		return this.reservaDAO.abrirConexion(reserva);
		
	}

	public void cerrarConexion() {
		this.reservaDAO.cerrarConexion();
		
	}

	public void eliminar(int idReserva) {
		this.reservaDAO.eliminar(idReserva);
		
	}

	public void modificar(Reserva reserva) {
		this.reservaDAO.modificar(reserva);
		
	}

	public List<Reserva> buscar(String busqueda, String campo) {
		return reservaDAO.buscar(busqueda, campo);
		
	}


	
	
	

}
