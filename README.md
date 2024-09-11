# Documentación del Proyecto de Gestión de Tareas

Este proyecto es una aplicación de gestión de tareas implementada en Java que permite crear, leer, actualizar y eliminar tareas en una base de datos MySQL. A continuación, se proporciona una descripción detallada de cada componente del proyecto y sus métodos.

## Índice
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Clase Create](#clase-create)
- [Clase Read](#clase-read)
- [Clase Update](#clase-update)
- [Clase Delete](#clase-delete)
- [Clase Opciones](#clase-opciones)

## Estructura del Proyecto

El proyecto está organizado en paquetes que corresponden a las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y opciones de usuario. A continuación se describe cada clase principal del proyecto.

## Clase Create

La clase `Create` maneja la creación de nuevas tareas en la base de datos.

### Métodos
- **crearTarea(String descripcion, int prioridad)**: Crea una nueva tarea en la base de datos con la descripción y prioridad proporcionadas.

## Clase Read

La clase `Read` se encarga de leer tareas desde la base de datos.

### Métodos
- **leerTarea(int id_tarea)**: Lee una tarea específica de la base de datos utilizando su ID.
- **leerTodasLasTareas()**: Obtiene y devuelve una lista de todas las tareas en la base de datos.
- **imprimirTodasLasTareas()**: Imprime todas las tareas en la consola.
- **preguntarContinuar()**: Pregunta al usuario si desea realizar otra acción.
- **tareaID(Scanner sc)**: Permite al usuario ingresar el ID de una tarea específica para visualizarla.

## Clase Update

La clase `Update` gestiona la actualización de tareas existentes.

### Métodos
- **tareaAEditar()**: Solicita al usuario el ID de la tarea que desea editar y llama a métodos para realizar la actualización.
- **confirmarYActualizar(int idTarea, String nuevaDescripcion, int nuevaPrioridad)**: Pide confirmación antes de actualizar una tarea en la base de datos.

## Clase Delete

La clase `Delete` maneja la eliminación de tareas en la base de datos.

### Métodos
- **borrarTarea(int id_tarea)**: Elimina una tarea de la base de datos por su ID.
- **tareaABorrar()**: Solicita al usuario que seleccione una tarea para borrar.
- **opcionesTareaBorrar()**: Muestra un menú con opciones para introducir el ID de la tarea directamente o ver todas las tareas disponibles antes de borrar.
- **confirmarYBorrar(int idTarea)**: Pide confirmación al usuario antes de eliminar la tarea.
- **leerTodasLasTareas()**: Obtiene una lista de todas las tareas desde la base de datos.

## Clase Opciones

La clase `Opciones` muestra un menú para que el usuario seleccione la acción que desea realizar.

### Métodos
- **ListaOpciones()**: Muestra un menú principal con opciones para crear, ver, editar, borrar tareas o salir del programa.
- **OpcionEscogida()**: Maneja la opción seleccionada por el usuario y devuelve si el menú debe continuar mostrándose.
- **confirmarOpcion(int OpcionElegidaEnNumero)**: Pide confirmación al usuario para la opción seleccionada y ejecuta la acción correspondiente.
- **ejecutarOpcion(int OpcionConfirmada)**: Ejecuta la acción correspondiente según la opción confirmada por el usuario.

## Uso

Para utilizar el programa, simplemente ejecute la aplicación y siga las opciones del menú para gestionar sus tareas. Asegúrese de que la base de datos MySQL esté configurada correctamente y que las credenciales sean válidas.

## Contribuciones

Las contribuciones son bienvenidas. Si desea colaborar en el proyecto, por favor, envíe un pull request o abra un issue en el repositorio.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulte el archivo `LICENSE` para más detalles.
