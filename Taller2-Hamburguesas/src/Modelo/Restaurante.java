package Modelo;
import java.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Restaurante {
	
	// revisar si ponerlos publicos o privados !!!
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Producto> menuBase;
	private ArrayList<Combo> combos;
	
	public Restaurante() {
		ingredientes = new ArrayList<Ingrediente>();
		menuBase = new ArrayList<Producto>();
		combos = new ArrayList<Combo>();
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		
	}
	
	public void cerrarYGuardarPedido() {}
	
	public Pedido getPedidoEnCurso() {
		
	}
	
	public ArrayList<Producto> getMenuBase(){
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
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
		linea = br.readLine();
		while (linea != null) { // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(",");
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
		linea = br.readLine();
		while (linea != null){ // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(",");
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
		linea = br.readLine();
		while (linea != null){ // Cuando se llegue al final del archivo, linea tendrá el valor null
			// Separar los valores que estaban en una línea
			String[] partes = linea.split(",");
			String nombreCombo = partes[0];
			int descuento = Integer.parseInt(partes[1]);
			//Falta remover el simbolo de porcentaje antes de volver int !!!
			Combo elCombo = new Combo(nombreCombo, descuento);
			for(int i=2; i<partes.length; i++) {
		         String nombreProducto = partes[i];
		         // buscar prodcuto en la lista de productos
		         Producto prod = buscarProducto(menuBase, nombreProducto);
		         // si existe, agregar producto a combo
		         if (prod != null) {
		        	 elCombo.agregarItemACombo(prod);
		         }
		    }
			// calcular precio del combo??? luego de agregar todos los productos
			combos.add(elCombo);
			linea = br.readLine(); // Leer la siguiente línea
		}
		br.close();
	}
	
	private Producto buscarProducto(ArrayList<Producto> menuBase, String nombreProducto){
		Producto elProducto = null;
		for (int i = 0; i<menuBase.size()-1 && elProducto == null; i++){
			Producto unProducto = menuBase.get(i);
			if (unProducto.getNombre().equals(nombreProducto)){
				elProducto = unProducto;
			}
		}
		return elProducto;
	}
}
