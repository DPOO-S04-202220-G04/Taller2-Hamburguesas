package Modelo;
import java.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Restaurante {
	
	// revisar si ponerlos publicos o privados !!!
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	
	public Restaurante() throws IOException {
		ingredientes = new ArrayList<Ingrediente>();
		menuBase = new ArrayList<ProductoMenu>();
		combos = new ArrayList<Combo>();
		pedidos = new ArrayList<Pedido>();
		pedidoEnCurso = null;
		File ingredientesFile = new File("data/ingredientes.txt");
		File combosFile = new File("data/combos.txt");
		File menuFile = new File("data/menu.txt");
		cargarInformacionRestaurante(ingredientesFile, menuFile, combosFile);
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombreCliente, direccionCliente, pedidos.size());
	}
	
	public void cerrarYGuardarPedido() {
		pedidos.add(pedidoEnCurso);
		pedidoEnCurso = null;
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	
	public ArrayList<ProductoMenu> getMenuBase(){
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
	}
	
	//Justificar por qué se agregó esto
	public ArrayList<Combo> getCombos(){
		return combos;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
	}
	
	private void cargarIngredientes(File archivoIngredientes) throws IOException{
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		//linea = br.readLine();
		while (linea != null) { // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreIngrediente = partes[0];
			int costoAdicional = Integer.parseInt(partes[1]);
			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente, costoAdicional);
			ingredientes.add(elIngrediente);
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}

	
	private void cargarMenu(File archivoMenu) throws IOException {
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		//linea = br.readLine();
		while (linea != null){ // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precioBase = Integer.parseInt(partes[1]);
			ProductoMenu elProductoMenu = new ProductoMenu(nombre, precioBase);
			menuBase.add(elProductoMenu);
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}
	
	private void cargarCombos(File archivoCombos) throws IOException {
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		//linea = br.readLine();
		while (linea != null){ // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombreCombo = partes[0];
			double descuento = Double.parseDouble(partes[1].substring(0,partes[1].length()-1))/100;
			Combo elCombo = new Combo(nombreCombo, descuento);
			for(int i=2; i<partes.length; i++) {
		         String nombreProducto = partes[i];
		         // buscar producto en la lista de productos
		         Producto prod = buscarProducto(menuBase, nombreProducto);
		         // si existe, agregar producto a combo
		         if (prod != null) {
		        	 elCombo.agregarItemACombo(prod);
		         }
		    }
			elCombo.getPrecio();
			combos.add(elCombo);
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}
	
	private Producto buscarProducto(ArrayList<ProductoMenu> menuBase, String nombreProducto){
		Producto elProducto = null;
		for (int i = 0; i<menuBase.size() && elProducto == null; i++){
			Producto unProducto = menuBase.get(i);
			if (unProducto.getNombre().equals(nombreProducto)){
				elProducto = unProducto;
			}
		}
		return elProducto;
	}
}
