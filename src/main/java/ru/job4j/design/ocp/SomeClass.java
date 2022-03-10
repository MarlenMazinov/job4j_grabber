package ru.job4j.design.ocp;
/*
Некоторый класс SomeClass, экземпляры которого выполняют сохранение информации.
Приведенный ниже метод save(String information) принимает в качестве параметра объект
типа String. Если понадобится сохранить объект содержимое БД или изображение,
то данный метод придется изменить. Поэтому праильно будет сделать реализацию этого метода
с исспользованием параметра:

public void save(Т information) {
        some code that save information
}
*/

public class SomeClass {
    public void save(String information) {
        /*
        some code that save information
         */
    }
}
