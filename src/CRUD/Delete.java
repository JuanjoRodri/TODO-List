package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase {@code Delete} maneja la eliminación de tareas en la base de datos.
 */

public class Delete {
	
	/**
     * Elimina una tarea de la base de datos por su ID.
     *
     * @param id_tarea El ID de la tarea a eliminar.
     */
	
    public void borrarTarea(int id_tarea) { 
    	
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tareas";
        String user = "root";
        String pass = "";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM tarea WHERE id_tarea = ?")) {

            Class.forName(driver);

            ps.setInt(1, id_tarea);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La tarea fue eliminada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna tarea con ese ID.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, "Error SQL al eliminar tarea", ex);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, "Driver no encontrado", e);
        }
    }

    /**
     * Solicita al usuario que seleccione una tarea para borrar.
     * Muestra un menú con opciones para introducir el ID de la tarea directamente
     * o ver todas las tareas disponibles antes de seleccionar una para borrar.
     */
    
    public void tareaABorrar() { 
        
    	System.out.println("¿Qué tarea desea borrar?");
        opcionesTareaBorrar();
    }
    
    /**
     * Muestra opciones para borrar una tarea, ya sea introduciendo el ID de la tarea directamente
     * o viendo todas las tareas disponibles para seleccionar una.
     */
    
    public void opcionesTareaBorrar() { 
    	
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (true) {
            try {
                System.out.println("1 - Introducir ID de la tarea");
                System.out.println("2 - Ver todas las tareas");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce el ID de la tarea: ");
                        int idTarea = sc.nextInt();
                        confirmarYBorrar(idTarea);
                        return;

                    case 2:
                        List<Tarea> todasTareas = leerTodasLasTareas();
                        if (!todasTareas.isEmpty()) {
                            System.out.println("Tareas disponibles:");
                            for (Tarea t : todasTareas) {
                                System.out.println("ID: " + t.getId_tarea() + ", Descripción: " + t.getDescripcionTarea() + ", Prioridad: " + t.getPrioridadTarea());
                            }

                            System.out.print("Introduce el ID de la tarea que deseas borrar: ");
                            int idTareaBorrar = sc.nextInt();
                            confirmarYBorrar(idTareaBorrar);
                        } else {
                            System.out.println("No hay tareas disponibles.");
                        }
                        return;

                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.next(); // Limpiar la entrada no válida
            }
        }
    }
    
    /**
     * Solicita confirmación al usuario antes de borrar una tarea.
     * Si el usuario confirma, llama al método {@code borrarTarea} para eliminar la tarea de la base de datos.
     *
     * @param idTarea El ID de la tarea a borrar.
     */
    
    public void confirmarYBorrar(int idTarea) { 
    	
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Está seguro que desea borrar la tarea con ID " + idTarea + "? (S/N): ");
        String confirmacion = sc.next();

        if (confirmacion.equalsIgnoreCase("S")) {
            borrarTarea(idTarea);
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    public List<Tarea> leerTodasLasTareas() { /*  Este método utiliza una instancia de la clase Read para obtener y 
    	devolver una lista de todas las tareas almacenadas en la base de datos.
     	Es útil para mostrar las tareas antes de decidir cuál borrar.*/

        Read read = new Read ();
        return read.leerTodasLasTareas();
    }
}
