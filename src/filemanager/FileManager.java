package filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager<T> {
    public FileManager() {
    }

    public List<T> readFile(String filePath) {
        FileInputStream fileInputStream = null;
        List<T> list = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = null;
            while ((obj = objectInputStream.readObject()) != null) {
                list.add((T) obj);
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeFile(String filePath, List<T> list) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (T object : list) {
            objectOutputStream.writeObject(object);
        }
        fileOutputStream.close();
        objectOutputStream.close();
    }
}
