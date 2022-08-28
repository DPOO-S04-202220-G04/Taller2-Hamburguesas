package Consola;
import java.util.*;
import Modelo.*;
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
				else if (opcion_seleccionada == 3)
					ejecutarAgregarElemento();
				else if (opcion_seleccionada == 4)
					ejecutarCerrarPedidoGuardarFactura();
				else if (opcion_seleccionada == 5)
					ejecutarInfoDeUnPedido();
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
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
	public int ejecutarMostrarMenu() {
		System.out.println("\nOpciones de menú:\n1. Menú de Combos\n2. Menú de Productos\n3. Regresar al menú principal");
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
		return opcion_seleccionada;
	}
	
	// 2. INICIAR PEDIDO
	public void ejecutarIniciarNuevoPedido() {
		String nombreCliente = input("Ingrese su nombre");
		String direccionCliente = input("Ingrese su dirección");
		restaurante.iniciarPedido(nombreCliente, direccionCliente);
	}
	
	// Mostrar ingredientes
	public void mostrarIngredientes() {
		ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
		System.out.println("Menú de Ingredientes: ");
		int contador = 1;
		for (Ingrediente in : ingredientes) {
			System.out.println(contador + ". " + in.toString());
			contador += 1;
		}
		System.out.println(contador + ". " + "Ingrese -1 si ya se finalizó la edición del producto");
	}
	
	// 3. AGREGAR UN ELEMENTO AL PEDIDO
	public void ejecutarAgregarElemento() {
		boolean continuar = true;
		while (continuar){
			try{
				int num = ejecutarMostrarMenu();
				if (num == 1) {
					int opcion_seleccionada = Integer.parseInt(input("Ingrese el número del elemento que desea agregar al pedido"));
					Combo unCombo = restaurante.getCombos().get(opcion_seleccionada-1);
					restaurante.getPedidoEnCurso().agregarProducto(unCombo);
				}
				else if (num == 2) {
					int opcion_seleccionada = Integer.parseInt(input("Ingrese el número del elemento que desea agregar al pedido"));
					ProductoMenu unProductoMenu = restaurante.getMenuBase().get(opcion_seleccionada-1);
					int quiereIng = Integer.parseInt(input("¿Desea agregar/eliminar algún ingrediente? 1(Sí)/2(No) "));
					if (quiereIng == 1) {
						ProductoAjustado unProductoAjustado = new ProductoAjustado(unProductoMenu);
						boolean continuar2 = true;
						while (continuar2) {
							
							mostrarIngredientes();
							int numIngrediente = Integer.parseInt(input("Ingrese el número del ingrediente que desea agregar/eliminar"));
							if (numIngrediente == -1) {
								continuar2 = false;
								restaurante.getPedidoEnCurso().agregarProducto(unProductoAjustado);
							}
							else {
								int agregarOEliminar = Integer.parseInt(input("Desea agregarlo o eliminarlo? 1(agregar)/2(eliminar)"));
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
					continuar = false;
				}
			}
			catch (NumberFormatException e){
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	
	// 4. Cerrar el pedido y guardar la factura
	public void ejecutarCerrarPedidoGuardarFactura() throws IOException {
		Pedido pedido = restaurante.getPedidoEnCurso();
		int ID = pedido.getIdPedido();
		File archivo = new File("data/factura_pedido_" + ID + ".txt");
		pedido.guardarFactura(archivo);
		restaurante.cerrarYGuardarPedido();
		System.out.println("Su pedido con ID: " + ID + " fue guardado con éxito!");
	}
	
	//5. Información de un pedido
	public void ejecutarInfoDeUnPedido() throws IOException {
		int id = Integer.parseInt(input("Ingrese el ID del pedido que desea consultar"));
		File archivo = new File("data/factura_pedido_" + id + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(archivo));
		String linea = br.readLine(); 
		while (linea != null) {
			System.out.println(br.readLine());
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