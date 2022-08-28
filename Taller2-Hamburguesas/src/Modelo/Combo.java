package Modelo;
import java.util.*;
import java.lang.Math;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> productos; //JUSTIFICAR POR QUÉ AGREGAMOS ESTO
	
	public Combo(String nombre, double descuento) {
		nombreCombo = nombre;
		this.descuento = descuento;
		productos = new ArrayList<Producto>();
	}
	
	public void agregarItemACombo(Producto itemCombo) {
		productos.add(itemCombo);
	}
	
	public int getPrecio() {
		double precioBase = 0;
		for (Producto producto : productos) {
			precioBase += producto.getPrecio();
		}
		int precio = (int) Math.round(precioBase*descuento);
		return precio; 
	}
	
	public String generarTextoFactura() {}
	
	public String getNombre() {
		return nombreCombo;
	}
}
