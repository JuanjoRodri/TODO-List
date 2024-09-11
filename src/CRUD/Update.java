package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Opciones.Opciones;

/**
 * La clase {@code Update} maneja la actualización de tareas existentes en la base de datos.
 */

public class Update {
	
	/**
     * Actualiza una tarea en la base de datos.
     *
     * @param id_tarea          El ID de la tarea a actualizar.
     * @param nuevaDescripcion  La nueva descripción de la tarea.
     * @param nuevaPrioridad    La nueva prioridad de la tarea.
     */
	
	public void editarTarea(int id_tarea, String nuevaDescripcion, int nuevaPrioridad) {
	    String driver = "com.mysql.cj.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/tareas";
	    String user = "root";
	    String pass = "";
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, user, pass);

	        String query = "UPDATE tarea SET descripcion = ?, prioridad = ? WHERE id_tarea = ?";
	        ps = conn.prepareStatement(query);
	        ps.setString(1, nuevaDescripcion);
	        ps.setInt(2, nuevaPrioridad);
	        ps.setInt(3, id_tarea);

	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("La tarea fue actualizada exitosamente.");
	        } else {
	            System.out.println("No se encontró ninguna tarea con ese ID.");
	        }

	    } catch (SQLException ex) {
	        Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	            Opciones.ListaOpciones();
	        } catch (SQLException ex) {
	            Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	}
	
	/**
     * Solicita al usuario qué tarea desea editar.
     */
	
	public void tareaAEditar () { 
		
		System.out.println("Que tarea desea editar?");
		opcionesTareaEditar();
		
	}
	
	/**
     * Muestra opciones para editar una tarea, ya sea introduciendo el ID directamente o viendo todas las tareas.
     */

	public void opcionesTareaEditar() {
	    Scanner sc = new Scanner(System.in);
	    int opcion = 0;

	    while (true) {
	        try {
	            System.out.println("1 - Introducir id de la tarea");
	            System.out.println("2 - Ver todas las tareas");
	            opcion = sc.nextInt();

	            switch (opcion) {
	                case 1:
	                    System.out.print("Introduce el ID de la tarea: ");
	                    int idTarea = sc.nextInt();
	                    Tarea tarea = leerTarea(idTarea);

	                    if (tarea != null) {
	                        System.out.println("Tarea encontrada: " + tarea.getDescripcionTarea());
	                    } else {
	                        System.out.println("No se encontró ninguna tarea con ese ID.");
	                    }
	                    return; // Salir del bucle y método

	                case 2:
	                    List<Tarea> todasTareas = leerTodasLasTareas();

	                    if (!todasTareas.isEmpty()) {
	                        System.out.println("Tareas disponibles:");
	                        for (Tarea t : todasTareas) {
	                            System.out.println("ID: " + t.getId_tarea() + ", Descripción: " + t.getDescripcionTarea() + ", Prioridad: " + t.getPrioridadTarea());
	                        }

	                        System.out.print("Introduce el ID de la tarea que deseas editar: ");
	                        int idTareaEditar = sc.nextInt();
	                        Tarea tareaEditar = leerTarea(idTareaEditar);

	                        if (tareaEditar != null) {
	                            System.out.println("Tarea seleccionada: " + tareaEditar.getDescripcionTarea());
	                            datosAEditar(tareaEditar.getId_tarea());
	                        } else {
	                            System.out.println("No se encontró ninguna tarea con ese ID.");
	                        }
	                    } else {
	                        System.out.println("No hay tareas disponibles.");
	                    }
	                    return; // Salir del bucle y método

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
     * Lee una tarea específica de la base de datos por su ID.
     *
     * @param id_tarea El ID de la tarea a leer.
     * @return Un objeto {@code Tarea} con los datos leídos, o {@code null} si no se encuentra.
     */
	
	public Tarea leerTarea(int id_tarea) { 
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tareas";
        String user = "root";
        String pass = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Tarea t = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM tarea WHERE id_tarea = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id_tarea);
            rs = ps.executeQuery();

            if (rs.next()) {
                t = new Tarea();
                t.setId_tarea(rs.getInt("id_tarea"));
                t.setDescripcionTarea(rs.getString("descripcion"));
                t.setPrioridadTarea(rs.getInt("prioridad"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return t;
    }
	 
	/**
     * Lee todas las tareas de la base de datos.
     *
     * @return Una lista de objetos {@code Tarea} con todas las tareas.
     */
	
	public List<Tarea> leerTodasLasTareas() { 
				
		Read read = new Read ();
		return read.leerTodasLasTareas();
        
    }
	
	/**
     * Solicita al usuario la nueva descripción y prioridad de una tarea para actualizarla.
     *
     * @param idTarea El ID de la tarea a editar.
     */
	
	public void datosAEditar(int idTarea) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la descripcion a editar: ");
		String nuevaDescripcion = sc.nextLine();
		System.out.println("Introduce la prioridad a editar: ");
		int nuevaPrioridad = sc.nextInt();
		editarTarea(idTarea, nuevaDescripcion, nuevaPrioridad);
		Opciones.ListaOpciones();
	}
	
}
