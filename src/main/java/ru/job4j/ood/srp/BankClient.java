package ru.job4j.ood.srp;

public interface BankClient {
    Account createAccount(int id, String name);

    void makeTransaction(Account recipient, float amount);
}
