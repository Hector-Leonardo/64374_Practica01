import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorContactosBuffers {

    private static final String NOMBRE_ARCHIVO = "contactos.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            while (!scanner.hasNextInt()) {  // Validar que sea número
                System.out.println("Por favor ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarContacto();
                    break;
                case 2:
                    mostrarContactos();
                    break;
                case 3:
                    buscarContacto();
                    break;
                case 4:
                    modificarContacto();
                    break;
                case 5:
                    eliminarArchivo();
                    break;
                case 6:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== GESTOR DE CONTACTOS ===");
        System.out.println("1. Agregar contacto");
        System.out.println("2. Mostrar todos los contactos");
        System.out.println("3. Buscar contacto por nombre");
        System.out.println("4. Modificar contacto");
        System.out.println("5. Eliminar archivo de contactos");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarContacto() {
        System.out.println("\n--- AGREGAR CONTACTO ---");
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(NOMBRE_ARCHIVO), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(nombre + "," + telefono);
            writer.newLine();
            
            // Imprimir los datos agregados
            System.out.println("\n--- CONTACTO AGREGADO CORRECTAMENTE ---");
            System.out.println("Nombre: " + nombre);
            System.out.println("Teléfono: " + telefono);
            
        } catch (IOException e) {
            System.out.println("Error al agregar contacto: " + e.getMessage());
        }
    }

    private static void mostrarContactos() {
        System.out.println("\n--- LISTA DE CONTACTOS ---");
        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        int contador = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    System.out.println("Nombre: " + partes[0] + ", Teléfono: " + partes[1]);
                    contador++;
                }
            }
            System.out.println("\nTotal de contactos: " + contador);
        } catch (IOException e) {
            System.out.println("Error al leer contactos: " + e.getMessage());
        }
    }

    private static void buscarContacto() {
        System.out.println("\n--- BUSCAR CONTACTO ---");
        System.out.print("Ingrese el nombre o parte del nombre a buscar: ");
        String nombreBuscado = scanner.nextLine();

        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        List<String[]> coincidencias = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].toLowerCase().contains(nombreBuscado.toLowerCase())) {
                    coincidencias.add(partes);
                }
            }
            
            if (coincidencias.isEmpty()) {
                System.out.println("No se encontraron contactos que coincidan con \"" + nombreBuscado + "\".");
            } else {
                System.out.println("\n--- COINCIDENCIAS ENCONTRADAS ---");
                for (int i = 0; i < coincidencias.size(); i++) {
                    System.out.println((i + 1) + ". Nombre: " + coincidencias.get(i)[0] + ", Teléfono: " + coincidencias.get(i)[1]);
                }
                System.out.println("\nTotal de coincidencias: " + coincidencias.size());
            }
            
        } catch (IOException e) {
            System.out.println("Error al buscar contacto: " + e.getMessage());
        }
    }

    private static void modificarContacto() {
        System.out.println("\n--- MODIFICAR CONTACTO ---");
        System.out.print("Ingrese el nombre o parte del nombre del contacto a modificar: ");
        String nombreBuscado = scanner.nextLine();

        File archivo = new File(NOMBRE_ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("No hay contactos registrados.");
            return;
        }

        List<String> todasLasLineas = new ArrayList<>();
        List<String[]> coincidencias = new ArrayList<>();

        // Leer todas las líneas y encontrar coincidencias
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(NOMBRE_ARCHIVO))) {
            String linea;
            int indice = 0;
            while ((linea = reader.readLine()) != null) {
                todasLasLineas.add(linea);
                String[] partes = linea.split(",");
                if (partes.length == 2 && partes[0].toLowerCase().contains(nombreBuscado.toLowerCase())) {
                    coincidencias.add(new String[]{partes[0], partes[1], String.valueOf(indice)});
                }
                indice++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer contactos: " + e.getMessage());
            return;
        }

        if (coincidencias.isEmpty()) {
            System.out.println("No se encontraron contactos que coincidan con \"" + nombreBuscado + "\".");
            return;
        }

        // Mostrar coincidencias
        System.out.println("\n--- CONTACTOS ENCONTRADOS ---");
        for (int i = 0; i < coincidencias.size(); i++) {
            System.out.println((i + 1) + ". Nombre: " + coincidencias.get(i)[0] + ", Teléfono: " + coincidencias.get(i)[1]);
        }

        // Seleccionar contacto a modificar
        int seleccion;
        do {
            System.out.print("\nSeleccione el número del contacto a modificar (1-" + coincidencias.size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor ingrese un número válido.");
                scanner.next();
            }
            seleccion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (seleccion < 1 || seleccion > coincidencias.size()) {
                System.out.println("Número de selección inválido. Intente nuevamente.");
            }
        } while (seleccion < 1 || seleccion > coincidencias.size());

        String[] contactoSeleccionado = coincidencias.get(seleccion - 1);
        int indiceLinea = Integer.parseInt(contactoSeleccionado[2]);

        System.out.println("\nContacto seleccionado: " + contactoSeleccionado[0] + " - " + contactoSeleccionado[1]);

        // Pedir nuevo nombre
        System.out.print("Ingrese el nuevo nombre (presione Enter para mantener \"" + contactoSeleccionado[0] + "\"): ");
        String nuevoNombre = scanner.nextLine();
        if (nuevoNombre.trim().isEmpty()) {
            nuevoNombre = contactoSeleccionado[0];
        }

        // Pedir nuevo teléfono
        System.out.print("Ingrese el nuevo teléfono (presione Enter para mantener \"" + contactoSeleccionado[1] + "\"): ");
        String nuevoTelefono = scanner.nextLine();
        if (nuevoTelefono.trim().isEmpty()) {
            nuevoTelefono = contactoSeleccionado[1];
        }

        // Actualizar la línea correspondiente
        todasLasLineas.set(indiceLinea, nuevoNombre + "," + nuevoTelefono);

        // Reescribir el archivo
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(NOMBRE_ARCHIVO))) {
            for (String linea : todasLasLineas) {
                if (!linea.trim().isEmpty()) {
                    writer.write(linea);
                    writer.newLine();
                }
            }
            
            // Mostrar los datos actualizados
            System.out.println("\n--- CONTACTO MODIFICADO CORRECTAMENTE ---");
            System.out.println("Nombre actualizado: " + nuevoNombre);
            System.out.println("Teléfono actualizado: " + nuevoTelefono);
            
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios: " + e.getMessage());
        }
    }

    private static void eliminarArchivo() {
        System.out.println("\n--- ELIMINAR ARCHIVO ---");
        File archivo = new File(NOMBRE_ARCHIVO);

        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}