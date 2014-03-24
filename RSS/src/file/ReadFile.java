package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private static ArrayList<String> data;

    public static ArrayList<String> readFeeds(File file) {
        try {
            
            data = new ArrayList();

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            
            while((line=br.readLine())!=null) {
               data.add(line);//agregamos las lineas de texto en la lista de lineas
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
