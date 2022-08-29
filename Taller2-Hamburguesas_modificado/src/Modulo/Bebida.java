package Modulo;

public class Bebida implements Producto{
	private String nombre;
	private int precio;
	private int calorias;
	
	public Bebida(String nombre, int precio, int calorias) {
		this.nombre = nombre;
		this.precio = precio;
		this.calorias = calorias;
		}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	public String generarTextoFactura() {
		return "+ " + nombre + " $" + precio + "\n";
	}
	
	public String toString() {
		return nombre + " $" + precio;
	}
}
