package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.DateTimeParser;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.SqlRuDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        getInformation(doc);
        Elements pages = doc.select("td:contains(Страницы)").get(0).children();
        for (int i = 1; i < 5; i++) {
            doc = Jsoup.connect(pages.get(i).attr("href")).get();
            getInformation(doc);
        }
    }

    private static void getInformation(Document document) {
        Elements row = document.select("tr");
        for (Element tr : row) {
            if (tr.children().hasClass("postslisttopic")) {
                System.out.println(tr.child(1).text());
                System.out.println(tr.child(1).child(0).attr("href"));
                System.out.println(tr.child(5).text());
            }
        }
    }

    @Override
    public Post detail(String link) {
        Post post = new Post();
        try {
            Document doc = Jsoup.connect(link).get();
            post.setId(Integer.parseInt(doc.select("td.msgFooter").get(0).child(0).text()));
            post.setTitle(doc.select("td.messageHeader").get(0).text());
            post.setLink(link);
            Elements msgBody = doc.select("td.msgBody").get(1).children();
            StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
            stringJoiner.add(doc.select("td.msgBody").get(1).text());
            for (Element el : msgBody) {
                if (el.hasText()) {
                    stringJoiner.add(el.text());
                }
            }
            post.setDescription(stringJoiner.toString());
            String[] arr = doc.select("td.msgFooter").get(0).text().split("\u00A0");
            SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
            post.setCreated(parser.parse(arr[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements pages = doc.select("td:contains(Страницы)").get(0).children();
            for (int i = 1; i < 5; i++) {
                doc = Jsoup.connect(pages.get(i).attr("href")).get();
                Elements row = doc.select("tr");
                for (Element tr : row) {
                    if (tr.children().hasClass("postslisttopic")) {
                        String title = detail(tr.child(1).child(0).attr("href")).getTitle().
                                toLowerCase();
                        if (!title.contains("javascript")
                                && title.contains("java")) {
                            rsl.add(detail(tr.child(1).child(0).attr("href")));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}