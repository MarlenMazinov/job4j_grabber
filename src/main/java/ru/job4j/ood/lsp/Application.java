package ru.job4j.ood.lsp;

import org.junit.Ignore;

public class Application {

    private int memory;

    public Application(int memory) {
        this.memory = memory;
    }

    public int getMemory() {
        return memory;
    }

    public void run() {
        if (memory < 16384) {
            throw new IllegalArgumentException("Memory failure!");
        }
        /*
        some code
         */
    }
}
