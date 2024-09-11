

package Opciones;

import java.util.Scanner;

import CRUD.Delete;
import CRUD.Read;
import CRUD.Update;

/**
 * La clase {@code Opciones} maneja el menú principal y la navegación entre diferentes opciones.
 */

public class Opciones {

    private static Scanner scanner = new Scanner(System.in); 
    
    /**
     * Muestra el menú principal y gestiona la navegación.
     */
    
    public static void ListaOpciones() { 
    	boolean continuar = true;

        while (continuar) {
        	
            System.out.println("Bienvenido a listas pendientes.");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Crear Tareas");
            System.out.println("2- Ver Tareas");
            System.out.println("3- Editar Tarea");
            System.out.println("4- Borrar tarea");
            System.out.println("5- Salir");

            continuar = OpcionEscogida();
        }
    }
    
    /**
     * Lee la opción seleccionada por el usuario y llama a {@code confirmarOpcion()}.
     *
     * @return {@code true} si se debe continuar mostrando el menú, {@code false} si se debe salir.
     */
    
    public static boolean OpcionEscogida() {
    	try {
            System.out.print("Introduzca una opción: ");
            int opcionIntroducida = scanner.nextInt();

            switch (opcionIntroducida) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return confirmarOpcion(opcionIntroducida);
                default:
                    System.out.println("El valor introducido no es correcto.");
                    return true;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Inténtelo de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
            return true;
        }
    }
    
    /**
     * Solicita confirmación del usuario para continuar con la opción seleccionada.
     *
     * @param OpcionElegidaEnNumero La opción elegida por el usuario.
     * @return {@code true} si se debe continuar mostrando el menú, {@code false} si se debe salir.
     */
    
    public static boolean confirmarOpcion(int OpcionElegidaEnNumero) { 
    	System.out.println("Usted ha elegido la opción " + OpcionElegidaEnNumero + ". ¿Desea continuar? (S/N)");

        String respuesta = scanner.next().toLowerCase();

        switch (respuesta) {
            case "si":
            case "sí":
            case "s":
                ejecutarOpcion(OpcionElegidaEnNumero);
                return true;  // Seguir mostrando el menú
            case "no":
            case "n":
                System.out.println("Volviendo al menú principal.");
                return true;  
            default:
                System.out.println("Respuesta no válida, por favor introduzca S o N.");
                return confirmarOpcion(OpcionElegidaEnNumero);  // Volver a pedir confirmación sin recursividad
        }
    }
    
    /**
     * Ejecuta la lógica correspondiente para la opción confirmada por el usuario.
     *
     * @param OpcionConfirmada La opción confirmada por el usuario.
     */
    
    public static void ejecutarOpcion(int OpcionConfirmada) { 
    	switch (OpcionConfirmada) {
            case 1:
                Opcion_Crear.Crear_Tarea();  // Lógica para crear tarea
                break;
            case 2:
                Read read = new Read ();
                read.imprimirTodasLasTareas();
                break;
            case 3:
                Update update = new Update ();
                update.tareaAEditar();
                break;
            case 4:
                System.out.println("Caso 4: Borrar Tarea.");
                Delete delete = new Delete();
                delete.tareaABorrar();
                break;
            case 5:
                System.out.println("Saliendo del programa...");
                scanner.close();  // Cerrar el scanner antes de salir
                System.exit(0);   // Salir del programa
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
