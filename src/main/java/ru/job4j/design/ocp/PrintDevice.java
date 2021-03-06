package ru.job4j.design.ocp;
/*
В интерфейсе PrintDevice объявлен метод print() который возвращает объект типа TextDocument.
Но если создать класс реализующий этот интерфейс, который должен печатать фото, то метод print()
из данного интерфейса будет невозможно использовать. Нужно будет создавать новый метод в нашем
классе для печати фото. Для избежания этих сложностей нужно сделать интерфейс параметризованным.

public interface PrintDevice<T> {
    T print();
}
 */
public interface PrintDevice {
    TextDocument print();
}
