package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;


public class HuespedController {
	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Huesped> listar() {
		
		return this.huespedDAO.listar();
	}

	public void ingresarHuesped(Huesped huespedIngresado) {
		this.huespedDAO.ingresarHuesped(huespedIngresado);
		
	}

	public void eliminar(int idHuesped) {
		huespedDAO.eliminar(idHuesped);
		
	}

	public void modificar(Huesped huespedModificacion) {
		huespedDAO.modificar(huespedModificacion);
		
	}

	
}
