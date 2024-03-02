import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileReader;

public class Main {
    static String nombreCarpeta = "./data/";
    static String nombreF = "";

    static String nombreFichero = nombreCarpeta+nombreF;
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
                        break;
                    case 2:
                        leerArchivo();
                        break;
                    case 3:
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
        System.out.println("3. XXXXXXXXXXXXXXX");
        System.out.println("4. XXXXXXXXXXXXXXX");
        System.out.println("5. XXXXXXXXXXXXXXX");
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
               return (nombreCarpeta + nombreFichero);
           }
           return (nombreCarpeta + listaDeArchivos[numFile - 1].getName());
       }
         return nombreFichero;
    }
    private static void cambiarNombreDeArchivo() {
        System.out.print("Ingrese el nuevo nombre del archivo: ");
        nombreFichero = leerCarpeta();
    }

    private static void leerArchivo() {
        File archivo = new File(nombreFichero);
        BufferedReader lector = null;

        try {
            lector = new BufferedReader(new FileReader(archivo));
            String linea;

            System.out.println("Contenido del archivo:");
            System.out.println("======================================================================");

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("presione enter para continuar...");
            scannerStr.nextLine();
            System.out.println("======================================================================");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
        } finally {
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }

}