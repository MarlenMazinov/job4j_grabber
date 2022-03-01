package ru.job4j.tdd;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(ticket, new Ticket3D(account, 1, 1, date));
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