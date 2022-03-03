package ru.job4j.ood.srp;

public interface GetAndPrintText<S, T> {
    S getText(T source);

    void printText(S data);
}
