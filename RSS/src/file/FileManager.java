
package file;

import item.Item;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
    
    private static File feeds = new File("./feeds.txt");
    
    public FileManager(){

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
    
    public static ArrayList<String> readFeedList(){
        if (!feeds.exists()) {
            return null;
        }
        ArrayList<String> feedList = ReadFile.readFeeds(feeds);
        return feedList;
    }
    
    
    public static void checkAndCreateFeedFiles(){
        ArrayList<String> feedList = readFeedList();
        ArrayList<File> feedFileList = new ArrayList();

        if (!feedList.isEmpty()) {
            for(String f:feedList){
                File file = new File("./"+f);
                feedFileList.add(file);
            }
            
            for (File f : feedFileList) {
                try {
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                } catch (Exception e) {
                    System.out.println("Error writing Feed file.");
                }
            }
        }
    }
    
    
    

    public static void saveFeedData(ArrayList<Item> itemList) {
        for (Item i : itemList) {
            try {
                
                if (!feeds.exists()) {
                    feeds.createNewFile();
                }
            } catch (Exception e) {
                System.out.println("Error writing Feeds Index file.");
            }
        }

    }

}
