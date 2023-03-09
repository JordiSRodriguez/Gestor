import java.io.*;
import java.util.HashMap;

public class Escritor {
    private File file;

    public Escritor(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void escribe(int id, double nota) throws IOException {
        file = new File("notas.txt");
        if (!file.canWrite()) {
            throw new FileNotFoundException("No se tienen permisos de escritura en el fichero");
        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(id + " " + nota);
            bw.newLine();
            bw.close();
        }
    }
    public void escribe(HashMap<Integer, Double> hm) throws IOException {
        file = new File("notas.txt");
        if (!file.canWrite()) {
            throw new FileNotFoundException("No se tienen permisos de escritura en el fichero");
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