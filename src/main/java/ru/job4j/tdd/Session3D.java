package ru.job4j.tdd;

import java.util.Objects;

public class Session3D implements Session {
    private int startTime;
    private int finishtTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session3D session3D = (Session3D) o;
        return startTime == session3D.startTime && finishtTime == session3D.finishtTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, finishtTime);
    }
}
