import java.io.*;
import java.util.HashMap;

public class Lector {
    /**
     * El archivo que se va a leer.
     */
    public File file;

    /**
     * Constructor de la clase Lector.
     * @param file el archivo que se va a leer.
     */
    public Lector(File file) {
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
     * Lee un archivo y busca la línea que contiene el identificador especificado.
     * Devuelve el valor en formato double que se encuentra en la misma línea.
     * @param id el identificador que se busca en el archivo.
     * @return el valor en formato double que se encuentra en la línea correspondiente al identificador especificado.
     * Si el archivo no existe, se crea un nuevo archivo vacío.
     * Si no se puede leer el archivo, se lanza una excepción FileNotFoundException.
     * Si el identificador no se encuentra en el archivo, se devuelve -1.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public double lee(int id) throws IOException {
        file = new File(getPath());
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canRead()) {
            throw new FileNotFoundException("No se ha podido acceder al archivo");
        } else {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (Integer.parseInt(parts[0]) == id) {
                    return Double.parseDouble(parts[1]);
                }
            }
            br.close();
            return -1;
        }
    }

    /**
     * Lee un archivo y devuelve un HashMap con los valores que contiene.
     * @return un HashMap con los valores que contiene el archivo.
     * Si el archivo no existe, se crea un nuevo archivo vacío.
     * Si no se puede leer el archivo, se lanza una excepción FileNotFoundException.
     * Si el archivo está vacío, se devuelve un HashMap vacío.
     * @throws IOException si ocurre un error al leer el archivo.
     */
    public HashMap<Integer, Double> lee() throws IOException {
        file = new File(getPath());
        if (!file.exists()) {
            file.createNewFile();
        }
        if (!file.canRead()) {
            throw new FileNotFoundException("No se ha podido acceder al archivo");
        } else {
            HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                hm.put(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
            }
            br.close();
            return hm;
        }
    }
}
