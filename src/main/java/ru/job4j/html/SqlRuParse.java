package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
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
}