package ru.job4j.grabber.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "января"),
            Map.entry("фев", "февраля"),
            Map.entry("мар", "марта"),
            Map.entry("апр", "апреля"),
            Map.entry("май", "мая"),
            Map.entry("июн", "июня"),
            Map.entry("июл", "июля"),
            Map.entry("авг", "августа"),
            Map.entry("сен", "сентября"),
            Map.entry("окт", "октября"),
            Map.entry("ноя", "ноября"),
            Map.entry("дек", "декабря"));
    private static final Map<String, LocalDate> TODAYORYESTERDAY =
            Map.of("сегодня", LocalDate.now(),
                    "вчера", LocalDate.now().minusDays(1));

    @Override
    public LocalDateTime parse(String parse) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d MMMM yy HH:mm");
        parse = parse.replaceAll(",", "");
        String[] arr = parse.split(" ");
        String changedParse;
        LocalDateTime rsl;
        if (MONTHS.containsKey(arr[1])) {
            changedParse = parse.replace(arr[1], MONTHS.get(arr[1]));
            rsl = LocalDateTime.parse(changedParse, formatter);
        } else {
            if (TODAYORYESTERDAY.containsKey(arr[0])) {
                String[] timeArr = arr[1].split(":");
                rsl = TODAYORYESTERDAY.get(arr[0]).atTime(Integer.parseInt(timeArr[0]),
                        Integer.parseInt(timeArr[1]));
            } else {
                throw new IllegalArgumentException("There not found correct date.");
            }
        }
        return rsl;
    }
}