package process;

public class DataProcess extends Thread {

    @Override
    public void run() {
        while(true){
            System.out.println("Procesando y descargando feeds...");
            System.out.println("Por favor, no interactúe con el menú.");
            //Implementar la descarga de los artículos de los RSS.
            
            
            System.out.println("Fin del proceso.");
            //Dormimos el hilo durante 30 minutos.
            try {
                this.sleep(1800000);
            } catch (InterruptedException ex) {
            }
        }

    }
}
