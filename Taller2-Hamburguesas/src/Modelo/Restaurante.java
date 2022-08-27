package Modelo;
import java.util.*;
import java.io.File;

public class Restaurante {
	
	public Restaurante() {}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		
	}
	
	public void cerrarYGuardarPedido() {}
	
	public Pedido getPedidoEnCurso() {}
	
	public ArrayList<Producto> getMenuBase(){}
	
	public ArrayList<Ingrediente> getIngredientes(){}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) {}
	
	private void cargarIngredientes(File archivoIngredientes) {}
	
	private void cargarMenu(File archivoMenu) {}
	
	private void cargarCombos(File archivoCombos) {}
	
	
}
