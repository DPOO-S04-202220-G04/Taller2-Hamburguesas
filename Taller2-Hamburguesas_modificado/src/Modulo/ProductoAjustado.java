package Modulo;
import java.util.*;

import Modulo.Ingrediente;
import Modulo.ProductoAjustado;

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
	
	public ArrayList<Ingrediente> getAgregados (){
		return agregados;
	}
	
	public ArrayList<Ingrediente> getEliminados(){
		return eliminados;
	}
	
	// Overriding equals() to compare two ProductoAjustado objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of ProductoAjustado or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ProductoAjustado)) {
            return false;
        }
         
        // typecast o to ProductoMenu so that we can compare data members
        ProductoAjustado unProductoAjustado = (ProductoAjustado) o;
        
        // Compare the data members and return accordingly
        if (this.agregados.size() == unProductoAjustado.agregados.size()) {
        	if (this.eliminados.size() == unProductoAjustado.eliminados.size()){
        		
        		ArrayList<Ingrediente> unaListaAgregados = unProductoAjustado.getAgregados();
        	    ArrayList<Ingrediente> miListaAgregados = this.getAgregados();
        	       
        	    boolean igualesAgregados = CompararListas (unaListaAgregados, miListaAgregados);
        	    
        	    if (igualesAgregados == true) {
	        	    ArrayList<Ingrediente> unaListaEliminados = unProductoAjustado.getEliminados();
	        	    ArrayList<Ingrediente> miListaEliminados = this.getEliminados();
	        	       
	        	    boolean igualesEliminados = CompararListas (unaListaEliminados, miListaEliminados);
	        	    
	        	    if (igualesEliminados == true) {
	        	    	return true;
	        	    }
	        	    else {
	        	    	return false;
	        	    }
        		
        	    }
        	    else {
        	    	return false;
        	    }
        		
        	}
        		
        }
		return false;
    }
    
   private boolean CompararListas (ArrayList<Ingrediente> unaListaIngrediente, ArrayList<Ingrediente> miListaIngrediente) {
	   
       // Compare the data members and return accordingly
       if (unaListaIngrediente.size() == miListaIngrediente.size()) {
       	for (int i = 0; i<unaListaIngrediente.size(); i++){
       		Ingrediente unIngrediente= unaListaIngrediente.get(i);
       		
       		boolean encontrar = false; 
	   			for (int j = 0; j<miListaIngrediente.size(); j++){
	   				Ingrediente miIngrediente= miListaIngrediente.get(j);
	      			if (miIngrediente.equals(unIngrediente)) {
	      				encontrar = true;
	      				break;
	      			 }
	   			}
	   			
	   			if (encontrar == false) {
	      			return false;
	      		}
	   		}
       	return true;
       }
       return false;
   }
}
