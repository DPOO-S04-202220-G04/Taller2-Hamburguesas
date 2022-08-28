package Modelo;

public class ProductoAjustado implements Producto{
	private int precio;
	private String nombre;
	private String factura;
	
	public ProductoAjustado(ProductoMenu base) {
		this.precio = base.getPrecio();
		this.nombre = base.getNombre();
		this.factura = base.generarTextoFactura();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public String generarTextoFactura() {
		return factura;
	}
}
