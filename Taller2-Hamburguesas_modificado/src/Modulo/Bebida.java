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
		return "+ " + nombre + " $" + precio + "--> " + calorias + "calorias\n";
	}
	
	public String toString() {
		return nombre + " $" + precio;
	}
	
	// Overriding equals() to compare two Bebida objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Bebida or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Bebida)) {
            return false;
        }
         
        // typecast o to ProductoMenu so that we can compare data members
        Bebida unaBebida = (Bebida) o;
         
        // Compare the data members and return accordingly
        return  this.getNombre().equals(unaBebida.getNombre());
    }
}
