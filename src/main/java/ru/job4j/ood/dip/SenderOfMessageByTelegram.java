package ru.job4j.ood.dip;

/*
Например у нас имеется некая сущность SenderOfMessageByTelegram в некотором  сервисе,
которая отвечает за отправку сообщений через мессенджер Telegram. Но логика этого сервиса
предполагает возможность отправки сообщений с помощью других мессенджеров. Тогда правильно будет
создать абстрактный класс SenderOfMessageByMessenger, в нем создать поле Messenger и объявить
абстрактный класс send(). Затем унаслдоваться от этого класса с помощью
того же класса SenderOfMessageByTelegram, и тогда реализация этого класса примет вид:

public class SenderOfMessageByTelegram extends SenderOfMessageByMessenger {
    private Messenger messenger;

    public SenderOfMessageByTelegram(Messenger messenger) {
        this.messenger = messenger;
    }

    public boolean send() {
        return messenger.send();
    }
}
 */
public class SenderOfMessageByTelegram {
    private Telegram telegram;

    public SenderOfMessageByTelegram(Telegram telegram) {
        this.telegram = telegram;
    }

    public boolean send() {
        return telegram.send();
    }
}
