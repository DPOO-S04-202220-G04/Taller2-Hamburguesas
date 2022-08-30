package Modulo;
import java.util.*;

import Modulo.Combo;

import java.lang.Math;

public class Combo implements Producto{
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> itemsCombo; //JUSTIFICAR POR QUÉ AGREGAMOS ESTO
	private int precio;
	private int calorias;
	
	public Combo(String nombre, double descuento) {
		nombreCombo = nombre;
		this.descuento = descuento;
		this.precio = 0;
		this.calorias = 0;
		itemsCombo = new ArrayList<Producto>();
		
	}
	
	public void agregarItemACombo(Producto itemCombo) {
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
	
	public int getCalorias() {
		for (Producto producto : itemsCombo) {
			this.calorias += producto.getCalorias();
		}
		return calorias; 
	}
	
	public String generarTextoFactura() {
		String factura = "+ " + nombreCombo + " $" + precio + ":\n";
		for (Producto p : itemsCombo) {
			factura += "	" + p.getNombre() + " (" + p.getCalorias() +" cal.)\n";
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
	
	// Overriding equals() to compare two Combos objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Combo or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Combo)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Combo unCombo = (Combo) o;
         
        // Compare the data members and return accordingly
        return  this.nombreCombo.equals(unCombo.getNombre());
    }
}

