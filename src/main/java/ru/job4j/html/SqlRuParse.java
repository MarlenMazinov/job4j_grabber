package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select("tr");
        for (Element tr : row) {
            if (tr.children().hasClass("postslisttopic")) {
                System.out.println(tr.child(1).text());
                System.out.println(tr.child(1).child(0).attr("href"));
                System.out.println(tr.child(5).text());
            }
        }
    }
}