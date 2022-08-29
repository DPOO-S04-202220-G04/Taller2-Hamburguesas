package Modulo;
import java.util.*;

public class ProductoAjustado implements Producto{
	private int precio; //justificar
	private String factura;//justificar 
	private int calorias; //justificar
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	private ProductoMenu base;
	
	public ProductoAjustado(ProductoMenu base) {
		this.base = base;
		precio = base.getPrecio();
		calorias = base.getCalorias();
		factura = base.generarTextoFactura();
		agregados = new ArrayList<Ingrediente>();
		eliminados = new ArrayList<Ingrediente>();
	}
	
	//JUSTIFICAR
	public void agregarIngrediente(Ingrediente agregado) {
		precio += agregado.getCostoAdicional();
		agregados.add(agregado);
		calorias += agregado.getCalorias();
		factura += "	" + "+ " + agregado.getNombre() + " $" + agregado.getCostoAdicional() + "\n";
	}
	
	//JUSTIFICAR
	public void eliminarIngrediente(Ingrediente eliminar) {
		eliminados.add(eliminar);
		calorias -= eliminar.getCalorias();
		factura += "	" + "- " + eliminar.getNombre() + "\n";
	}
	
	public String getNombre() {
		return base.getNombre();
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public int getCalorias() {
		return calorias;
	}
	
	public String generarTextoFactura() {
		return factura;
	}
}
