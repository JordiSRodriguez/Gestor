import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Gestor {
    public static void main(String[] args) {
//        if (args.length < 3 || args.length > 4) {
//            System.out.println("Error: número de argumentos incorrecto");
//            System.exit(1);
//        }
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
                    escritor.escribe(id, nota);
                    System.out.println("Guardado el id " + id + " con nota " + nota + " en " + path);
                }
                case "r" -> {
                    if (id == -1) {
                        notas = lector.lee();
                        for (Integer i : notas.keySet()) {
                            System.out.println("La nota de " + i + " es " + notas.get(i));
                        }
                    } else {
                        nota = lector.lee(id);
                        if (nota == -1) {
                            System.out.println("El id " + id + " no existe");
                        } else {
                            System.out.println("La nota de " + id + " es " + nota);
                        }
                    }
                }
                case "m" -> {
                    notas = lector.lee();
                    if (!notas.containsKey(id)) {
                        System.out.println("El id " + id + " no existe");
                    } else {
                        // Actualizar la nota del estudiante
                        notas.put(id, nota);
                        // Escribir todas las notas en el archivo
                        escritor.escribe(notas);
                        System.out.println("Modificada la nota del id " + id + " a " + nota);
                    }
                }
                case "d" -> {
                    notas = lector.lee();
                    if (!notas.containsKey(id)) {
                        System.out.println("El id " + id + " no existe");
                    } else {
                        // Eliminar la nota del estudiante
                        notas.remove(id);
                        // Escribir todas las notas en el archivo
                        escritor.escribe(notas);
                        System.out.println("Eliminado el id " + id);
                    }
                }
                default -> {
                    System.err.println("Error: operación incorrecta");
                    System.exit(1);
                }
            }
        } catch (
                NumberFormatException e) {
            System.err.println("Error: formato de argumentos incorrecto");
            System.exit(1);
        } catch (
                IOException e) {
            System.err.println("Error: no se ha podido acceder al fichero");
            System.exit(1);
        }
    }
}
