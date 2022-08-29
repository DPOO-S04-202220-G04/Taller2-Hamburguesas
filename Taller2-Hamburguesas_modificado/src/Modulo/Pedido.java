package Modulo;

import java.util.ArrayList;

import Modulo.Pedido;

import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pedido {
	//private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productosPedido; //JUSTIFICAR POR QUÉ AGREGAMOS ESTO

	
	public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		productosPedido = new ArrayList<Producto>();
		
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		productosPedido.add(nuevoItem);
	}
	
	public ArrayList<Producto> getProductosPedido(){
		return productosPedido;
	}
	
	public int getCaloriasTotal() {
		int calorias = 0;
		for (Producto prod: productosPedido) {
			calorias += prod.getCalorias();
		}
		return calorias;
	}
	
	private int getPrecioNetoPedido() {
		int precio = 0;
		for (Producto prod: productosPedido) {
			precio += prod.getPrecio();
		}
		return precio;
	}
	
	private int getPrecioTotalPedido() {
		int precioTotal = getPrecioNetoPedido() + getPrecioIVAPedido();
		return precioTotal;
	}
	
	private int getPrecioIVAPedido() {
		double preciodouble = getPrecioNetoPedido();
		int precioIVA = (int)Math.round(preciodouble*0.19);
		return precioIVA;
	}
	
	private String generarTextoFactura() {
		String id = "Factura electronica - " + idPedido + "\n";
		String nombreRestaurante = "Restaurante Tienda de Hamburguesas\n";
		String infoCliente =  "Nombre: " + nombreCliente + "\nDirección: " + direccionCliente + "\n";
		String titulo = "PRODUCTOS\n";
		String factura = "";
		for (Producto producto : productosPedido) {
			factura += producto.generarTextoFactura();
		}
		String caloriasTotal = "Calorias Total = " + getCaloriasTotal() + " calorias\n";
		String precioNeto = "Precio Neto = $" + getPrecioNetoPedido() + "\n";
		String precioIVA = "Precio IVA (19%) = $" + getPrecioIVAPedido() + "\n";
		String precioTotal = "Precio Total = $" + getPrecioTotalPedido() + "\n";
		String facturaCompleta = id + nombreRestaurante + infoCliente + titulo + factura 
				+ caloriasTotal + precioNeto + precioIVA + precioTotal;
		return facturaCompleta;
	}
	
	public void guardarFactura(File archivo) throws IOException {
		String[] factura = generarTextoFactura().split("\n");
		FileWriter file = new FileWriter(archivo);
		for (String s : factura) {
			file.write(s + "\n");
		}
		file.close();
	}
	
	// Overriding equals() to compare two Pedido objects
    @Override
    public boolean equals(Object o) {
 
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Pedido or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Pedido)) {
            return false;
        }
         
        // typecast o to Pedido so that we can compare data members
        Pedido unPedido = (Pedido) o;
        
        ArrayList<Producto> unaListaProducto = unPedido.getProductosPedido();
        ArrayList<Producto> miListaProducto = this.getProductosPedido();
        
        // Compare the data members and return accordingly
        if (unaListaProducto.size() == miListaProducto.size()) {
        	for (int i = 0; i<unaListaProducto.size(); i++){
        		Producto unProducto= unaListaProducto.get(i);
        		
        		boolean encontrar = false; 
	   			for (int j = 0; j<miListaProducto.size(); j++){
	      			Producto miProducto= miListaProducto.get(j);
	      			if (miProducto.equals(unProducto)) {
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
