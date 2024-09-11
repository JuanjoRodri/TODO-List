package Opciones;

import java.util.Scanner;

import CRUD.Create;

/**
 * La clase {@code Opcion_Crear} maneja las opciones relacionadas con la creación de tareas.
 */

public class Opcion_Crear {
	
	/**
     * Solicita al usuario la descripción de una nueva tarea.
     */
	
	public static void Crear_Tarea () { 
		
		System.out.println("Introduca la tarea que desea guardar, recuerde tiene un maximo de 200 caracteres.");
		Scanner sc = new Scanner (System.in);
		String descripcionTarea = sc.nextLine();
		Prioridad(descripcionTarea);
		
	}
	
	/**
     * Solicita al usuario la prioridad de la nueva tarea.
     *
     * @param descripcionTarea La descripción de la nueva tarea.
     */
	
	public static void Prioridad (String descripcionTarea) { 
		
		System.out.println("Introduca la prioridad de la tarea del 1 al 5 siendo 1 la prioridad mas baja y 5 la prioridad mas alta.");
		Scanner sc = new Scanner (System.in);
		int prioridadTarea = sc.nextInt();
		Validar_Descripcion_Tarea(descripcionTarea, prioridadTarea);
		
	}
	
	/**
     * Valida la descripción y la prioridad de la tarea con el usuario antes de guardarla.
     *
     * @param descripcionTarea La descripción de la nueva tarea.
     * @param prioridad        La prioridad de la nueva tarea.
     */
	
	public static void Validar_Descripcion_Tarea (String descripcionTarea, int prioridadTarea) {
		System.out.println("La tarea que desea guardar es la siguiente : " + descripcionTarea);
		System.out.println("La prioridad de la tarea es de " + prioridadTarea);
		System.out.println("Es correcto? S/N");
		Scanner introducirConfirmacion = new Scanner(System.in); 
			String opcionNormalizada = introducirConfirmacion.next();
			opcionNormalizada = opcionNormalizada.toLowerCase();
			
			switch (opcionNormalizada) {
			    case "si":
			    case "sí":
			    case "s":
			        System.out.println("Has confirmado que sí.");
			        Create crearTarea = new Create ();
			        crearTarea.crearTarea(descripcionTarea, prioridadTarea);
			        preguntarContinuar();
			        break;

			    case "no":
			    case "n":
			        System.out.println("Has confirmado que no.");
			        preguntarContinuar();
			        break;

			    default:
			        System.out.println("Respuesta no válida, por favor ingrese 'Sí' o 'No'.");
			        break;
		}
	}
	
	/**
     * Pregunta al usuario si desea realizar otra acción o volver al menú principal.
     */
	
	public static void preguntarContinuar() { 
        System.out.println("¿Desea realizar otra acción? (1- Crear otra tarea / 2- Volver al menú principal)");

        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                Crear_Tarea();  
                break;
            case 2:
                Opciones.ListaOpciones(); 
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
                Opciones.ListaOpciones();  
                break;
        }
	}
}
	

