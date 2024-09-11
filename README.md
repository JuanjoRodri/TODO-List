TODO List - Java Console Application
Este proyecto es una aplicación de consola para gestionar una lista de tareas (TODO list) utilizando Java y MySQL. Permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre las tareas almacenadas en una base de datos.

Clases Principales
1. Main
Función: Punto de entrada del programa. Presenta el menú principal y gestiona la interacción inicial del usuario, redirigiéndolo a las distintas opciones disponibles para manejar las tareas.
2. Tarea
Función: Representa una tarea individual. Contiene atributos como id_tarea, descripcion, y prioridad. Proporciona métodos para obtener y establecer estos atributos.
3. Opciones
Función: Contiene el menú principal y las opciones disponibles para el usuario. Desde aquí, se redirige a las funciones CRUD específicas.
4. Create
Función: Proporciona métodos para añadir nuevas tareas a la base de datos. Interactúa con la base de datos MySQL para insertar nuevos registros.
5. Read
Función: Ofrece funcionalidades para leer y mostrar tareas desde la base de datos. Incluye métodos para leer una tarea específica o todas las tareas, así como para imprimirlas en la consola.
6. Update
Función: Permite actualizar tareas existentes en la base de datos. Los usuarios pueden modificar la descripción y la prioridad de una tarea específica.
7. Delete
Función: Gestiona la eliminación de tareas de la base de datos. Permite a los usuarios eliminar una tarea seleccionada por su ID.
8. MenuPrincipal
Función: Proporciona una interfaz de navegación más detallada, permitiendo al usuario acceder a funciones adicionales, como listar todas las tareas o volver al menú principal.
Base de Datos

MySQL: Se utiliza para almacenar y gestionar las tareas. La base de datos tareas contiene una tabla tarea con campos para el ID de la tarea, descripción y prioridad.
Funcionamiento General
Inicio: El programa inicia presentando un menú principal, donde el usuario puede elegir entre las opciones CRUD.
Interacción: Dependiendo de la opción seleccionada, se llama a la clase correspondiente para manejar la acción (Crear, Leer, Actualizar o Eliminar).
Conexión: Cada operación realiza una conexión con la base de datos MySQL para realizar la acción solicitada.
Continuación: Tras completar una operación, el usuario puede optar por realizar otra acción o salir del programa.
