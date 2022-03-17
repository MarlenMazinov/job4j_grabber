package ru.job4j.ood.dip;

import java.io.FileWriter;

/*
Метод save() нарушает принцип DIP, т.к. на вход принимает аргумент path - путь к файлу,
в который сохраняет информацию принимаемую в виде строки String.
Правильно будет создать интерфейс и от него реализовать класс SimpleSaver. В интерфейсе
метод save() создать с параметрами, чтобы можно было сохранять не только в файл, а например
в БД, а принимаемая информация, которую сохраняет метод, может быть не только в виде объекта
String, а например объектом какого либо класса.
 */
public class SimpleSaver {
    public void save(String path, String information) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(information);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
