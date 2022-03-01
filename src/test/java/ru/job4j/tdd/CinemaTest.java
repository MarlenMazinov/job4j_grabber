package ru.job4j.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuySuccessful() {
        Account account = new AccountCinema();
        Cinema3D cinema = new Cinema3D();
        List<Calendar> dates = new ArrayList<>();
        Calendar freeDate = Calendar.getInstance();
        freeDate.set(2020, 10, 10, 23, 00, 00);
        dates.add(freeDate);
        cinema.setFreeDates(dates);
        Calendar boughtDate = Calendar.getInstance();
        boughtDate.set(2020, 10, 10, 23, 00, 00);
        Ticket ticket = cinema.buy(account, 1, 1, boughtDate);
        assertEquals(ticket, new Ticket3D(account, 1, 1, boughtDate));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyWrongDate() {
        Account account = new AccountCinema();
        Cinema3D cinema = new Cinema3D();
        List<Calendar> dates = new ArrayList<>();
        Calendar freeDate = Calendar.getInstance();
        freeDate.set(2020, 10, 10, 23, 00, 00);
        dates.add(freeDate);
        cinema.setFreeDates(dates);
        Calendar boughtDate = Calendar.getInstance();
        boughtDate.set(2020, 10, 10, 21, 00, 00);
        Ticket ticket = cinema.buy(account, 12, 1, boughtDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyWrongRow() {
        Account account = new AccountCinema();
        Cinema3D cinema = new Cinema3D();
        List<Calendar> dates = new ArrayList<>();
        Calendar freeDate = Calendar.getInstance();
        freeDate.set(2020, 10, 10, 23, 00, 00);
        dates.add(freeDate);
        cinema.setFreeDates(dates);
        Calendar boughtDate = Calendar.getInstance();
        boughtDate.set(2020, 10, 10, 23, 00, 00);
        Ticket ticket = cinema.buy(account, 12, 1, boughtDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyWrongColumn() {
        Account account = new AccountCinema();
        Cinema3D cinema = new Cinema3D();
        List<Calendar> dates = new ArrayList<>();
        Calendar freeDate = Calendar.getInstance();
        freeDate.set(2020, 10, 10, 23, 00, 00);
        dates.add(freeDate);
        cinema.setFreeDates(dates);
        Calendar boughtDate = Calendar.getInstance();
        boughtDate.set(2020, 10, 10, 23, 00, 00);
        Ticket ticket = cinema.buy(account, 10, 25, boughtDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyBusyPlace() {
        Account account = new AccountCinema();
        Cinema3D cinema = new Cinema3D();
        List<Calendar> dates = new ArrayList<>();
        Calendar freeDate = Calendar.getInstance();
        freeDate.set(2020, 10, 10, 23, 00, 00);
        dates.add(freeDate);
        cinema.setFreeDates(dates);
        int[][] arr = new int[10][20];
        arr[5][10] = 1;
        cinema.setPlaces(arr);
        Calendar boughtDate = Calendar.getInstance();
        boughtDate.set(2020, 10, 10, 23, 00, 00);
        Ticket ticket = cinema.buy(account, 5, 10, boughtDate);
    }

    @Test
    public void whenFind() {
        Cinema3D cinema = new Cinema3D();
        cinema.setSessions(List.of(new Session3D()));
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(sessions, Arrays.asList(new Session3D()));
    }

    @Test
    public void whenAdd() {
        Cinema3D cinema = new Cinema3D();
        cinema.add(new Session3D());
        assertEquals(cinema.getSessions().get(0), new Session3D());
    }
}