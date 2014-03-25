
package file;

import item.Item;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class FileManager {
    
    private static File feeds = new File("./feeds.txt");
    private static ArrayList<File> feedFileList = new ArrayList();
    
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

    public static void addFeed(URL url) {
        WriteToFile.appendFeed(feeds, url.toString());
    }

    public static ArrayList<String> readFeedList() {
        if (!feeds.exists()) {
            return null;
        }
        ArrayList<String> feedList = ReadFile.readFeeds(feeds);
        return feedList;
    }

    public static void checkAndCreateFeedFiles() {
        ArrayList<String> feedList = readFeedList();

        if (!feedList.isEmpty()) {
            for (String f : feedList) {
                File file = new File("./" + f.substring(f.indexOf(".") + 1, f.lastIndexOf("/")) + ".txt");
                feedFileList.add(file);

            }

            for (File f : feedFileList) {
                try {
                    if (!f.exists()) {
                        f.createNewFile();
                        System.out.println("File created: " + f.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error writing Feed file.");
                }
            }
        }
    }

    public static void saveFeedData(ArrayList<Item> itemList) {
        for (Item i : itemList) {
            try {
                int posicion = -1;

                for (int j = 0; j < feedFileList.size(); j++) {
                    if (feedFileList.get(j).getName().equals("" + i.URL.substring(i.URL.indexOf(".") + 1, i.URL.lastIndexOf("/")) + ".txt")) {
                        posicion = j;
                    }
                }

                String concat = i.URL + "  ---  " + i.title;
                WriteToFile.appendItem(feedFileList.get(0), concat);

            } catch (Exception e) {
                System.out.println("Error writing Feed data.");
            }
        }

    }
}
