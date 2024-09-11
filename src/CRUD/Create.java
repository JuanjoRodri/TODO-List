package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Opciones.Opcion_Crear;

/**
 * La clase {@code Create} maneja la creación de nuevas tareas en la base de datos.
 */

public class Create {

    String driver ="com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/tareas";
    String user= "root";
    String pass="";
    Connection conn=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    /**
     * Crea una nueva tarea en la base de datos.
     *
     * @param descripcion La descripción de la tarea.
     * @param prioridad   La prioridad de la tarea (del 1 al 5).
     */
    
	public void crearTarea(String descripcion, int prioridad){ 
		
		try{
            
            Class.forName(driver);
            conn=DriverManager.getConnection(url,user,pass);
            conn.createStatement();
            
            ps=conn.prepareStatement("INSERT INTO tarea (descripcion, prioridad) VALUES (?, ?);");

            ps.setString(1, descripcion);
            ps.setInt(2, prioridad); 
            ps.executeUpdate();
            System.out.println("ejecucion de guardado");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Algo ha fallado ");
        }catch(ClassNotFoundException e){
            e.printStackTrace();   
            System.out.println("Algo ha fallado ");
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                    Opcion_Crear opcionCrear = new Opcion_Crear();
                    opcionCrear.preguntarContinuar();
                }
            }catch(SQLException ex){
                    
            }
        }
	}
}
