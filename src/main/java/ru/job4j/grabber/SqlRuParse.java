package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            post.setTitle(doc.select("td.messageHeader").get(0).text());
            post.setLink(link);
            post.setDescription(doc.select("td.msgBody").get(1).text());
            String[] arr = doc.select("td.msgFooter").get(0).text().
                    replaceAll("\u00A0", "").split("\\[");
            post.setCreated(dateTimeParser.parse(arr[0]));
        } catch (Exception e) {
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
                        String postLink = tr.child(1).child(0).attr("href");
                        String title = Jsoup.connect(postLink).get().
                                select("td.messageHeader").get(0).text().toLowerCase();
                        if (!title.contains("javascript")
                                && title.contains("java")) {
                            rsl.add(detail(postLink));
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