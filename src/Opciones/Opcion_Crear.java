package Opciones;

import java.util.Scanner;

import CRUD.Create;

public class Opcion_Crear {

	public static void Crear_Tarea () { // Vamos a almacenar  la tarea en una variable 
		
		System.out.println("Introduca la tarea que desea guardar, recuerde tiene un maximo de 200 caracteres.");
		Scanner sc = new Scanner (System.in);
		String descripcionTarea = sc.nextLine();
		Prioridad(descripcionTarea);
		
	}
	
	public static void Prioridad (String descripcionTarea) {
		
		System.out.println("Introduca la prioridad de la tarea del 1 al 5 siendo 1 la prioridad mas baja y 5 la prioridad mas alta.");
		Scanner sc = new Scanner (System.in);
		int prioridadTarea = sc.nextInt();
		Validar_Descripcion_Tarea(descripcionTarea, prioridadTarea);
		
	}
	
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
			        // Aquí puedes ejecutar la lógica que sigue si el usuario confirma "Sí"
			        //System.out.println("ejecucion de guardado");
			        Create crearTarea = new Create ();
			        crearTarea.crearTarea(descripcionTarea, prioridadTarea);
			        preguntarContinuar();
			        break;

			    case "no":
			    case "n":
			        System.out.println("Has confirmado que no.");
			        // Aquí puedes ejecutar la lógica que sigue si el usuario confirma "No"
			        preguntarContinuar();
			        break;

			    default:
			        System.out.println("Respuesta no válida, por favor ingrese 'Sí' o 'No'.");
			        // Lógica para manejar respuestas no válidas
			        break;
		}
	}
	
	public static void preguntarContinuar() {
        System.out.println("¿Desea realizar otra acción? (1- Crear otra tarea / 2- Volver al menú principal)");

        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();

        switch (opcion) {
            case 1:
                Crear_Tarea();  // Volver a crear otra tarea
                break;
            case 2:
                Opciones.ListaOpciones();  // Volver al menú principal
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
                Opciones.ListaOpciones();  // Si la opción es inválida, vuelve al menú principal
                break;
        }
	}
}
	

