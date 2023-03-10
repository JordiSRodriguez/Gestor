import java.io.*;
import java.util.HashMap;

public class Escritor {
    /**
     * El archivo que se va a leer.
     */
    private File file;

    /**
     * Constructor de la clase Escritor.
     * @param file el archivo que se va a leer.
     */
    public Escritor(File file) {
        this.file = file;
    }

    /**
     * Devuelve el archivo.
     * @return el archivo.
     */
    public File getFile() {
        return file;
    }

    /**
     * Establece el archivo.
     * @param file el archivo.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Establece el path del archivo.
     * @param path el path del archivo.
     */
    public void setPath(String path) {
        file = new File(path);
    }

    /**
     * Devuelve el path del archivo.
     * @return el path del archivo.
     */
    public String getPath() {
        return file.getPath();
    }

    /**
     * Escribe en el archivo una línea con el identificador y la nota especificados.
     * Si el archivo no existe, se crea un nuevo archivo vacío.
     * Si no se puede escribir en el archivo, se lanza una excepción FileNotFoundException.
     * @param id el identificador que se escribe en el archivo.
     * @param nota la nota que se escribe en el archivo.
     * @throws IOException si ocurre un error al escribir en el archivo.
     */
    public void escribe(int id, double nota) throws IOException {
        file = new File(getPath());
        if (!file.canWrite()) {
            throw new FileNotFoundException("No se ha podido acceder al archivo");
        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(id + " " + nota);
            bw.newLine();
            bw.close();
        }
    }

    /**
     * Escribe en el archivo una línea con el identificador y la nota especificados.
     * Si el archivo no existe, se crea un nuevo archivo vacío.
     * Si no se puede escribir en el archivo, se lanza una excepción FileNotFoundException.
     * @param hm el HashMap que se escribe en el archivo.
     * @throws IOException si ocurre un error al escribir en el archivo.
     */
    public void escribe(HashMap<Integer, Double> hm) throws IOException {
        file = new File(getPath());
        if (!file.canWrite()) {
            throw new FileNotFoundException("No se ha podido acceder al archivo");
        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Integer key : hm.keySet()) {
                bw.write(key + " " + hm.get(key));
                bw.newLine();
            }
            bw.close();
        }
    }
}