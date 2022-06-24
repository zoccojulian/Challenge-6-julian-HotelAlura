package modelo;

import java.time.LocalDate;

public class Reserva {
	private Integer id;
	private LocalDate ingreso;
	private LocalDate egreso;
	private String pago;
	private Double precio;
	private Huesped huesped;
	
	public Reserva(Integer id, LocalDate ingreso, LocalDate egreso, String pago, double precio) {
		this.id = id;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.pago = pago;
		this.precio = precio;
	}

	
	public Reserva(Integer id, LocalDate ingreso, LocalDate egreso, String pago, Huesped huesped, double precio) {
		this.id = id;
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.pago = pago;
		this.huesped = huesped;
		this.precio = precio;
	}

	public Reserva(LocalDate ingreso, LocalDate egreso, String pago, double precio) {
		this.ingreso = ingreso;
		this.egreso = egreso;
		this.pago = pago;
		this.precio = precio;
	}


	public Huesped getHuesped() {
		return huesped;
	}


	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getIngreso() {
		return ingreso;
	}

	public void setIngreso(LocalDate ingreso) {
		this.ingreso = ingreso;
	}

	public LocalDate getEgreso() {
		return egreso;
	}

	public void setEgreso(LocalDate egreso) {
		this.egreso = egreso;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
	
	
	
}
