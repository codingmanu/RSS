
package file;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class FileManager {
    
    private static File feeds = new File("./feeds.txt");
    
    public FileManager(){
        createFeedIndex();
    }

    public static void createFeedIndex() {
        try {
            if (!feeds.exists()) {
                feeds.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error writing Feeds Index file.");
        }

    }

    public static void addFeed(URL url){
        WriteToFile.appendFeed(feeds, url.toString());
    }
    
    public static ArrayList<String> readFeeds(){
        if (!feeds.exists()) {
            return null;
        }
        ArrayList<String> feedList = ReadFile.readFeeds(feeds);
        return feedList;
    }
    
    
    
}
