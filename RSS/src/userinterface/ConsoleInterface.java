package userinterface;

import file.FileManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface extends Thread {

    @Override
    public void run() {

        Scanner input = new Scanner(System.in);

        String command, urlString;

        do {
            command = "";
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Añadir nuevo feed");
            System.out.println("2. Ver feeds añadidos");
            command = input.next();
            switch (command) {
                case "1":
                    System.out.println("Introduzca URL del RSS:");
                    urlString = input.next();
                    URL url;
                    try {
                        url = new URL(urlString);
                        FileManager.addFeed(url);
                        System.out.println("Feed añadido.");
                        break;
                    } catch (MalformedURLException ex) {
                        System.out.println("URL NO VÁLIDA.");
                    } catch (Exception e) {
                        System.out.println("Error desconocido.");
                    }
                case "2":

                    ArrayList<String> feeds;
                    try {
                        feeds = FileManager.readFeedList();
                        System.out.println("\n****Listado de Feeds:****");
                        if (!feeds.isEmpty()) {
                            for (String n : feeds) {
                                System.out.println(n);
                            }
                        } else {
                            System.out.println("No hay feeds.");
                        }
                        System.out.println("**** ---- FIN ---- ****\n");
                        break;
                    } catch (NullPointerException ex) {
                        System.out.println("No se encuentra el archivo de feeds.\n");
                    }
            }
        } while (true);
    }
}
