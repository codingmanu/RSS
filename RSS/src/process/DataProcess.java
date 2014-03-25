package process;

import database.DatabaseConnector;
import database.DatabaseOperations;
import file.FileManager;
import item.Item;
import network.*;
import java.util.ArrayList;

public class DataProcess extends Thread {

    @Override
    public void run() {
        while(true){
            try {
                this.sleep(5000);
            } catch (InterruptedException ex) {
            }
            System.out.println("Procesando y descargando feeds...");
            System.out.println("Por favor, no interactúe con el menú.");

            //DatabaseOperations.checkTables();
            
            FileManager.checkAndCreateFeedFiles();
            
            ArrayList<String> feedList = FileManager.readFeedList();
            ArrayList<String> data;
            ArrayList<Item> items;
            
            for(String line:feedList){
                data = HTTPFetcher.HTTPFetcher(line);
                items = ArticleExtractor.extractNewItems(data);
                FileManager.saveFeedData(items);
            }

            System.out.println("Fin del proceso.");
            //Dormimos el hilo durante 30 minutos.
            try {
                //this.sleep(1800000);
                this.sleep(10000);
            } catch (InterruptedException ex) {
            }
        }

    }
}
