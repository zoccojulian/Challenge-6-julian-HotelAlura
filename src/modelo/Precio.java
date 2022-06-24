package modelo;

public class Precio {

	private double valorDia;

	public Precio() {
		
		this.valorDia = 2000.00;
	}
	
	public double valorEstadia(long dias) {
		return this.valorDia*dias;
	}
	
	
	
}
