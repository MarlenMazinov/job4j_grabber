package ru.job4j.ood.lsp;
/*
Класс SimpleApp нарушает правило "Предусловия не могут быть усилены в подклассе", т.к в методе
run() происходит сужение диаппазона допустимых значений входного параметра memory.
Это накладывает дополнительные ограничения на работу метода run().
 */
public class SimpleApp extends Application {
    private int memory;

    public SimpleApp(int memory) {
        super(memory);
    }

    @Override
    public void run() {
        if (memory < 8192) {
            throw new IllegalArgumentException("Memory failure!");
        }
        /*
        some code
         */
    }
}
