import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Gestor {

    /**
     * El hashmap que contiene los identificadores y las notas.
     */
    private static HashMap<Integer, Double> hm;

    public static void main(String[] args) {
        try {
            String operacion = args[0];
            String path = args[1];
            int id = Integer.parseInt(args[2]);
            double nota = 0;
            if (args.length == 4) {
                nota = Double.parseDouble(args[3]);
            }
            Lector lector = new Lector(new File(path));
            Escritor escritor = new Escritor(new File(path));
            switch (operacion) {
                case "w" -> {
                    write(args, path, id, nota, lector, escritor);
                }
                case "r" -> {
                    read(args, id, lector);
                }
                case "m" -> {
                    modify(args, id, nota, lector, escritor);
                }
                case "d" -> {
                    delete(args, id, lector, escritor);
                }
                default -> {
                    System.out.println("Error: operación incorrecta");
                    System.exit(1);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: formato de argumentos incorrecto");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error: no se ha podido acceder al fichero");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: numero de argumentos incorrecto");
            System.exit(1);
        }

    }


    /**
     * Escribe una nota de un estudiante concreto indicando su id y su nota.
     * @param args Los argumentos de la línea de comandos.
     * @param path El path del fichero.
     * @param id  El identificador del estudiante.
     * @param nota La nota del estudiante.
     * @param lector El lector de ficheros.
     * @param escritor El escritor de ficheros.
     * @throws IOException Si no se puede acceder al fichero.
     * @throws NumberFormatException Si el id o la nota no son números.
     * @throws ArrayIndexOutOfBoundsException Si no se introducen los argumentos correctos.
     */
    private static void write(String[] args, String path, int id, double nota, Lector lector, Escritor escritor) throws IOException {
        if (args.length != 4) {
            throw new ArrayIndexOutOfBoundsException();
        }
        hm = lector.lee();
        if (hm.containsKey(id)) {
            System.out.println("El id " + id + " ya existe");
        } else {
            escritor.escribe(id, nota);
            System.out.println("Guardado el id " + id + " con nota " + nota + " en " + path);
        }
    }

    /**
     * Lee una nota de un estudiante concreto indicando su id o todas las notas si se introduce -1.
     * @param args Los argumentos de la línea de comandos.
     * @param id   El identificador del estudiante.
     * @param lector El lector de ficheros.
     * @throws IOException Si no se puede acceder al fichero.
     * @throws NumberFormatException Si el id no es un número.
     * @throws ArrayIndexOutOfBoundsException Si no se introducen los argumentos correctos.
     */
    private static void read(String[] args, int id, Lector lector) throws IOException {
        double nota;
        if (args.length != 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        hm = lector.lee();
        if (id == -1) {
            for (Integer i : hm.keySet()) {
                System.out.println("La nota de " + i + " es " + hm.get(i));
            }
        } else {
            nota = lector.lee(id);
            if (!hm.containsKey(id)) {
                System.out.println("El id " + id + " no existe");
            } else {
                System.out.println("La nota de " + id + " es " + nota);
            }
        }
    }

    /**
     * Modifica la nota de un estudiante concreto introduciendo su id y la nueva nota.
     * @param args Los argumentos de la línea de comandos.
     * @param id   El identificador del estudiante.
     * @param nota La nueva nota del estudiante.
     * @param lector El lector de ficheros.
     * @param escritor El escritor de ficheros.
     * @throws IOException Si no se puede acceder al fichero.
     */
    private static void modify(String[] args, int id, double nota, Lector lector, Escritor escritor) throws IOException {
        if (args.length != 4) {
            throw new ArrayIndexOutOfBoundsException();
        }
        hm = lector.lee();
        if (!hm.containsKey(id)) {
            System.out.println("El id " + id + " no existe");
        } else {
            double notaAntigua = lector.lee(id);
            // Actualizar la nota del estudiante
            hm.put(id, nota);
            // Escribir todas las hm en el archivo
            escritor.escribe(hm);
            System.out.println("Modificada la nota de " + id + ", de " + notaAntigua + " a " + nota);
        }
    }

    /**
     * Elimina la nota de un estudiante concreto introduciendo su id.
     * @param args Los argumentos de la línea de comandos.
     * @param id   El identificador del estudiante.
     * @param lector El lector de ficheros.
     * @param escritor El escritor de ficheros.
     * @throws IOException Si no se puede acceder al fichero.
     * @throws NumberFormatException Si el id no es un número.
     * @throws ArrayIndexOutOfBoundsException Si no se introducen los argumentos correctos.
     */
    private static void delete(String[] args, int id, Lector lector, Escritor escritor) throws IOException {
        if (args.length != 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        hm = lector.lee();
        if (!hm.containsKey(id)) {
            System.out.println("El id " + id + " no existe");
        } else {
            double notaAntigua = lector.lee(id);
            // Eliminar la nota del estudiante
            hm.remove(id);
            // Escribir todas las hm en el archivo
            escritor.escribe(hm);
            System.out.println("Eliminado id " + id + " con nota " + notaAntigua);
        }
    }

}
