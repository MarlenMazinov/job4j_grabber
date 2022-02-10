package ru.job4j.grabber;

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
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("d MMMM yy HH:mm");
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";

    @Override
    public LocalDateTime parse(String parse) {
        Map<String, LocalDate> dayMap =
                Map.of(TODAY, LocalDate.now(),
                        YESTERDAY, LocalDate.now().minusDays(1));
        parse = parse.replaceAll(",", "");
        String[] arr = parse.split(" ");
        String changedParse;
        LocalDateTime rsl;
        boolean monthsContainsDate = MONTHS.containsKey(arr[1]);
        boolean dayMapContainsDate = dayMap.containsKey(arr[0]);
        if (!monthsContainsDate && !dayMapContainsDate) {
            throw new IllegalArgumentException("There not found correct date.");
        }
        if (monthsContainsDate) {
            changedParse = parse.replace(arr[1], MONTHS.get(arr[1]));
            rsl = LocalDateTime.parse(changedParse, FORMATTER);
        } else {
            String[] timeArr = arr[1].split(":");
            rsl = dayMap.get(arr[0]).atTime(Integer.parseInt(timeArr[0]),
                    Integer.parseInt(timeArr[1]));
        }
        return rsl;
    }
}