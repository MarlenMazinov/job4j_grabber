package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenKeysMatchMap() {
        Generator generator = new SomeGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        assertEquals(generator.produce("My name is ${name}", map), "My name is Petr Arsentev");
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExcessKeys() {
        Generator generator = new SomeGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("age", "36");
        generator.produce("My name is ${name}", map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeysInMap() {
        Generator generator = new SomeGenerator();
        Map<String, String> map = new HashMap<>();
        generator.produce("My name is ${name}", map);
    }
}