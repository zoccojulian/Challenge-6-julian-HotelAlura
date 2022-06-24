package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Huesped {

	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate nacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer id_reserva;
	
	
	public Huesped(Integer id, String nombre, String apellido, LocalDate nacimiento, String nacionalidad, String telefono,
			Integer id_reserva) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}



	public Huesped(Integer id, String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
	}



	public Huesped(String nombre, String apellido, LocalDate nacimiento, String nacionalidad, String telefono,
			Integer id_reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public LocalDate getNacimiento() {
		return nacimiento;
	}


	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public int getId_reserva() {
		return id_reserva;
	}


	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	
	
	
	
}
