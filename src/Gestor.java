import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Gestor {
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
            HashMap<Integer, Double> notas;
            switch (operacion) {
                case "w" -> {
                    if (args.length != 4) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    notas = lector.lee();
                    if (notas.containsKey(id)) {
                        System.out.println("El id " + id + " ya existe");
                    } else {
                        escritor.escribe(id, nota);
                        System.out.println("Guardado el id " + id + " con nota " + nota + " en " + path);
                    }
                }
                case "r" -> {
                    if (args.length != 3) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    notas = lector.lee();
                    if (id == -1) {
                        for (Integer i : notas.keySet()) {
                            System.out.println("La nota de " + i + " es " + notas.get(i));
                        }
                    } else {
                        nota = lector.lee(id);
                        if (!notas.containsKey(id)) {
                            System.out.println("El id " + id + " no existe");
                        } else {
                            System.out.println("La nota de " + id + " es " + nota);
                        }
                    }
                }
                case "m" -> {
                    if (args.length != 4) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    notas = lector.lee();
                    if (!notas.containsKey(id)) {
                        System.out.println("El id " + id + " no existe");
                    } else {
                        // Actualizar la nota del estudiante
                        notas.put(id, nota);
                        // Escribir todas las notas en el archivo
                        escritor.escribe(notas);
                        System.out.println("Modificada la nota de " + id + ", de " + lector.lee(id) + " a " + nota);
                    }
                }
                case "d" -> {
                    if (args.length != 3) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    notas = lector.lee();
                    if (!notas.containsKey(id)) {
                        System.out.println("El id " + id + " no existe");
                    } else {
                        // Eliminar la nota del estudiante
                        notas.remove(id);
                        // Escribir todas las notas en el archivo
                        escritor.escribe(notas);
                        System.out.println("Eliminado id " + id + " con nota " + lector.lee(id));
                    }
                }
                default -> {
                    System.out.println("Error: operación incorrecta");
                    System.exit(1);
                }
            }
        } catch (
                NumberFormatException e) {
            System.out.println("Error: formato de argumentos incorrecto");
            System.exit(1);
        } catch (
                IOException e) {
            System.out.println("Error: no se ha podido acceder al fichero");
            System.exit(1);
        } catch (
                ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: número de argumentos incorrecto");
            System.exit(1);
        }

    }
}
