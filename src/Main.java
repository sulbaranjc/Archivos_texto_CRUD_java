import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static String nombreCarpeta = "./data/";
    static String nombreFichero = "data.txt";

    static String nombreFicherCompleto = nombreCarpeta+nombreFichero;
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerNum = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String[] args) throws ClassNotFoundException {
        boolean salir = false;
        int opcion;
        do {
            menuPrincipal();
            opcion = scannerNum.nextInt();
            try {
                switch (opcion) {
                    case 1:
                        nombreFichero = leerCarpeta();
                        nombreFicherCompleto = nombreCarpeta+nombreFichero;
                        break;
                    case 2:
                        leerArchivo();
                        break;
                    case 3:
                        escribirArchivo();
                        break;
                    case 4:
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error de IO: " + e.getMessage());
            } catch (InputMismatchException ime) {
                System.out.println("Entrada no válida, por favor introduce un número.");
                scannerNum.next(); // Limpiar buffer de entrada
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        } while (!salir);

    }

    private static void menuPrincipal() {
        System.out.println();
        System.out.println();
        System.out.println("                          Menu de Opciones");
        System.out.println("======================================================================");
        System.out.println("Nombre del Archivo : " + nombreFichero);
        System.out.println("======================================================================");

        System.out.println("1. Cambiar Nombre del Archivo");
        System.out.println("2. Leer Archivo");
        System.out.println("3. Escribir Archivo");
        System.out.println("4. XXXXXXXXXXXXXXX");
        System.out.println("0. Salir");
        System.out.println();
        System.out.print("Ingrese una opcion: ");
    }
    public static String leerCarpeta() {
        File carpeta = new File(nombreCarpeta);
        File[] listaDeArchivos = carpeta.listFiles();
        int numFile;
        if (listaDeArchivos != null) {
            for (int i = 0; i < listaDeArchivos.length; i++) {
                if (listaDeArchivos[i].isFile()) {
                    System.out.println((i + 1) + ". " + listaDeArchivos[i].getName());
                }
            }
        } else {
            System.out.println("El directorio está vacío o no existe.");
        }
        System.out.println((listaDeArchivos.length + 1) + ". " + "Crear nuevo archivo");
        System.out.println("0" + ". " + "mantener el archivo actual");
        System.out.println("Elige un archivo: ");
        numFile = scannerNum.nextInt();
       if (numFile != 0) {
           if (numFile == listaDeArchivos.length + 1) {
               System.out.print("Ingrese el nombre del archivo: ");
               nombreFichero = scannerStr.nextLine();
               return (nombreFichero);
           }
           return (listaDeArchivos[numFile - 1].getName());
       }
         return nombreFichero;
    }
    private static void cambiarNombreDeArchivo() {
        System.out.print("Ingrese el nuevo nombre del archivo: ");
        nombreFichero = leerCarpeta();
    }

    private static void leerArchivo() throws IOException {
        File archivo = new File(nombreFicherCompleto);

        System.out.println("Contenido del archivo:");
        System.out.println("======================================================================");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("presione enter para continuar...");
            scannerStr.nextLine();
            System.out.println("======================================================================");
        }
    }
    private static void escribirArchivo() throws IOException {
        String texto;
        System.out.print("¿Quieres editar el fichero? (S/N)");
        String respuesta = scannerStr.nextLine();
        boolean editarFichero = !respuesta.toUpperCase().equals("N");

        try (BufferedWriter escritura = new BufferedWriter(new FileWriter(nombreFicherCompleto, editarFichero))) {
            do {
                System.out.println("Introduce texto (FIN para terminar)");
                texto = scannerStr.nextLine();
                if (!texto.toUpperCase().equals("FIN")) {
                    escritura.write(texto);
                    escritura.newLine();
                }
            } while (!texto.toUpperCase().equals("FIN"));
        }
    }
}