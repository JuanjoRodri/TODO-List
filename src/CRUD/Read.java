package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Opciones.Opciones;

public class Read {

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

    public List<Tarea> leerTodasLasTareas() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/tareas";
        String user = "root";
        String pass = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Tarea> tareas = new ArrayList<>();

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM tarea";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId_tarea(rs.getInt("id_tarea"));
                t.setDescripcionTarea(rs.getString("descripcion"));
                t.setPrioridadTarea(rs.getInt("prioridad"));
                tareas.add(t);
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

        return tareas;
    }

    public void imprimirTodasLasTareas() {
        List<Tarea> tareas = leerTodasLasTareas();
        for (Tarea tarea : tareas) {
            System.out.println("ID: " + tarea.getId_tarea());
            System.out.println("Descripción: " + tarea.getDescripcionTarea());
            System.out.println("Prioridad: " + tarea.getPrioridadTarea());
            System.out.println("-------------------------");
        }
        preguntarContinuar();
    }

    public void preguntarContinuar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (true) {
            try {
                System.out.println("¿Desea realizar otra acción? (1- Ver una tarea específica / 2- Volver al menú principal)");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        tareaID(sc);
                        return; // Salir del bucle y método
                    case 2:
                        Opciones.ListaOpciones();  // Volver al menú principal
                        return; // Salir del bucle y método
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.next();  // Limpiar la entrada no válida
            }
        }
    }

    public void tareaID(Scanner sc) {
        int id_tarea = 0;

        while (true) {
            try {
                System.out.println("Escriba el número de la tarea que quiere ver:");
                id_tarea = sc.nextInt();
                Tarea tarea = leerTarea(id_tarea);

                if (tarea != null) {
                    System.out.println("ID: " + tarea.getId_tarea());
                    System.out.println("Descripción: " + tarea.getDescripcionTarea());
                    System.out.println("Prioridad: " + tarea.getPrioridadTarea());
                    break; // Salir del bucle si se encuentra la tarea
                } else {
                    System.out.println("No se encontró ninguna tarea con ID " + id_tarea);
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.next();  // Limpiar la entrada no válida
            }
        }

        preguntarContinuar();
    }
}

