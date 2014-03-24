package main;


import userinterface.ConsoleInterface;
import process.DataProcess;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread dataProcess = new Thread(new DataProcess());
        Thread userInterface = new Thread(new ConsoleInterface());
        
        dataProcess.start();
        userInterface.start();
  
    }
}
