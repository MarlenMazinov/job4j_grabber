package ru.job4j.design.ocp;

import java.io.FileWriter;
import java.io.IOException;

/*
Класс Logger используется для записи логов в файл. Но если понадобится записывать логи
в БД либо передавать их по сети и сохранять на удаленном сервере, либо выводить на печать, то
данный класс будет непригоден. Поэтому нужно создать параметризованный абстрактный класс
либо интерфейс, и в качестве параметра передавать объект, в который нужно записывать логи.
 */
public class Logger {
    public void log(String text, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}