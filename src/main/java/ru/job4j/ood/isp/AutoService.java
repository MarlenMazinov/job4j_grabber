package ru.job4j.ood.isp;

/*
Приведенный ниже интерфейс нарушает принцип ISP, поскольку в нем определены методы, предполагающие
выполнение работ связанных с двигателями, устранение проблем с электрикой автомобиля,
восстановление кузова автомобиля соответственно. Многие автосервисы являются узкоспециализированными
и не занимаются выполнением всех вышеперечисленных видов работ - например класс SnallAutoService.
Поэтому имеет смысл разбить этот интерфейс на 3 отдельных интерфейса и в каждом определить
соответствующий метод.
 */
public interface AutoService {
    void repairEngine();

    void fixElectricianProblems();

    void renovateCarBody();
}
