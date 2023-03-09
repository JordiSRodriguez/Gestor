import java.io.*;
import java.util.HashMap;

public class Lector {
    public File file;

    public Lector(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public double lee(int id) throws IOException {
        file = new File("notas.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (!file.canRead()) {
            throw new FileNotFoundException();
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
    public HashMap<Integer, Double> lee() throws IOException {
        file = new File("notas.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        if (!file.canRead()) {
            throw new FileNotFoundException();
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
