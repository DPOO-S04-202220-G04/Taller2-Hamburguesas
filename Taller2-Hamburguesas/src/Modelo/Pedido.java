package Modelo;

import java.util.ArrayList;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Pedido {
	//private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido; 

	
	public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		itemsPedido = new ArrayList<Producto>();
		
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		itemsPedido.add(nuevoItem);
	}
	
	private int getPrecioNetoPedido() {
		int precio = 0;
		for (Producto prod: itemsPedido) {
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
		for (Producto producto : itemsPedido) {
			factura += producto.generarTextoFactura();
		}
		String precioNeto = "Precio Neto = $" + getPrecioNetoPedido() + "\n";
		String precioIVA = "Precio IVA (19%) = $" + getPrecioIVAPedido() + "\n";
		String precioTotal = "Precio Total = $" + getPrecioTotalPedido() + "\n";
		String facturaCompleta = id + nombreRestaurante + infoCliente + titulo + factura + precioNeto +
				precioIVA + precioTotal;
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
}
