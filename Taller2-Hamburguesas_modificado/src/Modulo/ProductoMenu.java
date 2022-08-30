package Modulo;

import Modulo.ProductoMenu;

//import Modulo.Producto;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	private int calorias;
	
	public ProductoMenu(String nombre, int precioBase, int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPrecio() {
		return precioBase;
	}
	
	public int getCalorias() {
		return calorias;
	}
	 
	public String generarTextoFactura() {
		String factura = "+ " + nombre + " ("+calorias+ "cal.) $" + precioBase + "\n";
		return factura;
	}
	
	public String toString() {
		return nombre + " $" + precioBase;
	}
	
	// Overriding equals() to compare two ProductoMenu objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of ProductoMenu or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ProductoMenu)) {
            return false;
        }
         
        // typecast o to ProductoMenu so that we can compare data members
        ProductoMenu unProductoMenu = (ProductoMenu) o;
         
        // Compare the data members and return accordingly
        return  this.getNombre().equals(unProductoMenu.getNombre());
    }
} 