package Modelo;

public class Pedido {
	private int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	
	public Pedido(String nombreCliente, String direccionCliente) {}
	
	public int getIdPedido() {}
	
	public void agregarProducto(Producto nuevoItem) {}
	
	private int getPrecioNetoPedido() {}
	
	private int getPrecioTotalPedido() {}
	
	private int getPrecioIVAPedido() {}
	
	private String generarTextoFactura() {}
	
	public void guardarFactura(File archivo) {}
}
