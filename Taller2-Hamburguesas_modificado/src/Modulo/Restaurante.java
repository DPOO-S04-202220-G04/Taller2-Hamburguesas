package Modulo;

import java.util.*;

import Modulo.Pedido;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Restaurante {
	
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private ArrayList<Bebida> bebidas;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	
	public Restaurante() throws IOException {
		ingredientes = new ArrayList<Ingrediente>();
		menuBase = new ArrayList<ProductoMenu>();
		combos = new ArrayList<Combo>();
		bebidas = new ArrayList<Bebida>();
		pedidos = new ArrayList<Pedido>();
		pedidoEnCurso = null;
		File ingredientesFile = new File("data/ingredientes.txt");
		File combosFile = new File("data/combos.txt");
		File menuFile = new File("data/menu.txt");
		File bebidasFile = new File("data/bebidas.txt");
		cargarInformacionRestaurante(ingredientesFile, menuFile, combosFile, bebidasFile);
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
	
	//Justificar
	public ArrayList<Pedido> getPedidos(){
		return pedidos;
	}
	
	//Justificar por qué se agregó esto
	public ArrayList<Combo> getCombos(){
		return combos;
	}
	
	public ArrayList<Bebida> getBebidas(){
		return bebidas;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos, File archivoBebidas) throws IOException{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarBebidas(archivoBebidas);
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
			int calorias = Integer.parseInt(partes[2]);
			Ingrediente elIngrediente = new Ingrediente(nombreIngrediente, costoAdicional, calorias);
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
			int calorias = Integer.parseInt(partes[2]);
			ProductoMenu elProductoMenu = new ProductoMenu(nombre, precioBase, calorias);
			menuBase.add(elProductoMenu);
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}
	private void cargarBebidas(File archivoBebidas) throws IOException{
		// Abrir el archivo y leerlo línea por línea usando un BufferedReader
		BufferedReader br = new BufferedReader(new FileReader(archivoBebidas));
		String linea = br.readLine(); // La primera línea del archivo se ignora porque únicamente tiene los títulos de
										// las columnas
		//linea = br.readLine();
		while (linea != null){ // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(";");
			String nombre = partes[0];
			int precio = Integer.parseInt(partes[1]);
			int calorias = Integer.parseInt(partes[2]);
			Bebida laBebida = new Bebida(nombre, precio, calorias);
			bebidas.add(laBebida);
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
		         Producto beb = buscarBebida(bebidas, nombreProducto);
		         Producto prod = buscarProducto(menuBase, nombreProducto);
		         
		         // si existe, agregar producto a combo
		         if (beb != null){
		        	 elCombo.agregarItemACombo(beb);
		         }
		         if (prod != null) {
		        	 elCombo.agregarItemACombo(prod);
		         }
		         
		         prod = null;
		         beb = null;
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
	
	private Producto buscarBebida(ArrayList<Bebida> bebidas, String nombreProducto) {
		Producto elProducto = null;
		for (int j = 0; j<bebidas.size() && elProducto == null; j++) {
			Producto otroProducto = bebidas.get(j);
			if (otroProducto.getNombre().equals(nombreProducto)) {
				elProducto = otroProducto;
			}
		}
		return elProducto;
	}
}
