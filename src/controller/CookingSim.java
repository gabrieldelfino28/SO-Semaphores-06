package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CookingSim extends Thread {
    private final Random r = new Random();
    private Semaphore semaphore;
    private int id;

    public CookingSim(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        String name;
        double tempoTotal;
        try {
            if (id % 2 != 0) {
                tempoTotal = r.nextInt(500, 800);
                name = "Sopa de Cebola";
            } else {
                tempoTotal = r.nextInt(600, 1200);
                name = "Lasanha a Bolonhesa";
            }
            Pratos(name, tempoTotal);
            semaphore.acquire();
            Entrega();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    private void Pratos(String name, double tempoTotal) throws InterruptedException {
        double tempo = 0;
        long porcentagem;
        System.out.println("O prato #" + id + " " + name + " iniciou!");
        do {
            sleep(100); // a cada 0.1 seg
            tempo += 100;
            porcentagem = (long) ((tempo / tempoTotal) * 100);
            if (porcentagem >= 100) {
                System.out.println("O prato #" + id + " " + name + " esta em 100%");
                tempo = tempoTotal;
            } else{
                System.out.println("O prato #" + id + " " + name + " esta em " + porcentagem + "%");
            }
        } while (tempo < tempoTotal);
        sleep(50);
        System.err.println("O prato #" + id + " " + name + " terminou!");
    }

    private void Entrega() throws InterruptedException {
        System.out.println("Realizando entrega ...");
        sleep(500);
        System.err.println("Entrega finalizada!");
    }
}
