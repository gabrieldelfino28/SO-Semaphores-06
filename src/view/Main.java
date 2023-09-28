package view;

import controller.CookingSim;

import java.util.concurrent.Semaphore;

public class Main {

    static Semaphore entrega = new Semaphore(1);

    public static void main(String[] args) {

        for (int prato = 1; prato <= 5; prato++) {
            Thread cook = new CookingSim(entrega, prato);
            cook.start();
        }

    }
}
