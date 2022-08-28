package Consola;
import java.util.*;
import java.io.InputStreamReader;

public class Aplicacion {
	private Restaurante restaurante();
	
	public Aplicacion() {
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
	
	public void ejecutarOpcion(int opcionSeleccionada) {
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

		boolean continuar = true;
		while (continuar){
			try{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarMostrarMenu();
				else if (opcion_seleccionada == 2 && calculadora != null)
					ejecutarAtletasPorAnio();
				else if (opcion_seleccionada == 3 && calculadora != null)
					ejecutarMedallasEnRango();
				else if (opcion_seleccionada == 4 && calculadora != null)
					ejecutarAtletasPorPais();
				else if (opcion_seleccionada == 5 && calculadora != null)
					ejecutarPaisConMasMedallistas();
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
	public void ejecutarMostrarMenu() {
		System.out.println("Opciones de menú:\n1. Menú de Ingredientes\n2. Menú de Combos\n3. Menú de Productos");
		int opcion_seleccionada = Integer.parseInt(input("¿Qué menú desea ver? "));
		if (opcion_seleccionada == 1) {
			
		}
		else if (opcion_seleccionada == 2) {
			
		}
		else if (opcion_seleccionada == 3) {
			
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
