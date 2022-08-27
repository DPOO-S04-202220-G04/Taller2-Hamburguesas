package Modelo;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	
	public Combo(String nombre, double descuento) {}
	
	public void agregarItemACombo(Producto itemCombo) {}
	
	public int getPrecio() {}
	
	public String generarTextoFactura() {}
	
	public String getNombre() {}
}
