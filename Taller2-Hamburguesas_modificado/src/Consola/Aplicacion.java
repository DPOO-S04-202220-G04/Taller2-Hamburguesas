package Consola;
import java.util.*;

import Modulo.Pedido;
import Modulo.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class Aplicacion {
	private Restaurante restaurante;
	
	public Aplicacion() throws IOException {
		restaurante = new Restaurante();
	}
	
	public void mostrarMenu() {
		
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar pedido y guardar factura");
		System.out.println("5. Consultar la información de un pedido");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	public void ejecutarOpcion() throws IOException {
		System.out.println("Restaurante Tienda de hamburguesas\n");

		boolean continuar = true;
		while (continuar){
			try{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 2)
					ejecutarIniciarNuevoPedido();
				else if (opcion_seleccionada == 3 && restaurante.getPedidoEnCurso() != null)
					ejecutarAgregarElemento();
				else if (opcion_seleccionada == 4 && restaurante.getPedidoEnCurso() != null)
					ejecutarCerrarPedidoGuardarFactura();
				else if (opcion_seleccionada == 5)
					ejecutarInfoDeUnPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante.getPedidoEnCurso() == null)
				{
					System.out.println("Para poder ejecutar esta opción primero debe iniciar un pedido.");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	// Justificar este método?
	// 1. MOSTRAR MENÚ
	private int ejecutarMostrarMenu() {
		System.out.println("\nOpciones de menú:\n1. Menú de Combos\n2. Menú de Productos\n3. Menú de bebidas\n4. Regresar al menú principal");
		int opcion_seleccionada = Integer.parseInt(input("\n¿Qué menú desea ver? "));
		
		if (opcion_seleccionada == 1) {
			ArrayList<Combo> combos = restaurante.getCombos();
			int contador = 1;
			System.out.println("\nMENÚ DE COMBOS");
			for (Combo com : combos) {
				System.out.println("\n" + contador + ". " + com.toString());
				contador += 1;
			}
		}
		else if (opcion_seleccionada == 2) {
			ArrayList<ProductoMenu> productos = restaurante.getMenuBase();
			int contador = 1;
			System.out.println("\nMENÚ DE PRODUCTOS");
			for (Producto pro : productos) {
				System.out.println(contador + ". " + pro.toString());
				contador += 1;
			}
		}
		else if (opcion_seleccionada == 3) {
			ArrayList<Bebida> bebidas = restaurante.getBebidas();
			int contador = 1;
			System.out.println("\nMENÚ DE BEBIDAS");
			for (Bebida beb : bebidas) {
				System.out.println(contador + ". " + beb.toString());
				contador += 1;
			}
		}
		return opcion_seleccionada;
	}
	
	// 2. INICIAR PEDIDO
	private void ejecutarIniciarNuevoPedido() {
		String nombreCliente = input("Ingrese su nombre");
		String direccionCliente = input("Ingrese su dirección");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
	}
	
	// Mostrar ingredientes
	private void mostrarIngredientes() {
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		System.out.println("\nMenú de Ingredientes: ");
		int contador = 1;
		for (Ingrediente in : ingredientes) {
			System.out.println(contador + ". " + in.toString());
			contador += 1;
		}
		System.out.println(contador + ". " + "Si finalizó la edición del producto, ingrese (-1)");
	}
	
	// 3. AGREGAR UN ELEMENTO AL PEDIDO
	private void ejecutarAgregarElemento() {
		boolean continuar = true;
		while (continuar){
			try{
				System.out.println("\nPedido " + restaurante.getPedidoEnCurso().getIdPedido() + " en curso...");
				int num = ejecutarMostrarMenu();
				if (num == 1) {
					int opcion_seleccionada = Integer.parseInt(input("\nIngrese el número del elemento que desea agregar al pedido"));
					Combo unCombo = restaurante.getCombos().get(opcion_seleccionada-1);
					restaurante.getPedidoEnCurso().agregarProducto(unCombo);
				}
				else if (num == 2) {
					int opcion_seleccionada = Integer.parseInt(input("\nIngrese el número del elemento que desea agregar al pedido"));
					ProductoMenu unProductoMenu = restaurante.getMenuBase().get(opcion_seleccionada-1);
					int quiereIng = Integer.parseInt(input("\n¿Desea agregar o eliminar algún ingrediente? 1(Sí)/2(No) "));
					if (quiereIng == 1) {
						ProductoAjustado unProductoAjustado = new ProductoAjustado(unProductoMenu);
						boolean continuar2 = true;
						while (continuar2) {
							
							mostrarIngredientes();
							int numIngrediente = Integer.parseInt(input("\nIngrese el número del ingrediente que desea agregar o eliminar"));
							if (numIngrediente == -1) {
								continuar2 = false;
								restaurante.getPedidoEnCurso().agregarProducto(unProductoAjustado);
							}
							else {
								int agregarOEliminar = Integer.parseInt(input("\nDesea agregarlo o eliminarlo? 1(agregar)/2(eliminar)"));
								Ingrediente unIngrediente = restaurante.getIngredientes().get(numIngrediente-1);
								if (agregarOEliminar == 1) {
									unProductoAjustado.agregarIngrediente(unIngrediente);
								}
								else if (agregarOEliminar == 2) {
									unProductoAjustado.eliminarIngrediente(unIngrediente);
								}
							}
						}
					}
					else if (quiereIng == 2) {
						restaurante.getPedidoEnCurso().agregarProducto(unProductoMenu);
					}
				}	
				else if (num == 3) {
					int opcion_seleccionada = Integer.parseInt(input("\nIngrese el número de la bebida que desea agregar al pedido"));
					Bebida unaBebida = restaurante.getBebidas().get(opcion_seleccionada-1);
					restaurante.getPedidoEnCurso().agregarProducto(unaBebida);
				
				}
				else if (num == 4) {
					continuar = false;
				}
			}
			catch (NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	// 4. Cerrar el pedido y guardar la factura
	private void ejecutarCerrarPedidoGuardarFactura() throws IOException {
		Pedido pedido = restaurante.getPedidoEnCurso();
		boolean hayPedidoIgual =  buscarPedidoIgual(pedido);
		if (hayPedidoIgual == true) {
			System.out.println("SI ha habido un pedido igual");
		}
		else {
			System.out.println("NO ha habido un pedido igual");
		}
		int ID = pedido.getIdPedido();
		File archivo = new File("data/factura_pedido_" + ID + ".txt");
		pedido.guardarFactura(archivo);
		restaurante.cerrarYGuardarPedido();
		System.out.println("\nSu pedido con ID: " + ID + " fue guardado con éxito!");
	}
	
	private boolean buscarPedidoIgual(Pedido elPedido) {
		 ArrayList<Pedido> pedidos = restaurante.getPedidos() ;
		 for (int i = 0; i<pedidos.size(); i++){
			 Pedido unPedido = pedidos.get(i);
			 if (elPedido.equals(unPedido)) {
				 return true;
			 }
		 }
		 return false;
	}
	
	//5. Información de un pedido
	private void ejecutarInfoDeUnPedido() throws IOException {
		int id = Integer.parseInt(input("Ingrese el ID del pedido que desea consultar"));
		File archivo = new File("data/factura_pedido_" + id + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine(); 
		System.out.println("");
		while (linea != null) {
			System.out.println(linea);
			linea = br.readLine();
		}
		br.close();
	}
	
	// PARA QUE FUNCIONE INPUT
	public String input(String mensaje){
		try{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e){
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	// MAIN
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();
	}
}
