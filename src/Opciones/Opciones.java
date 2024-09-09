package Opciones;

import java.util.Scanner;

import CRUD.Read;

public class Opciones {

    private static Scanner scanner = new Scanner(System.in); // Mantener el Scanner abierto

    public static void ListaOpciones() {
        boolean continuar = true;

        // Bucle que se repetirá hasta que el usuario decida salir
        while (continuar) {
            // Mostrar las opciones disponibles
            System.out.println("Bienvenido a listas pendientes.");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1- Crear Tareas");
            System.out.println("2- Ver Tareas");
            System.out.println("3- Editar Tarea");
            System.out.println("4- Borrar tarea");
            System.out.println("5- Salir");

            continuar = OpcionEscogida();  // El bucle continuará mientras el usuario no elija salir
        }
    }

    public static boolean OpcionEscogida() {
        try {
            System.out.print("Introduzca una opción: ");
            int opcionIntroducida = scanner.nextInt();

            // Llamar a confirmarOpcion según la opción seleccionada
            switch (opcionIntroducida) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return confirmarOpcion(opcionIntroducida);  // Devolver si continúa o no
                default:
                    System.out.println("El valor introducido no es correcto.");
                    return true;  // Continuar mostrando las opciones
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Inténtelo de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
            return true;  // Continuar en el menú de opciones
        }
    }

    public static boolean confirmarOpcion(int OpcionElegidaEnNumero) {
        System.out.println("Usted ha elegido la opción " + OpcionElegidaEnNumero + ". ¿Desea continuar? (S/N)");

        String respuesta = scanner.next().toLowerCase(); // Normalizar a minúsculas

        switch (respuesta) {
            case "si":
            case "sí":
            case "s":
                ejecutarOpcion(OpcionElegidaEnNumero);
                return true;  // Seguir mostrando el menú
            case "no":
            case "n":
                System.out.println("Volviendo al menú principal.");
                return true;  // Volver al menú de opciones
            default:
                System.out.println("Respuesta no válida, por favor introduzca S o N.");
                return confirmarOpcion(OpcionElegidaEnNumero);  // Volver a pedir confirmación sin recursividad
        }
    }

    public static void ejecutarOpcion(int OpcionConfirmada) {
        switch (OpcionConfirmada) {
            case 1:
                System.out.println("Caso 1: Crear Tarea.");
                Opcion_Crear.Crear_Tarea();  // Lógica para crear tarea
                break;
            case 2:
                System.out.println("Caso 2: Ver Tareas.");
                Read read = new Read ();
                read.imprimirTodasLasTareas();
                break;
            case 3:
                System.out.println("Caso 3: Editar Tarea.");
                // Lógica para editar tarea
                break;
            case 4:
                System.out.println("Caso 4: Borrar Tarea.");
                // Lógica para borrar tarea
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
