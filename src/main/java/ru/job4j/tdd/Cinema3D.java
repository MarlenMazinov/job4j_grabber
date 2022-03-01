package ru.job4j.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private List<Session> sessions = new ArrayList<>();
    private int[][] places = new int[10][20];
    private List<Calendar> freeDates;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public int[][] getPlaces() {
        return places;
    }

    public void setPlaces(int[][] places) {
        this.places = places;
    }

    public List<Calendar> getFreeDates() {
        return freeDates;
    }

    public void setFreeDates(List<Calendar> freeDates) {
        this.freeDates = freeDates;
    }

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return List.of(new Session3D());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        Ticket rsl = null;
        if (row > 10 || column > 20) {
            throw new IllegalArgumentException("Места с таким номером не существует");
        }
        if (places[row][column] != 0) {
            throw new IllegalArgumentException("Данное место продано");
        }

        for (Calendar elem : freeDates) {
            elem.set(Calendar.MILLISECOND, 00);
            date.set(Calendar.MILLISECOND, 00);
            if (elem.equals(date)) {
                places[row][column] = 1;
                rsl = new Ticket3D(account, row, column, date);
            }
        }
        return rsl;
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}