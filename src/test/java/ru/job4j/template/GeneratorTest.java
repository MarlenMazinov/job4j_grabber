package ru.job4j.template;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeysMatchMap() {
        Generator generator = new SomeGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        generator.produce("My name is ${name}", map);
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