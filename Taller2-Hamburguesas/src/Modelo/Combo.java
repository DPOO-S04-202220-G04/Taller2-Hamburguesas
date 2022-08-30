package Modelo;
import java.util.*;
import java.lang.Math;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo; //JUSTIFICAR POR QUÉ AGREGAMOS ESTO
	private int precio;
	
	public Combo(String nombre, double descuento) {
		nombreCombo = nombre;
		this.descuento = descuento;
		itemsCombo = new ArrayList<ProductoMenu>();
		this.precio = 0;
	}
	
	public void agregarItemACombo(ProductoMenu itemCombo) {
		itemsCombo.add(itemCombo);
	}
	
	public int getPrecio() {
		double precioBase = 0;
		for (Producto producto : itemsCombo) {
			precioBase += producto.getPrecio();
		}
		this.precio = (int) Math.round(precioBase - precioBase*descuento);
		return precio; 
	}
	
	public String generarTextoFactura() {
		String factura = "+ " + nombreCombo + " $" + precio + ":\n";
		for (Producto p : itemsCombo) {
			factura += "	" + p.getNombre() + "\n";
		}
		return factura;
	}
	
	public String getNombre() {
		return nombreCombo;
	}
	
	public String toString() {
		String s = nombreCombo + " $" + precio + ":\n";
		for (Producto p : itemsCombo) {
			s += "   --> " + p.getNombre() + "\n";
		}
		return s;
	}
}
