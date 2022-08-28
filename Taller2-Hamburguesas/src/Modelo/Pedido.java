package Modelo;

import java.util.ArrayList;

public class Pedido {
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productosPedido; //JUSTIFICAR POR QUÉ AGREGAMOS ESTO
	
	public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		
	}
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		
	}
	
	private int getPrecioNetoPedido() {}
	
	private int getPrecioTotalPedido() {}
	
	private int getPrecioIVAPedido() {}
	
	private String generarTextoFactura() {}
	
	public void guardarFactura(File archivo) {}
}
